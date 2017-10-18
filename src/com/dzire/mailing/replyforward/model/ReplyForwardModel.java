package com.dzire.mailing.replyforward.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import com.dzire.mailing.replyforward.model.ReplyForwardBean;
import com.dzire.mailing.util.DBManager;

public class ReplyForwardModel {
	List<String> emails = new ArrayList<String>();
	ReplyForwardBean bean;
	Connection con;
	
	public ReplyForwardModel(ReplyForwardBean fb){
		bean = fb;
	}
	
	public boolean validatingEmail(){
		String to = bean.getTo();
		String emailsTo[] = to.split(",");
		for(int j=0;j<emailsTo.length;j++){
			if(emailsTo[j].length() > 8)
				emails.add(emailsTo[j].trim());
		}
		
		for(int i=0;i<emails.size();i++){
			if(! EmailValidator.getInstance().isValid(emails.get(i))){
				return false;
			}
		}
		return true;
	}
	
	public boolean storeData(){
		Date dd=new Date();		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm aaa");
		String mailingdate=sdf.format(dd);
		
		try{
			//TODO: manipulate the mail here.. get message_id and update in mail table
			con = DBManager.getConnection();
			for (int i = 0; i < emails.size(); i++) {
				PreparedStatement ps1 = con.prepareStatement("insert into mails(message,starred_status,sender_email,reciever_email,inbox_status,sent_status,draft_status,trash_status,subject,read_status,mailingdate) values(?, ?, ?, ?, ?, ?, ?, ?,?,?,?)");
				ps1.setString(1, bean.getMessage_body());
				ps1.setInt(2, 0);
				ps1.setString(3, bean.getEmail_id());
				ps1.setString(4, emails.get(i));
				ps1.setInt(5, 1);
				ps1.setInt(6, 1);
				ps1.setInt(7, 0);
				ps1.setInt(8, 0);
				ps1.setString(9, bean.getSubject());
				ps1.setInt(10, 0);
				ps1.setString(11, mailingdate);
			
				int bool = ps1.executeUpdate();
				if(bool == 0){
					con.close();
					return false;
				}	
			}
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
