package org.zerock.persistence;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String AWS = "";
    private static final String URL = "jdbc:mysql://" + AWS + ":3306/mydb?useSSL=false";
    private static final String USER = "root";
    private static final String PW = "admin";

    @Test
    public void testConnection() throws Exception {
        Class.forName(DRIVER);
        try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
            System.out.println(con);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}