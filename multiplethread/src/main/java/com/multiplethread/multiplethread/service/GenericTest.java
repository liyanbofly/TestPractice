package com.multiplethread.multiplethread.service;

public class GenericTest<G> {

    public String getModel(G p1){

        try {
            G g= (G) p1.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return "it is g";

    }

    public <T extends Person> T getNewT(T ss){
        T p= null;
        try {
            p = (T) ss.getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        p.setAge(333);
        return p;
    }
}
