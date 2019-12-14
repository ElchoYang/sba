package com.elcho.provideuserapi.dao;

import com.elcho.provideuserapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);

    Boolean existsByUserName(String userName);
}
