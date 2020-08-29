package com.spring.crud.service;

import com.spring.crud.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getSingleUser(Long id);
    UserEntity createUser(UserEntity userEntity);
    void deleteUser(Long id);

    List<UserEntity> getUsersBySurname(String surname);
    List<UserEntity> getUsersByNameAndSurname(String name, String surname);

    //Named Query
    List<UserEntity> getUserBySurnameNamedQuery(String surname);

    //Named Native Query
    List<UserEntity> getUserBySurnameNamedNativeQuery(String surname);

    //JPQL Query
    List<UserEntity> getUserBySurnameJpqlQuery(String surname);

    //Native Sql Query
    List<UserEntity> getUserBySurnameNativeSqlQuery(String surname);

    //Async Query
    CompletableFuture<UserEntity> findByNameAsync(String name);
}
