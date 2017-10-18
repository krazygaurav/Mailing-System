package com.dzire.mailing.sentmail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dzire.mailing.sentmail.model.OpenSentMailMessageBean;
import com.dzire.mailing.sentmail.model.OpenSentMailMessageModel;

@WebServlet("/OpenSentMailMessageServlet")
public class OpenSentMailMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	Map<Long,ArrayList<String>> message;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message_id = request.getParameter("id");
		if(message_id == null){
			request.getRequestDispatcher("/mailing/SentMessage.jsp").forward(request, response);
		}
		session = request.getSession();
		String email_id = session.getAttribute("email_id").toString();
		if(message_id == null ||  message_id.equals("") || email_id==null){
			//through the user to login page
			response.sendRedirect("mailing/static.jsp");
		}
		long id = Long.parseLong(message_id);
		message = new HashMap<Long,ArrayList<String>>();
		
		OpenSentMailMessageBean bean = new OpenSentMailMessageBean();
		bean.setEmail_id(email_id);
		bean.setId(id);
		bean.setMessage(message);
		
		OpenSentMailMessageModel model = new OpenSentMailMessageModel(bean);
		boolean status = model.openMessage();
		if(status){
			//success
			request.setAttribute("message", bean.getMessage());
			request.getRequestDispatcher("/mailing/SentMessage.jsp").forward(request, response);
		}
		else{
			//failure
			response.sendError(500,"Error in opening message. check OpenSentMessageServlet");
		}
	}
}
