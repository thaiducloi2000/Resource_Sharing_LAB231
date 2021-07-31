<%-- 
    Document   : verify
    Created on : May 20, 2021, 1:58:49 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Email</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/register.css">
    </head>
    <body>
        <form action="MainController" method="POST">
            <div class="register-form">
                <h2 class="text-center">Verify Your Email for Active Account</h2>		
                <div class="form-group">
	  <div class="input-group">                
	      <div class="input-group-prepend">
	          <span class="input-group-text">
	              <span class="fa fa-code"></span>
	          </span>                    
	      </div>
	      <input type="text" class="form-control" name="code" placeholder="Verify Code" required="required">
	  </div>
                </div>
                <div class="form-group">
	  <button type="submit" name="action" class="btn btn-success btn-block login-btn" value="Verify">Confirm</button>
                </div>
        </form>
    </body>
</html>
