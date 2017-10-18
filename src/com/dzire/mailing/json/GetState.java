package com.dzire.mailing.json;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dzire.mailing.util.DBManager;

@WebServlet("/GetState")
public class GetState extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		
		String stateId = request.getParameter("country");
		int id = Integer.parseInt(stateId);
		JSONArray jSONArray=new JSONArray();
		try{
			Connection con=DBManager.getConnection();
			PreparedStatement st = con.prepareStatement("select * from state where country_id=? ");
			//PreparedStatement st = con.prepareStatement("SELECT * FROM state where country_id=(select id from country where country_name=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				JSONObject jSONObject=new JSONObject();
				jSONObject.put("id", rs.getInt("id"));
				jSONObject.put("state_name", rs.getString("state_name"));
				jSONArray.put(jSONObject);
			}
			//sending json to index
			out.print(jSONArray.toString());
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
