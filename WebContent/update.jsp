<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        	    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/results.css" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
	<form  class="center-div" action="update.do" method="POST">
		<input id="display" type="text" name="updateby"/><input type="submit" name="submit" value ="Update"/>
	</form>
	<c:choose>
	<c:when test="${! empty changes}">
	<p>(${changes}) changes were made to the Database.</p>
	</c:when>
	</c:choose>

</body>
</html>