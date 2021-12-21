package org.spoto.dao.impl;

import org.spoto.dao.UserDao;
import org.spoto.model.Users;
import org.spoto.utlis.JdbcUtils;
import org.spoto.utlis.SpringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<Users> getListUserDao(Integer offset, Integer rows) {
        List<Users> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ((JdbcUtils) SpringUtils.getBean("applicationContext_Before_Spring.xml", "jdbcUtils")).getConnection();

            ps = con.prepareStatement("select * from users limit ?,?");
            ps.setInt(1, offset);
            ps.setInt(2, rows);

            rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setSex(rs.getString("sex"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getInt("status"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("用户信息查询异常！");
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, ps, rs);
        }
        return list;
    }

    @Override
    public Integer getCountUserDao() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ((JdbcUtils) SpringUtils.getBean("applicationContext_Before_Spring.xml", "jdbcUtils")).getConnection();
            ps = con.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("用户信息查询异常！");
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, ps, rs);
        }
        return 0;
    }

    @Override
    public boolean saveUserDao(Users user) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ((JdbcUtils) SpringUtils.getBean("applicationContext_Before_Spring.xml", "jdbcUtils")).getConnection();

            if (user.getId() > 0) {
                ps = con.prepareStatement("update users set username = ?, password = ?, nickname = ?, email = ? where id = ?");
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getNickname());
                ps.setString(4, user.getEmail());
                ps.setInt(5, user.getId());
            } else {
                ps = con.prepareStatement("insert into users  (username, password, nickname, email) values (?,?,?,?)");
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getNickname());
                ps.setString(4, user.getEmail());
            }
            int save = ps.executeUpdate();
            if (save == 1) {
                System.out.println("用户状态更新成功！");
                return true;
            } else {
                System.out.println("用户状态更新失败！");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("用户新增或更新异常！");
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, ps);
        }
        return false;
    }

    @Override
    public boolean deleteUserDao(Integer userId) {
        Connection con = ((JdbcUtils) SpringUtils.getBean("applicationContext_Before_Spring.xml", "jdbcUtils")).getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("delete from users where id = ?");
            ps.setInt(1, userId);
            int del = ps.executeUpdate();
            if (del == 1) {
                System.out.println("删除成功！");
                return true;
            } else {
                System.out.println("删除失败！");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("数据库执行删除异常！");
            e.printStackTrace();
            return false;
        } finally {
            JdbcUtils.close(con, ps);
        }
    }
}
