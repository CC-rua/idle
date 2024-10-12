package com.cc.idle.game.user.game.expression;


import com.cc.idle.framework.common.conf.common.ConfigExpression;
import com.cc.idle.framework.common.exception.util.ServiceExceptionUtil;
import com.cc.idle.game.user.enums.ErrorCodeConstants;
import com.cc.idle.game.user.events.base._IPlayerGameEvent;
import com.cc.idle.game.user.game.PlayerDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerExpressionCenter {
    private final List<_IExpressionDealer> expressionDealerList;

    private _IExpressionDealer lookupDealer(ConfigExpression configExpression) {
        if (configExpression == null) {
            return null;
        }
        return expressionDealerList.stream()
                .filter(dealer -> dealer.getExpressionDealerType() == configExpression.getExpressionType())
                .findFirst()
                .orElse(null);
    }

    /**
     * 从event中获取表达式计算的值
     *
     * @param playerDomain
     * @param configExpression
     * @param event
     * @return
     */
    public ExpressionVal getVal(PlayerDomain playerDomain, ConfigExpression configExpression, _IPlayerGameEvent event) {
        if (configExpression == null) {
            return null;
        }
        _IExpressionDealer dealer = lookupDealer(configExpression);
        if (dealer == null) {
            log.error("没有合适的表达式处理器 {}", configExpression);
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.GAME_CONFIG_ERROR);
        }
        return dealer.getVal(playerDomain, configExpression, event);
    }
}
