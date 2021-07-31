<%-- 
    Document   : history
    Created on : May 25, 2021, 10:49:35 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">


                <div class="col-md-12">
	  <c:if test="${sessionScope.LIST_USER ==null}">
	      <h1>You have to <a href="login.jsp">login</a> for continue</h1> 
	  </c:if>
	  <c:if test="${sessionScope.LIST_USER !=null}">
	      <c:if test="${not empty sessionScope.LIST_USER}">
	          <h1>Welcome ${sessionScope.LIST_USER.userName}</h1>
	          <a href="main.jsp">Go To Request Page</a>
	          <form action="MainController">
	              <input type="submit" name="action" value="logout" />
	          </form>
	          <form action="MainController">
	              Search <input type="date" name="searchH" value="${param.searchH}">
	              <input type="hidden" name="userID" value="${sessionScope.LIST_USER.userID}"/>
	              <input type="submit" name="action" value="SearchH">
	          </form>
	          <div class="table-responsive">
	              <c:if test="${sessionScope.List_History !=null}">
		<c:if test="${not empty sessionScope.List_History}">
		    <table id="mytable" class="table table-bordred table-striped">
		        <thead>
		            <tr>
		                <th scope="col">No</th>
		                <th scope="col">Item Name</th>
		                <th scope="col">Using Time</th>
		                <th scope="col">Date Request</th>
		                <th scope="col">Status</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="req" varStatus="counter" items="${sessionScope.List_History}">
		            <form action="MainController">	          
		                <tr>
			  <td scope="row">${counter.count}</td>
			  <td>${req.itemID}</td>
			  <td>${req.usingTime}</td>
			  <td>${req.dateRequest}</td>
			  <td>${req.statusID}</td>
			  <td>
			      <c:url var="delete" value="MainController">
			          <c:param name="action" value="Delete"></c:param>
			          <c:param name="reqID" value="${req.requestID}"></c:param>
			      </c:url>
			      <a href="${delete}">Delete</a>
			  </td>
		                </tr>
		            </form>
		        </c:forEach>
		        </tbody>
		    </table>

		</c:if>
	              </c:if>
	          </c:if>
	      </c:if>
	  </div>
                </div>
            </div>
        </div> 
    </body>
</html>
