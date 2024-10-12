package com.cc.idle.game.user.game.expression;


import com.cc.idle.framework.common.conf.common.ConfigExpression;
import com.cc.idle.framework.common.enums.biz.EGameExpressionType;
import com.cc.idle.framework.common.enums.biz.EGameSummaryType;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.events.impl.Event_2101_PlayerGainItem;
import com.cc.idle.game.user.game.ItemCenter;
import com.cc.idle.game.user.game.PlayerDomain;
import org.springframework.stereotype.Component;

import static com.cc.idle.game.user.game.expression.ExpressionVal.val;

@Component
public class ExpressionDealer_1002_PLAYER_HAS_ITEM_COUNT implements _IExpressionDealer {

    @Override
    public EGameExpressionType getExpressionDealerType() {
        return EGameExpressionType.PLAYER_HAS_ITEM_COUNT;
    }

    @Override
    public ExpressionVal getVal(PlayerDomain playerDomain, ConfigExpression configExpression, _IPlayerGameEvent event) {
        if (event instanceof Event_2101_PlayerGainItem e) {
            String[] params = configExpression.getParams();
            if (params.length < 1) {
                return null;
            }
            long itemConfigId = Long.parseLong(params[0]);
            if (e.getItemId() != itemConfigId) {
                return null;
            }
            long itemCount = ItemCenter.getItemCount(playerDomain, itemConfigId);
            return val(itemCount, EGameSummaryType.GT);
        }
        return null;
    }
}
