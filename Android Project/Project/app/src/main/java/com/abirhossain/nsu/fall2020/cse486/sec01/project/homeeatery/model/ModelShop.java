package com.abirhossain.nsu.fall2020.cse486.sec01.project.homeeatery.model;

public class ModelShop {
private String uid,email,name,shopName,phone,deliveryFee,country,state,city,address,latitude,longitude,timestamp,accountType,online,shopOpen,profileImage;

    public ModelShop() {
    }

    public ModelShop(String uid, String email, String name, String shopName, String phone, String deliveryFee, String country, String state, String city, String address,
                     String latitude, String longitude, String timestamp, String accountType, String online, String shopOpen, String profileImage) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.shopName = shopName;
        this.phone = phone;
        this.deliveryFee = deliveryFee;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.accountType = accountType;
        this.online = online;
        this.shopOpen = shopOpen;
        this.profileImage = profileImage;
    }
    

}
