package com.cc.idle.game.user.game;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cc.idle.framework.common.conf.common.ConfigItem;
import com.cc.idle.framework.common.enums.biz.*;
import com.cc.idle.framework.common.exception.util.ServiceExceptionUtil;
import com.cc.idle.framework.common.id.IdManager;
import com.cc.idle.framework.common.util.date.GlobalTimeUtil;
import com.cc.idle.framework.common.util.json.JsonUtils;
import com.cc.idle.framework.common.util.object.CastUtil;
import com.cc.idle.game.config.api.conf.Config_GameConstApi;
import com.cc.idle.game.config.api.conf.dto.Config_GameConstDTO;
import com.cc.idle.game.user.enums.EGameCommand;
import com.cc.idle.game.user.enums.EOnlineState;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.game.comp._APlayerComponent;
import com.cc.idle.game.user.game.comp._IPlayerItemDealer;
import com.cc.idle.game.user.game.comp.param.ParamComponent;
import com.cc.idle.game.user.game.notify.CommonNotifyData;
import com.cc.idle.game.user.game.notify._IGameNotify;
import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameParamDo;
import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameRoleDo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.baomidou.mybatisplus.extension.toolkit.Db.saveOrUpdate;
import static com.cc.idle.game.user.enums.ErrorCodeConstants.*;
import static com.cc.idle.game.user.game.GameConstant.PLAYER_DOMAIN_MAX_ONLINE_TIME;


/**
 * 玩家处理对象
 */
@Component
@Scope("prototype")
@Slf4j
@RequiredArgsConstructor
public class PlayerDomain {
    /**
     * 异步执行器
     */
    private final ThreadPoolTaskExecutor asyncServiceExecutor;
    /**
     * spring上下文
     */
    private final ApplicationContext applicationContext;
    /**
     * 通用配置
     */
    private final Config_GameConstApi constApi;
    /**
     * 客户端通知消息
     */
    private final RedisTemplate<String, Object> clientNotifyRedisTemplate;
    /**
     * 玩家身份数据
     */
    @Getter
    private PlayerGameRoleDo userRoleDO;
    /**
     * 玩家组件
     */
    private List<_APlayerComponent> componentList;
    /**
     * 物品处理器
     */
    private List<_IPlayerItemDealer> itemDealerList;
    /**
     * 玩家在线情况
     */
    @Getter
    private EOnlineState onlineState;

    /**
     * 最后一次响应时间戳，如果太长时间没有响应，则进入断线处理
     */
    @Getter
    @Setter
    private long lastRespTime;


    public Long getRoleId() {
        return userRoleDO.getId();
    }

    public String getRoleName() {
        return userRoleDO.getName();
    }


    public Config_GameConstDTO getConstConf() {
        return constApi.get();
    }

    /**
     * 初始化所有组件
     *
     * @param userRoleDO
     * @param componentList
     */
    public void init(PlayerGameRoleDo userRoleDO, List<_APlayerComponent> componentList) {
        this.userRoleDO = userRoleDO;
        this.componentList = componentList;
        this.lastRespTime = GlobalTimeUtil.getNow();
        this.onlineState = EOnlineState.ONLINE;
        List<CompletableFuture<Void>> futures = componentList.stream()
                .map(component -> CompletableFuture.runAsync(()
                        -> component.initAsync(this), asyncServiceExecutor))
                .toList();
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        completableFuture.exceptionally(ex -> {
            log.error(ex.getMessage());
            ex.printStackTrace();
            return null;
        });
        completableFuture.join();

        itemDealerList = componentList.stream()
                .filter(comp -> comp instanceof _IPlayerItemDealer)
                .map(comp -> (_IPlayerItemDealer) comp)
                .toList();
    }

    public void tick() {
        if (!onlineState.equals(EOnlineState.ONLINE)) {
            return;
        }
        for (_APlayerComponent aPlayerComponent : componentList) {
            aPlayerComponent.tick();
        }
        //判断是否离线
        if (onlineState == EOnlineState.ONLINE) {
            long now = GlobalTimeUtil.getNow();
            if (now - lastRespTime > PLAYER_DOMAIN_MAX_ONLINE_TIME) {
                //修改状态为离线
                onlineState = EOnlineState.OFFLINE;
            }
        }
    }

