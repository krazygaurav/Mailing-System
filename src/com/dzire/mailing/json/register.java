package com.dzire.mailing.json;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dzire.mailing.util.DBManager;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<Data> country = new LinkedList<Data>();
		try{
			Connection con=DBManager.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from country");
			while(rs.next()){
				country.add(new Data(rs.getInt("id"), rs.getString("country_name")));
			}
			con.close();
			request.setAttribute("country", country);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
