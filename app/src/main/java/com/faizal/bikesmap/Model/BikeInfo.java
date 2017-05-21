package com.faizal.bikesmap.Model;

/**
 * Created by fpatel on 21/05/2017.
 */

public class BikeInfo {

    String number;
    String name;
    String address;

    public String getNumber() {
        return number;
    }

    public BikeInfo setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public BikeInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BikeInfo setAddress(String address) {
        this.address = address;
        return this;
    }
}
