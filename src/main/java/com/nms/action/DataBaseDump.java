package com.nms.action;

import java.sql.*;

public class DataBaseDump {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";


    static final String USER = "sa";
    static final String PASS = "";

    static Connection connection = null;

    static
    {
        try
        {
            Class.forName(JDBC_DRIVER);  //in static block
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void DBDumpIP(String IPAdress) {
        Statement statement = null;

        System.out.println(IPAdress);

        try
        {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

//            statement = connection.createStatement();

            String queryForIPDump = "INSERT INTO MONITOR_TABLE " +
                    "VALUES (?,?);";

/*
            statement.executeUpdate(queryForIPDump);
*/

            PreparedStatement preparedStatement = connection.prepareStatement(queryForIPDump);

            preparedStatement.setInt(1, 1);

            preparedStatement.setString(2, IPAdress);

            preparedStatement.executeUpdate();

            preparedStatement.close();

//            statement.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }
}
