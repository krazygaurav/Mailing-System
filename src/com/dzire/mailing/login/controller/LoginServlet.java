package com.dzire.mailing.login.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.BorderFactory;

import com.dzire.mailing.login.model.CheckLogin;
import com.dzire.mailing.login.model.LoginBean;
import com.dzire.mailing.login.model.LoginModel;
import com.dzire.mailing.util.DBManager;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    HttpSession session;   
    public LoginServlet(){
    	super();
    	//new CheckLogin();
    	//new CheckLogin();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean bean = new LoginBean();
		bean.setUsername(username);
		bean.setPassword(password);
		
		LoginModel model = new LoginModel(bean);
		boolean status = model.doLogin();
		if(status){
			session = request.getSession(true);
			session.setMaxInactiveInterval(60*60);
			session.setAttribute("email_id", bean.getEmail_id());
			session.setAttribute("username", bean.getUsername());
			response.sendRedirect(request.getContextPath()+"/mailing/static.jsp");
		}
		else{
			request.setAttribute("login_error", "Wrong Credentials");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
	}

}
