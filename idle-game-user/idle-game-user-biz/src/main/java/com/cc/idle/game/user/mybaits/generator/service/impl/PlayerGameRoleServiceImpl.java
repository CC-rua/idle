package com.cc.idle.game.user.mybaits.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.idle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameRoleDo;
import com.cc.idle.game.user.mybaits.generator.service.PlayerGameRoleService;
import com.cc.idle.game.user.mybaits.generator.mapper.PlayerGameRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author cc
* @description 针对表【player_game_role(游戏角色)】的数据库操作Service实现
* @createDate 2024-10-19 15:29:49
*/
@Service
public class PlayerGameRoleServiceImpl extends ServiceImpl<PlayerGameRoleMapper, PlayerGameRoleDo>
    implements PlayerGameRoleService{

    @Override
    public PlayerGameRoleDo getByName(String name) {
        LambdaQueryWrapperX<PlayerGameRoleDo> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(PlayerGameRoleDo::getName, name);
        return this.getOne(queryWrapperX);
    }
}




