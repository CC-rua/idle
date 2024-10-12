package com.cc.idle.game.user.enums;

import lombok.Getter;

@Getter
public enum EOnlineState {
    ONLINE(1),
    OFFLINE(2);

    private int value;

    EOnlineState(int value) {
        this.value = value;
    }
}
