<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.Set"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/mailing/css/list.css" type="text/css" />
	<%@include file="static.jsp" %>
		<form action="<%=request.getContextPath() %>/ComposeServlet" method="post" onsubmit="return compose_validate();">
       <div class="container" id="compose" onClick="open_compose()">
        	<div class="content-header">
	            	<input type="submit" value="SEND" class="button-send"/>
                    <input type="button" value="SAVE TO DRAFT" class="button-send"/>
            </div>
            <div class="content">
            	<input type="text" class="compose-text" placeholder="To" name="to" id="to" required/>
                <div id="to_error">${to_error}</div>
                <input type="text" class="compose-text" placeholder="Cc" name="cc" id="cc"/>
                <input type="text" class="compose-text" placeholder="Bcc" name="bcc" id="bcc"/>
                <input type="text" class="compose-text" placeholder="Subject" name="subject" id="subject"/>
                <div id="subject_error"></div>
                <textarea name="message_body" id="message_body" cols="100"></textarea>
                <div id="message_error">${message_error}</div>
            </div>
        </div>
        </form>
	</div>
</body>
</html>