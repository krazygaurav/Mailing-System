package com.dzire.mailing.starred.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dzire.mailing.util.DBManager;

/**
 * Servlet implementation class SentMailControlServlet
 */
@WebServlet("/SentMailControlServlet")
public class StarredControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	HttpSession session;   
   
    public StarredControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String delete=null,unstar=null;
		try{
			delete = request.getParameter("starredDelete");
			unstar = request.getParameter("starredUnstar");
		}
		catch(Exception e){
			response.sendRedirect("StarredServlet");
		}
		
		PrintWriter out = response.getWriter();
		con = DBManager.getConnection();
		String ids[] = request.getParameterValues("messages");
		long message_ids[] = null ;
		try {
			System.out.println("try");
			message_ids = new long[ids.length];
		}catch(NullPointerException e){
			System.out.println("catch");
			response.sendRedirect("StarredServlet");
		}
		
		for(int i=0;i<ids.length;i++){
			message_ids[i] = Long.parseLong(ids[i]);
			out.println(message_ids[i]);
		}
		session = request.getSession();
		String email_id = session.getAttribute("email_id").toString();
		if(delete!=null){
			//out.print("Hello from Krazy Codes. You have pressed <b>Delete</b> Button");
			try{
				for(int i=0;i<ids.length;i++){
					String query = "update mails set trash_status=? where message_id=? and reciever_email=?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, 1);
					ps.setLong(2, message_ids[i]);
					ps.setString(3, email_id);
					int bool = ps.executeUpdate();
					if(bool == 1){
						out.print("OK");
					}
					else{
						out.print("NOT OK");
					}
				}
				con.close();
				response.sendRedirect("StarredServlet");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(unstar!=null){
			try{
				for(int i=0;i<ids.length;i++){
					String query = "update mails set starred_status=? where message_id=? and reciever_email=?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, 1);
					ps.setLong(2, message_ids[i]);
					ps.setString(3, email_id);
					int bool = ps.executeUpdate();
					if(bool == 1){
						out.print("OK");
					}
					else{
						out.print("NOT OK");
					}
				}
				con.close();
				response.sendRedirect("StarredServlet");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
