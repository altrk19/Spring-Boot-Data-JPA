package com.spring.crud.service;

import com.spring.crud.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getSingleUser(Long id);
    UserEntity createUser(UserEntity userEntity);
    void deleteUser(Long id);
}
