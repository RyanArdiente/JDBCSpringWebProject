<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="css/results.css" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
	<c:choose>
	<c:when test="${! empty rows}">
	<table class="w3-table w3-striped"  id="tabledata">
	<thead>
			<c:forEach var="head" items="${headers}">
    			<th>${head}</th>
			</c:forEach>
	</thead>
	<tbody>
	<c:choose>
	<c:when test="${! empty rows}">
		<c:forEach var="row" items="${rows}">
			<tr>
				<c:forEach var="column" items="${row}">
					<td>${column} </td>
				</c:forEach>
			</tr>
		</c:forEach>
	</c:when>
	</c:choose>
	</c:when>
	<c:otherwise>
		<p>Results not found</p>
	</c:otherwise>
	</c:choose>
	</tbody>
	</table>
</body>
</html>