package com.rizal.laundry;

public class Model {
    String id,uid,name,phone,address,city;

    public Model(String id ,String uid, String name, String phone, String address,String city ) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }

    public String getId(){
        return id;
    }
    public String getUId(){
        return uid;
    }
    public String getName(){
        return name;
    }

    public String getCity() { return city; };

    public String getPhone(){
        return phone;
    }

    public String getAddress(){
        return address;
    }
}
