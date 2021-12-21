package org.spoto.utlis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service("jdbcUtils")
public class JdbcUtils {
    @Value("${jdbc.mysql.url}")
    private String url;
    @Value("${jdbc.mysql.username}")
    private String username;
    @Value("${jdbc.mysql.password}")
    private String password;
    @Value("${jdbc.mysql.driverClassName}")
    private String driver;

    public Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("数据库连接工具类异常！");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭数据库资源
     * @param con Connection
     * @param ps PreparedStatement
     * @param rs ResultSet
     */
    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("rs数据库关闭工具类异常！");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println("ps数据库关闭工具类异常！");
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println("con数据库关闭工具类异常！");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 关闭数据库资源
     * @param con
     * @param ps
     */
    public static void close(Connection con, PreparedStatement ps) {
        close(con, ps, null);
    }

}
