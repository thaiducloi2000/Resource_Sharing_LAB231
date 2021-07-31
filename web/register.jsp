<%-- 
    Document   : register
    Created on : May 20, 2021, 10:19:54 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/register.css">
    </head>
    <body>
        <form action="MainController" method="post">
            <!--            <label>User Name</label>
	      <input type="text" name="username">
	      <label>User Email</label>
	      <input type="email" name="useremail">
	      <input type="submit" value="Register">-->
            <div class="register-form">
                <h2 class="text-center">Register</h2>		
                <div class="form-group">
	  <div class="input-group">                
	      <div class="input-group-prepend">
	          <span class="input-group-text">
	              <span class="fa fa-user"></span>
	          </span>                    
	      </div>
	      <input type="text" class="form-control" name="userID" placeholder="User ID" required="required">
	  </div>
                </div>
                <div class="form-group">
	  <div class="input-group">
	      <div class="input-group-prepend">
	          <span class="input-group-text">
	              <i class="fa fa-lock"></i>
	          </span>                    
	      </div>
	      <input type="password" class="form-control" name="password" placeholder="Password" required="required">
	  </div>
                </div>
                <div class="form-group">
	  <div class="input-group">
	      <div class="input-group-prepend">
	          <span class="input-group-text">
	              <i class="fa fa-lock"></i>
	          </span>                    
	      </div>
	      <input type="password" class="form-control" name="confirm" placeholder="Confirm Password" required="required">
	  </div>
                </div>
                <div class="form-group">
	  <div class="input-group">
	      <div class="input-group-prepend">
	          <span class="input-group-text">
	              <i class="fa fa-phone"></i>
	          </span>                    
	      </div>
	      <input type="text" class="form-control" name="phone" placeholder="phone" required="required">
	  </div>
                </div>
                <div class="form-group">
	  <div class="input-group">
	      <div class="input-group-prepend">
	          <span class="input-group-text">
	              <i class="fa fa-user"></i>
	          </span>                    
	      </div>
	      <input type="text" class="form-control" name="userName" placeholder="Your Name" required="required">
	  </div>
                </div>
                <div class="form-group">
	  <div class="input-group">
	      <div class="input-group-prepend">
	          <span class="input-group-text">
	              <i class=" fa fa-address-book-o"></i>
	          </span>                    
	      </div>
	      <input type="text" class="form-control" name="address" placeholder="Address" required="required">
	  </div>
                </div>
                <div class="form-group">
	  <div class="input-group">
	      <div class="input-group-prepend">
	          <span class="input-group-text">
	              <i class="fa fa-envelope"></i>
	          </span>                    
	      </div>
	      <input type="email" class="form-control" name="email" placeholder="Your email" required="required">
	  </div>
                </div>
                <div class="form-group">
	  <button type="submit" name="action" class="btn btn-success btn-block login-btn" value="Register">Register</button>
                </div>
                <div class="hint-text"> Have an account? <a href="login.jsp" class="text-success">Login Here!</a></div>
            </div>
        </form>
    </body>
</html>
