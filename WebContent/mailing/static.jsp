<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*;"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Krazy Codes</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/mailing/css/home-style.css" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath() %>/mailing/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/mailing/js/jquery.autocomplete.js"></script>
<script type="text/javascript">
	/*$(function(){
		$("#to").autocomplete("GetEmails.jsp");
	});*/
	$('document').ready(function(e) {
        $('#reply_button').click(function(e) {
			$('#reply-forward').slideUp(500);
            $('#reply').slideDown(500);
        });
        $('#forward_botton').click(function(e) {
			$('#reply-forward').slideUp(500);
            $('#forward').slideDown(500);
        });
    });
</script>
</head>
<body>
	<%
		//session.setAttribute("email_id", "krazy.gaurav92@gmail.com");
		
		try{		
			String email_id = session.getAttribute("email_id").toString();	
		}
		catch(Exception e){
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		
	%>
	<div class="main">
    	<!--Header part-->
        <div class="header">
        	<div class="logo">
            	<img src="<%=request.getContextPath() %>/mailing/images/logo.jpg" alt="logo" width="100" height="75"/>
            </div>
            <form action="#" method="get">
            	<input type="search" placeholder="Search" class="search-box"/>
            </form>
            <div class="logout">
            	<ul>
                	<li><%=session.getAttribute("email_id").toString() %></li>
                    <li><img src="<%=request.getContextPath() %>/mailing/images/21.Jpg" /></li>
                </ul>
                <a href="<%=request.getContextPath() %>/logout.jsp">Log Out</a>
            </div>
            <div class="clear"></div>
        </div>
        
        <!--sideBar part-->
        <div class="side-bar">
           	<ul>
           		<li  class="compose-button"><a href="<%=request.getContextPath() %>/mailing/Compose.jsp">COMPOSE</a></li>
           	</ul>
            <ul>
            	<li><a href="<%=request.getContextPath() %>/InboxServlet" onClick="open_inbox()">Inbox</a></li>
                <li><a href="<%=request.getContextPath() %>/StarredServlet" onClick="open_stared()" >Starred</a></li>
                <li><a href="<%=request.getContextPath() %>/SentMailServlet" onClick="open_sentMail()">Sent Mails</a></li>
                <li><a href="<%=request.getContextPath() %>/DraftServlet" onClick="open_draft()">Drafts</a></li>
                <li><a href="<%=request.getContextPath() %>/TrashServlet" onClick="open_trash()">Trash</a></li>
            </ul>
            <div class="tipOfTheDay">
            	<h2>Tip of the Day</h2>
                <p>Hello. Today is June 16, 2014. Have a nice day.</p>
                <p>"Keep your feet on the ground and keep reaching for the stars."	
				-Casey Kasem</p>
            </div>
        </div>
    
