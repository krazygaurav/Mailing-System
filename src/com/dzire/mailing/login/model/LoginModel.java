package com.dzire.mailing.login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dzire.mailing.util.DBManager;

public class LoginModel {
	LoginBean bean;
	public LoginModel(LoginBean bean){
		this.bean=bean;
	}
	public boolean doLogin(){
		try{
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from login where username=? and password=?");
			
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getPassword());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				bean.setEmail_id(rs.getString("email_id"));
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
