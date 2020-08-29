package com.spring.crud.entity;

import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "user_table")
@DynamicUpdate
@NamedQueries(value = {
        @NamedQuery(name = "UserEntity.getUserBySurnameNamedQuery", query = "SELECT u FROM UserEntity u WHERE u.surname = :surname"),
})
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "UserEntity.getUserBySurnameNamedNativeQuery", query = "SELECT * FROM user_table WHERE surname = :surname",resultClass=UserEntity.class),
})
public class UserEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_user", allocationSize = 1)
    @GeneratedValue(generator = "seq_user", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "name")
    private String name;

    @Column(length = 100, name = "surname")
    private String surname;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<AddressEntity> addressList;

    public UserEntity() {
    }

    public UserEntity(Long id, String name, String surname,
                      List<AddressEntity> addressList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.addressList = addressList;
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

    public List<AddressEntity> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressEntity> addressList) {
        this.addressList = addressList;
    }
}