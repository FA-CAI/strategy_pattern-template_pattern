package com.shangxue.strategy_pattern.pay;

import com.shangxue.strategy_pattern.pay.payport.Payment;
import com.shangxue.strategy_pattern.pay.payport.PayStrategy;

/**
 * @program: gupao_pattern_mode
 * @description:
 * @author: zk
 * @create: 2019-04-05 16:19
 **/
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public MsgResult pay(String payKey){
        Payment payment= PayStrategy.getPayStrategy(payKey);
        System.out.println("欢迎使用"+payment.getName());
        System.out.println("本次交易金额为："+amount);
        return payment.pay(uid,amount);
    }
}
