package com.dzire.mailing.sentmail.controller;

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

@WebServlet("/SentMailControlServlet")
public class SentMailControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	HttpSession session;   
   
    public SentMailControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String delete=null;
		String move=null;
		System.out.println("here");
		try{
			delete = request.getParameter("sentMailDelete");
			move = request.getParameter("sentMailMoveToInbox");
		}
		catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("SentMailServlet");
		}
		
		PrintWriter out = response.getWriter();
		con = DBManager.getConnection();
		String ids[] = request.getParameterValues("messages");
		if(ids == null){
			response.sendRedirect("SentMailServlet");
		}
		long message_ids[] = new long[ids.length];
		
		
		for(int i=0;i<ids.length;i++){
			message_ids[i] = Long.parseLong(ids[i]);
			//out.println(message_ids[i]);
		}
		session = request.getSession();
		String email_id = session.getAttribute("email_id").toString();
		
		if(delete!=null){
			try{
				System.out.println("here");
				for(int i=0;i<ids.length;i++){
					String query = "update mails set sent_status=?, trash_status=? where message_id=? and sender_email=?";
					System.out.println("here");
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, 1);
					ps.setInt(2, 1);
					ps.setLong(3, message_ids[i]);
					ps.setString(4, email_id);
					int bool = ps.executeUpdate();
					if(bool == 1){
						out.print("OK");
					}
					else{
						out.print("NOT OK");
					}
				}
				con.close();
				response.sendRedirect("SentMailServlet");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(move!=null){
			//out.print("Hello from Krazy Codes. You have pressed <b>Move To Inbox</b> Button");
			
		}
	}
}
