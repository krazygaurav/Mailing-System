package com.dzire.mailing.sentmail.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dzire.mailing.sentmail.model.DeleteSentMessageBean;
import com.dzire.mailing.sentmail.model.DeleteSentMessageModel;

@WebServlet("/DeleteSentMessageServlet")
public class DeleteSentMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("button")!=null){
			String message_id=request.getParameter("message_id");
			session = request.getSession();
			String email_id = session.getAttribute("email_id").toString();
			
			DeleteSentMessageBean bean = new DeleteSentMessageBean();
			bean.setMessage_id(message_id);
			bean.setEmail_id(email_id);
			
			DeleteSentMessageModel model = new DeleteSentMessageModel(bean);
			
			boolean status = model.deleteMessage();
			if(status){
				//message deleted Successful
				response.sendRedirect(request.getContextPath()+"/SentMailServlet");
			}
			else{
				response.sendError(500, "Error in deleting the message");
			}
		}
		else{
			response.sendRedirect(request.getContextPath()+"/SentMailServlet");
		}
	}

}
