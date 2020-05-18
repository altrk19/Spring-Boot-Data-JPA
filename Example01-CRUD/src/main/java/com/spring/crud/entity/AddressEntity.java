package com.spring.crud.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "user_address")
public class AddressEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_user_address", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_address", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, name = "addressLine")
    private String addressLine;

    @Column(length = 50, name = "city")
    private String city;

    @Column(length = 50, name = "country")
    private String country;

    @Enumerated
    private AddressType addressType;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public AddressEntity() {
    }

    public AddressEntity(Long id, String addressLine, String city, String country,
                         AddressType addressType, boolean active, UserEntity user) {
        this.id = id;
        this.addressLine = addressLine;
        this.city = city;
        this.country = country;
        this.addressType = addressType;
        this.active = active;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
