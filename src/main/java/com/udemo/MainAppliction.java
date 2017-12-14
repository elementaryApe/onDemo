package com.udemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Desc: 主程序启动入口
 * User: hansh
 * Date: 2017/12/7
 * Time: 15:42
 */
@SpringBootApplication
@EnableScheduling //开启定时任务
@MapperScan("com.udemo.mapper")//项目中对应的mapper类的路径
public class MainAppliction {

    public static void main(String[] args) {
        SpringApplication.run(MainAppliction.class, args);
    }

//    @Bean
//    public StartCommandLineRunner startCommandLineRunner(){
//        return  new StartCommandLineRunner();
//    }


//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }



}
