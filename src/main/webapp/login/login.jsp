<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
		<style>
		
			.login-header {
				text-align: center;
			}
			
			.main-div {
				border: 1px solid black;
				margin-top: 50px;
			}
			
			.submit-btn {
				margin-bottom: 20px;
			}
			
		</style>
		
		<jsp:include page="../common/header.jsp"></jsp:include>
        <div class="container col-md-4 col-md-offset-3 main-div">
            <h1 class="login-header">Login Form</h1>
            <form action="<%=request.getContextPath()%>/login" method="post">
                <div class="form-group">
                    <label for="uname">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
                </div>

                <div class="form-group">
                    <label for="uname">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
                </div>

                <button type="submit" class="btn btn-primary submit-btn">Submit</button>
            </form>
        </div>
    	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>