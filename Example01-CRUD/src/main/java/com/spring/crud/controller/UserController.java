package com.spring.crud.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.spring.crud.dto.UserListResource;
import com.spring.crud.dto.UserResource;
import com.spring.crud.entity.UserEntity;
import com.spring.crud.service.UserService;
import com.spring.crud.utils.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<UserListResource> getAllUsers() {
        log.debug("Received request to get all users");
        List<UserEntity> userEntities = userService.getAllUsers();
        UserListResource userListResource = UserConverter.toUserListResource(userEntities);
        log.debug("Request completed for get all users");
        return ResponseEntity.status(HttpStatus.OK).body(userListResource);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResource> getSingleUser(@PathVariable("id") @NotNull Long id) throws Exception {
        log.debug("Received request to get single user");
        UserResource userResource;
        Optional<UserEntity> userEntityOpt = userService.getSingleUser(id);

        if (userEntityOpt.isPresent()) {
            userResource = UserConverter.toUserResource(userEntityOpt.get());
        } else {
            log.debug("user not found");
            throw new Exception("User not found");
        }
        log.debug("Request completed for get single user");
        return ResponseEntity.status(HttpStatus.OK).body(userResource);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResource> createUser(@Valid @RequestBody UserResource userResource) {
        log.debug("Received request to create a user");
        UserEntity userEntity = UserConverter.toUserEntity(userResource);
        UserEntity createdUser = userService.createUser(userEntity);
        UserResource userResourceCreated = UserConverter.toUserResource(createdUser);
        log.debug("Request completed for create a user");
        return ResponseEntity.status(HttpStatus.CREATED).body(userResourceCreated);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> createUser(@PathVariable("id") @NotNull Long id) {
        log.debug("Received request to delete a user");
        userService.deleteUser(id);
        log.debug("Request completed for delete a user");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/surname/{surname}")
    public ResponseEntity<UserListResource> getUsersBySurname(@PathVariable("surname") @NotNull String surname) {
        log.debug("Received request to get users by surname");
        List<UserEntity> userEntities = userService.getUsersBySurname(surname);
        UserListResource userListResource = UserConverter.toUserListResource(userEntities);
        log.debug("Request completed to get users by surname");
        return ResponseEntity.status(HttpStatus.OK).body(userListResource);
    }

    @GetMapping("/users/name/{name}/surname/{surname}")
    public ResponseEntity<UserListResource> getUsersByNameAndSurname(@PathVariable("name") @NotNull String name,
                                                                     @PathVariable("surname") @NotNull String surname) {
        log.debug("Received request to get users by name and surname");
        List<UserEntity> userEntities = userService.getUsersByNameAndSurname(name, surname);
        UserListResource userListResource = UserConverter.toUserListResource(userEntities);
        log.debug("Request completed to get users by name and surname");
        return ResponseEntity.status(HttpStatus.OK).body(userListResource);
    }

    @GetMapping("/users/surname/{surname}/named_query")
    public ResponseEntity<UserListResource> getUsersBySurnameNamedQuery(@PathVariable("surname") @NotNull String surname) {
        log.debug("Received request to get users by surname named query");
        List<UserEntity> userEntities = userService.getUserBySurnameNamedQuery(surname);
        UserListResource userListResource = UserConverter.toUserListResource(userEntities);
        log.debug("Request completed to get users by surname named query");
        return ResponseEntity.status(HttpStatus.OK).body(userListResource);
    }

    @GetMapping("/users/surname/{surname}/named_native_query")
    public ResponseEntity<UserListResource> getUsersBySurnameNamedNativeQuery(@PathVariable("surname") @NotNull String surname) {
        log.debug("Received request to get users by surname named native query");
        List<UserEntity> userEntities = userService.getUserBySurnameNamedNativeQuery(surname);
        UserListResource userListResource = UserConverter.toUserListResource(userEntities);
        log.debug("Request completed to get users by surname named native query");
        return ResponseEntity.status(HttpStatus.OK).body(userListResource);
    }

    @GetMapping("/users/surname/{surname}/jpql_query")
    public ResponseEntity<UserListResource> getUsersBySurnameJpqlQuery(@PathVariable("surname") @NotNull String surname) {
        log.debug("Received request to get users by surname named jpql query");
        List<UserEntity> userEntities = userService.getUserBySurnameJpqlQuery(surname);
        UserListResource userListResource = UserConverter.toUserListResource(userEntities);
        log.debug("Request completed to get users by surname named jpql query");
        return ResponseEntity.status(HttpStatus.OK).body(userListResource);
    }

    @GetMapping("/users/surname/{surname}/native_sql_query")
    public ResponseEntity<UserListResource> getUsersBySurnameNativeSqlQuery(@PathVariable("surname") @NotNull String surname) {
        log.debug("Received request to get users by surname named native sql query");
        List<UserEntity> userEntities = userService.getUserBySurnameNativeSqlQuery(surname);
        UserListResource userListResource = UserConverter.toUserListResource(userEntities);
        log.debug("Request completed to get users by surname named native sql query");
        return ResponseEntity.status(HttpStatus.OK).body(userListResource);
    }

    @GetMapping("/user/{name}/async")
    public ResponseEntity<UserResource> getUsersByIdAsync(@PathVariable("name") @NotNull String name)
            throws InterruptedException, ExecutionException, TimeoutException {
        log.debug("Received request to get users by name async");
        CompletableFuture<UserEntity> completableFuture = userService.findByNameAsync(name);
        UserEntity user = completableFuture.get(20, TimeUnit.SECONDS);
        UserResource userListResource = UserConverter.toUserResource(user);
        log.debug("Request completed to get users by name async");
        return ResponseEntity.status(HttpStatus.OK).body(userListResource);
    }

}