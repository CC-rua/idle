package com.cc.idle.game.user.mybaits.generator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 游戏角色
 * @TableName player_game_role
 */
@TableName(value ="player_game_role")
@Data
public class PlayerGameRoleDo implements Serializable {
    /**
     * 唯一id
     */
    @TableField(value = "id")
    private Long id;

    /**
     * 昵称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 是否新用户
     */
    @TableField(value = "is_new")
    private Boolean isNew;

    /**
     * 是否锁定
     */
    @TableField(value = "locked")
    private Boolean locked;

    /**
     * 是否删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}