<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Todo App</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">

</head>
<body>

 <style>
 	.todo-form-heading {
 		text-align: center;
 	}
 	
 	.main-container {
 		margin-top: 20px;
 	}
 	
 </style>

 <header>
  <nav class="navbar navbar-expand-md navbar-dark"
   style="background-color: tomato">
   <div>
    <a href="" class="navbar-brand"> Todo App</a>
   </div>

   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/list"
     class="nav-link">Todos</a></li>
   </ul>

   <ul class="navbar-nav navbar-collapse justify-content-end">
    <li><a href="<%=request.getContextPath()%>/logout"
     class="nav-link">Logout</a></li>
   </ul>
  </nav>
 </header>
 <div class="container col-md-5 main-container">
  <div class="card">
   <div class="card-body">
    
    <c:if test="${todo != null}">
     <form action="update" method="post">
    </c:if>
    
    <c:if test="${todo == null}">
     <form action="insert" method="post">
    </c:if>
	    <caption>
	     <h2 class="todo-form-heading">
	      <c:if test="${todo != null}">
	               Edit Todo
	      </c:if>
	      <c:if test="${todo == null}">
	               Add New Todo
	      </c:if>
	     </h2>
	    </caption>
	
	    <c:if test="${todo != null}">
	     <input type="hidden" name="id" value="<c:out value='${todo.id}' />" 
	    />
	    
	    </c:if>
	    
	    <fieldset class="form-group">
	     <label>Todo Title</label> <input type="text"
	      value="<c:out value='${todo.title}' />" class="form-control"
	      name="title" required="required" minlength="5">
	    </fieldset>
	
	    <fieldset class="form-group">
	     <label>Todo Decription</label> <input type="text"
	      value="<c:out value='${todo.description}' />" class="form-control"
	      name="description" minlength="5">
	    </fieldset>
	
	    <fieldset class="form-group">
	     <label>Todo Status</label> <select class="form-control"
	      name="isDone">
	      <option value="false">In Progress</option>
	      <option value="true">Complete</option>
	     </select>
	    </fieldset>
	
	    <fieldset class="form-group">
	     <label>Todo Target Date</label> <input type="date"
	      value="<c:out value='${todo.targetDate}' />" class="form-control"
	      name="targetDate" required="required">
	    </fieldset>
	
	    <button type="submit" class="btn btn-success">Save</button>
    </form>
   </div>
  </div>
 </div>

 <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>