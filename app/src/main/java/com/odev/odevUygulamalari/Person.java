package com.odev.odevUygulamalari;

import java.util.List;


public class Person {

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    private String name;
    private String password;
    private int photo_id;

    public String getName()
    {
        return this.name;
    }
    public String getPassword()
    {
        return this.password;
    }
    public int getPhoto_id()
    {
        return this.photo_id;
    }

    public Person(String name,String password,int photo_id)
    {
        this.name = name;
        this.password = password;
        this.photo_id = photo_id;
    }
    public Person()
    {

    }


}
