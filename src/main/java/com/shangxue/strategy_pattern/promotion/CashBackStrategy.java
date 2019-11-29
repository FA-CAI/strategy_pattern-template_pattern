package com.shangxue.strategy_pattern.promotion;

/**
 * @program: gupao_pattern_mode
 * @description:
 * @author: zk
 * @create: 2019-04-05 15:58
 **/
public class CashBackStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现优惠策略");
    }
}
