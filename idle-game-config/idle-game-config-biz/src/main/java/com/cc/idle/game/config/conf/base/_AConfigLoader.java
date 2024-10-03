package com.cc.idle.game.config.conf.base;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import com.cc.idle.framework.common.conf.base.annotation.GameConfig;
import com.cc.idle.framework.common.util.object.CastUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@Getter
public abstract class _AConfigLoader<CONFIG extends _AConfig> {
    /**
     * 存储所有配置的map
     */
    protected final Map<Long, CONFIG> configMap = new TreeMap<>();
    /**
     * 方便遍历和排序的list
     */
    protected final ArrayList<CONFIG> configList = new ArrayList<>();
    /**
     * 表头部信息
     */
    protected final Map<Integer, ConfigHeader> configHeaderMap = new HashMap<>();

    /**
     * 清空容器数据
     */
    public void clear() {
        configHeaderMap.clear();
        configMap.clear();
        configList.clear();
    }

    /**
     * 加载数据
     *
     * @param jsonObject
     */
    public void load(JSONObject jsonObject) {
        clear();

        CONFIG config = createNewConfig();
        //读取并创建表头信息
        List<ConfigHeaderDTO> headers = jsonObject.getBeanList("header", ConfigHeaderDTO.class);
        loadHeaders(headers, config.getClass());
        boolean headerOk = true;
        for (ConfigHeader header : configHeaderMap.values()) {
            if (!header.validate()) {
                log.error("表头信息有误，表名为{}", config.getConfigName());
                headerOk = false;
                break;
            }
        }
        if (!headerOk) {
            throw new RuntimeException("表头信息有误");
        }
        //读取数据列表
        JSONArray dataList = jsonObject.getJSONArray("data");
        for (Object data : dataList) {
            JSONObject dataObject = (JSONObject) data;
            loadOneData(dataObject);
        }
        configList.addAll(configMap.values());
        afterLoadDone();
        log.info("加载配置{}完成，共加载{}条数据", config.getConfigName(), dataList.size());
    }

    /**
     * 读取一行数据
     *
     * @param dataObject json 数据行
     */
    private void loadOneData(JSONObject dataObject) {
        CONFIG config = createNewConfig();
        for (ConfigHeader header : configHeaderMap.values()) {
            String valueStr = dataObject.getStr(header.getId().toString());
            Field field = header.getField();
            Object fieldVal = CastUtil.convertObj(field, valueStr);
            ReflectUtil.setFieldValue(config, field, fieldVal);
        }
        configMap.put(config.getId(), config);
    }

    /**
     * 加载表头信息
     *
     * @param headers     表头数据
     * @param configClass config 类元信息
     */
    private void loadHeaders(List<ConfigHeaderDTO> headers, Class<? extends _AConfig> configClass) {
        headers.forEach(header -> {
            ConfigHeader configHeader = new ConfigHeader();
            configHeader.setId(header.getId());
            configHeader.setHeaderDTO(header);
            configHeaderMap.put(header.getId(), configHeader);
        });
        //补充field信息
        Field[] fields = configClass.getFields();
        for (Field field : fields) {
            GameConfig annotation = field.getAnnotation(GameConfig.class);
            //要求忽略的字段
            if (annotation != null && annotation.ignoreField()) {
                continue;
            }
            for (ConfigHeader header : configHeaderMap.values()) {
                if (StrUtil.equals(header.getHeaderDTO().getFieldName(), field.getName())) {
                    header.setField(field);
                    break;
                }
            }
        }
    }

    protected abstract CONFIG createNewConfig();

    protected abstract void afterLoadDone();

    public CONFIG getById(Long id) {
        return configMap.get(id);
    }

    public List<CONFIG> list() {
        return new ArrayList<>(configList);
    }
}
