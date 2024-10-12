package com.cc.idle.game.user.game.notify;

import lombok.Data;

@Data
public class CommonNotifyData {
    private Integer type;
    private Long msgSerial;
    private String data;
}
