package dataAcces;

import exceptions.CustomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser{

    private DAO dao = new DAO();

    public void create(
            long ci, String username, String password, String name
    ) throws CustomException {
        try {
            dao.createConnection();
            String sql = "INSERT INTO users VALUES (?, ?, ?, ?)";
            PreparedStatement statement = dao.getConnection().prepareStatement(sql);
            statement.setLong( 1, ci);
            statement.setString(3, username);
            statement.setString(2, name);
            statement.setString( 4, password);
            statement.executeUpdate();

            dao.closeConnection();
        } catch (CustomException | SQLException e) {
            e.printStackTrace();
            throw new CustomException("Ha ocurrido un error al insertar el usuario ");
        }
    }

    public Boolean checkIfUserExists(long ci){
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
    public Boolean signin(String username, String password) throws CustomException {
        Boolean isValid = false;
        try{
            dao.createConnection();
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement statement = dao.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if ( resultSet.next()) isValid = true;
            dao.closeConnection();
        }catch (Exception e ){
            e.printStackTrace();
            throw new CustomException("Ha ocurrido un error intente mas tarde ");
        }
        return isValid;
    }

    public List<Integer> listAccounts(long ci){
        List<Integer> accountList = new ArrayList<>();
        try{
            dao.createConnection();
            String sql = "SELECT a.number FROM users JOIN accounts a on users.ci = a.fk_user WHERE users.ci=" + ci;
            Statement statement = dao.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("LIST ACCOUNTS: " + resultSet.getInt(1));
                accountList.add( resultSet.getInt(1));
            }
            dao.closeConnection();
        }catch (Exception e ){
            e.printStackTrace();
        }
        return accountList;
    }
    public Integer numberOfAccounts(long ci){
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
