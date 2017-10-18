<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.*"%>
	<%@include file="static.jsp" %>
		<div class="container" id="sent_mail">
		<form action="SentMailControlServlet" method="get">
        	<div class="content-header">
                <input type="submit" value="Delete" class="button01" name="sentMailDelete"/>
                <input type="submit" value="Move to Inbox" class="button01" name="sentMailMoveToInbox"/>
			</div>
        	<div class="content">
			<%	
			Map<Long,ArrayList<String>> mail;
			try{
				mail = (Map<Long, ArrayList<String>>)request.getAttribute("sentMail");
				Set set = mail.keySet();
	        	Iterator t=set.iterator();
	        	
	        	while(t.hasNext()) {
	      			Long key = (Long)t.next();
	      			ArrayList<String> component = (ArrayList<String>)mail.get(key);
	      			String s[] = new String[component.size()+1];
        			s[0] = component.get(0);
        			s[1] = component.get(1);
        			s[2] = component.get(2);
        			s[3] = component.get(3);
	    			if(s[0].length() > 25)
	      				s[0] = s[0].substring(0, 23) + "..";
	      			if(s[1].length() > 30)
	      				s[1] = s[1].substring(0, 30) + "..";
	      			if(s[2].length() > 25)
	      				s[2] = s[2].substring(0, 25) + "..";
	      			%>
	      			<div class="button02"><input type="checkbox" value="<%=key%>" name="messages">
	      			<a href="<%=request.getContextPath() %>/OpenSentMailMessageServlet?id=<%=key%>">
	      				<div class="from"><%out.println("To: "+s[0]);%></div>
	      				<div class="message_center">
	      				<span class="message_subject"><%out.println(s[1]);%></span>
	      				<span class="message_body"><%out.println(s[2]);%></span>
	      				</div>
	      				<div class="message_time"><%out.println(s[3]);%></div>
	      			</a>
	      			</div>
	      			<%
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