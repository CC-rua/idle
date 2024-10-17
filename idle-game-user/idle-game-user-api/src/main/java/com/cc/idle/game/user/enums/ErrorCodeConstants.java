package com.cc.idle.game.user.enums;


import com.cc.idle.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    // ========== 游戏玩家 1-003-001-000 ==========
    ErrorCode GAME_USER_NOT_EXISTS = new ErrorCode(1_003_001_001, "用户信息不存在");
    ErrorCode GAME_USER_LOCK_EQUAL = new ErrorCode(1_003_001_002, "游戏账号已锁定或已解锁,无法再次执行操作");
    ErrorCode COMPONENT_LOAD_ERROR = new ErrorCode(1_003_001_003, "组件加载失败");
    ErrorCode GAME_CONFIG_ERROR = new ErrorCode(1_003_001_004, "未找到指定配置");
    ErrorCode ITEM_DEALER_ERROR = new ErrorCode(1_003_001_005, "没有合理的物品处理器");
    ErrorCode ITEM_DEALER_OPT_ERROR = new ErrorCode(1_003_001_006, "物品处理器不支持此操作");
    ErrorCode PLAYER_SHOW_CACHE_ERROR = new ErrorCode(1_003_001_007, "玩家展示信息缓存失败");
    ErrorCode GAME_PARAM_NOT_EXISTS = new ErrorCode(1_003_001_008, "玩家 - 参数不存在");
    ErrorCode GAME_ITEM_NOT_ENOUGH = new ErrorCode(1_003_001_009, "玩家 - 道具不足");
    ErrorCode GAME_USER_NOT_ONLINE = new ErrorCode(1_003_001_010, "玩家 - 不在线");
    ErrorCode GAME_NAME_NOT_CHANGE = new ErrorCode(1_003_001_011, "玩家 - 与当前用户名一致");
    ErrorCode GAME_NAME_EMPTY = new ErrorCode(1_003_001_011, "玩家 - 用户名不能为空");
    // ========== 挂机组件 1-003-001-000 ==========
    ErrorCode GAME_IDLE_NOT_START = new ErrorCode(1_003_002_001, "挂机 - 挂机未开始");

}
