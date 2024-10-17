package com.cc.idle.game.user.game.comp.item;


import com.cc.idle.framework.common.conf.common.ConfigItem;
import com.cc.idle.framework.common.enums.biz.EGameItemType;
import com.cc.idle.game.user.enums.EGameCommand;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.game.PlayerDomain;
import com.cc.idle.game.user.game.comp._APlayerComponent;
import com.cc.idle.game.user.game.comp._IPlayerItemDealer;
import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameItemDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.*;

@Component
@Scope("prototype")
@Slf4j
public class ItemComponent extends _APlayerComponent implements _IPlayerItemDealer {
    private final List<PlayerGameItemDo> doList;

    public ItemComponent() {
        this.doList = new ArrayList<>();
    }

    @Override
    public void initAsync(PlayerDomain playerDomain) {
        this.player = playerDomain;
        Long roleId = playerDomain.getRoleId();
        List<PlayerGameItemDo> playerDoList = lambdaQuery(PlayerGameItemDo.class)
                .eq(PlayerGameItemDo::getRoleId, roleId)
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


    @Override
    public EGameItemType getItemDealerType() {
        return EGameItemType.ITEM;
    }

    @Override
    public void gainItem(ConfigItem configItem) {
        PlayerGameItemDo playerDo = ensureDo(configItem.getId());
        Long updateCount = playerDo.getCount() + configItem.getCount();
        playerDo.setCount(updateCount);
        saveOrUpdate(playerDo);
    }

    @Override
    public void consumeItem(ConfigItem configItem) {
        PlayerGameItemDo playerDo = ensureDo(configItem.getId());
        long updateCount = playerDo.getCount() - configItem.getCount();
        playerDo.setCount(updateCount);
        saveOrUpdate(playerDo);
    }

    @Override
    public boolean hasItem(ConfigItem configItem) {
        return getItemCount(configItem.getId()) >= configItem.getCount();
    }

    @Override
    public long getItemCount(long itemConfigId) {
        PlayerGameItemDo playerDo = ensureDo(itemConfigId);
        return playerDo.getCount();
    }

    public List<PlayerGameItemDo> getDoList() {
        return new ArrayList<>(doList);
    }

    public PlayerGameItemDo lookup(Long configId) {
        return doList.stream()
                .filter(questDO -> questDO.getConfigId().equals(configId))
                .findFirst()
                .orElse(null);
    }

    public PlayerGameItemDo ensureDo(Long configId) {
        PlayerGameItemDo playerDo = lookup(configId);
        if (playerDo == null) {
            playerDo = new PlayerGameItemDo();
            playerDo.setRoleId(getRoleId());
            playerDo.setConfigId(configId);
            playerDo.setCount(0L);
            save(playerDo);
            doList.add(playerDo);
        }
        return playerDo;
    }
}
