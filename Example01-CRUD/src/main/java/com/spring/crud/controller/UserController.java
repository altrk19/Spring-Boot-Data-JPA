package com.spring.crud.controller;

import java.util.List;
import java.util.Optional;
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


}