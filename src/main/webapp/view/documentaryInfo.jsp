<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Add documentary info</title>
</head>
<body>
	<h2>Add info for documentary ${title}</h2>
	<form:form method="post" action="add_info"
		modelAttribute="documentaryInfo" enctype="multipart/form-data">
		<table>
			<form:input path="title" type="hidden" value="${title}" />
			<form:input path="rating" type="hidden" value="10" />
			<tr>
				<td style="vertical-align: top"><form:label path="description">Description: </form:label></td>
				<td><form:textarea cols="60" rows="25" path="description" /></td>
			</tr>
			<tr>
				<td><form:label path="imagePath">Add image: </form:label></td>
				<td><input type="file" name="image" /></td>
			</tr>
			<tr>
				<td><form:label path="videoLink">Video link: </form:label></td>
				<td><form:input path="videoLink" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add info" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>