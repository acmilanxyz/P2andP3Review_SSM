package org.spoto.service;

import org.spoto.model.Users;

import java.util.List;

public interface UserService {
    List<Users> getListUserService(Integer index);

    Integer getCountUserService();

    boolean saveUserService(Users users);

    boolean deleteUserService(Integer userId);
}
