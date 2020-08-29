package com.spring.crud.repo;

import com.spring.crud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findBySurname(String surname);

    List<UserEntity> findByNameAndSurname(String name, String surname);

    //Named Query
    List<UserEntity> getUserBySurnameNamedQuery(String surname);

    //Named Native Query
    List<UserEntity> getUserBySurnameNamedNativeQuery(String surname);

    //JPQL Query
    @Query(value = "SELECT u FROM UserEntity u WHERE u.surname = :surname")
    List<UserEntity> getUserBySurnameJpqlQuery(String surname);

    //Native Sql Query
    @Query(value = "SELECT * FROM user_table WHERE surname = :surname", nativeQuery = true)
    List<UserEntity> getUserBySurnameNativeSqlQuery(String surname);

    //Async Query
    @Async
    CompletableFuture<UserEntity> findByName(String name);
}
