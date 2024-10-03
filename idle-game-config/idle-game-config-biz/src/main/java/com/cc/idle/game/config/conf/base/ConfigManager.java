package com.cc.idle.game.config.conf.base;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cc.idle.framework.common.conf.base.annotation.GameConfig;
import com.cc.idle.framework.common.exception.util.ServiceExceptionUtil;
import com.cc.idle.game.config.conf.base.column.base.ColumnsTableCore;
import com.cc.idle.game.config.vo.ConfigRawDataVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.cc.idle.game.config.ErrorCodeConstants.*;

/**
 * 配置文件管理器
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ConfigManager {

    /**
     * json 配置文件的根路径
     */
    @Value("${gameplat.game.config.json-path}")
    private String basePath;
    /**
     * 所有的配置文件
     */
    private final List<_AConfig> configList;

    private final ColumnsTableCore columnsTableCore;


    public _AConfig lookupByConfigName(String configName) {

        return configList.stream()
                .filter(config -> StrUtil.equals(configName, config.getConfigName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 加载一项配置
     *
     * @param configName 配置文件名 / 配置名
     */
    public void loadOne(String configName) {
        File file = new File(basePath + FileUtil.FILE_SEPARATOR + configName);
        if (!file.exists()) {
            throw ServiceExceptionUtil.exception(FILE_NOT_EXIST);
        }
        _AConfig aConfig = configList.stream().filter(config -> {
                    String name = Optional.ofNullable(config.getClass().getAnnotation(GameConfig.class))
                            .map(GameConfig::fileName)
                            .orElse(null);
                    return StrUtil.equals(name, configName);
                }
        ).findFirst().orElse(null);

        if (aConfig == null) {
            throw ServiceExceptionUtil.exception(CONFIG_CLASS_NOT_FOUND);
        }

        ConfigLoadPlanDTO plan = ConfigLoadPlanDTO.builder()
                .configName(configName)
                .jsonFile(file)
                .config(aConfig)
                .build();

        loadConfig(plan);
    }

    /**
     * 加载所有配置
     */
    public void loadAllConfig() {
        //获取配置文件根目录
        File rootDictionary = new File(basePath);
        //列出所有配置文件
        Collection<File> files = FileUtils.listFiles(rootDictionary, new SuffixFileFilter(".json"), EmptyFileFilter.EMPTY);
        //所有需要处理的配置计划
        Map<String, ConfigLoadPlanDTO> map = new HashMap<>();
        for (File file : files) {
            ConfigLoadPlanDTO plan = ConfigLoadPlanDTO.builder()
                    .configName(file.getName())
                    .jsonFile(file)
                    .build();
            map.putIfAbsent(file.getName(), plan);
        }
        //匹配文件和config类 形成配置计划
        for (_AConfig config : configList) {
            if (config == null) {
                continue;
            }
            //配置的类信息
            Class<? extends _AConfig> configClass = config.getClass();
            //获取注解
            GameConfig annotation = configClass.getAnnotation(GameConfig.class);
            if (annotation == null) {
                throw ServiceExceptionUtil.exception(CONFIG_CLASS_NOT_COMMENT);
            }
            //注解配置的文件名
            String fileName = annotation.fileName();
            ConfigLoadPlanDTO plan = map.getOrDefault(fileName, ConfigLoadPlanDTO.builder().configName(fileName).config(config).build());
            plan.setConfig(config);
            map.put(fileName, plan);
        }
        for (ConfigLoadPlanDTO plan : map.values()) {
            try {
                loadConfig(plan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 完成一个加载计划
     *
     * @param plan 加载计划
     */
    public void loadConfig(ConfigLoadPlanDTO plan) {
        boolean validate = plan.validate();
        if (!validate) {
            return;
        }

        //从 file 中读取json数据
        JSONObject jsonObject = JSONUtil.readJSONObject(plan.getJsonFile(), StandardCharsets.UTF_8);
        _AConfigLoader<?> configLoader = plan.getConfig().getConfigLoader();
        if (configLoader == null) {
            throw ServiceExceptionUtil.exception(CONFIG_LOADER_NOT_FOUND);
        }
        //由加载其加载
        configLoader.load(jsonObject);
    }

    public List<String> listConfigName() {
        return configList.stream().map(_AConfig::getConfigName).map(FileNameUtil::mainName).toList();
    }

    public void run() {
        //springboot启动后加载一次
        try {
            checkConfigClass();
            loadAllConfig();
            loadDone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkConfigClass() {
        HashSet<String> configNameSet = new HashSet<>();
        for (_AConfig aConfig : configList) {
            if (!configNameSet.add(aConfig.getConfigName())) {
                log.error("配置类指向的配置文件重复 {}", aConfig.getClass().getName());
                throw ServiceExceptionUtil.exception(CONFIG_CLASS_DUPLICATE);
            }
        }
    }

    /**
     * 配置表头信息
     *
     * @param configName 配置名词
     * @return
     */
    public List<ConfigHeaderDTO> configHeader(String configName) {
        _AConfig aConfig = lookupByConfigName(configName);
        if (aConfig == null) {
            return null;
        }

        return aConfig.getHeaderDTOs();
    }

    public List<ConfigRawDataVo> configRawData(String configName) {
        _AConfig aConfig = lookupByConfigName(configName);
        if (aConfig == null) {
            return null;
        }
        return aConfig.getRawData();
    }

    /**
     * 加载完成处理
     */
    public void loadDone() {
        columnsTableCore.autoReadTable();
    }
}
