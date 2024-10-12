package com.cc.idle.game.user.game;

import com.cc.idle.framework.common.conf.common.ConfigItem;
import com.cc.idle.game.user.events.impl.Event_2101_PlayerGainItem;
import com.cc.idle.game.user.events.impl.Event_2102_PlayerConsumeItem;
import com.cc.idle.game.user.game.comp._IPlayerItemDealer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ItemCenter {
    public final static long GOLD_ITEM_ID = 1001L;
    public final static long STAR_ITEM_ID = 1002L;

    public static boolean hasItem(PlayerDomain playerDomain, ConfigItem configItem) {
        _IPlayerItemDealer itemDealer = playerDomain.lookupItemDealer(configItem.getId());
        if (itemDealer == null) {
            log.error("检查物品数量时时没有找到合适的物品处理器 {}", configItem.getId());
            return false;
        }
        return itemDealer.hasItem(configItem);
    }

    public static boolean hasItems(PlayerDomain playerDomain, List<ConfigItem> configItemList) {
        for (ConfigItem configItem : configItemList) {
            if (!hasItem(playerDomain, configItem)) {
                return false;
            }
        }
        return true;
    }

    public static void gainItem(PlayerDomain player, Long configId, Long count) {
        gainItem(player, new ConfigItem(configId, count));
    }

    public static void gainItem(PlayerDomain playerDomain, ConfigItem configItem) {
        _IPlayerItemDealer itemDealer = playerDomain.lookupItemDealer(configItem.getId());
        if (itemDealer == null) {
            log.error("获得物品时没有找到合适的物品处理器 {}", configItem.getId());
            return;
        }
        itemDealer.gainItem(configItem);
        Event_2101_PlayerGainItem event = Event_2101_PlayerGainItem.builder()
                .roleId(playerDomain.getRoleId())
                .itemId(configItem.getId())
                .count(configItem.getCount())
                .build();
        playerDomain.publishEvent(event);
    }

    public static void gainItems(PlayerDomain playerDomain, List<ConfigItem> configItemList) {
        for (ConfigItem configItem : configItemList) {
            gainItem(playerDomain, configItem);
        }
    }

    public static boolean consumeItem(PlayerDomain playerDomain, ConfigItem configItem) {
        _IPlayerItemDealer itemDealer = playerDomain.lookupItemDealer(configItem.getId());
        if (itemDealer == null) {
            log.error("消耗物品时没有找到合适的物品处理器 {}", configItem.getId());
            return false;
        }
        if (!itemDealer.hasItem(configItem)) {
            return false;
        }
        itemDealer.consumeItem(configItem);
        Event_2102_PlayerConsumeItem event = Event_2102_PlayerConsumeItem.builder()
                .roleId(playerDomain.getRoleId())
                .itemId(configItem.getId())
                .count(configItem.getCount())
                .build();
        playerDomain.publishEvent(event);
        return true;
    }

    public static boolean consumeItems(PlayerDomain playerDomain, List<ConfigItem> configItemList) {
        if (!hasItems(playerDomain, configItemList)) {
            return false;
        }
        for (ConfigItem configItem : configItemList) {
            consumeItem(playerDomain, configItem);
        }
        return true;
    }

    public static long getItemCount(PlayerDomain playerDomain, long itemConfigId) {
        _IPlayerItemDealer itemDealer = playerDomain.lookupItemDealer(itemConfigId);
        if (itemDealer == null) {
            log.error("获得物品数量时没有找到合适的物品处理器 {}", itemConfigId);
            return 0;
        }
        return itemDealer.getItemCount(itemConfigId);
    }

    /**
     * 计算物品数量额外获得
     *
     * @param configItemList 源物品
     * @param itemIds        需要加成的id
     * @param multiple       加成倍率
     * @return
     */
    public static ArrayList<ConfigItem> multiItem(List<ConfigItem> configItemList, List<Long> itemIds, long multiple) {
        ArrayList<ConfigItem> configItems = new ArrayList<>();
        configItemList.forEach(configItem -> {
            if (!itemIds.contains(configItem.getId())) {
                return;
            }
            ConfigItem newItem = new ConfigItem();
            newItem.setId(configItem.getId());
            newItem.setCount(configItem.getCount());
            long count = (long) (newItem.getCount() * (multiple / 10000.00f));
            newItem.setCount(count);
            configItems.add(newItem);
        });
        return configItems;
    }
}
