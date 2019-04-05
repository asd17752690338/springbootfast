package com.technology.springboot.connDb.jpa.demo1;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAddress {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Long addressId;

    private String city;

    private  String town;

    /**乡村**/
    private String rural;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getRural() {
        return rural;
    }

    public void setRural(String rural) {
        this.rural = rural;
    }
}
