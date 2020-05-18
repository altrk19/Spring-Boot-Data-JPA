package com.spring.crud.service;

import com.spring.crud.entity.AddressEntity;
import com.spring.crud.entity.UserEntity;
import com.spring.crud.repo.AddressRepository;
import com.spring.crud.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        for(AddressEntity addressEntity : addressEntityList){
            addressRepository.save(addressEntity);
        }
        return createdUserEntity;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
