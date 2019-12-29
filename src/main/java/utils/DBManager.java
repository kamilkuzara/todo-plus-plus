package utils;

import java.sql.*;

public class DBManager{
    private final static String DB_URL = DBCredentials.DB_URL.getValue();
    private final static String DB_USERNAME = DBCredentials.DB_USERNAME.getValue();
    private final static String DB_PASSWORD = DBCredentials.DB_PASSWORD.getValue();

    /*public static void registerUser(String username, String passwordHash, String salt)
    {
      try
      {
          Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

          PreparedStatement updateQuery = dbConnection.prepareStatement("INSERT INTO user (username, password_hash, salt) VALUES (?, ?, ?)");

          updateQuery.setString(1, username);
          updateQuery.setString(2, passwordHash);
          updateQuery.setString(3, salt);

          updateQuery.executeUpdate();
      }
      catch(SQLException exception)
      {
          exception.printStackTrace();
      }
    }*/

    public static boolean userExists(String username)
    {
        boolean userExists = false;

        Connection dbConnection = null;
        PreparedStatement query = null;
        ResultSet queryResult = null;

        try
        {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // use PreparedStatement to prevent SQL Injection
            query = dbConnection.prepareStatement("SELECT COUNT(username) FROM user WHERE username=?");
            query.setString(1, username);
            queryResult = query.executeQuery();

            if(queryResult.next() && queryResult.getInt("COUNT(username)") == 1)
                userExists = true;
            else
                userExists = false;
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
            userExists = false;
        }
        finally   // close the resources
        {
            try
            {
                if(dbConnection != null)
                    dbConnection.close();

                if(query != null)
                    query.close();

                if(queryResult != null)
                    queryResult.close();
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
            }
        }

        return userExists;
    }

    public static String getPasswordHash(String username)
    {
        String passwordHash = "";

        Connection dbConnection = null;
        PreparedStatement query = null;
        ResultSet queryResult = null;

        try
        {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // use PreparedStatement to prevent SQL Injection
            query = dbConnection.prepareStatement("SELECT password_hash FROM user WHERE username=?");
            query.setString(1, username);
            queryResult = query.executeQuery();

            if(queryResult.next())
                passwordHash = queryResult.getString("password_hash");
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
        }
        finally   // close the resources
        {
            try
            {
                if(dbConnection != null)
                    dbConnection.close();

                if(query != null)
                    query.close();

                if(queryResult != null)
                    queryResult.close();
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
            }
        }

        return passwordHash;
    }

    public static String getSalt(String username)
    {
        String salt = "";

        Connection dbConnection = null;
        PreparedStatement query = null;
        ResultSet queryResult = null;

        try
        {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // use PreparedStatement to prevent SQL Injection
            query = dbConnection.prepareStatement("SELECT salt FROM user WHERE username=?");
            query.setString(1, username);
            queryResult = query.executeQuery();

            if(queryResult.next())
                salt = queryResult.getString("salt");
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
        }
        finally   // close the resources
        {
            try
            {
                if(dbConnection != null)
                    dbConnection.close();

                if(query != null)
                    query.close();

                if(queryResult != null)
                    queryResult.close();
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
            }
        }

        return salt;
    }
}
