<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div style="text-align:center;">

			<table border="1">
			<col width="130">
			<th>Name</th>
			<th>Mobile No</th>
			<th>Balance</th>
			<c:forEach items="${customerlist}" var="info">
			
				<tr>
					<td><c:out value="${info.name}" /></td>
					<td><c:out value="${info.mobileNo}" /></td>
					<td><c:out value="${info.wallet.balance}" /></td>
				</tr>
			
		</c:forEach>
		</table>
	</div>

</body>
</html>