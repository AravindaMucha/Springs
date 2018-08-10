<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>

	<div>
		<h3>Login here</h3>
	</div>

	<%-- <form:form action="loginCustomer" method="post"
				modelAttribute="customer">
				<table>
				<tr>
					<td>Mobile Number</td>
					<td><form:input path="mobileNo" size="30" /></td>
					<td><form:errors path="mobileNo" cssClass="error" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login"></td>
				</tr>
				</table>
			</form:form> --%>

	<form action="loginCustomer" method="post" name="customerFrm">
		<table>
			<tr>
				<td>Mobile Number</td>
				<td><input type="text" name="mobileNo" /></td>
				<td><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>

	<div>
		<font color="red"> <c:if test="${not empty errorMessage}">
			${errorMessage}
			</c:if>
		</font>

	</div>



</body>
</html>