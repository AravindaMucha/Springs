<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<style>
.error {
	color: red;
	font-weight: bold;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<div">
			<h3>Enroll here to register</h3>
		</div>
		<table>
			<form:form action="fundTransferAction" method="post"
				modelAttribute="customer">


				<tr>
				<td>Source Mobile Number</td>
				<td><input type="text" name="mobileNo1" /></td>
			</tr>
			
			<tr>
				<td>Target Mobile Number</td>
				<td><input type="text" name="mobileNo2" /></td>
			</tr>
			
			<tr>
				<td>Amount</td>
				<td><input type="text" name="wallet.balance" /></td>
			</tr>
				
				
				<tr>
					<td><input type="submit" value="Submit"></td>
				</tr>
			</form:form>
			
			
		</table>
		
		<div>
		<font color="red"> <c:if test="${not empty errorMessage}">
			${errorMessage}
			</c:if>
		</font>

	</div>
	</center>
</body>
</html>