package com.atminterface.bean;

public class ATMInterfaceBean {
 private int accountno,pin,cardno,amount;
 private String TransactionType; 
 
public ATMInterfaceBean( ) {super();}
 
public ATMInterfaceBean(int accountno, int cardno) {
	this.accountno = accountno;
	this.cardno = cardno;
}
public ATMInterfaceBean( int pin, int cardno, int amount) {
	super();
	this.pin = pin;
	this.cardno = cardno;
	this.amount = amount;
}


public ATMInterfaceBean( int pin, int cardno, int amount,String TransactionType) {
	super();
	this.pin = pin;
	this.cardno = cardno;
	this.amount = amount;
	this.TransactionType=TransactionType;
}

public int getAccountno() {
	return accountno;
}
public void setAccountno(int accountno) {
	this.accountno = accountno;
}
public int getPin() {
	return pin;
}
public void setPin(int pin) {
	this.pin = pin;
}
public int getCardno() {
	return cardno;
}
public void setCardno(int cardno) {
	this.cardno = cardno;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}

public String getTransactionType() {
	return TransactionType;
}

public void setTransactionType(String transactionType) {
	TransactionType = transactionType;
}
 
 
}
