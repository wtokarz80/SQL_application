package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class PostgreSQLJDBC {

    protected Connection connection = null;
    protected Statement statement;

    public void connectToBD(){

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ccdb", "wt80", "dupa123");
            this.statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        System.out.println("Opened database successfully");
    }
}