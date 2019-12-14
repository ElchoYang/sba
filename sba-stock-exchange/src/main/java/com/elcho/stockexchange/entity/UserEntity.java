package com.elcho.stockexchange.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "TBL_USER")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String userType;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column
    private boolean confirmed;

    public UserEntity() {

    }

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
