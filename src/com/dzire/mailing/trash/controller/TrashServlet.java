package com.dzire.mailing.trash.controller;

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
 * Servlet implementation class TrashServlet
 */
@WebServlet("/TrashServlet")
public class TrashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	HttpSession session;
	Map<Long,ArrayList<String>> trash;
	
	public TrashServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		con = DBManager.getConnection();
		trash = new HashMap<Long,ArrayList<String>>();
		ArrayList<String> list;
		session = request.getSession();
		String emailId = session.getAttribute("email_id").toString();
		String trash_type="";
		try{
			String query = "select * from mails where (reciever_email=? or sender_email=?) and trash_status = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, emailId);
			ps.setString(2, emailId);
			ps.setInt(3, 1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Long message_id = rs.getLong("message_id");
				String from = rs.getString("reciever_email");
				String sender = rs.getString("sender_email");
				String subject = rs.getString("subject");
				String message = rs.getString("message");
				String time = rs.getString("mailingdate");
				if(from.equalsIgnoreCase(emailId))
					trash_type = "From : "+ sender;
				else
					trash_type = "To : "+ from;
				list = new ArrayList<String>();
				//new TrashMessage();
				list.add(0, trash_type);
				list.add(1,subject);
				list.add(2,message);
				list.add(3,time);
				trash.put(message_id, list);
			}
			con.close();
			request.setAttribute("trash", trash);
			request.getRequestDispatcher("/mailing/Trash.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
