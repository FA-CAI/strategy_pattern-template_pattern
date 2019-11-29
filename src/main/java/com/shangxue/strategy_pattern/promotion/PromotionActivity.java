package com.shangxue.strategy_pattern.promotion;

/**
 * @program: gupao_pattern_mode
 * @description:
 * @author: zk
 * @create: 2019-04-05 16:01
 **/
public class PromotionActivity {

    PromotionStrategy promotionStrategy;
    public PromotionActivity(PromotionStrategy promotionStrategy){
        this.promotionStrategy=promotionStrategy;
    }
    public void execute(){
        promotionStrategy.doPromotion();
    }
}
