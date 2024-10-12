package com.cc.idle.game.user.game.comp;


import com.cc.idle.framework.common.conf.common.ConfigItem;
import com.cc.idle.framework.common.enums.biz.EGameItemType;

public interface  _IPlayerItemDealer {

    EGameItemType getItemDealerType();

    void gainItem(ConfigItem configItem);

    void consumeItem(ConfigItem configItem);

    boolean hasItem(ConfigItem configItem);

    long getItemCount(long itemConfigId);
}
