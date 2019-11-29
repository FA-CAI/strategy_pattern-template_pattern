package com.shangxue.strategy_pattern.pay.payport;

import com.shangxue.strategy_pattern.pay.MsgResult;

/**
 * @program: gupao_pattern_mode
 * @description:
 * @author: zk
 * @create: 2019-04-05 16:23
 **/
public abstract class Payment {
    public abstract String getName();
    protected  abstract  double queryBalance(String uid);

    public MsgResult pay(String uid,double amount){
        if(queryBalance(uid)<amount){
            return new MsgResult(500,"支付失败","余额不足");
        }else{
            return new MsgResult(200,"支付成功","支付金额为："+amount);
        }
    }

}
