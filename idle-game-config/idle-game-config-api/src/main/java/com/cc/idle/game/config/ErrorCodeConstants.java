package com.cc.idle.game.config;


import com.cc.idle.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    // ========== 游戏玩家 1-005-000-000 ==========
    ErrorCode FILE_TYPE_ERROR = new ErrorCode(1_005_001_001, "文件类型必须是表格 xls xlsx");
    ErrorCode FILE_TO_JSON_ERROR = new ErrorCode(1_005_001_002, "表格转义失败");
    ErrorCode FILE_NOT_EXIST = new ErrorCode(1_005_001_003, "文件不存在");
    ErrorCode FILE_CLIENT_NOT_LOCAL = new ErrorCode(1_005_001_004, "配置文件需要本地存储器");
    ErrorCode CONFIG_CLASS_NOT_FOUND = new ErrorCode(1_005_001_005, "配置类没有找到");
    ErrorCode CONFIG_LOADER_NOT_FOUND = new ErrorCode(1_005_001_005, "配置类没有加载器找到");
    ErrorCode CONFIG_CLASS_NOT_COMMENT = new ErrorCode(1_005_001_006, "配置文件没有注解");
    ErrorCode CONFIG_CLASS_DUPLICATE = new ErrorCode(1_005_001_007, "配置类指向了同样的配置文件");

}
