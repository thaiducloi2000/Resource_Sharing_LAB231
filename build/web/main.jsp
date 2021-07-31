<%-- 
    Document   : employee_page
    Created on : May 10, 2021, 10:47:58 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Page</title>    
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
	          <h1>Welcome ${sessionScope.LIST_USER.userName}</h1>
	          <a href="history.jsp">Go To History</a>
	          <form action="MainController">
	              <input type="submit" name="action" value="logout" />
	          </form>
	          <form action="MainController">
	              Search <input placeholder="Search" type="text" name="search" value="${param.search}"> 
	              <input type="hidden" name="page" value="1"/>
	              <input type="submit" name="action" value="Search">
	          </form>	
	          <div class="table-responsive">
	              <c:if test="${sessionScope.List_Items !=null}">
		<c:if test="${not empty sessionScope.List_Items}">
		    <table  id="mytable" class="table table-bordred table-striped">
		        <thead>
		            <tr>
		                <th >No</th>
		                <th >Item Name</th>
		                <th >Color</th>
		                <th >Category</th>
		                <th  >Using Date</th>
		                <th >Quantity</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="item" varStatus="counter" items="${requestScope.List_Items}">
		            <form action="MainController">
		                <tr>
			  <td>${counter.count}</td>
			  <td>${item.itemName}</td>
			  <td>${item.color}</td>
			  <td>${item.cateName}</td>
			  <td>${item.usingDate}</td>
			  <td>∞</td>
			  <td>
			      <c:url var="book" value="MainController">
			          <c:param name="action" value="Book"></c:param>
			          <c:param name="userID" value="${sessionScope.LIST_USER.userID}"></c:param>
			          <c:param name="search" value="${param.search}"></c:param>
			          <c:param name="itemID" value="${item.itemID}"></c:param>
			          <c:param name="usingDate" value="${item.usingDate}"></c:param>
			      </c:url>
			      <a href="${book}">Booking</a>
			  </td>
		                </tr>	
		            </form>
		        </c:forEach>	  
		        </tbody>
		    </table>
		    <div class="pagination">	  
		        <c:forEach begin="1" end="${numOfPage}" var="i">
		            <a href="SearchPagingController?page=${i}">${i}</a>
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
