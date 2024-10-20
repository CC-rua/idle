package com.cc.idle.game.user.mybaits.generator.service;

import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameRoleDo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author cc
* @description 针对表【player_game_role(游戏角色)】的数据库操作Service
* @createDate 2024-10-19 15:29:49
*/
public interface PlayerGameRoleService extends IService<PlayerGameRoleDo> {

    PlayerGameRoleDo getByName(String name);
}
