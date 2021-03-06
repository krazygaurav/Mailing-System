package com.dzire.mailing.starred.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.dzire.mailing.util.DBManager;

public class OpenStarredMessageModel {
	OpenStarredMessageBean bean;
	private ArrayList<String> list;
	Connection con;
	private Map<Long,ArrayList<String>> message;
	
	public OpenStarredMessageModel(OpenStarredMessageBean bean){
		this.bean = bean;
	}
	
	public boolean openMessage(){
		try{
			con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from mails where message_id=? and reciever_email=? and starred_status=?");
			ps.setLong(1, bean.getId());
			ps.setString(2, bean.getEmail_id());
			ps.setInt(3, 1);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String subject = rs.getString("subject");
				if(subject.equals("")){
					subject = "No Subject";
				}
				String message_body = rs.getString("message");
				String time = rs.getString("mailingdate");
				String reviever = rs.getString("reciever_email");
				list = new ArrayList<String>();
				list.add(0, subject);
				list.add(1, reviever);
				list.add(2, time);
				list.add(3, message_body);
				list.add(4,""+bean.getId());
				
				message = bean.getMessage();
				message.put(bean.getId(), list);
				bean.setMessage(message);
				con.close();
				return true;
			}
			else{
				con.close();
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
