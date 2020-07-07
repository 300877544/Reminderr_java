package com.bini.babu.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bini.babu.dao.reminderDAO;
import com.bini.babu.reminder.model.reminder;


@WebServlet("/")
public class reminderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private reminderDAO remDAO;
    
    public reminderServlet() {
       this.remDAO = new reminderDAO();
       
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		switch(action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertReminder(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateUser(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				listreminder(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	 int ReminderID = Integer.parseInt(request.getParameter("ReminderID"));
	 reminder existingreminder = remDAO.selectreminder(ReminderID);
	 RequestDispatcher d = request.getRequestDispatcher("reminder-form.jsp");
	 request.setAttribute("rem", existingreminder);
	 d.forward(request, response);
	}
	
	private void listreminder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		List<reminder> listremind = remDAO.selectAllReminder();
		request.setAttribute("listremind", listremind);
		RequestDispatcher rd = request.getRequestDispatcher("rem-list.jsp");
		rd.forward(request, response);
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int ReminderID = Integer.parseInt(request.getParameter("ReminderID"));
		System.out.println(ReminderID);
		String Task = request.getParameter("Task");
		String Subject_reminder = request.getParameter("Subject_reminder");
		String Notes = request.getParameter("Notes");
		reminder rem = new reminder(ReminderID, Task, Subject_reminder, Notes);
		remDAO.updateReminder(rem);
		response.sendRedirect("list");
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher d = request.getRequestDispatcher("reminder-form.jsp");
		d.forward(request, response);
	}
	private void insertReminder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException  {
		String Task= request.getParameter("Task");
		String Subject_reminder= request.getParameter("Subject_reminder");
		String Notes= request.getParameter("Notes");
		reminder rem = new reminder(Task, Subject_reminder, Notes);
		remDAO.insertReminder(rem);
		response.sendRedirect("list");
	}
	

}
