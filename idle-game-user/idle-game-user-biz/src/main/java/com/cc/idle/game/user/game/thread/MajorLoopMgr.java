package com.cc.idle.game.user.game.thread;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MajorLoopMgr {
    private final List<MajorLoop> majorLoopList;

    public void run() {
        for (MajorLoop majorLoop : majorLoopList) {
            majorLoop.start0();
        }
    }
}
