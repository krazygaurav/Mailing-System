package com.dzire.mailing.starred.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dzire.mailing.starred.model.DeleteStarredMessageBean;
import com.dzire.mailing.starred.model.DeleteStarredMessageModel;

@WebServlet("/DeleteStarredMessageServlet")
public class DeleteStarredMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("button")!=null){
			String message_id=request.getParameter("message_id");
			session = request.getSession();
			String email_id = session.getAttribute("email_id").toString();
			
			DeleteStarredMessageBean bean = new DeleteStarredMessageBean();
			bean.setMessage_id(message_id);
			bean.setEmail_id(email_id);
			
			DeleteStarredMessageModel model = new DeleteStarredMessageModel(bean);
			boolean status = model.deleteMessage();
			if(status){
				//message deleted Successful
				response.sendRedirect(request.getContextPath()+"/StarredServlet");
			}
			else{
				response.sendError(500, "Error in deleting the message");
				response.sendRedirect(request.getContextPath()+"/StarredServlet");
			}
		}
		else{
			response.sendRedirect(request.getContextPath()+"/StarredServlet");
		}
	}

}
