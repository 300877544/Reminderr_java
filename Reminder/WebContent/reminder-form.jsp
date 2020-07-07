<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Reminder Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Reminder App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">reminder</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${rem != null}">
					<form action="update" method="post"></form>
				</c:if>
				<c:if test="${rem == null}">
					<form action="insert" method="post"></form>
				</c:if>

				<caption>
					<h2>
						<c:if test="${rem != null}">
            			Edit User
            		</c:if>
						<c:if test="${rem == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${rem != null}">
					<input type="hidden" name="ReminderID" value="<c:out value='${rem.ReminderID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Task</label> <input type="text"
						value="<c:out value='${rem.Task}' />" class="form-control"
						name="Task" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Subject</label> <input type="text"
						value="<c:out value='${rem.Subject_reminder}' />" class="form-control"
						name="Subject_reminder">
				</fieldset>

				<fieldset class="form-group">
					<label>Notes</label> <input type="text"
						value="<c:out value='${rem.Notes}' />" class="form-control"
						name="Notes">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
