<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Krazy Codes</title>
<link rel="stylesheet" href="css/home-style.css" type="text/css" />
<script type="text/javascript" src="js/custom.js"></script>
</head>
<body>
	<%
		session.setAttribute("email_id", "krazy.gaurav92@gmail.com");
	%>
	<div class="main">
    	<!--Header part-->
        <div class="header">
        	<div class="logo">
            	<img src="images/logo.jpg" alt="logo" width="100" height="75"/>
            </div>
            <form action="#" method="get">
            	<input type="search" placeholder="Search" class="search-box"/>
            </form>
            <div class="logout">
            	<ul>
                	<li>Krazy.gaurav92@gmail.com</li>
                    <li><img src="mailing/images/21.Jpg" /></li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
        
        <!--sideBar part-->
        <div class="side-bar">
           	<input type="button" onClick="open_compose()" value="COMPOSE" class="comose-button"/>
            <ul>
            	<li><a onClick="open_inbox()" >Inbox</a></li>
                <li><a onClick="open_stared()" >Starred</a></li>
                <li><a onClick="open_sent_mail()">Sent Mails</a></li>
                <li><a onClick="open_draft()">Drafts</a></li>
                <li><a onClick="open_trash()">Trash</a></li>
                <li><a >Trash</a></li>
            </ul>
            <div class="tipOfTheDay">
            	<h2>Tip of the Day</h2>
                <p>Hello. Today is June 16, 2014. Have a nice day.</p>
                <p>"Keep your feet on the ground and keep reaching for the stars."	
				-Casey Kasem</p>
            </div>
        </div>
        
        <!--Body Part-->
		<!--Sent Mails-->
        <div class="container" id="sent_mail">
        	<form action="SentMailServlet" method="get">
        	<div class="content-header">
                <input type="submit" value="Delete" class="button01" name="sentMailDelete"/>
                <input type="submit" value="Move to Inbox" class="button01" name="sentMailMoveToInbox"/>
			</div>
        	<div class="content">
				<div class="button02"><input type="checkbox" >To : kamal </div>
				<div class="button02"><input type="checkbox" >To : Abhishek</div>
				<div class="button02"><input type="checkbox" >To : college</div>
				<div class="button02"><input type="checkbox" >To : Asians institute of tech</div>
				<div class="button02"><input type="checkbox" >To : Subham</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
			</div>
			</form>
		</div>
        
        <!--Draft Mail-->
        <div class="container" id="draft">
        	<form action="DraftServlet" method="get">
        	<div class="content-header">
                <input type="submit" value="Delete" class="button01" name="draftDelete"/>
                <input type="submit" value="Move to Inbox" class="button01" name="draftMoveToInbox"/>
			</div>
        	<div class="content">
				<div class="button02"><input type="checkbox" >To : kamal </div>
				<div class="button02"><input type="checkbox" >To : Abhishek</div>
				<div class="button02"><input type="checkbox" >To : college</div>
				<div class="button02"><input type="checkbox" >To : Asians institute of tech</div>
				<div class="button02"><input type="checkbox" >To : Subham</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
			</div>
            </form>
		</div>
        
        <!--Stared Mail-->
        <div class="container" id="stared">
        	<form action="StarredServlet" method="get">
        	<div class="content-header">
                <input type="submit" value="Delete" class="button01" name="starredDelete"/>
                <input type="submit" value="Unstar" class="button01" name="starredUnstar"/>
			</div>
        	<div class="content">
				<div class="button02"><input type="checkbox" >To : kamal </div>
				<div class="button02"><input type="checkbox" >To : Abhishek</div>
				<div class="button02"><input type="checkbox" >To : college</div>
				<div class="button02"><input type="checkbox" >To : Asians institute of tech</div>
				<div class="button02"><input type="checkbox" >To : Subham</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
				<div class="button02"><input type="checkbox" >To : kamal</div>
			</div>
			</form>
		</div>
        
        <!--Inbox Mail-->
        <div class="container" id="inbox">
        	<form action="InboxServlet" method="get">
        	<div class="content-header">
                <input type="submit" value="Delete" class="button01" name="inboxDelete"/>
                <input type="submit" value="Mark as read" class="button01" name="inboxMarkAsRead"/>
			</div>
        	<div class="content">
				<div class="button02"><input type="checkbox" > jyoti </div>
				<div class="button02"><input type="checkbox" > Abhishek</div>
				<div class="button02"><input type="checkbox" > college</div>
				<div class="button02"><input type="checkbox" > Asians institute of tech</div>
				<div class="button02"><input type="checkbox" > google+</div>
				<div class="button02"><input type="checkbox" > kamal</div>
				<div class="button02"><input type="checkbox" >infosoft</div>
				<div class="button02"><input type="checkbox" > micromax</div>
				<div class="button02"><input type="checkbox" >paytem</div>
				<div class="button02"><input type="checkbox" >snapdeal</div>
				<div class="button02"><input type="checkbox" >facebook</div>
				<div class="button02"><input type="checkbox" >facebook</div>
				<div class="button02"><input type="checkbox" >facebook</div>
				<div class="button02"><input type="checkbox" >facebook</div>
				<div class="button02"><input type="checkbox" >facebook</div>
				<div class="button02"><input type="checkbox" >facebook</div>
				<div class="button02"><input type="checkbox" >facebook</div>
				<div class="button02"><input type="checkbox" >facebook</div>
				<div class="button02"><input type="checkbox" >facebook</div>
			</div>
            </form>
		</div>
        
       <!--Trash Mail-->
        <div class="container" id="trash">
        	<form action="TrashServlet" method="get">
        	<div class="content-header">
                <input type="submit" value="Delete" class="button01" name="trashDelete"/>
                <input type="submit" value="Move to Inbox" class="button01" name="trashMoveToInbox"/>
			</div>
        	<div class="content">
        	<%
        		for(int i=0;i<50;i++){
        			%><div class="button02"><input type="checkbox" > Trash </div><%
        		}
        	%>
				
			</div>
            </form>
		</div> 
        
       <!--Compose Code Code-->
       <form action="ComposeServlet" method="post" onsubmit="return compose_validate();">
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
                <textarea name="message_body" id="message_body"></textarea>
                <div id="message_error">${message_error}</div>
            </div>
        </div>
        </form>
        
        <!--Inbox Code
        <div class="inbox" id="message">
        	<div class="subject">
            	Your Return of Amazon.in order 171-9102776-1366725
            </div>
            <div class="message">
            	<div class="sender">
                	<div class="sender-name">From: orders@amazon.in</div>
                    <div class="msg-date">Jun 11 (6 days ago)</div>
                    <div class="clear"></div>	
                </div>
            	<pre>
            	Thank you for using Amazon.in's Returns Centre.

