package dataAcces;


import entitys.TransactionEntity;
import exceptions.CustomException;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DAOAccount {

    private DAO dao = new DAO();

    public void create( long ci, float initialAmount, long accountNumber) throws CustomException {
        try {
            dao.createConnection();
            String sql = "INSERT INTO accounts (id, fk_user, number, current_balance) VALUES (?, ?, ?, ?)";
            String sql2 = "SELECT id FROM accounts ORDER BY ID DESC LIMIT 1";
            Statement statement2 = dao.getConnection().createStatement();
            ResultSet resultSet = statement2.executeQuery(sql2);
            resultSet.next();
            System.out.println( "Last id in Accounts: " + resultSet.getInt(1));
            int lastId = resultSet.getInt( 1) + 1;
            PreparedStatement statement = dao.getConnection().prepareStatement(sql);
            statement.setInt( 1, lastId);
            statement.setLong( 2, ci);
            statement.setLong(3, accountNumber);
            statement.setFloat(4, initialAmount);
            statement.executeUpdate();
            String sql3 = "SELECT id FROM transactions ORDER BY ID DESC LIMIT 1";
            Statement statement3 = dao.getConnection().createStatement();
            ResultSet resultSet3 = statement3.executeQuery(sql3);
            resultSet3.next();
            System.out.println( "Last id in Transactions: " + resultSet.getInt(1));
            int lastId2 = resultSet3.getInt( 1) + 1;
            String sql4 = "INSERT INTO transactions (id, amount, type,date,description,fk_source_account, fk_destination_account) VALUES (?,?,?, ?,? , ?,?)";
            PreparedStatement statement4 = dao.getConnection().prepareStatement(sql4);
            statement4.setInt( 1, lastId2);
            statement4.setFloat( 2, initialAmount);
            statement4.setString( 3, "deposit");
            statement4.setTimestamp( 4, Timestamp.from(Instant.now()));
            statement4.setString( 5, "Initial Deposit");
            statement4.setLong( 6, accountNumber);
            statement4.setNull(7, Types.INTEGER);
            statement4.executeUpdate();
            dao.closeConnection();
        } catch (CustomException | SQLException e) {
            e.printStackTrace();
            throw new CustomException("Ha ocurrido un error al insertar la cuenta");
        }
    }

    public List<TransactionEntity> listTransactions(long accountNumber) throws CustomException {
        List<TransactionEntity> result = new ArrayList<>();
        try {
            dao.createConnection();
            String sql = "SELECT * FROM transactions t " +
                            "WHERE fk_destination_account =" + accountNumber +
                                "OR fk_source_account = " + accountNumber + " ORDER BY id DESC LIMIT 5;";
            Statement statement = dao.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TransactionEntity row = new TransactionEntity();
                row.setId(resultSet.getInt("id"));
                row.setAmount( resultSet.getFloat("amount"));
                row.setDate( resultSet.getDate("date"));
                row.setDescription( resultSet.getString("description"));
                row.setType( resultSet.getString("type"));
                row.setFk_source_account( resultSet.getInt("fk_source_account"));
                row.setFk_destination_account( resultSet.getInt( "fk_destination_account"));
                result.add( row);
            }
            System.out.println( "Transacciones" + result);
            dao.closeConnection();
        }catch (SQLException | CustomException e){
            e.printStackTrace();
        }
        if ( result.isEmpty() ) throw new CustomException("No posee transacciones");
        return result;
    }


    public List<String> checkAnotherAccount(long accountNumber, long ci){
        List<String> result = new ArrayList<>();
        boolean notNull = false;
        try {
            dao.createConnection();
            String sql = "SELECT u.ci, u.name, u.username FROM users as u " +
                                "LEFT JOIN accounts as a ON a.fk_user = u.ci " +
                                "WHERE  a.number=? AND u.ci = ?";
            PreparedStatement statement = dao.getConnection().prepareStatement(sql);
            statement.setLong( 1, accountNumber);
            statement.setLong( 2, ci);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                notNull = true;
                result.add( resultSet.getString("username"));
                result.add( resultSet.getString("name"));
                result.add(String.valueOf( resultSet.getString("ci")));
            }
            System.out.println( "Transacciones" + result);
            dao.closeConnection();
        }catch (SQLException | CustomException e) {
            e.printStackTrace();
        }
        return notNull?result:null;
    }

    public List<String> operation( float amount, String description, long accountNumber, String type, long destAccount) throws CustomException {
        List<String> result = new ArrayList<>();
        try {
            dao.createConnection();
            String sql = "SELECT id FROM transactions ORDER BY ID DESC LIMIT 1";
            Statement statement = dao.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            System.out.println( "Last id in Transactions: " + resultSet.getInt(1));
            int lastId = resultSet.getInt( 1) + 1;
            String sql2 = "INSERT INTO transactions (id, amount, type,date,description,fk_source_account, fk_destination_account) VALUES (?,?,?, ?,? , ?,?)";
            PreparedStatement statement2 = dao.getConnection().prepareStatement(sql2);
            statement2.setInt( 1, lastId);
            statement2.setFloat( 2, amount);
            statement2.setString( 3, type);
            statement2.setTimestamp( 4, Timestamp.from(Instant.now()));
            statement2.setString( 5, description);
            statement2.setLong( 6, accountNumber);
            if (type.equals("transference")) {
                statement2.setLong(7, destAccount);
            } else {
                statement2.setNull(7, Types.INTEGER);
            }
            if ( statement2.executeUpdate() == 1){
                float current_balance;
                if ( !type.equals("transference")) {
                    String sql3 = "SELECT current_balance FROM accounts WHERE number=?";
                    PreparedStatement statement3 = dao.getConnection().prepareStatement(sql3);
                    statement3.setLong(1, accountNumber);
                    ResultSet resultSet1 = statement3.executeQuery();
                    resultSet1.next();
                    current_balance = resultSet1.getFloat("current_balance");
                    current_balance += amount;
                    String sql4 = "UPDATE accounts SET current_balance=? WHERE number = ?";
                    PreparedStatement statement4 = dao.getConnection().prepareStatement(sql4);
                    statement4.setFloat(1, current_balance);
                    statement4.setLong(2, accountNumber);
                    statement4.executeUpdate();
                }else{
                    String sql3 = "SELECT current_balance FROM accounts WHERE number=?";
                    PreparedStatement statement3 = dao.getConnection().prepareStatement(sql3);
                    statement3.setLong(1, accountNumber);
                    ResultSet resultSet1 = statement3.executeQuery();
                    resultSet1.next();
                    current_balance = resultSet1.getFloat("current_balance");
                    current_balance -= amount;
                    String sql4 = "UPDATE accounts SET current_balance=? WHERE number = ?";
                    PreparedStatement statement4 = dao.getConnection().prepareStatement(sql4);
                    statement4.setFloat(1, current_balance);
                    statement4.setLong(2, accountNumber);
                    statement4.executeUpdate();
                    String sql5 = "SELECT current_balance FROM accounts WHERE number=?";
                    PreparedStatement statement5 = dao.getConnection().prepareStatement(sql5);
                    statement5.setLong(1, destAccount);
                    ResultSet resultSet2 = statement5.executeQuery();
                    resultSet2.next();
                    float current_balance_dest = resultSet2.getFloat("current_balance");
                    current_balance_dest += amount;
                    String sql6 = "UPDATE accounts SET current_balance=? WHERE number = ?";
                    PreparedStatement statement6 = dao.getConnection().prepareStatement(sql6);
                    statement6.setFloat(1, current_balance_dest);
                    statement6.setLong(2, destAccount);
                    statement6.executeUpdate();
                }
                result.add( "Tipo de transaccion:" + type);
                result.add( "Fecha: " + String.valueOf(Timestamp.from(Instant.now())));
                result.add( "Monto: "+String.valueOf(amount));
                result.add( "Descripcion" + description);
                result.add( "Nuevo Balance " + current_balance);
                result.add( "Origen: "+ String.valueOf(accountNumber));
                if ( type.equals("transference")) result.add( "Destino: "+ String.valueOf( destAccount));
            }else{
                result = null;
            }
            dao.closeConnection();
        }catch (SQLException | CustomException e) {
            //e.printStackTrace();
            throw new CustomException("Ha ocurrido un error al realizar la operacion, revise los datos suministrados o intente mas tarde");
        }
        System.out.println( result);
        return result;
    }
}
