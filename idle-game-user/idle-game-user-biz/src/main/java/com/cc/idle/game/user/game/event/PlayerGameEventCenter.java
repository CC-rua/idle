package com.cc.idle.game.user.game.event;


import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.game.PlayerDomain;
import com.cc.idle.game.user.game.PlayerDomainMgr;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerGameEventCenter {
    private final PlayerDomainMgr playerDomainMgr;

    @EventListener(_IPlayerGameEvent.class)
    public void onPlayerGameEvent(_IPlayerGameEvent event) {
        PlayerDomain playerDomain = playerDomainMgr.getOrCreatePlayerDomain(event.getRoleId());
        if (playerDomain != null) {
            try {
                playerDomain.onEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
