package com.shangxue.template_pattern.jdbc.dao;

import com.shangxue.template_pattern.jdbc.RowMapper;
import com.shangxue.template_pattern.jdbc.JdbcTemplate;
import com.shangxue.template_pattern.jdbc.entity.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 *为了不用抽象类？为了不继承。为什么不想继承？为了解耦^_^  （本来是抽象类，springjdbc改成接口，这样把继承重写，改成了实现抽象类(匿名内部类)，而这个接口实现是跟MemberDao没有任何耦合的(与MemberDao解耦)，你用的时候再去实现(匿名实现)，这样与MemberDao是没有任何强耦合的 ）
 */
//我们spring中用不是继承，而是@Autowired注入JdbcTemplate对象  //这MemberDao也可以看作是对JdbcTemplate的静态代理
//public class MemberDao extends JdbcTemplate {
public class MemberDao {

    private JdbcTemplate jdbcTemplate=new JdbcTemplate(null);  //这里实战中是要配个数据源的吧dataSource

   /* public MemberDao(DataSource dataSource) {
        super(dataSource);
    }*/

    public List<?> selectAll(){
        String sql = "select * from t_member";  //1
                                               //new抽象类的实现类（就是匿名内部类[可它具有：匿名，且在类内部的类(内部类)两大特征，所以取名叫匿名内部类]）
       // return super.executeQuery(sql, new RowMapper<Member>() {
          return jdbcTemplate.executeQuery(sql, new RowMapper<Member>() {
                              //这就是模板（方法）模式中我们所需要实现的部分，而JdbcTemplate中的流程我们是不用改变的
                              @Override
                              public Member mapRow(ResultSet rs, int rowNum) throws Exception {
                                  Member member = new Member();    //2
                                  //字段过多，可用原型模式clone？
                                  member.setUsername(rs.getString("username"));
                                  member.setPassword(rs.getString("password"));
                                  member.setAge(rs.getInt("age"));
                                  member.setAddr(rs.getString("addr"));
                                  return member;
                              }
                      },null);
    }
}

/**
 * 把//1和//2处再次封装 （用到反射），就是自己手写一个ORM框架了（神彩最爱~），可以出去装比了
 */


/**
 *这里实战中springjdbc配数据源dataSource
 初始化DataSource
 DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
 driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
 driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/suveng?serverTimezone=Asia/Shanghai&characterEncoding=utf8");
 driverManagerDataSource.setUsername("root");
 driverManagerDataSource.setPassword("root");
 //初始化jdbcTemplate
 JdbcTemplate jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
 //JdbcTemplate中executeQuery()
 1 、获取连接
 Connection conn = dataSource.getConnection()；

 -----------------------------
 复习： jdbc中配数据源
 DBUtils dbUtils=new DBUtils();
 dbUtils.setUrl("jdbc:mysql://localhost:3306/suveng?serverTimezone=Asia/Shanghai&characterEncoding=utf8");
 dbUtils.setUsername("root");
 dbUtils.setPassword("root");
 //        1. 加载驱动
 Class.forName("com.mysql.cj.jdbc.Driver");
 //2. 获得数据库连接
 Connection conn = DriverManager.getConnection(dbUtils.getUrl(), dbUtils.getUsername(), dbUtils.getPassword());

（原文链接：原生jdbc示例 与spring JDBC 示例 https://blog.csdn.net/qq_37933685/article/details/81676680
 */

/*
面试题：
spring中的jdbc与传统的jdbc有什么区别?
Spring的JDBC是在原生态JDBC上面的一层简单的封装，提供了一些可用的接口，节省代码，不管连接(Connection)，不管事务、不管异常、不管关闭(con.close() ps.close )。只需要实现Spring提供的回调类。传统的JDBC执行过程如下：

a、创建连接

b、创建语句（SQL）

c、执行语句

d、返回结果集（设置到JavaBean中等处理）

e、关闭连接释放资源

Spring JDBC只需要实现b和d两个步骤，其他的都由Spring替你完成，而b和d都是通过实现指定的接口，然后将实现类传递给Spring就OK了。

（原文链接：Spring常见问题总结 https://blog.csdn.net/DERRANTCM/article/details/46616837
*/