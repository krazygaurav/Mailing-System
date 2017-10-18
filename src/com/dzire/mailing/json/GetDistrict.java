package com.dzire.mailing.json;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dzire.mailing.util.DBManager;

@WebServlet("/GetDistrict")
public class GetDistrict extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stateId = request.getParameter("district");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(stateId);
		JSONArray jSONArray=new JSONArray();
		try{
			Connection con=DBManager.getConnection();
			PreparedStatement st = con.prepareStatement("select * from city where state_id=? ");
			//PreparedStatement st = con.prepareStatement("SELECT * FROM city where state_id=(select id from state where state_name=?)");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				JSONObject jSONObject=new JSONObject();
				jSONObject.put("id", rs.getInt("id"));
				jSONObject.put("city_name", rs.getString("city_name"));
				jSONArray.put(jSONObject);
				//System.out.println(""+rs.getString("city_name"));
			}
			out.print(jSONArray.toString());
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
