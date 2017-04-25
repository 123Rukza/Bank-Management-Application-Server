package com.frostmourne.bankapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    Connection connection;

    public Database() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String database = "jdbc:derby://localhost:1527/BankDatabase";
            connection = DriverManager.getConnection(database, "bankAdmin", "123");
            System.out.println("Database deployed");

        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!");
        } catch (SQLException ex) {
            System.out.println("Connection failed!");
        }
    }

    public boolean employeeLogin(String username, String password) {

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Employee WHERE USERNAME='" + username + "' AND PASSWORD='" + password + "'");
            return rs.next();

        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            return false;
        }
    }

    public boolean createEmployee(String name, String position, String username, String password) {
        try {
            String query = ("INSERT INTO Employee(Name, Position, Username, Password) VALUES (?,?,?,?)");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, position);
            statement.setString(3, username);
            statement.setString(4, password);

            return statement.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(String name, String position, String usernameNew, String usernameOld, String password) {
        try {
            String query = ("UPDATE Employee SET Name= ? , Position = ? , Username = ? , Password = ? WHERE Username = ?");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, position);
            statement.setString(3, usernameNew);
            statement.setString(4, password);
            statement.setString(5, usernameOld);

            return statement.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            return false;
        }
    }

    public boolean deleteEmployee(String username) {
        try {
            String query = ("DELETE FROM Employee WHERE Username = ?");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            return statement.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            return false;
        }
    }

    public boolean checkEmployeeExists(String username) {
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Employee WHERE USERNAME='" + username + "'");
            return rs.next();

        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            return false;
        }
    }

    public ArrayList<String> getAllCustomers() {

        ArrayList<String> result = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Customer");

            while (rs.next()) {
                result.add(rs.getString("Name"));
                result.add(rs.getString("Birthdate"));
                result.add(rs.getString("Address"));
                result.add(rs.getString("Mobile"));
                result.add(rs.getString("Email"));
                result.add(rs.getString("AccountType"));
                result.add(rs.getString("AccountNumber"));
                result.add(rs.getString("SortCode"));
                result.add(rs.getString("Balance"));
                result.add(rs.getString("Card"));
            }

            return result;
        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            return result;
        }
    }

    public ArrayList<String> getCustomerDetails(String accountNumber) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Customer WHERE AccountNumber ='" + accountNumber + "'");

            while (rs.next()) {
                result.add(rs.getString("Name"));
                result.add(rs.getString("Birthdate"));
                result.add(rs.getString("Address"));
                result.add(rs.getString("Mobile"));
                result.add(rs.getString("Email"));
                result.add(rs.getString("AccountType"));
                result.add(rs.getString("AccountNumber"));
                result.add(rs.getString("SortCode"));
                result.add(rs.getString("Balance"));
                result.add(rs.getString("Card"));
            }

            return result;
        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            return result;
        }
    }

    public boolean createCustomer(String name, String birthdate, String address, String mobile, String email, String accounttype, String accountnumber, String sortcode, String balance, String card) {
        try {
            String query = ("INSERT INTO Customer(Name, Birthdate, Address, Mobile, Email, AccountType, AccountNumber, SortCode, Balance, Card) VALUES (?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, birthdate);
            statement.setString(3, address);
            statement.setString(4, mobile);
            statement.setString(5, email);
            statement.setString(6, accounttype);
            statement.setString(7, accountnumber);
            statement.setString(8, sortcode);
            statement.setString(9, balance);
            statement.setString(10, card);

            return statement.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(String accountNumber) {
        try {
            String query = ("DELETE FROM Customer WHERE AccountNumber = ?");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, accountNumber);

            return statement.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            return false;
        }
    }

    public boolean updateCustomer(String name, String birthdate, String address, String mobile, String email, String accounttype, String accountnumber, String accountNumberOld, String sortcode, String balance, String card) {
        try {
            String query = ("UPDATE Customer SET Name= ?, Birthdate= ?, Address= ?, Mobile= ?, Email= ?, AccountType= ?, AccountNumber= ?, SortCode= ?, Balance= ?, Card= ?) WHERE AccountNumber = ?");
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, birthdate);
            statement.setString(3, address);
            statement.setString(4, mobile);
            statement.setString(5, email);
            statement.setString(6, accounttype);
            statement.setString(7, accountnumber);
            statement.setString(8, sortcode);
            statement.setString(9, balance);
            statement.setString(10, card);
            statement.setString(11, accountNumberOld);

            return statement.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println("Query execution failed!");
            ex.printStackTrace();
            return false;
        }
    }
}
