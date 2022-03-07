package com.multiplethread.multiplethread.controller;

import com.multiplethread.multiplethread.Model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @RequestMapping("save")
    public  String save(Person person){

        return "it is success";
    }
}
