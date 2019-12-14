package com.elcho.provideuserapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userType, that.userType);
    }

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
