package dbprocessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    java.sql.Connection conn;
    java.sql.Statement stmt;
    java.sql.ResultSet rs;

    // CONSTRUCTOR !
    DBConnector() {
        connect();
    }

    // METHOD (1)
    void connect() {
        String dbpath = "jdbc:mysql://localhost:3307/hsm?serverTimezone=UTC&characterEncoding=UTF-8";
        String dbid = "root";
        String dbpw = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn=java.sql.DriverManager.getConnection(dbpath, dbid, dbpw);
            this.stmt = this.conn.createStatement();
            System.out.println(">> Connected to DB HSM");
        }
        catch(Exception e) {
            System.out.println("Connection Error : " + e);
        }
    }

    // METHOD (2)
    void update(String dbCommand) {
        try {
            this.stmt.executeUpdate(dbCommand);
            System.out.println(">> DB Updated.");
        } catch (Exception e) {
            System.out.println("Update Error : " + e);
        }
    }

    // METHOD (3)
    void select(String dbSelect) {
        try {
            this.rs = this.stmt.executeQuery(dbSelect);
        } catch(Exception e){
            System.out.println("Select Error : " + e);
        }
    }

    // METHOD (4)
    void close() {
        try {
            conn.close();
        } catch(Exception e) {
            System.out.println("Close Error : " + e);
        }
    }
}
