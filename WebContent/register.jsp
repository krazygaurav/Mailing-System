<%@page import="java.util.*,com.dzire.mailing.json.Data,org.json.*"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
<link href="<%=request.getContextPath() %>/css/fdw-demo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/jscss.css">
<script src="<%=request.getContextPath() %>/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.2.6.min.js"></script>
<script src="<%=request.getContextPath() %>/js/myjs.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery-ui-1.10.4.custom.js"></script>
 <script>
	$(function() {
		$( "#datepicker" ).datepicker();
	});
</script>
</head>

<body>
		<div class="heading">
			<div>Mailing<span>System</span></div>
		</div>
		<div class="form">
    		<form action="RegisterServlet" method="get" id="register"> 
    			<p class="contact"><label for="name">Name</label></p> 
    			<input type="text" tabindex="1" required placeholder="First and last name" name="name" id="name"> <br/>
    			<span id="name_error">${name_error}</span>
    			<p class="contact"><label for="email">Email</label></p> 
    			<input type="email" required placeholder="example@domain.com" name="email_id" id="email_id" tabindex="2"><br/> 
                <div id="status1"></div>
                <span id="email_error">${email_error}</span>
                <p class="contact"><label for="username">Create a username</label></p> 
    			<input type="text" tabindex="3" required placeholder="username" name="username" id="username"><br/> 
    			<span id="username_error">${username_error}</span>
                <div id="status2"></div>
                <p class="contact"><label for="password">Create a password</label></p> 
    			<input type="password" required name="password" id="password" tabindex="4"><br/>
    			<span id="password_error">${password_error}</span> 
                <p class="contact"><label for="repassword">Confirm your password</label></p> 
    			<input type="password" required name="repassword" id="repassword" tabindex="5"/><br/>
    			<span id="repassword_error">${repassword_error}</span> 
				<p class="contact"><label for="password">Date Of Birth</label></p>
				<input type="text" id="datepicker" tabindex="6" name="dob"></p>
				<p class="contact"><label for="password">My Place</label></p>
				<ul>
					
					<li>
					Country: &nbsp;
					<select id="country" tabindex="7" name="country">
					<%
						LinkedList<Data> c = (LinkedList<Data>)request.getAttribute("country");
						for(Data d:c){
							int id = d.id;
							String name = d.name;		
					%>
					<option value=<%=id%> >
					<%=name %>
					<%
						}
					%>
					</option>
					</select>
					</li>
					<li>
					State: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="state" tabindex="8" name="state">
		
					</select></li>
					<li>City:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<select id="district" tabindex="9" name="city">		
					
					</select></li>
					</ul>
					<div id="wait" style="display: none; width: 100px; height: 100px;position:absolute;top:50%;left:50%;padding:2px;">
					<img src="<%=request.getContextPath() %>/images/demo_wait.gif" width="64" height="64"/><br/>Loading..
					</div>
				
			<!-- 	
               	<fieldset>
                 <label>Birthday</label>
                  <label class="month"> 
                  <select name="BirthMonth" class="select-style">
                  <option value="">Month</option>
                  <option value="01">January</option>
                  <option value="02">February</option>
                  <option value="03">March</option>
                  <option value="04">April</option>
                  <option value="05">May</option>
                  <option value="06">June</option>
                  <option value="07">July</option>
                  <option value="08">August</option>
                  <option value="09">September</option>
                  <option value="10">October</option>
                  <option value="11">November</option>
                  <option value="12">December</option>
                  
                 </select>    
                <label>Day<input required placeholder="Day" name="BirthDay" maxlength="2" class="birthday"></label>
                <label>Year <input required placeholder="Year" name="BirthYear" maxlength="4" class="birthyear"></label>
              </label></fieldset>
  			 -->
            <p class="contact" style="margin-top: 10px;"><label for="password">Gender</label></p>
            <select name="gender" class="select-style gender" tabindex="10">
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="others">Other</option>
            </select><br><br>
            
            <p class="contact"><label for="phone">Mobile phone</label></p> 
            <input type="text" required placeholder="phone number" name="mobile" id="phone" tabindex="11"> <br/>
            <span id="phone_error">${phone_error}</span>
            <input type="submit" value="Sign me up!" tabindex="12" id="submit" name="submit" class="buttom"> 	 
  	 </form> 
  	</div>
</body>
</html>
