package com.dzire.mailing.inbox.controller;

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
public class InboxControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	HttpSession session;   
   
    public InboxControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String delete=null,read=null,starred=null,unread=null;
		try{
			delete = request.getParameter("inboxDelete");
			read = request.getParameter("inboxMarkAsRead");
			unread = request.getParameter("inboxMarkAsUnRead");
			starred = request.getParameter("inboxMarkAsStarred");
		}
		catch(Exception e){
			response.sendRedirect("InboxServlet");
		}
		
		PrintWriter out = response.getWriter();
		con = DBManager.getConnection();
		String ids[] = request.getParameterValues("messages");
		if(ids == null){
			response.sendRedirect("InboxServlet");
		}
		long message_ids[] = new long[ids.length];
		
		
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
					String query = "update mails set inbox_status=?, trash_status=? where message_id=?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, 0);
					ps.setInt(2, 1);
					ps.setLong(3, message_ids[i]);
					int bool = ps.executeUpdate();
					if(bool == 1){
						//out.print("OK");
					}
					else{
						//out.print("NOT OK");
					}
				}
				con.close();
				response.sendRedirect("InboxServlet");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(read!=null){
			try{
				for(int i=0;i<ids.length;i++){
					String query = "update mails set read_status=? where message_id=? and read_status=?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, 1);
					ps.setLong(2, message_ids[i]);
					ps.setInt(3, 0);
					int bool = ps.executeUpdate();
					if(bool == 1){
						out.print("OK");
					}
					else{
						out.print("NOT OK");
					}
				}
				con.close();
				response.sendRedirect("InboxServlet");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(unread!=null){
			try{
				for(int i=0;i<ids.length;i++){
					String query = "update mails set inbox_status=?, trash_status=? read_status=? where message_id=?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, 0);
					ps.setInt(2, 1);
					ps.setInt(3, 0);
					ps.setLong(4, message_ids[i]);
					int bool = ps.executeUpdate();
					if(bool == 1){
						out.print("OK");
					}
					else{
						out.print("NOT OK");
					}
				}
				con.close();
				response.sendRedirect("InboxServlet");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(starred!=null){
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
				response.sendRedirect("InboxServlet");
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
