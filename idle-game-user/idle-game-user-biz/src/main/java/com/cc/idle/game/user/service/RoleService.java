package com.cc.idle.game.user.service;

import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameRoleDo;
import com.cc.idle.game.user.mybaits.generator.service.PlayerGameRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final PlayerGameRoleService playerGameRoleService;

    public boolean createRole(String name) {
        //TODO:检查
        PlayerGameRoleDo entity = new PlayerGameRoleDo();
        entity.setName(name);
        entity.setIsNew(true);
        entity.setLocked(false);
        return playerGameRoleService.save(entity);
    }

    public PlayerGameRoleDo getRole(String name) {
        return playerGameRoleService.getByName(name);
    }
}
