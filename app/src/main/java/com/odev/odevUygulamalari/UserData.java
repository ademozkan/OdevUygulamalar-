package com.odev.odevUygulamalari;

import com.odevUygulamalari.R;

import java.util.ArrayList;

public class UserData {
    public ArrayList<Person> getUserData(){

        ArrayList<Person> person_list = new ArrayList<Person>();

        person_list.add(new Person("admin", "123", R.mipmap.a1));
        person_list.add(new Person("hakan", "456", R.mipmap.a2));
        person_list.add(new Person("mustafa", "758", R.mipmap.a3));
        person_list.add(new Person("servet", "910", R.mipmap.a4));
        person_list.add(new Person("kerim", "147", R.mipmap.a5));
        person_list.add(new Person("ceylin", "258", R.mipmap.a6));
        person_list.add(new Person("hasan", "369", R.mipmap.a7));
        person_list.add(new Person("merve", "321", R.mipmap.a8));
        person_list.add(new Person("adem", "654", R.mipmap.a9));
        person_list.add(new Person("volkan", "987", R.mipmap.a10));
        person_list.add(new Person("fatma", "159", R.mipmap.a11));
        person_list.add(new Person("zehra", "357", R.mipmap.a12));
        person_list.add(new Person("aykut", "148", R.mipmap.a13));
        person_list.add(new Person("yasemin", "259", R.mipmap.a14));
        person_list.add(new Person("selda", "368", R.mipmap.a15));
        person_list.add(new Person("duygu", "742", R.mipmap.a16));
        person_list.add(new Person("nihal", "853", R.mipmap.a17));
        person_list.add(new Person("mert", "961", R.mipmap.a18));
        person_list.add(new Person("nurcan", "568", R.mipmap.a19));
        person_list.add(new Person("zafer", "895", R.mipmap.a20));
        return person_list;
    }
}
