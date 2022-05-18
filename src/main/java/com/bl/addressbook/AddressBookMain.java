package com.bl.addressbook;

import java.sql.*;
import java.util.Scanner;

/**
 * Program to add a new Contact to Address Book
 *
 * @author : Snehal Patil.
 */

public class AddressBookMain {
    public static void main(String[] args) throws Exception {
        System.out.println("----------- Welcome to address book program ☺ -------------");

        AddressBookMain addressBookMain = new AddressBookMain();

        // Calling address book menu function
        addressBookMain.addressBookMenu();
    }

    /*
     * This take user choice of operation
     */
    public void addressBookMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/address_book_system";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book_system", "root", "Mysqlpassword@1");
            AddressBook addressBook = new AddressBook();
            boolean change = true;
            do {
                System.out.println("\n Select the operation you want to perform : ");
                System.out.println(
                        "1.Create table\n2.Add contact\n3.Exit Address book System");
                switch (scanner.nextInt()) {
                    case 1:
                        addressBook.createTable(connection);
                        break;
                    case 2:
                        addressBook.addContact(connection);
                        break;
                    default:
                        change = false;
                        System.out.println("Exiting Address Book... ");
                }
            } while (change);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Closing statement and connection
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
