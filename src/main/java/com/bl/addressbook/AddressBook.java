package com.bl.addressbook;

import java.sql.*;
import java.util.Scanner;

public class AddressBook {
    Scanner scanner = new Scanner(System.in);
    PreparedStatement preparedStatement = null;

    /*
     *This method to create table in database
     */
    public void createTable(Connection Connection){
        try {
            // Query to create table
            String query = "create table contact_details(id int not null auto_increment primary key, first_name varchar(15) unique, last_name varchar(15) not null, address varchar(20), city varchar(20) not null, state varchar(20) not null, zip int(6), phone int(12) not null, email varchar(20) unique)";
            Statement statement = null;

            if (Connection != null) {
                statement = Connection.createStatement();
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
     * input - Connection
     */
    public void addContact(Connection Connection) {
        try {
            // Query to add contact in table
            String insertQuery = " insert into contact_details(first_name , last_name, address, city, state , zip, phone, email) values(?,?,?,?,?,?,?,?)";

            preparedStatement = Connection.prepareStatement(insertQuery);

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
                System.out.println("Contact Added Successfully !\n");
            } else {
                System.out.println("Contact Insertion Failed !\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editContact(Connection connection) throws Exception {
        boolean flag = true;
        do {
            System.out.println("What you want to change choose from below option:");
            System.out.println("1.First Name \n2.Last name \n3.Address \n4.City \n5.State \n6.zip \n7.Phone \n8.Email" +
                    "\n9.Exit Edit Function");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    editFirstName(connection);
                    break;
                case "2":
                    editLastName(connection);
                    break;
                case "3":
                    editAddress(connection);
                    break;
                case "4":
                    editCity(connection);
                    break;
                case "5":
                    editState(connection);
                    break;
                case "6":
                    editZip(connection);
                    break;
                case "7":
                    editPhone(connection);
                    break;
                case "8":
                    editEmail(connection);
                    break;
                case "9":
                    flag = false;
                    System.out.println("Thank you !");
                    break;
                default:
                    System.out.println("Enter Valid Option !");
            }
            System.out.println();
        } while (flag);
    }

    private void editFirstName(Connection connection) throws SQLException {
        String query = "update contact_details set first_name = ? where first_name= ?";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter old first name :");
        String oldName = scanner.next();
        System.out.println("Enter new first name :");
        String newName = scanner.next();
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, oldName);
        int result = preparedStatement.executeUpdate();
        if (result != 0) {
            System.out.println("Record Updated Successfully !");
        } else {
            System.out.println("Failed to update !");
        }
    }

    private void editLastName(Connection connection) throws SQLException {
        String query = "update contact_details set last_name = ? where first_name= ?";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter first name :");
        String firstName = scanner.next();
        System.out.println("Enter new last name :");
        String lastName = scanner.next();
        preparedStatement.setString(1, lastName);
        preparedStatement.setString(2, firstName);
        int result = preparedStatement.executeUpdate();
        if (result != 0) {
            System.out.println("Record Updated Successfully !");
        } else {
            System.out.println("Failed to update !");
        }
    }

    private void editAddress(Connection connection) throws Exception {
        String query = "update contact_details set address = ? where first_name= ?";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter first name :");
        String firstName = scanner.next();
        System.out.println("Enter new address :");
        String address = scanner.next();
        preparedStatement.setString(1, address);
        preparedStatement.setString(2, firstName);
        int result = preparedStatement.executeUpdate();
        if (result != 0) {
            System.out.println("Record Updated Successfully !");
        } else {
            System.out.println("Failed to update !");
        }
    }

    private void editCity(Connection connection) throws Exception {
        String query = "update contact_details set city = ? where first_name= ?";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter first name :");
        String firstName = scanner.next();
        System.out.println("Enter new city name :");
        String city = scanner.next();
        preparedStatement.setString(1, city);
        preparedStatement.setString(2, firstName);
        int result = preparedStatement.executeUpdate();
        if (result != 0) {
            System.out.println("Record Updated Successfully !");
        } else {
            System.out.println("Failed to update !");
        }
    }

    private void editState(Connection connection) throws SQLException {
        String query = "update contact_details set state = ? where first_name= ?";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter first name :");
        String firstName = scanner.next();
        System.out.println("Enter new State name:");
        String state = scanner.next();
        preparedStatement.setString(1, state);
        preparedStatement.setString(2, firstName);
        int result = preparedStatement.executeUpdate();
        if (result != 0) {
            System.out.println("Record Updated Successfully !");
        } else {
            System.out.println("Failed to update !");
        }
    }

    private void editZip(Connection connection) throws Exception {
        String query = "update contact_details set zip = ? where first_name= ?";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter first name :");
        String firstName = scanner.next();
        System.out.println("Enter new Zip Code :");
        int zip = scanner.nextInt();
        preparedStatement.setInt(1, zip);
        preparedStatement.setString(2, firstName);
        int result = preparedStatement.executeUpdate();
        if (result != 0) {
            System.out.println("Record Updated Successfully !");
        } else {
            System.out.println("Failed to update !");
        }
    }

    private void editPhone(Connection connection) throws Exception {
        String query = "update contact_details set phone = ? where first_name= ?";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter first name :");
        String firstName = scanner.next();
        System.out.println("Enter new phone number :");
        int phone = scanner.nextInt();
        preparedStatement.setInt(1, phone);
        preparedStatement.setString(2, firstName);
        int result = preparedStatement.executeUpdate();
        if (result != 0) {
            System.out.println("Record Updated Successfully !");
        } else {
            System.out.println("Failed to update !");
        }
    }

    private void editEmail(Connection connection) throws SQLException {
        String query = "update contact_details set email = ? where first_name= ?";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter first name :");
        String firstName = scanner.next();
        System.out.println("Enter new email :");
        String eMail = scanner.next();
        preparedStatement.setString(1, eMail);
        preparedStatement.setString(2, firstName);
        int result = preparedStatement.executeUpdate();
        if (result != 0) {
            System.out.println("Record Updated Successfully !");
        } else {
            System.out.println("Failed to update !");
        }
    }

    public void deleteContact(Connection connection)throws SQLException{
        String deleteQuery = "delete from contact_details where first_name = ?";
        preparedStatement = connection.prepareStatement(deleteQuery);
        System.out.println("Enter Contact name : ");
        String firstName = scanner.next();
        preparedStatement.setString(1,firstName);
        int result = preparedStatement.executeUpdate();
        if(result != 0){
            System.out.println("Contact Deleted Successfully !");
        } else {
            System.out.println("Contact Deletion Failed !");
        }
    }
}