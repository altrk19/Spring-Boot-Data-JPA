package com.spring.crud.dto;

import com.spring.crud.entity.AddressType;

import javax.validation.constraints.NotNull;

public class AddressResource {
    @NotNull

    private String addressLine;
    @NotNull

    private String city;
    @NotNull

    private String country;
    @NotNull

    private AddressType addressType;
    private boolean active;

    public AddressResource() {
    }

    public AddressResource(String addressLine, String city, String country, AddressType addressType, boolean active) {
        this.addressLine = addressLine;
        this.city = city;
        this.country = country;
        this.addressType = addressType;
        this.active = active;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
