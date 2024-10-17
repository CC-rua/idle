package com.cc.idle.game.user.mybaits.generator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 玩家道具
 * @TableName player_game_item
 */
@TableName(value ="player_game_item")
@Data
public class PlayerGameItemDo implements Serializable {
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
     * 配置id
     */
    @TableField(value = "config_id")
    private Long configId;

    /**
     * 数量
     */
    @TableField(value = "count")
    private Long count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}