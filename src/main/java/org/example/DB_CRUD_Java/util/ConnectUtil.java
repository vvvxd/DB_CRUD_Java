package org.example.DB_CRUD_Java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectUtil {
    private static ConnectUtil connectUtil = null;
    private String DATABASE_URL = "jdbc:postgresql://localhost:5432/crud";
    //private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String USER = "postgres";
    private String PASSWORD = "123";

    public Connection getConnection() {
        return connection;
    }

    private Connection connection;

    private ConnectUtil() {
        try {
            //Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectUtil getInstance() {
        if (connectUtil == null) {
            return new ConnectUtil();
        }
        return connectUtil;
    }

    public static PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getInstance().getConnection().prepareStatement(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return preparedStatement;
    }
}
