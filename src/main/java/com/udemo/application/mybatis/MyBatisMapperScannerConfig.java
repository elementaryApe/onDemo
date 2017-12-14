package com.udemo.application.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Desc: MyBatis扫描接口
 * User: hansh
 * Date: 2017/12/12
 * Time: 17:07
 */
//@Configuration
//@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.udemo.mapper");
//        return mapperScannerConfigurer;
//    }
}
