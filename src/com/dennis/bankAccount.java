package com.dennis;

public class bankAccount {
    private String accountNumber;
    private String accountName;
    private float balance;

    bankAccount(String accountName,String accountNumber,float balance){
        this.accountName=accountName;
        this.accountNumber=accountNumber;
        setBalance(balance);
    }
    public float getBalance(){ return balance;}

    public  void setBalance(float balance){this.balance= balance;}

    public  void setAccountName(String accountName){this.accountName=accountName;}

    public  String getAccountName(){return accountName;}

    public  String getAccountNumber(){return accountNumber;}

    public  void deposit(float amount){
        float fee = 0.35f;
        float newBal= getBalance()-(amount*fee)-amount;
        setBalance(newBal);

    }
}
