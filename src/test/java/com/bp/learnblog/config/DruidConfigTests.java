package com.bp.learnblog.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public class DruidConfigTests {

    @Resource
    DataSource dataSource;

    @Test
    public void druidDataSourceTest() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println("=======================");

        System.out.println(dataSource.getConnection());
        System.out.println("=======================");

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("Druid 最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("=======================");

        System.out.println("Druid 初始连接数：" + druidDataSource.getInitialSize());
        System.out.println("=======================");
    }
}
