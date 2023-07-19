package com.atminterface.bean;

import java.util.Date;

public class StatmentBean {
	 private int cardno;
	    private int amount;
	    private int transactionid;
	    private String transactionType;
	    private Date transactionDate;

	    
	    
	    public StatmentBean( ) {
			super();
			 
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
	        return transactionType;
	    }

	    public void setTransactionType(String transactionType) {
	        this.transactionType = transactionType;
	    }

	    public Date getTransactionDate() {
	        return transactionDate;
	    }

	    public void setTransactionDate(Date transactionDate) {
	        this.transactionDate = transactionDate;
	    }

		public int getTransactionid() {
			return transactionid;
		}

		public void setTransactionid(int transactionid) {
			this.transactionid = transactionid;
		}
	    
	}