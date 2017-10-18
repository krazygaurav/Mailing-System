
	<%@include file="static.jsp" %>
		<%
			Map<Long,ArrayList<String>> mail = (Map<Long, ArrayList<String>>)request.getAttribute("message");
        	  
        	  Set set = mail.keySet();
        	  Iterator t=set.iterator();
        	  String rcv[] = new String[4];
        	  String message_id="";
        	  while(t.hasNext()) {
        		  	Long key = (Long)t.next();
        			ArrayList<String> component = (ArrayList<String>)mail.get(key);
        			rcv[0] = component.get(0);
        			rcv[1] = component.get(1);
        			rcv[2] = component.get(2);
        			rcv[3] = component.get(3);
        			message_id = component.get(4);
        			if(rcv[0].length() > 100)
        				rcv[0] = rcv[0].substring(0, 100) + "..";
        	  }
      	%>
      	<a href="StarredServlet" class="message-controls">
        	<div class="ar6"></div>
        </a>
        <form action="DeleteStarredMessageServlet" method="post">
        	<input type="hidden" value="<%=message_id%>" name="message_id" />
        	<button type="submit" value="" name="button">
        		<div class="ar9"></div>
        	</button>
        </form>
      	
		<div class="inbox" id="message">
        	<div class="subject">
            	<%out.println(rcv[0]); %>
            </div>
            <div class="message">
            	<div class="sender">
                	<div class="sender-name">From: <%=rcv[1] %></div>
                    <div class="msg-date"><%=rcv[2] %></div>
                    <div class="clear"></div>	
                </div>
            	<pre>
           			<%=rcv[3] %> 	
           		</pre>
            </div>
            <div class="reply-forward" id="reply-forward">
            	click here to <label class="reply-forward-button" id="reply_button">Reply</label> or <label class="reply-forward-button" id="forward_botton">Forward</label>  
            </div>
            <div class="reply" id="reply">
              <form action="<%=request.getContextPath() %>/ForwardServlet" method="get">
            	<div class="reply-to">
                	To: <textarea name="to" class="t1" placeholder="Enter Email-address seperated by ," required autofocus><%=rcv[1]%></textarea>
                </div>
                <div class="reply-subject">
                	<input type="text" name="subject" placeholder="Enter Subject"/>
                </div>
                <div class="reply-message">
                	<textarea class="t2" name="message">
                    </textarea>
                </div>
                <div class="message-control">
                	<input type="submit" value="SEND" class="send-button"/>
                </div>
              </form>
            </div>
            <div class="reply" id="forward">
            <form action="<%=request.getContextPath() %>/ForwardServlet" method="get">
            	<div class="reply-to">
                	To: <textarea name="to" class="t1" placeholder="Enter Email-address seperated by ," required autofocus></textarea>
                </div>
                <div class="reply-subject">
                	<input type="text" name="subject" placeholder="Enter Subject" value="<%=rcv[0]%>"/>
                </div>
                <div class="reply-message">
                	<textarea class="t2" name="message" placeholder="Enter Email- Address">
                    <%=rcv[3] %>
                    </textarea>
                </div>
                <div class="message-control">
                	<input type="submit" value="SEND" class="send-button"/>
                	<span id="send_error">${send_error}</span>
                </div>
            </form>
            </div>
            
        </div>
	</div>
</body>
</html>