package com.shangxue.strategy_pattern.pay;

/**
 * 模拟后端返回前端的数据json?
 **/
public class MsgResult {

    private int code;
    private String msg;
    private Object data;

    public MsgResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "支付状态：[" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ']';
    }
}
