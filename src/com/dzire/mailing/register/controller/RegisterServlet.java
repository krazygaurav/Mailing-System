package com.dzire.mailing.register.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dzire.mailing.register.model.RegisterBean;
import com.dzire.mailing.register.model.RegisterModel;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterBean bean;
	private RegisterModel model;
	
	public RegisterServlet(){
		bean = new RegisterBean();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String mobile = request.getParameter("mobile");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String id = request.getParameter("email_id");
			String password = request.getParameter("password");
			String confirmPass = request.getParameter("repassword");
			String country = request.getParameter("country");
			String state = request.getParameter("state");
			String city = request.getParameter("city");
			
			//checking validations
			if(name.length() < 5) {
				request.setAttribute("name_error", "Invalid Name<br/>");
				request.getRequestDispatcher("/register.jsp").forward(request,response);		
				//response.sendRedirect("register.jsp");
			}
			else if(mobile.length() != 10){
				request.setAttribute("phone_error", "Invalid Mobile no<br/>");
				request.getRequestDispatcher("/register.jsp").forward(request,response);		
				//response.sendRedirect("register.jsp");
			}
			else if(dob.equals("")) {
				request.setAttribute("message7", "select birth date<br/>");
				request.getRequestDispatcher("/register.jsp").forward(request,response);			
			}
			else if(password.length() < 4 ) {
				request.setAttribute("password_error", "Password length must be greater than 4<br/>");
				request.getRequestDispatcher("/register.jsp").forward(request,response);			
				//response.sendRedirect("register.jsp");
			}
			else if(!confirmPass.equals(password)) {
				request.setAttribute("repassword_error", "Password Not Match<br/>");
				request.getRequestDispatcher("/register.jsp").forward(request,response);			
				//response.sendRedirect("register.jsp");
			}
			else if(state.equals("") || city.equals("")){
				request.setAttribute("place_error", "Please Select your place<br/>");
				request.getRequestDispatcher("/register.jsp").forward(request,response);
			} 
			else {
				//checking whether or not Id available
				bean.setCity(city);
				bean.setCountry(country);
				bean.setDob(dob);
				bean.setGender(gender);
				bean.setId(id);
				bean.setMobile(mobile);
				bean.setName(name);
				bean.setPassword(password);
				bean.setState(state);
				bean.setUsername(username);
				model = new RegisterModel(bean);
				String status = model.doRegister();
				if(status.equals("success")){
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}else if(status.equals("failure")){
					
				}else if(status.equals("email_error")){
					response.sendRedirect(request.getContextPath()+"/register");
				}else if(status.equals("username_error")){
					response.sendRedirect(request.getContextPath()+"/register");
				}else{
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
