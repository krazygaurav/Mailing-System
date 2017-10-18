<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,java.sql.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mailing","root","root");
		PreparedStatement ps = con.prepareStatement("select * from mails");
		ResultSet rs = ps.executeQuery();
		List li = new ArrayList();
		while(rs.next()){
			li.add(rs.getString("sender_email"));
			li.add(rs.getString("reciever_email"));
		}
		String emails[] = new String[li.size()];
		Iterator it = li.iterator();
		
		int i=0;
		while(it.hasNext()){
			emails[i++] = (String)it.next(); 
		}
		
		//jquery
		String query = (String)request.getParameter("q");
		int cnt=1;
		for(int j=0;j<emails.length;j++){
			if(emails[j].toLowerCase().startsWith(query.toLowerCase())){
				out.println(emails[j]+"\n");
				if(cnt>=5)
					break;
				cnt++;
			}
		}
		con.close();
	}
	catch(Exception e){
	
	}
	%>
</body>
</html>