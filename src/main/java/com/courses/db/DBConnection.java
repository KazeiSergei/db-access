package com.courses.db;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    /*private static final String user = "sergei";
    private static final String password = "sergei";
    private static final String url = "jdbc:mysql://localhost:3306/STUDENT_DB";
    private static final String driver = "com.mysql.jdbc.Driver";*/
    private static final String user = "postgres";
    private static final String password = "sergei";
    private static final String url = "jdbc:postgresql://localhost:5432/STUDENT_DB_POSTGRESQL";
    private static final String driver = "org.postgresql.Driver";
    public Connection getConnection() throws DaoException {
        try {
            Class.forName(driver);
            System.out.println("Connecting to database...");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new DaoException("Could not open connection", e);
        }
    }
}
