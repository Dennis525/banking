package com.dennis;

import java.sql.*;
import java.util.Scanner;

public class Database {
    Connection con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    Database(){
       con= getCon();
    }

    public  Connection getCon(){
       try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver found");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
            System.out.println("Successfully connected to DB");
        } catch (SQLException e){
            System.err.println(e);
        }
        return con;
    }
    public void addAccount(bankAccount b){
        String sql = "INSERT into bank(accountName, accountNumber, balance , pin) VALUES(?,?,?,?)";
        try {pst= con.prepareStatement(sql);
        pst.setString(1,b.getAccountName());
        pst.setString(2,b.getAccountNumber());
        pst.setFloat(3,b.getBalance());
            System.out.println("Enter pin:");
            Scanner sc = new Scanner(System.in);
            int pin= sc.nextInt();
            pst.setInt(4,pin);
            pst.execute();
            System.out.println("Successfully added!");



        }
        catch (SQLException e){
            System.err.println(e);
            System.out.println("Account not created");
        }
    }
   public bankAccount getAccount(String accountno, int pin){
        String sql = "SELECT * from bank where accountNumber=? AND pin = ?";
        bankAccount b;
        try{pst = con.prepareStatement(sql);
        pst.setString(1,accountno );
        pst.setInt(2,pin);
        rs=pst.executeQuery();
        while (rs.next()){
            String name=rs.getString("accountName");
            float balance = rs.getFloat("balance");
            String accountNumber = rs.getString("accountNumber");
            b = new bankAccount(name,accountNumber,balance);
            System.out.println("Data found");

            return b;
        }
            System.out.println("Account not found");

        }
        catch (SQLException e){
            System.err.println(e);

        }
        return null;
   }
   //UPDATE from 'bank'
   public void UpdateAccount(bankAccount b ,int pin){
        String sql = "UPDATE bank SET accountName = ?,balance = ? ,pin = ? WHERE bank.accountNumber = ?";
        try{
            pst= con.prepareStatement(sql);
            pst.setString(1,b.getAccountName());
            pst.setDouble(2,b.getBalance());
            pst.setInt(3,pin);
            pst.setString(4,b.getAccountNumber());
            pst.executeUpdate();
            System.out.println("SUCCESSFULLY UPDATED");


        }catch (SQLException e){
            System.out.println("COULD NOT UPDATE");
            System.err.println(e);

        }


   }
   public  void delAccount(bankAccount b){
        String sql ="DELETE FROM bank WHERE bank.accountNumber=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,b.getAccountNumber());
            pst.executeUpdate();
            System.out.println("ACCOUNT DELETED SUCCESSFULLY");
        }catch (SQLException e){
            System.out.println("COULD NOT DELETE");
            System.err.println(e);
        }
   }

}

