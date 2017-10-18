package com.dzire.mailing.replyforward.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dzire.mailing.replyforward.model.ReplyForwardBean;
import com.dzire.mailing.replyforward.model.ReplyForwardModel;;

@WebServlet("/ForwardServlet")
public class ReplyForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		String email_id = session.getAttribute("email_id").toString();
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String message_body = request.getParameter("message");
		
		ReplyForwardBean bean = new ReplyForwardBean();
		bean.setTo(to);
		bean.setSubject(subject);
		bean.setMessage_body(message_body);
		bean.setEmail_id(email_id);
		
		ReplyForwardModel model = new ReplyForwardModel(bean);
		boolean validate = model.validatingEmail();
		//not tested yet
		if(!validate){
			request.setAttribute("send_error", "Invalid Addresses");
			request.getRequestDispatcher(request.getContextPath()+"/OpenInboxMessageServlet").forward(request, response);
		}
		
		boolean sent = model.storeData();
		if(sent){
			//message sent
			//dispatcher does not use here
			response.sendRedirect(request.getContextPath()+"/InboxServlet");
		}
		else{
			//message not sent and feedback the user about it
			response.sendRedirect(request.getContextPath()+"/InboxServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
