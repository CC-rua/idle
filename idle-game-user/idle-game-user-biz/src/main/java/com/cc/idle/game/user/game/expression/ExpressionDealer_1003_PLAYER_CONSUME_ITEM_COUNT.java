package com.cc.idle.game.user.game.expression;


import com.cc.idle.framework.common.conf.common.ConfigExpression;
import com.cc.idle.framework.common.enums.biz.EGameExpressionType;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.events.impl.Event_2102_PlayerConsumeItem;
import com.cc.idle.game.user.game.PlayerDomain;
import org.springframework.stereotype.Component;

import static com.cc.idle.game.user.game.expression.ExpressionVal.val;


@Component
public class ExpressionDealer_1003_PLAYER_CONSUME_ITEM_COUNT implements _IExpressionDealer {

    @Override
    public EGameExpressionType getExpressionDealerType() {
        return EGameExpressionType.PLAYER_CONSUME_ITEM_COUNT;
    }

    @Override
    public ExpressionVal getVal(PlayerDomain playerDomain, ConfigExpression configExpression, _IPlayerGameEvent event) {
        if (event instanceof Event_2102_PlayerConsumeItem e) {
            String[] params = configExpression.getParams();
            if (params.length < 1) {
                return null;
            }
            long itemConfigId = Long.parseLong(params[0]);
            if (itemConfigId != e.getItemId()) {
                return null;
            }
            return val(e.getCount());
        }

        return null;
    }
}
