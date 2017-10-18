<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Mail Server</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/index.css" type="text/css" />

</head>

<body>

  <div class="body"></div>
		<div class="grad"></div>
		<div class="header">
			<div>Mailing<span>System</span></div>
		</div>
		<br>
		<div class="login">
        <form action="LoginServlet" method="post">
			<input type="text" placeholder="username" name="username"><br>
			<input type="password" placeholder="password" name="password"><br>
			<span id="login_error">${login_error}</span>
			<input type="submit" value="Login" class="login-button"/><br/>
            <div class="logout-button"><a href="<%=request.getContextPath()%>/register" style="text-decoration:none;color: #A18D6C;margin-left: 97px;">Register</a></div>
        </form>
		</div>

</body>

</html>