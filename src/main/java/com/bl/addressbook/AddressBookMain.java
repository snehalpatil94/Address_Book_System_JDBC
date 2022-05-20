package com.bl.addressbook;

import java.sql.*;
import java.util.Scanner;

/**
 * Program to delete existing contact person using their name.
 *
 * @author : Snehal Patil.
 */

public class AddressBookMain {
    public static void main(String[] args) throws Exception {
        System.out.println("----------- Welcome to address book program ☺ -------------\n");

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
            boolean flag = true;
            do {
                System.out.println("Select the operation you want to perform : ");
                System.out.println(
                        "1.Create table \n2.Add contact \n3.Edit Contact \n4.Delete Contact \n5.Exit Address book System ");
                String choice = scanner.next();
                switch (choice) {
                    case "1":
                        addressBook.createTable(connection);
                        break;
                    case "2":
                        addressBook.addContact(connection);
                        break;
                    case "3":
                        addressBook.editContact(connection);
                        break;
                    case "4":
                        addressBook.deleteContact(connection);
                        break;
                    case "5":
                        flag = false;
                        System.out.println("Thank You !! ");
                        break;
                    default:
                        System.out.println("Enter Valid Option !");
                }
            } while (flag);
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
