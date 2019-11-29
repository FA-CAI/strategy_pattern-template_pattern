package com.shangxue.template_pattern.jdbc;

import com.shangxue.template_pattern.jdbc.dao.MemberDao;

import java.util.List;

//模板方法模式
public class Test {


    public static void main(String[] args) {

        //spring中会用注入@Autowired 就好了
     //   MemberDao memberDao=new MemberDao(null);   //dataSource写null ，所以继续改造继续封装
        MemberDao memberDao = new MemberDao();
        List<?> result = memberDao.selectAll();
        System.out.println(result);


    }
}



//比如spring的jdbcTemplate

/**
 *策略模式：提供几种算法（策略）供你选择  【他好像是客户端挖空，也就是挖在“最后”】
 *
 * 模板模式（一个策略，一个缺空挖了个坑的策略？）：流程中挖一个空（抽象类的抽象方法）（作文模板？“活学活用”？）待你填，就出结果了   【他是挖在“当中”】。提高可重用复用性？
 *你的参与只能改变执行的结果，但并不能改变执行的流程
 */

//用户下单，创建订单，完成订单，支付成功，这一套算是模板模式，但实际开发中不可能单独使用哪一个模式，都是结合者用，比如这个订单流程一般可以用模板模式+工厂模式……
//  （一般复杂业务才会用设计模式？？？）
