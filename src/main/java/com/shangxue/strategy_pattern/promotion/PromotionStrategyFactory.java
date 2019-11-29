package com.shangxue.strategy_pattern.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: gupao_pattern_mode
 * @description:
 * @author: zk
 * @create: 2019-04-05 16:04
 **/
public class PromotionStrategyFactory {

    private static Map<String,PromotionStrategy> PROMOTION_STRATEGY_MAP=new HashMap<String, PromotionStrategy>();
    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON,new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK,new CashBackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUPBUY,new GroupBuyStrategy());
    }

    private static PromotionStrategy EMPRY_STRATEGY=new EmptyStrategy();
    private PromotionStrategyFactory (){};

    public static PromotionStrategy getPromotionStrategy(String promotiomKey){
        PromotionStrategy promotionStrategy=PROMOTION_STRATEGY_MAP.get(promotiomKey);
        if(promotionStrategy==null){
            return EMPRY_STRATEGY;
        }else{
            return promotionStrategy;
        }
    }
    private interface PromotionKey{
        String COUPON="COUPON";
        String GROUPBUY="GROUPBUY";
        String CASHBACK="CASHBACK";
    }
}
