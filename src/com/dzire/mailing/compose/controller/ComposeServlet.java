package com.dzire.mailing.compose.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.validator.routines.EmailValidator;

import com.dzire.mailing.compose.model.ComposeMessage;
import com.dzire.mailing.util.DBManager;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ComposeServlet")
public class ComposeServlet extends HttpServlet {
	Connection con = null;
	HttpSession session;
	String to,cc,bcc,subject,message_body;
	private static final long serialVersionUID = 1L;
     
    public ComposeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		session = request.getSession();
		PrintWriter out = response.getWriter();
		to=request.getParameter("to");
		cc=request.getParameter("cc");
		bcc=request.getParameter("bcc");
		subject=request.getParameter("subject");
		message_body=request.getParameter("message_body");
		
		String emailsTo[] = to.split(",");
		String emailsCc[] = cc.split(",");
		String emailsBcc[] = bcc.split(",");
		List<String> emails = new ArrayList<String>();
		
		for(int j=0;j<emailsTo.length;j++){
			if(emailsTo[j].length() > 8)
				emails.add(emailsTo[j]);
		}
		for(int j=0;j<emailsCc.length;j++){
			if(emailsCc[j].length() > 8)
				emails.add(emailsCc[j]);
		}
		for(int j=0;j<emailsBcc.length;j++){
			if(emailsBcc[j].length() > 8)
				emails.add(emailsBcc[j]);
		}
		
		
		for(int i=0;i<emails.size();i++){
			if(! EmailValidator.getInstance().isValid(emails.get(i))){
				request.setAttribute("to_error", "Invalid Addresses");
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}
		}
	
		if(message_body.length()<1){
			request.setAttribute("message_error", "Message body is Empty");
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		String emailId = session.getAttribute("email_id").toString();
		java.util.Date d = new java.util.Date(session.getCreationTime());
		con = DBManager.getConnection();
		
		Date dd=new Date();		
		//ComposeMessage message = new ComposeMessage();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm aaa");
		String mailingdate=sdf.format(dd);
		
		try{
				//TODO: manipulate the mail here.. get message_id and update in mail table
			for (int i = 0; i < emails.size(); i++) {
				PreparedStatement ps1 = con.prepareStatement("insert into mails(message,starred_status,sender_email,reciever_email,inbox_status,sent_status,draft_status,trash_status,subject,read_status,mailingdate) values(?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
				ps1.setString(1, message_body);
				ps1.setInt(2, 0);
				ps1.setString(3, emailId);
				ps1.setString(4, emails.get(i));
				ps1.setInt(5, 1);
				ps1.setInt(6, 1);
				ps1.setInt(7, 0);
				ps1.setInt(8, 0);
				ps1.setString(9, subject);
				ps1.setInt(10, 0);
				ps1.setString(11,mailingdate);
				
				int bool = ps1.executeUpdate();
				if(bool == 1){
					out.println("Message sent Successfully");
				}
				else{
					out.println("Message sent Not Successfully");
				}
			}
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
