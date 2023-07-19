package com.atminterface.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
public class DB {
public static Connection getCon(){
	Connection con=null;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atminterface","root","7972866545");
	}catch(Exception ex){System.out.println(ex);}
	return con;
}
}

