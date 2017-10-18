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
@WebServlet("/Username")
public class Username extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
			Connection con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from login where username='"+username+"'");
			//PreparedStatement ps = con.prepareStatement("select * from login where username like '"+username+"%'");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				out.print("<font color='red'>The nickname <STRONG>"+username+"</STRONG> is already in use.</font>");
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
