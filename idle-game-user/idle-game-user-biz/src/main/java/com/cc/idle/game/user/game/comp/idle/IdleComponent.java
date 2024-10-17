package com.cc.idle.game.user.game.comp.idle;


import com.cc.idle.framework.common.conf.common.ConfigItem;
import com.cc.idle.framework.common.exception.util.ServiceExceptionUtil;
import com.cc.idle.framework.common.util.date.GlobalTimeUtil;
import com.cc.idle.game.config.api.conf.Config_GameIdleApi;
import com.cc.idle.game.config.api.conf.dto.Config_GameIdleDTO;
import com.cc.idle.game.user.enums.EGameCommand;
import com.cc.idle.game.user.enums.ErrorCodeConstants;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.game.ItemCenter;
import com.cc.idle.game.user.game.PlayerDomain;
import com.cc.idle.game.user.game.comp._APlayerComponent;
import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameIdleDo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.*;

@Component
@Scope("prototype")
@Slf4j
public class IdleComponent extends _APlayerComponent {
    private final List<PlayerGameIdleDo> doList;
    @Resource
    private Config_GameIdleApi idleConfigApi;

    public IdleComponent() {
        this.doList = new ArrayList<>();
    }

    @Override
    public void initAsync(PlayerDomain playerDomain) {
        this.player = playerDomain;
        Long roleId = playerDomain.getRoleId();
        List<PlayerGameIdleDo> playerDoList = lambdaQuery(PlayerGameIdleDo.class)
                .eq(PlayerGameIdleDo::getRoleId, roleId)
                .list();
        doList.addAll(playerDoList);
    }

    @Override
    public void tick() {

    }

    @Override
    public void invokeCommand(EGameCommand commandType, Object req) {
        switch (commandType) {
            case REQ_LOGIN:
                log.info("quest component invoke reqLogin ...{}", getRoleId());
                break;
            default:
                break;
        }
    }

    @Override
    public void onEvent(_IPlayerGameEvent event) {

    }

    @Override
    public void online() {

    }

    @Override
    public void offline() {

    }

    public List<PlayerGameIdleDo> getDoList() {
        return new ArrayList<>(doList);
    }

    public PlayerGameIdleDo lookup(Long configId) {
        return doList.stream()
                .filter(questDO -> questDO.getConfigId().equals(configId))
                .findFirst()
                .orElse(null);
    }

    public PlayerGameIdleDo ensureDo(Long configId) {
        PlayerGameIdleDo playerDo = lookup(configId);
        if (playerDo == null) {
            playerDo = new PlayerGameIdleDo();
            playerDo.setRoleId(getRoleId());
            playerDo.setConfigId(configId);
            playerDo.setSettleTime(0L);
            save(playerDo);
            doList.add(playerDo);
        }
        return playerDo;
    }

    /**
     * 开始产出
     *
     * @param configId
     */
    public void start(Long configId) {
        Config_GameIdleDTO idleConf = idleConfigApi.get(configId);
        if (idleConf == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.GAME_CONFIG_ERROR);
        }
        PlayerGameIdleDo playerDo = ensureDo(configId);
        playerDo.setSettleTime(GlobalTimeUtil.getNow());
        saveOrUpdate(playerDo);
    }

    /**
     * 结算产出
     *
     * @param configId
     */
    public void end(Long configId) {
        Config_GameIdleDTO idleConf = idleConfigApi.get(configId);
        if (idleConf == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.GAME_CONFIG_ERROR);
        }
        PlayerGameIdleDo playerDo = lookup(configId);
        if (playerDo == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.GAME_IDLE_NOT_START);
        }
        Long settleTime = playerDo.getSettleTime();
        Long now = GlobalTimeUtil.getNow();
        Integer round = idleConf.getSettleTime();
        //计算结算周期数
        long settleRounds = (now - settleTime) / (round * 1000);
        //更新结算时间
        playerDo.setSettleTime(settleTime + settleRounds * round * 1000);
        saveOrUpdate(playerDo);

        //发放物品
        ConfigItem item = idleConf.getItem();
        ItemCenter.gainItem(player, item.getId(), item.getCount() * settleRounds);
    }
}
