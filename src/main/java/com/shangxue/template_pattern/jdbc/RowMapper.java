package com.shangxue.template_pattern.jdbc;

import java.sql.ResultSet;

/**
 * ORM映射定制化的接口  springjdbc不用ProcessResult而是用牛逼的rowmapper
 * @param <T>
 */
public interface  RowMapper<T> {
    //这样模板的那个抽象方法就不用写在JdbcTemplate里了，而是写在这了
    T mapRow(ResultSet rs, int rowNum) throws Exception;
}

/**
 *
 * 接口中定义的属性必须是public static final的，而接口中定义的方法则必须是public abstract的，因此这些修饰符可以部分或全部省略（当你用interface关键字标识时，这些修饰符默认了，所以可以省掉[偷懒、方便、省力、默认、隐式，约定？]，好比局部内部类中引用方法内的局部变量默认final）
 *
 */