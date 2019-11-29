package com.shangxue.strategy_pattern.pay.payport;


import java.util.HashMap;
import java.util.Map;

/**
 * @program: gupao_pattern_mode
 * @description:
 * @author: zk
 * @create: 2019-04-05 16:29
 **/
public class PayStrategy {

    private static Map<String,Payment> PAY_STRATEGY_MAP=new HashMap<String, Payment>();
    static {
        PAY_STRATEGY_MAP.put(PromotionKey.ALIPAY,new AliPay());
        PAY_STRATEGY_MAP.put(PromotionKey.JDPAY,new JDPay());
        PAY_STRATEGY_MAP.put(PromotionKey.WECHATPAY,new WeChatPay());
        PAY_STRATEGY_MAP.put(PromotionKey.UNIONPAY,new UnionPay());
    }

    private PayStrategy (){};

    public static Payment getPayStrategy(String promotiomKey){
        if(!PAY_STRATEGY_MAP.containsKey(promotiomKey)){
            return PAY_STRATEGY_MAP.get(PromotionKey.DEFAULT);
        }else{
            return PAY_STRATEGY_MAP.get(promotiomKey);
        }
    }
    interface PromotionKey{    //接口里面数据都是常量，默认是public final修饰的
        String ALIPAY="ALIPAY";
        String JDPAY="JDPAY";
        String WECHATPAY="WECHATPAY";
        String UNIONPAY="UNIONPAY";
        String DEFAULT =ALIPAY;
    }
}
