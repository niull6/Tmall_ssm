package com.app.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestTmall {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_ssm?useUnicode=true&characterEncoding=utf8",
                    "root","admin");
            Statement st = conn.createStatement();
            for(int i=1;i<=10;i++){
                String sqlFormat="insert into category values(null,'测试分类%d')";
                String sql =String.format(sqlFormat,i);
                st.execute(sql);
            }

            System.out.println("成功创建10条分类测试数据");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
