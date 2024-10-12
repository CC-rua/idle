package com.cc.idle.game.user.game.comp.param;


import com.cc.idle.framework.common.enums.biz.EGameParamType;
import com.cc.idle.game.user.enums.EGameCommand;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.game.PlayerDomain;
import com.cc.idle.game.user.game.comp._APlayerComponent;
import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameParamDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.*;

@Component
@Scope("prototype")
@Slf4j
public class ParamComponent extends _APlayerComponent {
    private final List<PlayerGameParamDo> questDOList;

    public ParamComponent() {
        this.questDOList = new ArrayList<>();
    }

    @Override
    public void initAsync(PlayerDomain playerDomain) {
        this.player = playerDomain;
        Long roleId = playerDomain.getRoleId();
        List<PlayerGameParamDo> gameQuestList = lambdaQuery(PlayerGameParamDo.class)
                .eq(PlayerGameParamDo::getRoleId, roleId)
                .list();
        questDOList.addAll(gameQuestList);
    }

    @Override
    public void tick() {

    }

    @Override
    public void invokeCommand(EGameCommand commandType, Object req) {
        switch (commandType) {
            case REQ_LOGIN:
                log.info("quest component invoke reqLogin ...{}", getRoleId());
                break;
            default:
                break;
        }
    }

    @Override
    public void onEvent(_IPlayerGameEvent event) {

    }

    @Override
    public void online() {

    }

    @Override
    public void offline() {

    }

    public List<PlayerGameParamDo> getQuestList() {
        return new ArrayList<>(questDOList);
    }

    public PlayerGameParamDo lookup(Integer paramId) {
        return questDOList.stream()
                .filter(questDO -> questDO.getParamType().equals(paramId))
                .findFirst()
                .orElse(null);
    }

    public PlayerGameParamDo ensureQuest(Integer paramType) {
        PlayerGameParamDo paramDO = lookup(paramType);
        if (paramDO == null) {
            paramDO = new PlayerGameParamDo();
            paramDO.setParamType(paramType);
            paramDO.setRoleId(getRoleId());
            paramDO.setValue("0");
            save(paramDO);
            questDOList.add(paramDO);
        }
        return paramDO;
    }

    public void setParam(EGameParamType paramType, String val) {
        PlayerGameParamDo paramDO = ensureQuest(paramType.getCode());
        paramDO.setValue(val);
        saveOrUpdate(paramDO);
    }
}
