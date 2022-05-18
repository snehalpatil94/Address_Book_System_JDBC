package com.bl.addressbook;

import java.sql.*;
import java.util.Scanner;

public class AddressBook {
    Scanner scanner = new Scanner(System.in);
    PreparedStatement preparedStatement = null;
    AddressBookMain addressBookMain = new AddressBookMain();

    /*
     *This method to create table in database
     */
    public void createTable(Connection connection) throws Exception {
        try {
            // Query to create table
            String query = "create table contact_details(id int not null auto_increment primary key, first_name varchar(15) unique, last_name varchar(15) not null, address varchar(20), city varchar(20) not null, state varchar(20) not null, zip int(6), phone int(12) not null, email varchar(20) unique)";
            Statement statement = null;

            if (connection != null) {
                statement = connection.createStatement();
                int result = statement.executeUpdate(query);
                if (result != 0) {
                    System.out.println("Table creation failed....");
                } else {
                    System.out.println("Table created successfully....");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *Method to add contact in table of database
     * input - connection
     */
    public void addContact(Connection connection) throws SQLException, Exception {
        try {
            // Query to add contact in table
            String insertQuery = " insert into contact_details(first_name , last_name, address, city, state , zip, phone, email) values(?,?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(insertQuery);

            System.out.println("Enter first name : ");
            Scanner scanner = new Scanner(System.in);
            String firstName = scanner.next();
            System.out.println("Enter last name : ");
            String lastName = scanner.next();
            System.out.println("Enter address : ");
            String address = scanner.next();
            System.out.println("Enter city : ");
            String city = scanner.next();
            System.out.println("Enter state : ");
            String state = scanner.next();
            System.out.println("Enter zip code : ");
            int zip = scanner.nextInt();
            System.out.println("Enter phone number : ");
            int phone = scanner.nextInt();
            System.out.println("Enter email : ");
            String email = scanner.next();

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setInt(6, zip);
            preparedStatement.setInt(7, phone);
            preparedStatement.setString(8, email);

            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                System.out.println("Contact inserted successfully");
            } else {
                System.out.println("Contact insertion failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
