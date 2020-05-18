package com.spring.crud.utils;

import com.spring.crud.dto.AddressResource;
import com.spring.crud.dto.UserListResource;
import com.spring.crud.dto.UserResource;
import com.spring.crud.entity.AddressEntity;
import com.spring.crud.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    private UserConverter() {
    }

    public static UserListResource toUserListResource(List<UserEntity> userEntityList) {
        UserListResource userListResource = new UserListResource(new ArrayList<>());

        if (!userEntityList.isEmpty()) {
            for (UserEntity userEntity : userEntityList) {
                UserResource userResource = new UserResource();
                userResource.setId(userEntity.getId());
                userResource.setName(userEntity.getName());
                userResource.setSurname(userEntity.getSurname());

                List<AddressResource> address = toAddressResourceList(userEntity.getAddressList());
                userResource.setAddress(address);
                userListResource.getUsers().add(userResource);
            }
        }
        return userListResource;
    }

    public static UserResource toUserResource(UserEntity createdUser) {
        UserResource userResource = new UserResource();
        userResource.setId(createdUser.getId());
        userResource.setName(createdUser.getName());
        userResource.setSurname(createdUser.getSurname());

        List<AddressResource> address = toAddressResourceList(createdUser.getAddressList());
        userResource.setAddress(address);

        return userResource;
    }

    public static UserEntity toUserEntity(UserResource userResource) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userResource.getName());
        userEntity.setSurname(userResource.getSurname());
        List<AddressEntity> address = toAddressEntityList(userResource.getAddress());
        userEntity.setAddressList(address);
        return userEntity;
    }

    public static List<AddressEntity> toAddressEntityList(List<AddressResource> addressResources) {
        List<AddressEntity> addressEntities = new ArrayList<>();
        if (!addressResources.isEmpty()) {
            for (AddressResource addressResource : addressResources) {
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setAddressLine(addressResource.getAddressLine());
                addressEntity.setCity(addressResource.getCity());
                addressEntity.setCountry(addressResource.getCountry());
                addressEntity.setActive(addressResource.isActive());
                addressEntity.setAddressType(addressResource.getAddressType());

                addressEntities.add(addressEntity);
            }
        }
        return addressEntities;
    }

    public static List<AddressResource> toAddressResourceList(List<AddressEntity> addressEntities) {
        List<AddressResource> addressResources = new ArrayList<>();
        if (!addressEntities.isEmpty()) {
            for (AddressEntity addressEntity : addressEntities) {
                AddressResource addressResource = new AddressResource();
                addressResource.setAddressLine(addressEntity.getAddressLine());
                addressResource.setCity(addressEntity.getCity());
                addressResource.setCountry(addressEntity.getCountry());
                addressResource.setActive(addressEntity.isActive());
                addressResource.setAddressType(addressEntity.getAddressType());

                addressResources.add(addressResource);
            }
        }
        return addressResources;
    }
}
