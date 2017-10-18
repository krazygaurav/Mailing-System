<%@page import="java.util.*,com.dzire.mailing.json.Data,org.json.*,java.sql.*"%>
	<%
		LinkedList<Data> country = new LinkedList<Data>();
		try{
			Connection con=null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/country_india","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from country");
			while(rs.next()){
				
				country.add(new Data(rs.getInt("id"), rs.getString("country_name")));
			}
			con.close();
			request.setAttribute("country", country);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	%>