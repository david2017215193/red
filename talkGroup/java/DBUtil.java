import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static BasicDataSource dataSource = null;

    private static void newDataSource() {
        try {
            dataSource = new BasicDataSource();

            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://47.106.170.202/client?serverTimezone=PRC");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setInitialSize(10);
            dataSource.setMaxIdle(20);
            dataSource.setMinIdle(5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            newDataSource();
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void release(Connection conn, Statement stmt, ResultSet rs) {
        // 关闭资源
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        rs = null;

        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stmt = null;

        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        conn = null;
    }
}
