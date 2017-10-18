<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*,com.dzire.mailing.login.model.*"%>
	<%response.setIntHeader("Refresh", 50000); %>
	<%@include file="static.jsp" %>
		<div class="container" id="inbox">
		<form action="InboxControlServlet" method="get">
        	<div class="content-header">
                <input type="submit" value="Delete" class="button01" name="inboxDelete"/>
<!--                 <input type="submit" value="Mark as read" class="button01" name="inboxMarkAsRead"/> -->
<!--                 <input type="submit" value="Mark as unread" class="button01" name="inboxMarkAsUnRead"/> -->
                <input type="submit" value="Mark Starred" class="button01" name="inboxMarkAsStarred"/>
			</div>
        	<div class="content">
        	  <%	
        	  try{
        	  Map<Long,ArrayList<String>> mail = (Map<Long, ArrayList<String>>)request.getAttribute("inbox");
        	  
        	  Set set = mail.keySet();
        	  Iterator t=set.iterator();
        	  //new CheckLogin();
        	  while(t.hasNext()) {
        		  	Long key = (Long)t.next();
        			ArrayList<String> component = (ArrayList<String>)mail.get(key);
        			String s[] = new String[component.size()+1];
        			s[0] = component.get(0);
        			s[1] = component.get(1);
        			s[2] = component.get(2);
        			s[3] = component.get(3);
        			s[4] = component.get(4);
        			if(s[0].length() > 25)
        				s[0] = s[0].substring(0, 23) + "..";
        			if(s[1].length() > 30)
        				s[1] = s[1].substring(0, 30) + "..";
        			if(s[2].length() > 25)
        				s[2] = s[2].substring(0, 25) + "..";
        			String read_status = s[3];
        			if(read_status.equals("1")){
        			%>
        			<div class="button02"><input type="checkbox" value="<%=key%>" name="messages">
        			<a href="<%=request.getContextPath() %>/OpenInboxMessageServlet?id=<%=key%>">
        				<div class="from"><%out.println("From: "+s[0]);%></div>
        				<div class="message_center">
        				<span class="message_subject"><%out.println(s[1]);%></span>
        				<span class="message_body"><%out.println(s[2]);%></span>
        				</div>
        				<div class="message_time"><%out.println(s[4]);%></div>
        			</a>
        			</div>
      			<%
        			}
        			else{
        		%>
        			<div class="button03"><input type="checkbox" value="<%=key%>" name="messages">
        			<a href="OpenInboxMessageServlet?id=<%=key%>">	
        				<div class="from" style="color:#CED0FA !important;"><%out.println("From: "+s[0]);%></div>
        				<div class="message_center"  style="color:#CED0FA !important;">
        				<span class="message_subject"><%out.println(s[1]);%></span>
        				<span class="message_body"><%out.println(s[2]);%></span>
        				</div>
        				<div class="message_time"  style="color:#CED0FA !important;"><%out.println(s[4]);%></div>
        			</a>	
        			</div>	
        		<% 
        			}
        	  }
        	  }
        	  catch(Exception e){
  				response.sendRedirect("static.jsp");
  			  }
				%>
			</div>
			</form>
		</div>
	</div>
</body>
</html>