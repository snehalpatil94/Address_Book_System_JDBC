package com.bl.addressbook;

import java.sql.*;

/**
 * Program to create a Contacts in Address Book with first and last names, address, city, state, zip,
 * phone number and email...
 *
 * @author : Snehal Patil.
 */

public class AddressBook {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/address_book_system";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            //creating jdbc connection object
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book_system", "root", "Mysqlpassword@1");

            // Query to create table
            String query = "create table contact_details(id int not null auto_increment primary key, first_name varchar(10) not null, last_name varchar(10) not null, address varchar(10), city varchar(10) not null, state varchar(10) not null, zip int(6), phone int(12) not null, email varchar(20) unique)";
            if (connection != null) {
                statement = connection.createStatement();
                int result = statement.executeUpdate(query);
                if (result != 0) {
                    System.out.println("Table creation failed....");
                } else {
                    System.out.println("Table created successfully....");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            // Closing prepared statement
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Closing Connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
