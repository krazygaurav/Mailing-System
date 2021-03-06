package com.dzire.mailing.starred.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dzire.mailing.util.DBManager;

/**
 * Servlet implementation class StarredServlet
 */
@WebServlet("/StarredServlet")
public class StarredServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	HttpSession session;
	Map<Long,ArrayList<String>> starred;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StarredServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		con = DBManager.getConnection();
		starred = new HashMap<Long,ArrayList<String>>();
		ArrayList<String> list;
		session = request.getSession();
		String emailId = session.getAttribute("email_id").toString();
		
		try{
			String query = "select * from mails where reciever_email = ? and inbox_status = ? and starred_status=? and trash_status=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, emailId);
			ps.setInt(2, 1);
			ps.setInt(3, 1);
			ps.setInt(4, 0);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Long message_id = rs.getLong("message_id");
				String from = rs.getString("reciever_email");
				String subject = rs.getString("subject");
				String message = rs.getString("message");
				String time = rs.getString("mailingdate");
				list = new ArrayList<String>();
				list.add(0, from);
				list.add(1,subject);
				list.add(2,message);
				list.add(3,time);
				starred.put(message_id, list);
			}
			con.close();
			request.setAttribute("starred", starred);
			request.getRequestDispatcher("/mailing/Starred.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
