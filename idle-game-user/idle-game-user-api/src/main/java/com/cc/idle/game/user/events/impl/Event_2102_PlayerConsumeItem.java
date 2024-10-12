package com.cc.idle.game.user.events.impl;


import com.cc.idle.framework.common.enums.biz.EGameEventType;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Event_2102_PlayerConsumeItem implements _IPlayerGameEvent {
    /**
     * 用户角色id
     */
    private long roleId;

    /**
     * 道具id
     */
    private long itemId;

    /**
     * 道具数量
     */
    private long count;

    @Override
    public long getRoleId() {
        return roleId;
    }

    @Override
    public int getEventType() {
        return EGameEventType.PLAYER_ITEM_CONSUME.getCode();
    }

}
