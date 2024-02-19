package com.example.ispw.connection;

import com.example.ispw.utilities.Printer;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//usato il pattern singleton
public class DBConnection {


    private DBConnection(){
        //privato per evitare che altri classi facciano la new()
    }

    private static Connection connection;

    public static Connection getConnection() {
        String user;
        String password;
        String url;
        String driverClassName;

        if (connection == null) {
            try{
                Properties db = loadProperties();
                user = db.getProperty("USER");
                url = db.getProperty("CONNECTION_URL");
                password = db.getProperty("PASS");
                driverClassName = db.getProperty("DRIVER_CLASS_NAME");

                Class.forName(driverClassName);

                connection = DriverManager.getConnection(url,user, password);

            } catch (SQLException | IOException | ClassNotFoundException e) {
                Printer.printError(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();

        //closing the resource fileInputStream is handled automatically by the try-with-resources
        try (FileInputStream fileInputStream = new FileInputStream("src/main/java/com/example/ispw/connection/db.properties")){
            properties.load(fileInputStream);
        }
        return properties;
    }


}

