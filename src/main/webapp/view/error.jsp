<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Documentaries</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
	<form:form method="GET" action="${pageContext.request.contextPath}/">
		<table>
			<tr>
				<td><h2 class="error">Error!</h2></td>
			</tr>
			<tr>
				<td><h2 class="error">${message}</h2></td>
			</tr>
			<tr>
				<td><input type="submit" value="back" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>