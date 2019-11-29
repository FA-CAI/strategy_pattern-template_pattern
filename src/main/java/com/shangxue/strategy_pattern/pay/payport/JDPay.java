package com.shangxue.strategy_pattern.pay.payport;

/**
 *
 **/
public class JDPay extends Payment {
    @Override
    public String getName() {
        return "京东";
    }

    @Override
    protected double queryBalance(String uid) {
        return 199;
    }
}