The following link will take you to your return instructions:

https://www.amazon.in/gp/orc/rml/DvYRCqnCRRMA

      If clicking on the link doesn't work, you can also copy and paste it into your browser's address window.

You can track your return here:

http://www.amazon.in/gp/orc/returns/track/DvYRCqnCRRMA

The  tracking number for your return package is:

710100351260

Once we receive the return at our warehouse, it will take us 1 business day to process it and initiate a refund. Your credit card issuer will take an additional 3-5 business days to credit the refund amount to your credit card. Please note that if you had purchased the item using EMI, this refund might cause a change in the terms of your EMI with your credit card issuer, and the issuer might levy a charge for this EMI cancellation. Please check with your credit card issuer on how your EMI will be affected.

See our Returns Policy for details: https://www.amazon.in/gp/help/customer/display.html?ie=UTF8&nodeId=201149900

Thank you for shopping at Amazon.in

-------------------------------------------------------------
Amazon.in
http://www.amazon.in
-------------------------------------------------------------

Please note: this e-mail was sent from a notification-only address that cannot accept incoming e-mail. Please do not reply to this message.
				</pre>
            </div>
            <div class="reply-forward">
            	click here to <label class="reply-forward-button" id="reply">Reply</label> or <label class="reply-forward-button" id="forward">Forward</label>  
            </div>
        </div>-->
        
        <div class="clear"></div>
    </div>
</body>
</html>
