package com.dzire.mailing.register.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dzire.mailing.util.DBManager;

public class RegisterModel {
	RegisterBean bean;
	public RegisterModel(RegisterBean bean){
		this.bean=bean;
	}
	
	public String doRegister(){
		try{
			Connection con = DBManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select * from login where email_id=?");
			pstmt.setString(1, bean.getId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return "email_error";
			}
			PreparedStatement pstmt1 = con.prepareStatement("select * from login where username=?");
			pstmt1.setString(1, bean.getUsername());
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				return "username_error";
			}
			else {
				Statement st = con.createStatement();
				String a = "select * from country where id="+Integer.parseInt(bean.getCountry());
				ResultSet ars = st.executeQuery(a);
				String country="";
				if(ars.next()){
					System.out.println("here");
					country = ars.getString("country_name");
					System.out.println("here 1");
				}
				System.out.println(bean.getCountry()+country);
				ars.close();
				a = "select * from state where id="+Integer.parseInt(bean.getState());
				ars = st.executeQuery(a);
				String state="";
				if(ars.next()){
					state = ars.getString("state_name");
				}
				System.out.println(bean.getState()+state);
				ars.close();
				a = "select * from city where id="+Integer.parseInt(bean.getCity());
				ars = st.executeQuery(a);
				String city="";
				if(ars.next()){
					city = ars.getString("city_name");
				}
				System.out.println(bean.getCity()+city);
				ars.close();
				
				PreparedStatement stmt = con.prepareStatement("insert into login(city,country,dob,gender,email_id,mobile,name,state,username,password) values(?,?,?,?,?,?,?,?,?,?)");
				stmt.setString(1,city);
				stmt.setString(2,country);
				stmt.setString(3,bean.getDob());
				stmt.setString(4,bean.getGender());
				stmt.setString(5,bean.getId());
				stmt.setString(6,bean.getMobile());
				stmt.setString(7,bean.getName());
				stmt.setString(8, state);
				stmt.setString(9, bean.getUsername());
				stmt.setString(10, bean.getPassword());
				int update = stmt.executeUpdate();
				con.close();
				if(update == 0){
					return "failure";
				}
				return "success";
			}
		}
		catch(Exception e) {	
			e.printStackTrace();
			return "failure";
		}
	}
}
