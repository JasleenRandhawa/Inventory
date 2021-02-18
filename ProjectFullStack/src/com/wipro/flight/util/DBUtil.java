package com.wipro.flight.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil 
{
    private DBUtil()
    {
        throw new IllegalStateException("Utility class");
    }

    private static Connection con = null;

    public static Connection getDBConnection()
    {
        //establish a connection
        //return java.sql.Connection reference
        
        try
        {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JASLEEN", "root");
            return con;
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return con;
        

    }
    
}
