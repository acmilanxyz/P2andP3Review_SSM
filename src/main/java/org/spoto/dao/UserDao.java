package org.spoto.dao;

import org.spoto.model.Users;

import java.util.List;

public interface UserDao {
    List<Users> getListUserDao(Integer offset,Integer rows);

    Integer getCountUserDao();

    boolean saveUserDao(Users user);

    boolean deleteUserDao(Integer userId);
}
