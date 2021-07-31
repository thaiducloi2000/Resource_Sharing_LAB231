<%-- 
    Document   : manager
    Created on : May 24, 2021, 3:55:06 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/main.css">
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
	          <h1> Welcome ${sessionScope.LIST_USER.userName}</h1>
	          <form action="MainController">
	              <input type="submit" name="action" value="logout" />
	          </form>
	          <form action="MainController">
	              Search Request <input type="text" name="itemName" placeholder="Enter Item Name" value="${param.search}"/>
	              <select name="status" value="${param.status}">
		<option>All</option>
		<c:forEach var="sta" items="${sessionScope.List_Status}">
		    <option>${sta}</option>
		</c:forEach>
	              </select>
	              <input type="hidden" name="page" value="1"/>
	              <input type="submit" name="action" value="Search Request">
	          </form>
	          <div class="table-responsive">
	              <c:if test="${sessionScope.LIST_REQUEST !=null}">
		<c:if test="${not empty sessionScope.LIST_REQUEST}">
		    <table id="mytable" class="table table-bordred table-striped">
		        <thead>
		            <tr>
		                <th>No</th>
		                <th>Employee</th>
		                <th>Item</th>
		                <th>Using Date</th>
		                <th>Day Request</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="req" varStatus="counter" items="${requestScope.LIST_REQUEST}">
		                <tr>
			  <td>${counter.count}</td>
			  <td>${req.userID}</td>
			  <td>${req.itemID}</td>
			  <td>${req.usingTime}</td>
			  <td>${req.dateRequest}</td>
			  <c:if test="${req.statusID == 'New'}">
			      <td>
			          <form action="MainController">
			              <input type="submit" name="action" value="Accept"/>
			              <input type="hidden" name="requestID" value="${req.requestID}"/>
			              <input type="hidden" name="status" value="${"A"}"/>
			          </form>
			      </td>
			      <td>
			          <form action="MainController">
			              <input type="submit" name="action" value="Reject"/>
			              <input type="hidden" name="requestID" value="${req.requestID}"/>
			              <input type="hidden" name="status" value="${"R"}"/>
			          </form>
			      </td>
			  </c:if>
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		    <div class="pagination">	  
		        <c:forEach begin="1" end="${numOfPage}" var="i">
		            <a href="SearchRePagingController?page=${i}">${i}</a>
		        </c:forEach>
		    </div>
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
