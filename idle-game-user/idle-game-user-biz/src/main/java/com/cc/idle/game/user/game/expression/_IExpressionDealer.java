package com.cc.idle.game.user.game.expression;


import com.cc.idle.framework.common.conf.common.ConfigExpression;
import com.cc.idle.framework.common.enums.biz.EGameExpressionType;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.game.PlayerDomain;

public interface _IExpressionDealer {
    EGameExpressionType getExpressionDealerType();

    ExpressionVal getVal(PlayerDomain playerDomain, ConfigExpression configExpression, _IPlayerGameEvent event);
}