    /**
     * 上线处理
     */
    public void online() {
        log.info("player online {}", getRoleId());
        componentList.forEach(_APlayerComponent::online);
    }

    /**
     * 离线处理
     */
    public void onOffline() {
        log.info("player offline {}", getRoleId());
        componentList.forEach(_APlayerComponent::offline);
    }

    /**
     * 响应请求
     *
     * @return
     */
    public Object invokeReq(EGameCommand requestType, Object req) {
        //刷新最后一次响应时间
        lastRespTime = GlobalTimeUtil.getNow();

        for (_APlayerComponent component : componentList) {
            component.invokeCommand(requestType, req);
        }
        return req;
    }

    /**
     * 发布游戏事件
     *
     * @param event
     */
    public void publishEvent(_IPlayerGameEvent event) {
        try {
            applicationContext.publishEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 响应事件
     *
     * @param event
     */
    public void onEvent(_IPlayerGameEvent event) {
        int eventCode = event.getEventType();
        EGameEventType eventType = EGameEventType.getByCode(eventCode);
        if (eventType == null) {
            log.error("event type not found {}", eventCode);
            return;
        }
        componentList.forEach(component -> component.onEvent(event));

    }

    @SuppressWarnings("unchecked")
    public <T> T getComponentByType(Class<? extends T> itemComponentClass) {
        return (T) componentList.stream()
                .filter(itemComponent -> itemComponentClass.isAssignableFrom(itemComponent.getClass()))
                .findFirst()
                .orElse(null);
    }

    public _IPlayerItemDealer lookupItemDealer(EGameItemType type) {
        return itemDealerList.stream().filter(itemDealer -> itemDealer.getItemDealerType() == type)
                .findFirst().orElse(null);
    }

    public _IPlayerItemDealer lookupItemDealer(long configId) {
//        Config_GameItemDTO itemConfig = itemConfigApi.get(configId);
//        if (itemConfig == null) {
//            log.error("无法找到物品的配置 configId:{}", configId);
//            return null;
//        }
        EGameItemType type = EGameItemType.ITEM;
        return lookupItemDealer(type);
    }

    /**
     * 发送客户端通知
     *
     * @param notify
     */
    public void notifyClient(_IGameNotify notify) {
        try {
            long msgId = IdManager.getInstance().applicateId(EGameIdType.ID_PLAYER_NOTIFY);
            EGameNotifyType notifyType = notify.getNotifyType();
            CommonNotifyData notifyData = new CommonNotifyData();
            notifyData.setType(notifyType.getCode());
            notifyData.setMsgSerial(msgId);
            notifyData.setData(JsonUtils.toJsonString(notify));

            //TODO: 选择一种推送给客户端的方式
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommonNotifyData redisObjToNotifyData(Object opt) {
        String jsonStr = Optional.ofNullable(opt).map(Object::toString).orElse(null);
        if (StrUtil.isNotBlank(jsonStr)) {
            JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
            return jsonObject.toBean(CommonNotifyData.class);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T> T getParam(EGameParamType paramType, Class<T> classType) {
        ParamComponent paramComponent = getComponentByType(ParamComponent.class);
        PlayerGameParamDo paramDO = paramComponent.lookup(paramType.getCode());
        if (paramDO == null) {
            return null;
        }
        return (T) CastUtil.convertObj(classType, paramDO.getValue());
    }

    public void setParam(EGameParamType paramType, String val) {
        ParamComponent paramComponent = getComponentByType(ParamComponent.class);
        paramComponent.setParam(paramType, val);
    }

    public void kick() {
        onlineState = EOnlineState.OFFLINE;
        onOffline();
    }

    /**
     * 用户修改名称
     *
     * @param name
     */
    public void setName(String name) {
        if (StrUtil.isEmpty(name)) {
            throw ServiceExceptionUtil.exception(GAME_NAME_EMPTY);
        }
        if (getRoleName().equals(name)) {
            throw ServiceExceptionUtil.exception(GAME_NAME_NOT_CHANGE);
        }
        Config_GameConstDTO constConf = getConstConf();
        ArrayList<ConfigItem> renameCostList = constConf.getRenameCostList();
        if (!ItemCenter.consumeItems(this, renameCostList)) {
            throw ServiceExceptionUtil.exception(GAME_ITEM_NOT_ENOUGH);
        }
        getUserRoleDO().setName(name);
        saveOrUpdate(getUserRoleDO());
    }

}
