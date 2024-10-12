package com.cc.idle.game.user.game.notify;


import com.cc.idle.framework.common.enums.biz.EGameNotifyType;
import com.cc.idle.game.user.events.pojo.ItemConfigVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 排行榜奖励结算
 */
@Data
@AllArgsConstructor
@Builder
public class Notify_Tips implements _IGameNotify {
    private String title;
    private String message;
    private List<ItemConfigVO> items;

    @Override
    public EGameNotifyType getNotifyType() {
        return EGameNotifyType.TIPS_NOTIFY;
    }
}
