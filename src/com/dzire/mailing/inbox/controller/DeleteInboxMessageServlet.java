package com.dzire.mailing.inbox.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dzire.mailing.inbox.model.DeleteInboxMessageBean;
import com.dzire.mailing.inbox.model.DeleteInboxMessageModel;

@WebServlet("/DeleteInboxMessageServlet")
public class DeleteInboxMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("button")!=null){
			String message_id=request.getParameter("message_id");
			session = request.getSession();
			String email_id = session.getAttribute("email_id").toString();
			
			DeleteInboxMessageBean bean = new DeleteInboxMessageBean();
			bean.setMessage_id(message_id);
			bean.setEmail_id(email_id);
			
			DeleteInboxMessageModel model = new DeleteInboxMessageModel(bean);
			boolean status = model.deleteMessage();
			if(status){
				//message deleted Successful
				response.sendRedirect(request.getContextPath()+"/InboxServlet");
			}
			else{
				response.sendError(500, "Error in deleting the message");
				response.sendRedirect(request.getContextPath()+"/InboxServlet");
			}
		}
		else{
			response.sendRedirect(request.getContextPath()+"/InboxServlet");
		}
	}

}
