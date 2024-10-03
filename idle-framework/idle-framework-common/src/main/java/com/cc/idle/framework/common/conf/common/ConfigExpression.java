package com.cc.idle.framework.common.conf.common;

import com.cc.idle.framework.common.conf.base._IStringConvertible;
import com.cc.idle.framework.common.enums.biz.EGameExpressionType;
import lombok.Data;

@Data
public class ConfigExpression implements _IStringConvertible {
    //表达式类型
    private EGameExpressionType expressionType;
    //表达式参数
    private String[] params;

    @Override
    public void convert(String value) {
        String[] split = value.split(":");
        if (split.length == 0) {
            return;
        }
        String expressionType = split[0];
        this.expressionType = EGameExpressionType.valueOf(expressionType);
        this.params = new String[split.length - 1];
        System.arraycopy(split, 1, this.params, 0, split.length - 1);
    }

    @Override
    public String toString() {
        String param = String.join(":", params);
        return String.format("%s:%s", expressionType, param);
    }
}
