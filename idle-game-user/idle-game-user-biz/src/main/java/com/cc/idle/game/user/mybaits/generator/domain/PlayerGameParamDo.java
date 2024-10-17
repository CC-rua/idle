package com.cc.idle.game.user.mybaits.generator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 玩家 - 参数
 * @TableName player_game_param
 */
@TableName(value ="player_game_param")
@Data
public class PlayerGameParamDo implements Serializable {
    /**
     * 
     */
    @TableField(value = "id")
    private Long id;

    /**
     * player_game_role 表的id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 参数类型
     */
    @TableField(value = "param_type")
    private Integer paramType;

    /**
     * 值
     */
    @TableField(value = "value")
    private String value;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}