package com.nms.action;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.sql.*;
import java.util.HashMap;

public class DataBaseConnection {

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

//            DataBaseConnection.DBDataFetch();
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }



    public static HashMap<String,Double> TOP5MaxRTT()
    {
        HashMap<String,Double> MaxRTT = new HashMap<>();

        try
        {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement statement = connection.createStatement();

            String query = "select count(MONITOR_ID) from MONITOR_TABLE";

            ResultSet resultSet = statement.executeQuery(query);

            int n = 0;

            while (resultSet.next())
                 n = resultSet.getInt(1);

//            System.out.println(n);

            String queryForTop5MinRTT = "SELECT * FROM (Select * from POLLING_TABLE order by TIME desc limit '"+n+"') ORDER BY RTT DESC limit 5";

            ResultSet resultSetForRTT = statement.executeQuery(queryForTop5MinRTT);

            while (resultSetForRTT.next())
            {

                String IPAdress = resultSetForRTT.getString("IP_ADDRESS");

                Double RTT = resultSetForRTT.getDouble("RTT");

                MaxRTT.put(IPAdress,RTT);

            }

            statement.close();

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return MaxRTT;
    }


    public static HashMap<String,Double> TOP5MinRTT()
    {
        HashMap<String,Double> MinRTT = new HashMap<>();

        try
        {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement statement = connection.createStatement();

            String query = "select count(MONITOR_ID) from MONITOR_TABLE";

            ResultSet resultSet = statement.executeQuery(query);

            int n = 0;

            while (resultSet.next())
                n = resultSet.getInt(1);

//            System.out.println(n);

            String queryForTop5MinRTT = "SELECT * FROM (Select * from POLLING_TABLE where RTT!=0.0 order by TIME desc limit '"+n+"') ORDER BY RTT ASC limit 5";

            ResultSet resultSetForRTT = statement.executeQuery(queryForTop5MinRTT);

            while (resultSetForRTT.next())
            {

                String IPAdress = resultSetForRTT.getString("IP_ADDRESS");

                Double RTT = resultSetForRTT.getDouble("RTT");

                MinRTT.put(IPAdress,RTT);

            }

            statement.close();

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

//        return MinRTT.toString().replace("=",": ");
          return MinRTT;
    }

    public static HashMap<String,String> IPStatus()
    {
        HashMap<String,String> IPStatus = new HashMap<>();

        try
        {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement statement = connection.createStatement();

            String query = "select count(MONITOR_ID) from MONITOR_TABLE";

            ResultSet resultSet = statement.executeQuery(query);

            int n = 0;

            while (resultSet.next())
                n = resultSet.getInt(1);

//            System.out.println(n);

            String queryForStatus = "select IP_ADDRESS,STATUS from (Select * from POLLING_TABLE order by TIME desc limit '"+n+"')";

            ResultSet resultSetForStatus = statement.executeQuery(queryForStatus);

            while (resultSetForStatus.next())
            {

                String IPAdress = resultSetForStatus.getString("IP_ADDRESS");

                String status = resultSetForStatus.getString("STATUS");

                IPStatus.put(IPAdress,status);

            }

// JSON.parse( val)
            statement.close();

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return IPStatus;

    }



    public static int[] UpDownCount()
    {
        int[] count = new int[2];

        try
        {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement statement = connection.createStatement();

            String query = "select count(MONITOR_ID) from MONITOR_TABLE";

            ResultSet resultSet = statement.executeQuery(query);

            int n = 0;

            while (resultSet.next())
                n = resultSet.getInt(1);

            System.out.println(n);

            String queryForUpCount = "SELECT COUNT(STATUS) from (Select * from POLLING_TABLE order by TIME desc limit '"+n+"') where STATUS='Up'";

            ResultSet resultSetForUpCount = statement.executeQuery(queryForUpCount);

            int UpCount = 0;

            while (resultSetForUpCount.next())
                UpCount = resultSetForUpCount.getInt(1);

            int DownCount = n-UpCount;

            count[0] = UpCount;

            count[1] = DownCount;

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return count;
    }





    public static HashMap<String, Object> getUIdatafromDatabase()
    {
        HashMap<String,Double> maxRTT = DataBaseConnection.TOP5MaxRTT();

        HashMap<String,Double> minRTT = DataBaseConnection.TOP5MinRTT();

        int[] upDownCount = DataBaseConnection.UpDownCount();

        HashMap<String,String> ipStatus = DataBaseConnection.IPStatus();

        HashMap<String, Object> jsonObject = new HashMap<>();

        jsonObject.put("MaxRtt", maxRTT);

        jsonObject.put("MinRtt", minRTT);

        jsonObject.put("Ipstatus", ipStatus);

        jsonObject.put("UpCount", upDownCount[0]);

        jsonObject.put("DownCount", upDownCount[1]);

        return jsonObject;
    }


    public static void DBDataFetch() {

        Statement statement = null;

        try {

//            System.out.println("Connecting to database...");

            connection = DriverManager.getConnection(DB_URL, USER, PASS);

//            System.out.println("Creating table in given database...");

            statement = connection.createStatement();

//            String queryForPollingTable = "create table if not exists POLLING_TABLE "+
//                    "(MONITOR_ID Integer not null, "+
//                    "RTT Integer not null, "+
//                    "PACKET_LOSS Integer not null, "+
//                    "STATUS varchar(4), "+
//                    "TIME varchar(255))";
//
//            statement.executeUpdate(queryForPollingTable);


//            String queryForTableCreation = "create table if not exists Monitor_Table "+
//                    "(Monitor_ID Integer not null, "+
//                     "IP_Address varchar(15))";
//
//            statement.executeUpdate(queryForTableCreation);
//
//            String query = "insert into Monitor_Table "+ "Values (1,'localhost')";
//            statement.executeUpdate(query);
//
//            query = "insert into Monitor_Table "+ "Values (2,'8.8.8.8')";
//            statement.executeUpdate(query);
//
//
//            query = "insert into Monitor_Table "+ "Values (3,'10.20.40.199')";
//            statement.executeUpdate(query);
//
//
//            query = "insert into Monitor_Table "+ "Values (4,'google.com')";
//            statement.executeUpdate(query);


            String query = "select distinct IP_ADDRESS from MONITOR_TABLE";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String IPAdress = resultSet.getString("IP_ADDRESS");

                PingUtility pingObject = new PingUtility();

                pingObject.Ping(IPAdress);

                String queryForDump = "";

                queryForDump = "insert into POLLING_TABLE VALUES (?,?,?,?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(queryForDump);

                preparedStatement.setString(1, IPAdress);

                preparedStatement.setDouble(2, (pingObject.getRTT()));

                preparedStatement.setString(3, pingObject.getPacketLoss());

                preparedStatement.setString(4, pingObject.getStatus());

                preparedStatement.setString(5, pingObject.getTime());

                preparedStatement.executeUpdate();

                preparedStatement.close();

//                System.out.println(s);
            }

            statement.close();

            connection.close();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (statement != null)

                    statement.close();
            }
            catch (SQLException se2)
            {
                se2.printStackTrace();
            }
            try
            {
                if (connection != null) connection.close();
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }

//        System.out.println("Goodbye!");
        }
    }
}