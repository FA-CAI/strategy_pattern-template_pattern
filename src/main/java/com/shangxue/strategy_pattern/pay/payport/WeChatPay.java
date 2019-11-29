package com.shangxue.strategy_pattern.pay.payport;

/**
 * @program: gupao_pattern_mode
 * @description:
 * @author: zk
 * @create: 2019-04-05 16:26
 **/
public class WeChatPay extends Payment {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 799;
    }
}
