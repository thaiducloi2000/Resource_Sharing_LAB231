<%-- 
    Document   : login
    Created on : May 10, 2021, 9:57:58 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="google-signin-client_id" content="400202893725-5ptkvqp0get8ep6fq4jhca06m9gmcdu0.apps.googleusercontent.com.apps.googleusercontent.com">
        <title>Resource Sharing</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <link rel="stylesheet" href="CSS/login.css">
        <script src="https://www.google.com/recaptcha/api.js"></script>
    </head>
    <body>
        <form action="MainController" method="POST">            
            <div class="login-form">
                <h2 class="text-center">Sign in</h2>		
                <div class="text-center social-btn">
	  <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Resource_Sharing_LAB231/login-google&response_type=code&client_id=400202893725-5ptkvqp0get8ep6fq4jhca06m9gmcdu0.apps.googleusercontent.com&approval_prompt=force" class="btn btn-danger btn-block">
	      <i class="fa fa-google"></i> Sign in with <b>Google</b></a>
                </div>
                <div class="or-seperator"><i>or</i></div>
                <c:if test="${requestScope.ERROR != null}">
	  <c:if test="${not empty requestScope.ERROR}">
	      <div class="alert">	
	          ${requestScope.ERROR}
	      </div>
	  </c:if>
                </c:if>
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
	  <button type="submit" name="action" class="btn btn-success btn-block login-btn" value="login">Sign in</button>
                </div>
                <div class="clearfix">
	  <div class="g-recaptcha" data-sitekey="6Lef7M0aAAAAAMCB3o4YBFjuJcgRhckXVVkHlM8J"></div>
                </div>  
                <div class="hint-text">Don't have an account? <a href="register.jsp" class="text-success">Register Now!</a></div>
            </div>
        </form>        
    </body>
</html>
