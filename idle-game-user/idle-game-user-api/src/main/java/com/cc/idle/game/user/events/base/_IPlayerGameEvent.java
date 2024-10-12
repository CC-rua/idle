package com.cc.idle.game.user.events.base;

/**
 * 玩家任务事件
 */
public interface _IPlayerGameEvent {
    /**
     * 用户角色id
     *
     * @return
     */
    long getRoleId();

    /**
     * 所属门店
     */
   default long getSubStoreId(){
       return 0L;
   }

    /**
     * 事件类型
     *
     * @return
     */
    int getEventType();

}
