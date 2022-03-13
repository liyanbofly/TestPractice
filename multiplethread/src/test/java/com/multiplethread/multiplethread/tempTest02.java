package com.multiplethread.multiplethread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class tempTest02 {

    @Test
    public  void  test01(){
        String str1="应收:12;实收:100;找零:88";
        String pattern2 = "找零:(\\d+)(.*)";
//        String pattern2 = "\\S+:(\\d+)(.*);";
//        String pattern2 = "\\S+:(\\d+)(.*)";
        // 创建 Pattern 对象
        Pattern r2 = Pattern.compile(pattern2);
        // 现在创建 matcher 对象
        Matcher m2 = r2.matcher(str1);
        if(m2.find()) {
            System.out.println("m.group(0) = " + m2.group(0));
            System.out.println("m.group(1) = " + m2.group(1));
        }  else {
            System.out.println("NO MATCH1");
        }

    }
}
