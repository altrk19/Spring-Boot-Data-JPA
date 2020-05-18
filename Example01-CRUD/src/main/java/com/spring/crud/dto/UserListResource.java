package com.spring.crud.dto;

import java.util.List;

public class UserListResource {
    private List<UserResource> users;

    public UserListResource() {
    }

    public UserListResource(List<UserResource> users) {
        this.users = users;
    }

    public List<UserResource> getUsers() {
        return users;
    }

    public void setUsers(List<UserResource> users) {
        this.users = users;
    }
}
