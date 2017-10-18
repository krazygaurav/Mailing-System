package com.dzire.mailing.register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dzire.mailing.util.DBManager;
@WebServlet("/Email")
public class Email extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			PrintWriter out = response.getWriter();
			String email_id = request.getParameter("email_id");
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from login where email_id ='"+email_id+"'");
			//PreparedStatement ps = con.prepareStatement("select * from login where email_id like '"+email_id+"%'");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				out.print("<font color='red'>The nickname <STRONG>"+email_id+"</STRONG> is already in use.</font>");
				//out.print(rs.getString("email_id"));
			}
			else{
				out.print("true");	
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
