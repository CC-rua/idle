package com.cc.idle.game.user.game.rank.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 排行榜管理器
 *
 * @author: cc
 * @date: 2024/1/12
 */
public class RankManager {
    /**
     * 排行榜数据集
     */
    private final Map<Long, _ABaseRank<?>> rankMap;

    public RankManager() {
        rankMap = new HashMap<>();
    }

    public void register(_ABaseRank<?> rank) {
        rankMap.putIfAbsent(rank.getRankId(), rank);
    }

    public void unregister(Long rankId) {
        rankMap.remove(rankId);
    }

    public _ABaseRank<?> lookup(Long rankId) {
        return rankMap.get(rankId);
    }

    public Collection<_ABaseRank<?>> getRankValues() {
        return rankMap.values();
    }
}
