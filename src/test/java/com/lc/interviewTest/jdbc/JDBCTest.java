package com.lc.interviewTest.jdbc;

import org.junit.Test;

import java.sql.*;

public class JDBCTest {

    @Test
    public void connectionJDBC() throws ClassNotFoundException, SQLException {

        // 1. 注册驱动
        // 使用java.sql.DriverManager类的静态方法registerDriver(Driver driver)
        // Driver是一个接口,参数传递:MySQL驱动程序的实现类
        // DriverManager.registerDriver(new Driver());
        // 查看驱动类源码,注册两次驱动,浪费资源
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获得连接
        // uri:数据库地址 jdbc:mysql://连接主机ip:端口号//数据库名字
        String url = "jdbc:mysql://114.116.154.251:3306/choujiang";
        // static Connection getConnection(String url, String user, String password)
        // 返回值是java.sql.Connection接口的实现类,在MySQL驱动程序中
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        System.out.println(conn);// com.mysql.jdbc.JDBC4Connection@10d1f30
        // 3. 获得语句执行平台,通过数据库连接对象,获取到SQL语句的执行者对象
        //conn对象,调用方法 Statement createStatement() 获取Statement对象,将SQL语句发送到数据库
        //返回的是Statement接口的实现类对象,在MySQL驱动程序中
        Statement stat = conn.createStatement();
        System.out.println(stat);//com.mysql.jdbc.StatementImpl@137bc9
        // 4. 执行sql语句
        //通过执行者对象调用方法执行SQL语句,获取结果
        //int executeUpdate(String sql)  执行数据库中的SQL语句,仅限于insert,update,delete
        //返回值int,操作成功数据库的行数
//        int row = stat.executeUpdate("select * from t1");
       ResultSet rs = stat.executeQuery("select * from t1");
        while (rs.next()) {
            // 获取每列的数据,使用的是ResultSet接口的方法getXXX
            String xname = rs.getString("xname");
            System.out.println(xname);

        }
        // 5. 释放资源
        stat.close();
        conn.close();

    }






}
