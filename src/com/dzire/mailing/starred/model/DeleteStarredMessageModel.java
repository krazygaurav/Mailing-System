package com.dzire.mailing.starred.model;

import java.sql.PreparedStatement;

import com.dzire.mailing.util.DBManager;
import java.sql.Connection;

public class DeleteStarredMessageModel {
	private DeleteStarredMessageBean bean;
	public DeleteStarredMessageModel(DeleteStarredMessageBean bean){
		this.bean = bean;
	}
	public boolean deleteMessage(){
		try{
			Connection con = DBManager.getConnection();
			String query = "update mails set starred_status=?, trash_status=? where message_id=? and reciever_email=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, 0);
			ps.setInt(2, 1);
			ps.setLong(3, Long.parseLong(bean.getMessage_id()));
			ps.setString(4, bean.getEmail_id());
			int bool = ps.executeUpdate();
			if(bool == 1){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
