package com.bp.learnblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author DH
 */
@EnableScheduling
@SpringBootApplication
public class LearnBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnBlogApplication.class, args);
    }

}
