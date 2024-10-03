package com.cc.idle.framework.common.conf.common;

import com.cc.idle.framework.common.conf.base._IStringConvertible;
import com.cc.idle.framework.common.enums.biz.EGameRoundType;
import com.cc.idle.framework.common.util.date.DateUtils;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * 周期配置
 */
@Data
public class ConfigRound implements _IStringConvertible {
    //表达式类型
    private EGameRoundType roundType = EGameRoundType.NONE;
    //表达式参数
    private String[] params;

    @Override
    public void convert(String value) {
        if (value == null) {
            return;
        }
        String[] split = value.split(":");
        if (split.length == 0) {
            return;
        }
        String expressionType = split[0];
        this.roundType = EGameRoundType.valueOf(expressionType);
        this.params = new String[split.length - 1];
        System.arraycopy(split, 1, this.params, 0, split.length - 1);
    }

    @Override
    public String toString() {
        String param = String.join(":", params);
        return String.format("%s:%s", roundType, param);
    }

    /**
     * 检查是否经过最近的刷新时间点
     *
     * @param lastRefreshTimeMs
     * @return
     */
    public long nextRefreshTime(Long lastRefreshTimeMs) {
        if (lastRefreshTimeMs == null) {
            lastRefreshTimeMs = 0L;
        }
        LocalDateTime localDateTime = DateUtils.toLocalDateTime(lastRefreshTimeMs);
        return switch (roundType) {
            case NONE -> //不需要刷新
                    -1;
            case DAY_OF_MONTH -> {
                //每月第x天0点刷新
                int param = 0;
                if (params.length == 1) {
                    param = Integer.parseInt(params[0]);
                }
                int dayOfMonth = localDateTime.getDayOfMonth();
                LocalDateTime calTime = localDateTime
                        .plusMonths(dayOfMonth >= param ? 1 : 0)
                        .withDayOfMonth(param)
                        .withHour(0)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0);
                yield DateUtils.toMillis(calTime);
            }
            case HOUR_OF_DAY -> {
                //每天第x小时刷新
                int param = 0;
                if (params.length == 1) {
                    param = Integer.parseInt(params[0]);
                }
                int hour = localDateTime.getHour();
                LocalDateTime calTime = localDateTime
                        .plusDays(hour >= param ? 1 : 0)
                        .withHour(param)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0);
                yield DateUtils.toMillis(calTime);
            }
            case DAY_OF_WEEK -> {
                //每周第x天刷新
                int param = 0;
                if (params.length == 1) {
                    param = Integer.parseInt(params[0]);
                }
                DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
                LocalDateTime calTime = localDateTime
                        .plusWeeks(dayOfWeek.getValue() >= param ? 1 : 0)
                        .with(TemporalAdjusters.nextOrSame(DayOfWeek.of(param)))
                        .withHour(0)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0);

                yield DateUtils.toMillis(calTime);
            }
            case DAY -> {
                //每x天刷新
                int param = 0;
                if (params.length == 1) {
                    param = Integer.parseInt(params[0]);
                }
                LocalDateTime calTime = localDateTime.plusDays(param);

                yield DateUtils.toMillis(calTime);
            }
            case HOUR -> {
                //每x小时刷新
                int param = 0;
                if (params.length == 1) {
                    param = Integer.parseInt(params[0]);
                }
                LocalDateTime calTime = localDateTime.plusHours(param);

                yield DateUtils.toMillis(calTime);
            }
            case SECOND -> {
                //每x秒刷新
                int param = 0;
                if (params.length == 1) {
                    param = Integer.parseInt(params[0]);
                }

                LocalDateTime calTime = localDateTime.plusSeconds(param);
                yield DateUtils.toMillis(calTime);
            }
            case WEEK -> {
                //每x秒刷新
                int param = 0;
                if (params.length == 1) {
                    param = Integer.parseInt(params[0]);
                }
                LocalDateTime calTime = localDateTime.plusWeeks(param);
                yield DateUtils.toMillis(calTime);
            }
            case MONTH -> {
                //每x秒刷新
                int param = 0;
                if (params.length == 1) {
                    param = Integer.parseInt(params[0]);
                }
                LocalDateTime calTime = localDateTime.plusMonths(param);
                yield DateUtils.toMillis(calTime);
            }
        };
    }
}
