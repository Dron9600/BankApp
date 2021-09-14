package kz.Prudnikov.BanksApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Repository {

    private Connection connection;

    public Repository(String user, String password) {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/bankdb", user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int accountNumber) {
        try {
            String query = "delete from bankdb.accounts where accountNumber = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, accountNumber);
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(int password, String nameForRewrite, int depositInAccount) {
        try {
            String sql = "INSERT INTO bankdb.accounts (accountNumber, OwnerName, Money) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, password);
            preparedStatement.setString(2, nameForRewrite);
            preparedStatement.setInt(3, depositInAccount);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Successfully updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
