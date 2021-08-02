package dataAcces;

import exceptions.CustomException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    private String username = "root";
    private String hostname = "localhost";
    private String port = "5432";
    private String database = "bank";
    private String password = "root";
    private Connection connection;

    public DAO() {
    }


    public void createConnection() throws CustomException {
        try{
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://"+ hostname +":"+ port +"/"+ database,
                    username,
                    password
            );
        }catch (SQLException e) {
            e.printStackTrace();
            throw new CustomException( e.toString());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws CustomException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch ( SQLException sqlex) {
                throw new CustomException( sqlex.toString());
            }
        }
    }

}
