package com.elcho.provideuserapi.model;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String userName;

    private String password;

    private String userType;

    private String email;

    private String mobile;

    private boolean confirmed;
}
