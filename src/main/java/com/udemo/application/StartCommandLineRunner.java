package com.udemo.application;

import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

/**
 * Desc: 上下文启动配置类
 * User: hansh
 * Date: 2017/12/7
 * Time: 16:33
 */
public class StartCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Let's look at the custom load component configuration by Spring Boot:");
        Arrays.sort(args);
        for (String beanName : args) {
            System.out.println(beanName);
        }
    }
}
