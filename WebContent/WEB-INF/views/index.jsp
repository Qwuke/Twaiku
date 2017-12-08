<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<table border="1">
		<c:forEach var="myVar" items="${tweetTable}">
			<tr>
				<td>${myVar.tweetID}</td>
				<td>${myVar.userName}</td>
				<td>${myVar.tweetString}</td>
				
		

			</tr>

		</c:forEach>

	</table>

</body>
</html>