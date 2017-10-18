package com.dzire.mailing.sentmail.controller;

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
 * Servlet implementation class SentMailServlet
 */
@WebServlet("/SentMailServlet")
public class SentMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	HttpSession session;
	Map<Long,ArrayList<String>> sentMail;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SentMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		con = DBManager.getConnection();
		sentMail = new HashMap<Long,ArrayList<String>>();
		ArrayList<String> list;
		session = request.getSession();
		String emailId = session.getAttribute("email_id").toString();
		
		try{
			String query = "select * from mails where sender_email = ? and sent_status = ? and trash_status=? order by message_id desc";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, emailId);
			ps.setInt(2, 1);
			ps.setInt(3, 0);
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
				sentMail.put(message_id, list);
			}
			con.close();
			request.setAttribute("sentMail", sentMail);
			request.getRequestDispatcher("/mailing/SentMail.jsp").forward(request, response);
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
