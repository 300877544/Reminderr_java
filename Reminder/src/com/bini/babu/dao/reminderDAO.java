package com.bini.babu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bini.babu.reminder.model.reminder;

public class reminderDAO {
private String jdbcURL = "jdbc:mysql://localhost:3306/rdbm?useSSL=false";
private String jdbcUsername="root";
private String jdbcPassword="admin";
private static final String Insert_Reminder = "INSERT INTO reminder" + " (Task, Subject_reminder, Notes) VALUES"
+ " (?,?,?);";
private static final String Select_Reminder_ById = "select ReminderID, Task, Subject_reminder, Notes from reminder where id=?";
private static final String Select_All = "select * from reminder";
private static final String Update_Reminder = "update reminder set Task=?, Subject_reminder=?, Notes=? where ReminderID = ?;";
protected Connection getConnection() {
	Connection connection = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	return connection;
}

public void insertReminder(reminder rem) throws SQLException {
	try(Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(Insert_Reminder)){
		    ps.setString(1, rem.getTask());
		    ps.setString(2, rem.getSubject_reminder());
		    ps.setString(3, rem.getNotes());
		    ps.executeUpdate();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
public boolean updateReminder(reminder rem) throws SQLException{
	boolean rowUpdated;
	try(Connection connection = getConnection();
		PreparedStatement s = connection.prepareStatement(Update_Reminder);){
		s.setString(1, rem.getTask());
		s.setString(2, rem.getSubject_reminder());
		s.setString(3, rem.getNotes());
		rowUpdated = s.executeUpdate()>0;
	}
	return rowUpdated;
}

public reminder selectreminder(int ReminderID) throws SQLException {
	reminder rem =null;
	try(Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(Select_Reminder_ById);){
		ps.setInt(1, ReminderID);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String Task = rs.getString("Task");
			String Subject_reminder = rs.getString("Subject_reminder");
			String Notes = rs.getString("Notes");
			rem = new reminder(ReminderID, Task, Subject_reminder, Notes);
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	return rem;
}

public List<reminder> selectAllReminder() 
{
	List<reminder> rem = new ArrayList<>();
	
	try(Connection connection = getConnection();
	PreparedStatement ps = connection.prepareStatement(Select_All);){
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int ReminderID = rs.getInt("ReminderID");
			String Task = rs.getString("Task");
			String Subject_reminder = rs.getString("Subject_reminder");
			String Notes = rs.getString("Notes");
			rem.add(new reminder(ReminderID, Task, Subject_reminder, Notes));
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return rem;
}

}
