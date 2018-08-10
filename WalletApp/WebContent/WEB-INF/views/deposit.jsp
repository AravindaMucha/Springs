
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	
		<div>
			<h3>Deposit </h3>
		</div>
		
			<form:form action="depositAmount" method="post"
				modelAttribute="customer">
				<table>
				
				<tr>
					<td>Mobile Number</td>
					<td><form:input path="mobileNo" size="30" /></td>
					<td><form:errors path="mobileNo" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Enter amount</td>
					<td><form:input path="wallet.balance"  size="30" /></td>
					<td><form:errors path="wallet.balance" cssClass="error"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Deposit"></td>
				</tr>
				</table>
			</form:form>
			
	
</body>
</html>