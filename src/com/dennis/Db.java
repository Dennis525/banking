package com.dennis;

public class Db {
    public static void main(String[] args) {
        Database db= new Database();
        bankAccount b = db.getAccount("12345",2040);
        bankAccount c =db.getAccount("113777",6754);
        bankAccount f = new bankAccount("Felix","009009",70000);
        db.addAccount(f);


        System.out.println("Name:"+b.getAccountName()+ "  balance "  +b.getBalance());
        System.out.println("Name:"+c.getAccountName() +"  balance "  +c.getBalance());
        System.out.println("Name:"+f.getAccountName());

        b.setAccountName("MARK");
        b.setBalance(40000);
        b = new bankAccount(b.getAccountName(), b.getAccountNumber(),b.getBalance());
        db.UpdateAccount(b,2040);
        b = db.getAccount("12345",2040);

        System.out.println("Name:"+b.getAccountName()+ "  balance "  +b.getBalance());


        db.delAccount(b);



    }
}
