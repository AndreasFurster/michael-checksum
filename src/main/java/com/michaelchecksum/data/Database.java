package com.michaelchecksum.data;
import java.sql.*;

public class Database {
    private Connection connection;

    public Database(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mariadb://185.182.57.80:3306/cornefs247_mchecksum", "cornefs247_mchecksum", "cck7C0Hi");

        } catch(SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
