package com.cc.idle.game.user.game;


import com.cc.idle.framework.common.exception.util.ServiceExceptionUtil;
import com.cc.idle.game.user.enums.EGameCommand;
import com.cc.idle.game.user.enums.EOnlineState;
import com.cc.idle.game.user.enums.ErrorCodeConstants;
import com.cc.idle.game.user.game.comp._APlayerComponent;
import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameRoleDo;
import com.cc.idle.game.user.mybaits.generator.service.PlayerGameRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
@RequiredArgsConstructor
public class PlayerDomainMgr {
    private final ApplicationContext applicationContext;
    private final PlayerGameRoleService gameUserRoleServer;
    /**
     * 玩家领域对象管理
     */
    private final Map<Long, PlayerDomain> onlinePlayerDomainMap = new ConcurrentHashMap<>();
    /**
     * 待移除内存的玩家领域对象
     */
    private final LinkedList<PlayerDomain> offlinePlayerDomainList = new LinkedList<>();
    /**
     * 列表锁
     */
    private final ReentrantLock lock = new ReentrantLock();


    public PlayerDomain getOnlinePlayerDomain(Long roleId) {
        lock.lock();
        try {
            return onlinePlayerDomainMap.get(roleId);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取或创建一个玩家领域对象
     *
     * @param roleId
     * @return
     */
    public PlayerDomain getOrCreatePlayerDomain(Long roleId) {
        lock.lock();
        try {
            PlayerDomain playerDomain = onlinePlayerDomainMap.get(roleId);
            if (playerDomain == null) {
                playerDomain = createPlayerDomain(roleId);
                onlinePlayerDomainMap.put(roleId, playerDomain);
                playerDomain.online();
            }
            return playerDomain;
        } finally {
            lock.unlock();
        }
    }


    private PlayerDomain createPlayerDomain(Long roleId) {
        lock.lock();
        try {
            PlayerDomain playerDomain = applicationContext.getBean(PlayerDomain.class);

            PlayerGameRoleDo roleDo = gameUserRoleServer.getById(roleId);
            if (roleDo == null) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.GAME_USER_NOT_EXISTS);
            }

            try {
                playerDomain.init(roleDo, makeComponentList());
            } catch (Exception e) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.COMPONENT_LOAD_ERROR);
            }

            return playerDomain;
        } finally {
            lock.unlock();
        }
    }

    private List<_APlayerComponent> makeComponentList() {
        Map<String, _APlayerComponent> beans = applicationContext.getBeansOfType(_APlayerComponent.class);
        return new ArrayList<>(beans.values());
    }

    /**
     * 全局tick
     */
    public void tick() {
        lock.lock();
        try {
            //检查玩家领域对象是否活跃超时,将离线用户添加到离线队列，从在线队列移除非活跃用户
            onlinePlayerDomainMap.forEach((k, v) -> {
                if (v.getOnlineState().equals(EOnlineState.OFFLINE)) {
                    offlinePlayerDomainList.add(v);
                }
            });
            onlinePlayerDomainMap.entrySet().removeIf(en
                    -> EOnlineState.OFFLINE.equals(en.getValue().getOnlineState()));
            //执行所有玩家领域对象的tick
            onlinePlayerDomainMap.forEach((k, v) -> v.tick());
            //执行所有离线用户的离线操作，并清空离线队列
            offlinePlayerDomainList.forEach(PlayerDomain::onOffline);
            offlinePlayerDomainList.clear();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 处理请求
     *
     * @param roleId
     * @param reqType
     * @param req
     */
    public Object invokeReq(Long roleId, EGameCommand reqType, Object req) {
        PlayerDomain domain = getOrCreatePlayerDomain(roleId);
        return domain.invokeReq(reqType, null);
    }

    public void offline(Long roleId) {
        PlayerDomain onlinePlayerDomain = getOnlinePlayerDomain(roleId);
        if (onlinePlayerDomain == null) {
            return;
        }
        onlinePlayerDomain.kick();
    }
}
