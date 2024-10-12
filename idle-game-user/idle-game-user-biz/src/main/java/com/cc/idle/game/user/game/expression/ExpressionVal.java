package com.cc.idle.game.user.game.expression;

import com.cc.idle.framework.common.enums.biz.EGameSummaryType;
import lombok.Data;

/**
 * 表达式返回值
 */
@Data
public class ExpressionVal {
    private float value;
    private EGameSummaryType summaryType;

    public ExpressionVal(long value) {
        this.value = value;
        this.summaryType = EGameSummaryType.ADD;
    }

    public ExpressionVal(long value, EGameSummaryType summaryType) {
        this.value = value;
        this.summaryType = summaryType;
    }

    public ExpressionVal(float value, EGameSummaryType summaryType) {
        this.value = value;
        this.summaryType = summaryType;
    }

    public static ExpressionVal val(long val) {
        return new ExpressionVal(val);
    }

    public static ExpressionVal val(long val, EGameSummaryType eGameSummaryType) {
        return new ExpressionVal(val, eGameSummaryType);
    }

    public static ExpressionVal val(float val, EGameSummaryType eGameSummaryType) {
        return new ExpressionVal(val, eGameSummaryType);
    }

    public long getLongValue() {
        return (long) value;
    }
}
