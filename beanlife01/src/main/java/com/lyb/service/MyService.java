package com.lyb.service;

import com.lyb.dao.MyDao;

public class MyService {

    private MyDao dao;

    public void setDao(MyDao dao) {
        this.dao = dao;
    }
    public String work(String s) {
        String rs="";
        for (int i = 0; i < 20; ++i) {
            rs +=dao.find(s);
        }
        return rs;
    }
}
