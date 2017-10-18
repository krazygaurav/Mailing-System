package com.dzire.mailing.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	public static Connection getConnection(){
		try{
			Connection con=null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mailing","root","root");
			return con;
		}catch(Exception e){
			return null;
		}
	}
}
