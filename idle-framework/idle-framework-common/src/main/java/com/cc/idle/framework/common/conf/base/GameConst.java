package com.cc.idle.framework.common.conf.base;

public interface GameConst {
    int serverId = 1;

    long SECOND = 1000;
    long DAY = 86400000;

    //玩家通知堆积数量上下
    int PLAYER_NOTIFY_MAX = 1000;

    String PLAYER_UNAME_PREFIX = "临时用户-";

    String QUEST_FINISH_PUSH_WX_MSG_CONTENT_TEMPLATE = "恭喜%s，您完成了%s任务";

    //完成任务推送
    String ACHIEVEMENT_FINISH_PUSH_WX_MSG_CONTENT_TEMPLATE = "恭喜您，%s，您完成了%s成就";

}
