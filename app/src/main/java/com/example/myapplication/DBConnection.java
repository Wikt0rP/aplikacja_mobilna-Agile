package com.example.myapplication;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection
{
    private static final String url = "jdbc:mysql://54.38.50.59:3306/www12890_agile";
    private static final String userName = "www12890_agile";
    private static final String password = "27ehRhH4zfpWVopQJWlG";


    private String sql;


    public DBConnection(String sql)
    {
        this.sql = sql;
    }

    public void dbConnect()
    {

    }
}
