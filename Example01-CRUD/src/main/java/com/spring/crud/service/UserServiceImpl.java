package com.spring.crud.service;

import com.spring.crud.entity.AddressEntity;
import com.spring.crud.entity.UserEntity;
import com.spring.crud.repo.AddressRepository;
import com.spring.crud.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getSingleUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public UserEntity createUser(UserEntity userEntity) {
        UserEntity createdUserEntity = userRepository.save(userEntity);
        List<AddressEntity> addressEntityList = userEntity.getAddressList();
        for (AddressEntity addressEntity : addressEntityList) {
            addressRepository.save(addressEntity);
        }
        return createdUserEntity;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> getUsersBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }

    @Override
    public List<UserEntity> getUsersByNameAndSurname(String name, String surname) {
        return userRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public List<UserEntity> getUserBySurnameNamedQuery(String surname){
        return userRepository.getUserBySurnameNamedQuery(surname);
    }

    @Override
    public List<UserEntity> getUserBySurnameNamedNativeQuery(String surname){
        return userRepository.getUserBySurnameNamedNativeQuery(surname);
    }

    @Override
    public List<UserEntity> getUserBySurnameJpqlQuery(String surname){
        return userRepository.getUserBySurnameJpqlQuery(surname);
    }

    @Override
    public List<UserEntity> getUserBySurnameNativeSqlQuery(String surname){
        return userRepository.getUserBySurnameNativeSqlQuery(surname);
    }

    @Override
    public CompletableFuture<UserEntity> findByNameAsync(String name){
        return userRepository.findByName(name);
    }
}
