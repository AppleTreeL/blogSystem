package com.qing.tools;

import com.bit.blog.exception.SystemException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Classname DBTools
 * @Description 获取数据库资源
 * @Date 2019/8/23 11:54
 * @Created by AppleTree
 */
public class DBTools {

    static private final String URL = "jdbc:mysql://localhost:3306/blogdemo?useUnicode=true&characterEncoding=utf8";
    static private final String USER_NAME = "root";
    static private final String PASSWORD = "123";
    static private volatile DataSource DATASOURCE;

    private DBTools() {

    }

    public static DataSource getDATASOURCE() {

        if(DATASOURCE == null) {
            synchronized (DBTools.class) {
                if(DATASOURCE == null) {
                    DATASOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATASOURCE).setURL(URL);
                    ((MysqlDataSource) DATASOURCE).setUser(USER_NAME);
                    ((MysqlDataSource) DATASOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATASOURCE;
    }

    public static Connection getConnection() {
        try {
            return getDATASOURCE().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection)
    {
        try {
        /*
        编译时：写代码时会报错
        运行时异常：运行时报错的异常
         */
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e)  {
            e.printStackTrace();
            throw new SystemException("系统异常");
        }
    }
}
