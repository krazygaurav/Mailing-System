package com.dzire.mailing.draft.controller;

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
public class DraftControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	HttpSession session;   
   
    public DraftControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String delete=null,move=null;
		try{
			delete = request.getParameter("draftDelete");
			move = request.getParameter("draftMoveToInbox");
		}
		catch(Exception e){
			response.sendRedirect("DraftServlet");
		}
		
		PrintWriter out = response.getWriter();
		con = DBManager.getConnection();
		String ids[] = request.getParameterValues("messages");
		if(ids == null){
			response.sendRedirect("DraftServlet");
		}
		long message_ids[] = new long[ids.length];
		
		
		for(int i=0;i<ids.length;i++){
			message_ids[i] = Long.parseLong(ids[i]);
		}
		session = request.getSession();
		String email_id = session.getAttribute("email_id").toString();
		if(delete!=null){
			try{
				for(int i=0;i<ids.length;i++){
					String query = "update mails set trash_status=? where message_id=? and sender_email=?";
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
				response.sendRedirect("DraftServlet");
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
