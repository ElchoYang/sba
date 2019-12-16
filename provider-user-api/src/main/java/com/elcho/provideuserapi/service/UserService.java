package com.elcho.provideuserapi.service;

import com.elcho.provideuserapi.model.User;

import java.util.List;

public interface UserService {

    String getUser();

    /*********
     * 插入一个帐户对象到数据库中
     * @param user
     */
    void save(User user);

    /***************
     * 根据帐户ID来查询帐户信息
     * @param id
     * @return
     */
    User findById(Long id);

    /****************
     * 查询所有帐户信息
     * @return
     */
    List<User> findAll();

    /*****************
     * 更新帐户
     * @param user
     */
    void update(User user);

    /***************
     * 删除帐户
     * @param id
     */
    void delete(Long id);


    /***********
     * 根据用户名来查询用户信息
     * @param userName
     * @return
     */
    User queryByUserName(String userName);

    /****************
     *
     * @param userName
     * @return
     */
    boolean isUserExists(String userName);

    /****************
     *
     * @param userName
     * @return
     */
    boolean isLoginUserExists(String userName, String password);
}
