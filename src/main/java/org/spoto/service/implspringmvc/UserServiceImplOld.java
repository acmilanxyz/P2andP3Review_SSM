package org.spoto.service.implspringmvc;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.spoto.dao.UsersMapper;
import org.spoto.model.Users;
import org.spoto.service.UserService;
import org.spoto.utlis.MyBatisUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SSM整合之前的Service代码
 */

/*
@Service("userService")
public class UserServiceImplOld implements UserService {
    //定义分页后每页显示条目数
    private static int pageSize = 4;

    //Service类更改2：摒弃MyBatisUtils，定义一个全局变量mapper，并加上@Resource,Spring会自动扫描UsersMapper接口的实现类并new
    @Resource
    private UsersMapper mapper;

    @Override
    public List<Users> getListUserService(Integer index) {
        //分页：跳过几行数据后开始检索
        int offset = (index - 1) * pageSize;

        //分页：读取几行数据
        int rows = pageSize;

        //MyBatis实现分页
        RowBounds rowBounds = new RowBounds(offset, rows);

        SqlSession sqlSession = MyBatisUtils.getSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        List<Users> usersList = mapper.getListUser(rowBounds);
        sqlSession.close();

        return usersList;
    }

    @Override
    public Integer getCountUserService() {
        //计算查询结果可获取的最大页数
        SqlSession sqlSession = MyBatisUtils.getSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        Integer count = mapper.getCountUser();

        sqlSession.close();
        return (count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1);
    }

    @Override
    public boolean saveUserService(Users users) {
        SqlSession sqlSession = MyBatisUtils.getSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);

        Integer save = 0;
        if (users.getId() > 0) {
            save = mapper.updateUser(users);
        } else {
            save = mapper.AddUser(users);
        }
        if (save > 0) {
            sqlSession.commit();
            sqlSession.close();
            return true;
        } else {
            sqlSession.rollback();
            sqlSession.close();
            return false;
        }
    }

    @Override
    public boolean deleteUserService(Integer userId) {
        SqlSession sqlSession = MyBatisUtils.getSession();
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        Integer delete = mapper.deleteUser(userId);
        if (delete > 0) {
            sqlSession.commit();
            sqlSession.close();
            return true;
        } else {
            sqlSession.rollback();
            sqlSession.close();
            return false;
        }
    }
}
*/