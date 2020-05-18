package com.spring.crud.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

public class UserResource {


    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private List<AddressResource> address;

    public UserResource() {
    }

    public UserResource(Long id, @NotNull String name,
                        @NotNull String surname, List<AddressResource> address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<AddressResource> getAddress() {
        return address;
    }

    public void setAddress(List<AddressResource> address) {
        this.address = address;
    }
}
