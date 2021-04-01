package lib.db;

import lib.EnvironmentConfig;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    private static Connection connection;
    private static String host=EnvironmentConfig.getServiceUrl("mysql");

    static  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+host+"/console_production?"+"user=root&password=root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getDbConnection(){
            return connection;
    }


}
