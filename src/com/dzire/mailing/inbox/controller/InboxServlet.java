package com.dzire.mailing.inbox.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import com.dzire.mailing.login.model.CheckLogin;
import com.dzire.mailing.util.DBManager;

@WebServlet("/InboxServlet")
public class InboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	HttpSession session;
	Map<Long,ArrayList<String>> inbox;
    public InboxServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
		
		con = DBManager.getConnection();
		
		inbox = new HashMap<Long,ArrayList<String>>();
		ArrayList<String> list;
		
		session = request.getSession();
		String emailId = session.getAttribute("email_id").toString();
		
		try{
			//getting read messages
			String query = "select * from mails where reciever_email = ? and inbox_status = ? and trash_status = ? order by message_id desc";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, emailId);
			ps.setInt(2, 1);
			ps.setInt(3, 0);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Long message_id = rs.getLong("message_id");
				String from = rs.getString("sender_email");
				String subject = rs.getString("subject");
				String message = rs.getString("message");
				String time = rs.getString("mailingdate");
				String read_status = ""+rs.getInt("read_status");
				list = new ArrayList<String>();
				list.add(0, from);
				list.add(1,subject);
				list.add(2,message);
				list.add(3,read_status);
				list.add(4, time);
			
				inbox.put(message_id, list);
			}
			
			con.close();
			request.setAttribute("inbox", inbox);
			request.getRequestDispatcher("/mailing/Inbox.jsp").forward(request, response);
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