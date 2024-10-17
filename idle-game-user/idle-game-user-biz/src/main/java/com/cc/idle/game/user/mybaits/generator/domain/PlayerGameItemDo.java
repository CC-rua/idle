package com.cc.idle.game.user.mybaits.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}