package com.cc.idle.game.user.game.comp;


import com.cc.idle.game.user.enums.EGameCommand;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.game.PlayerDomain;
import lombok.extern.slf4j.Slf4j;

/**
 * 玩家组件
 */

@Slf4j
public abstract class _APlayerComponent {

    protected PlayerDomain player;

    public Long getRoleId() {
        return player.getRoleId();
    }

    public String getRoleName() {
        return player.getRoleName();
    }

    public abstract void initAsync(PlayerDomain playerDomain);

    public abstract void tick();

    public abstract void invokeCommand(EGameCommand requestType, Object req);

    public abstract void onEvent(_IPlayerGameEvent event);

    public abstract void online();

    public abstract void offline();
}
