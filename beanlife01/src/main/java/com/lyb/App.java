package com.lyb;

import com.lyb.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //基础，启动时不出现任何的错误，说明配置基本成功
        MyService service=context.getBean(MyService.class);
        String rs=service.work("hello yao");
        System.out.println(rs);
    }
}
