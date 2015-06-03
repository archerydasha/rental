package com.rental.model;

import java.util.Date;

/**
 * Created by Dasha on 27.05.15.
 */
public class Property {
    private int id;
    private String name;
    private Person owner;
    private String country;
    private String state;
    private String region;
    private String city;
    private String address;
    private Date creationDate;
    private int dailyPrice;
    private int monthlyPrice;

    public Property(int id, String name, Person owner, String country, String state, String region, String city, String address, Date creationDate, int dailyPrice, int monthlyPrice) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.country = country;
        this.state = state;
        this.region = region;
        this.city = city;
        this.address = address;
        this.creationDate = creationDate;
        this.dailyPrice = dailyPrice;
        this.monthlyPrice = monthlyPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public int getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(int monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }
}
