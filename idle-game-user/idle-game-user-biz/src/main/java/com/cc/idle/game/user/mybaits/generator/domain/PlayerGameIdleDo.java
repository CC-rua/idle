package com.cc.idle.game.user.mybaits.generator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 挂机结算
 * @TableName player_game_idle
 */
@TableName(value ="player_game_idle")
@Data
public class PlayerGameIdleDo implements Serializable {
    /**
     * 
     */
    @TableField(value = "id")
    private Long id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 
     */
    @TableField(value = "config_id")
    private Long configId;

    /**
     * 结算时间戳
     */
    @TableField(value = "settle_time")
    private Long settleTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}