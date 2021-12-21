package org.spoto.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.spoto.model.Users;

import java.util.List;

public interface UsersMapper {
    List<Users> getListUser(RowBounds rowBounds);

    Integer getCountUser();

    Integer updateUser(Users users);

    Integer deleteUser(@Param("id") Integer id);

    Integer addUser(Users users);
}
