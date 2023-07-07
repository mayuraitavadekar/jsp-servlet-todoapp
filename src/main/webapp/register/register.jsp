<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register Page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
</head>
<body>

		<style>
			.register-heading {
				text-align: center;
			}
			
			.main-container {
				border: 1px solid black;
				margin-top: 50px;
				
			}
			
			.submit-btn {
				margin-bottom: 20px;
			}
		</style>

		<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="container main-container">

        	<h2 class="register-heading">Register</h2>
         	<div class="container col-md-6 col-md-offset-3">
             	
             	<div class="alert alert-success center" role="alert">
                 <p>${NOTIFICATION}</p>
             	</div>

             	<form action="<%=request.getContextPath()%>/register" method="post">

                	<div class="form-group">
	                     <label for="uname">First Name:</label> <input type="text" class="form-control" id="uname" placeholder="First Name" name="firstName" required>
	                </div>
	
	                <div class="form-group">
	                     <label for="uname">Last Name:</label> <input type="text" class="form-control" id="uname" placeholder="last Name" name="lastName" required>
	                </div>
	
	                <div class="form-group">
	                     <label for="uname">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
	                </div>
	
	                <div class="form-group">
	                     <label for="uname">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
	                </div>
	
	                <button type="submit" class="btn btn-primary submit-btn">Submit</button>

             	</form>
         	</div>
     	</div>
    	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>