package com.elcho.stockexchange.service.impl;

import com.elcho.stockexchange.dao.UserRepository;
import com.elcho.stockexchange.entity.UserEntity;
import com.elcho.stockexchange.model.User;
import com.elcho.stockexchange.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        UserEntity _user = new UserEntity();

        BeanUtils.copyProperties(user, _user);

        if(_user!=null)
            userRepository.save(_user);
    }

    @Override
    public User findById(Long id) {
        User _user = new User();

        BeanUtils.copyProperties(userRepository.findById(id),_user);

        return _user;
    }

    @Override
    public List<User> findAll() {
        List<User> _userList = new ArrayList<>();
        User _user = new User();
        for(UserEntity userEntity : userRepository.findAll()){
            BeanUtils.copyProperties(userEntity,_user);
            _userList.add(_user);
        }

        return _userList;
    }

    @Override
    public void update(User user) {
        UserEntity _user = new UserEntity();

        BeanUtils.copyProperties(user, _user);

        if(_user!=null)
            userRepository.saveAndFlush(_user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User queryByUserName(String userName) {
        User _user = new User();

        UserEntity userEntity = userRepository.findByUserName(userName);

        BeanUtils.copyProperties(userEntity, _user);

        return _user;
    }

    @Override
    public boolean isUserExists(String userName) {
        return userRepository.existsByUserName(userName);
    }
}
