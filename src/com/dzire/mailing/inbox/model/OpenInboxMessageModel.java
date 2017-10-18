package com.dzire.mailing.inbox.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.dzire.mailing.util.DBManager;

public class OpenInboxMessageModel {
	OpenInboxMessageBean bean;
	ArrayList<String> list;
	Connection con;
	private Map<Long,ArrayList<String>> message;
	
	public OpenInboxMessageModel(OpenInboxMessageBean bean){
		this.bean = bean;
	}
	
	public boolean openMessage(){
		try{
			con = DBManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from mails where message_id=? and reciever_email=? and inbox_status=?");
			ps.setLong(1, bean.getId());
			ps.setString(2, bean.getEmail_id());
			ps.setInt(3, 1);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int read_status = rs.getInt("read_status");
				String subject = rs.getString("subject");
				if(subject.equals("")){
					subject = "No Subject";
				}
				String message_body = rs.getString("message");
				String time = rs.getString("mailingdate");
				String sender = rs.getString("sender_email");
				list = new ArrayList<String>();
				list.add(0, subject);
				list.add(1, sender);
				list.add(2, time);
				list.add(3, message_body);
				list.add(4,""+bean.getId());
				if(read_status == 0){
					//change status to read i.e 1
					PreparedStatement ps1 = con.prepareStatement("update mails set read_status=? where message_id=? and reciever_email=?");
					ps1.setInt(1, 1);
					ps1.setLong(2, bean.getId());
					ps1.setString(3, bean.getEmail_id());
					ps1.executeUpdate();
				}
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
