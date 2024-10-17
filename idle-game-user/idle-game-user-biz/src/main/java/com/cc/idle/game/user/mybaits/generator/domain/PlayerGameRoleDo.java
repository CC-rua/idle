package com.cc.idle.game.user.mybaits.generator.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cc.idle.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 游戏角色
 *
 * @TableName player_game_role
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "player_game_role")
@Data
public class PlayerGameRoleDo extends BaseDO implements Serializable {
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}