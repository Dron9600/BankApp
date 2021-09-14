package kz.Prudnikov.BanksApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddNewCustomer {

    public AddNewCustomer(int newPassword, String newName, int zeroMoney){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/bankdb", "root", "Mysql4512345123");
            String sql = "INSERT INTO bankdb.accounts (accountNumber, OwnerName, Money) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newPassword);
            preparedStatement.setString(2, newName);
            preparedStatement.setInt(3, zeroMoney);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Registered successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
