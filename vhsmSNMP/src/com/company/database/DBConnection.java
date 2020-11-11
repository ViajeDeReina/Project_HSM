package com.company.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String db_name = "hsm";
    private String url = "jdbc:mysql://localhost:3307/" + db_name;
    private Connection conn = null;

    public DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){

        if(conn == null) {
            try {
                conn = DriverManager.getConnection(url, "root", "1234");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn;
    }

}
