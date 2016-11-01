package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.models.Event;

public class EventsJdbcDao {
	
	@Value("${eventsJdbcString}")
	private String jdbcString;
	
	@Value("${dbUserName}")
	private String dbUserName;
	
	@Value("${dbPassword}")
	private String dbPassword;
	
	Logger logger= Logger.getLogger(EventsJdbcDao.class);
	
	public String saveEvents(Event event)
	{

		System.out.println("-------- Events JDBC Connection Testing ------------");
		ArrayList<Event> eventsList=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("Where is your MySQL JDBC Driver?");
			logger.error(e.getStackTrace());
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(jdbcString,dbUserName,dbPassword);
		} catch (Exception e) {
			logger.error("Connection Failed! Check output console");
			logger.error(e.getStackTrace());
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			logger.debug("You made it, take control your database now!");

			try {
				String insertEventsSQL = "Insert into events (event_desc,created_date_time,user_id,resources_needed,place,event_date_time,is_archived,is_resources_satisfied) values(?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(insertEventsSQL);
				
				//event description
				if(event.getEvent_desc()!=null && !event.getEvent_desc().isEmpty())
					pstmt.setString(1,event.getEvent_desc());
				else
					pstmt.setString(1,null);
				
				//created date
				pstmt.setString(2,new Date().toString());
				
				//user-id
				if(event.getUser_id()!=null && event.getUser_id()>0)
					pstmt.setString(3,event.getUser_id().toString());
				else
					pstmt.setString(3,null);
				
				//resources-needed
				if(event.getResources_needed()!=null && !event.getResources_needed().isEmpty())
					pstmt.setString(4,event.getResources_needed());
				else
					pstmt.setString(4,null);
				
				//place of event
				if(event.getPlace()!=null && !event.getPlace().isEmpty())
					pstmt.setString(5,event.getPlace());
				else
					pstmt.setString(5,null);
				
				//place of event
				if(event.getPlace()!=null && !event.getPlace().isEmpty())
					pstmt.setString(6,event.getPlace());
				else
					pstmt.setString(5,null);
				
				//event-date
				if(event.getEvent_date_time()!=null)
					pstmt.setString(6,event.getEvent_date_time().toString());
				else
					pstmt.setString(6,null);
				
				//is-archived
				pstmt.setString(7,String.valueOf(event.getIs_archived()));
				
				//is_resources_satisfied
				pstmt.setString(8,String.valueOf(event.getIs_resources_satisfied()));
				
				//int[] updateCounts = pstmt.executeBatch();
				boolean updated = pstmt.execute();
				
				System.out.println();
				/*	
				ResultSet rs = statement.executeQuery(selectTableSQL.toString());
				eventsList=new ArrayList<Events>();
				while (rs.next()) {

					String subject_id= rs.getString("subject_id");
					String subject_name = rs.getString("subject_name");
					String proff_name = rs.getString("proff_name");
					String time=rs.getString("time");
					subject=new Subject();
					subject.setSubject_id(Integer.parseInt(subject_id));
					subject.setSubject_name(subject_name);
					subject.setProff_name(proff_name);
					subject.setTime(time);
					eventsList.add(subject);
					logger.warn(subject);
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Failed to make connection!");
		}
	return "Success";
	}
}