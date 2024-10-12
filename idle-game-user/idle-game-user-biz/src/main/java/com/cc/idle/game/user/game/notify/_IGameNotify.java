package com.cc.idle.game.user.game.notify;


import com.cc.idle.framework.common.enums.biz.EGameNotifyType;

public interface _IGameNotify {
    String RANK_REWARD_TIPS = "恭喜你，在%s中获得第%d名，请领取以下奖励";
    EGameNotifyType getNotifyType();
}
