<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Documentaries</title>
<style>
h2 {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<h2>Error!</h2>
	<h2>${message}</h2>
	<form:form method="GET" action="index">
		<table>
			<tr>
				<td><input type="submit" value="Back" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>