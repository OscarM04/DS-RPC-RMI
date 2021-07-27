package dataAcces;

import entitys.UserEntity;
import exceptions.CustomException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUser{

    private DAO dao = new DAO();

    public UserEntity create() {
        System.out.println("DAO User: Creando Usuario en BD");
        try {
            dao.createConnection();
            Statement statement = dao.getConnection().createStatement();
            System.out.println("Reading Table records...");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                System.out.printf(resultSet.getString("ci") + resultSet.getString("username"));
            }
            dao.closeConnection();

        } catch (CustomException | SQLException e) {
            e.printStackTrace();
        }
        return new UserEntity();
    }

    public Boolean checkIfUserExists(String ci){
        Boolean exists = false;
        try{
            dao.createConnection();
            String sql = "SELECT * FROM users AS u WHERE u.ci=" + ci;
            Statement statement = dao.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if ( resultSet.next()) exists = true;
            dao.closeConnection();
        }catch (Exception e ){
            e.printStackTrace();
        }
        return exists;
    }

    public Integer numberOfAccounts(String ci){
        Integer numberOfAccounts = 0;
        try{
            dao.createConnection();
            String sql = "SELECT count(*) FROM users JOIN accounts a on users.ci = a.fk_user WHERE users.ci=" + ci;
            Statement statement = dao.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            System.out.println(resultSet.getInt(1));
            numberOfAccounts = resultSet.getInt(1);
            dao.closeConnection();
        }catch (Exception e ){
            e.printStackTrace();
        }
        return numberOfAccounts;
    }
}
