package com.udemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 * User: hansh
 * Date: 2017/12/7
 * Time: 15:57
 */
@RestController
public class IndexController {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        // 设置收件人，寄件人
//        simpleMailMessage.setTo(new String[] {"563272313@qq.com"});
//        simpleMailMessage.setFrom("hshandzmq@163.com");
//        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
//        simpleMailMessage.setText("这里是一段简单文本。");
//        // 发送邮件
//        mailSender.send(simpleMailMessage);
//        System.out.println("邮件已发送");
        return "邮件已发送";
    }

}
