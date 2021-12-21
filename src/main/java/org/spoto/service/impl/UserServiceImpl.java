package org.spoto.service.impl;

import org.spoto.dao.UserDao;
import org.spoto.dao.impl.UserDaoImpl;
import org.spoto.model.Users;
import org.spoto.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    //定义分页后每页显示条目数
    private static int pageSize = 4;

    @Override
    public List<Users> getListUserService(Integer index) {
        //分页：跳过几行数据后开始检索
        int offset = (index - 1) * pageSize;

        //分页：读取几行数据
        int rows = pageSize;

        UserDao ud = new UserDaoImpl();
        return ud.getListUserDao(offset, rows);
    }

    @Override
    public Integer getCountUserService() {
        //计算查询结果可获取的最大页数
        UserDao ud = new UserDaoImpl();
        Integer count = ud.getCountUserDao();

        return (count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1);
    }

    @Override
    public boolean saveUserService(Users users) {
        UserDao ud = new UserDaoImpl();
        return ud.saveUserDao(users);
    }

    @Override
    public boolean deleteUserService(Integer userId) {
        UserDao ud = new UserDaoImpl();
        return ud.deleteUserDao(userId);
    }
}
