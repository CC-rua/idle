package com.cc.idle.game.user.enums;

import lombok.Getter;

@Getter
public enum EGameCommand {
    REQ_LOGIN(1001001, "登录");

    private final int value;
    private final String desc;

    EGameCommand(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static EGameCommand fromInt(int value) {
        for (EGameCommand eGameCommand : EGameCommand.values()) {
            if (eGameCommand.value == value) {
                return eGameCommand;
            }
        }
        return null;
    }

    public static EGameCommand fromName(String value) {
        for (EGameCommand eGameCommand : EGameCommand.values()) {
            if (eGameCommand.name().equals(value)) {
                return eGameCommand;
            }
        }
        return null;
    }
}
