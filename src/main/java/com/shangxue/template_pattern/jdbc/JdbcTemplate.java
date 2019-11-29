package com.shangxue.template_pattern.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板类
 */
//public abstract class JdbcTemplate {    //抽象类虽然不能被直接new（因为可能会有空气方法（抽象方法，无具体实现）？，不过，却特么能有构造方法，这不是太监的yj吗？）
public  class JdbcTemplate {

    private DataSource dataSource;  //写在外面是为了让它变成全局变量

    //有参构造器（无参构造器特意不要吗？）
    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }
                                            //接口
    public List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values){
        try {
            //1、获取连接
            Connection conn = this.getConnection();
            //2、创建语句集
            PreparedStatement pstm = this.createPrepareStatement(conn,sql);
            //3、执行语句集
            ResultSet rs = this.executeQuery(pstm,values);
            //4、处理结果集  ?? 解析语句集
            List<?> result = this.parseResultSet(rs,rowMapper);
            //5、关闭结果集
            this.closeResultSet(rs);
            //6、关闭语句集
            this.closeStatement(pstm);
            //7、关闭连接
            this.closeConnection(conn);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
//第二次改造，把内部的繁复的代码提出来，使得改得和springjdbc(用户体验)一样
/**设计模式通常来讲就是不断地封装（抽取？抽象？）、解耦、优化*/
//老师用private 这傻逼他在这用protected public，这样还不如不用呢，不懂原因目的而自作聪明反像傻逼(变得"为了用修饰符而用修饰符"，浮于表面，结果目的都跑偏了，没达到！与高手设想不符！)，我佛了
//7、关闭连接
    // protected void closeConnection(Connection conn) throws Exception {
     private void closeConnection(Connection conn) throws Exception {
         //通常是放到连接池中回收，而不是直接关
        conn.close();
    }

    //6、关闭语句集
    protected void closeStatement(PreparedStatement pstm) throws Exception {
        pstm.close();
    }

    //5、关闭结果集
    protected void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }

    //4、处理结果集、解析语句集
    protected List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<Object>();
        int rowNum = 1;
        while (rs.next()){   //游标，自动循环了
            result.add(rowMapper.mapRow(rs,rowNum ++));
        }
        return result;
    }

    //3、执行语句集
    protected ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws Exception {
        for (int i = 0; i < values.length; i++) {
            pstm.setObject(i,values[i]);
        }
        return pstm.executeQuery();
    }

    //2、创建语句集
    protected PreparedStatement createPrepareStatement(Connection conn, String sql) throws Exception {
        return conn.prepareStatement(sql);
    }

    //1、获取连接
    public Connection getConnection() throws Exception {
        return this.dataSource.getConnection();
    }
}
