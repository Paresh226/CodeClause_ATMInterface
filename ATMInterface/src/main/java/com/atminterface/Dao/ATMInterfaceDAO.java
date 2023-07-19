package com.atminterface.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.*;

import com.atminterface.bean.*;
 

public class ATMInterfaceDAO {
	public static boolean authenticateUser(int pin, int cardno) {
	    boolean isAuthenticated = false;
	    try {
	        Connection con = DB.getCon();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM bankatm WHERE cardnumber = ? AND pin = ?");
	        ps.setInt(1, cardno);
	        ps.setInt(2, pin);

	        ResultSet rs = ps.executeQuery();
	        isAuthenticated = rs.next(); // Check if a record exists in the result set

	        con.close();
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    return isAuthenticated;
	}
	
	public static boolean authenticateaccount(int accountno, int cardno) {
	    boolean isAuthenticated = false;
	    try {
	        Connection con = DB.getCon();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM bankatm WHERE cardnumber = ? AND accountnumber = ?");
	        ps.setInt(1, cardno);
	        ps.setInt(2, accountno);
	        ResultSet rs = ps.executeQuery();
	        isAuthenticated = rs.next(); // Check if a record exists in the result set
	        con.close();
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    return isAuthenticated;
	}

	
	public static int Deposit(ATMInterfaceBean bean) {
	    int status = 0;
	    try {
	        Connection con = DB.getCon();
	        PreparedStatement ps = con.prepareStatement("UPDATE bankatm SET amount = amount + ? WHERE cardnumber = ? AND pin = ?");
	        ps.setInt(1, bean.getAmount());
	        ps.setInt(2, bean.getCardno());
	        ps.setInt(3, bean.getPin());
	        status = ps.executeUpdate();

	        if (status > 0) {
	            PreparedStatement psInsert = con.prepareStatement("INSERT INTO transaction (cardnumber, amount, transaction_type, transactiondate) VALUES (?, ?, ?, ?)");
	            psInsert.setInt(1, bean.getCardno());
	            psInsert.setInt(2, bean.getAmount());
	            psInsert.setString(3, bean.getTransactionType());
	            psInsert.setTimestamp(4, new Timestamp(new Date().getTime()));
	            psInsert.executeUpdate();
	        }
	        con.close();
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    return status;
	}

	
	public static int ForgetPin( int pin, int cardno) {
	    int status = 0;
	    try {
	        Connection con = DB.getCon();
	        PreparedStatement ps = con.prepareStatement("UPDATE bankatm SET pin = ? WHERE cardnumber = ?");
	        ps.setInt(1, pin);
	        ps.setInt(2, cardno);
	        status = ps.executeUpdate();
	        con.close();
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    return status;
	}
		
	public static int Withdraw(ATMInterfaceBean bean) {
	    int status = 0;
	    try {
	        Connection con = DB.getCon();
	        PreparedStatement ps = con.prepareStatement("UPDATE bankatm "
	                + "SET amount = CASE WHEN amount >= ? THEN amount - ? ELSE amount END "
	                + "WHERE cardnumber = ? AND pin = ?");
	        //ps.setInt(1, amount);
	        ps.setInt(1, bean.getAmount());
	        ps.setInt(2, bean.getAmount());
	        ps.setInt(3, bean.getCardno());
	        ps.setInt(4, bean.getPin());
	        status = ps.executeUpdate();
	        if (status > 0) {
	            PreparedStatement psInsert = con.prepareStatement("INSERT INTO transaction (cardnumber, amount, transaction_type, transactiondate) VALUES (?, ?, ?, ?)");
	            psInsert.setInt(1, bean.getCardno());
	            psInsert.setInt(2, bean.getAmount());
	            psInsert.setString(3, bean.getTransactionType());
	            psInsert.setTimestamp(4, new Timestamp(new Date().getTime()));
	            psInsert.executeUpdate();
	        }
	        con.close();
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    return status;
	}
	public static int CheckBalance(int pin, int cardno) {
	    int balance = 0;
	    try {
	        Connection con = DB.getCon();
	        PreparedStatement ps = con.prepareStatement("SELECT amount FROM bankatm WHERE cardnumber = ? AND pin = ?");
	        ps.setInt(1, cardno);
	        ps.setInt(2, pin);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            balance = rs.getInt("amount");
	        } else {
	            // Handle the case when the card number and PIN combination is invalid
	            balance = -1;
	        }
	        con.close();
	    } catch (Exception ex) {
	        System.out.println(ex);
	        balance = -1; // Set balance to -1 in case of an exception
	    }
	    return balance;
	}
	
	public static ATMInterfaceBean getBalance(int pin, int cardno) {
	    ATMInterfaceBean bean = new ATMInterfaceBean();
	    try {
	        Connection con = DB.getCon();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM bankatm WHERE cardnumber = ? AND pin = ?");
	        ps.setInt(1, cardno);
	        ps.setInt(2, pin);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            bean.setPin(rs.getInt("pin"));
	            bean.setCardno(rs.getInt("cardnumber"));
	            bean.setAmount(rs.getInt("amount"));
	            bean.setAccountno(rs.getInt("accountnumber"));
	        } else {
	            // Handle the case when the card number and PIN combination is invalid
	            bean = null;
	        }
	        con.close();
	    } catch (Exception ex) {
	        //System.out.println(ex);
	       // bean = null; // Set bean to null in case of an exception
	    }
	    return bean;
	} 
	public static List<StatmentBean> getStatementBeans() {
	    List<StatmentBean> statementBeans = new ArrayList<>();
	    try {
	        Connection con = DB.getCon();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM transaction");
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            StatmentBean bean = new StatmentBean();
	            bean.setTransactionid(rs.getInt("transactionid"));
	            bean.setCardno(rs.getInt("cardnumber"));
	            bean.setAmount(rs.getInt("amount"));
	            bean.setTransactionDate(rs.getDate("transactiondate"));
	            bean.setTransactionType(rs.getString("transaction_type"));
	            statementBeans.add(bean);
	        }
	        con.close();
	    } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    return statementBeans;
	}


 


 
}
