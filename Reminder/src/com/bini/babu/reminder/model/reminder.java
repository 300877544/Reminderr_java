package com.bini.babu.reminder.model;

public class reminder {
private	int ReminderID;
private	String Task;
private	String Subject_reminder;
private String Notes;
	
	
	
	public reminder(int reminderID, String task, String subject_reminder, String notes) {
		
		ReminderID = reminderID;
		Task = task;
		Subject_reminder = subject_reminder;
		Notes = notes;
	}
	
	
	public reminder(String task, String subject_reminder, String notes) {
		
		Task = task;
		Subject_reminder = subject_reminder;
		Notes = notes;
	}


	public int getReminderID() {
		return ReminderID;
	}
	public void setReminderID(int reminderID) {
		ReminderID = reminderID;
	}
	public String getTask() {
		return Task;
	}
	public void setTask(String task) {
		Task = task;
	}
	public String getSubject_reminder() {
		return Subject_reminder;
	}
	public void setSubject_reminder(String subject_reminder) {
		Subject_reminder = subject_reminder;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
	
	
}
