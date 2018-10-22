package com.michaelchecksum.data;
import java.sql.*;

public class Database {
    private Connection connection;

    public Database(){
        try {
            this.connection = DriverManager.getConnection("jdbc:mariadb://185.182.56.176:3306/andredg46_proftaak21", "andredg46_proftaak21", "NgOuhEjW");

        } catch(SQLException  ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
