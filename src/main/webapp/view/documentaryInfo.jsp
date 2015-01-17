<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Add documentary info</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
	<div id="container">
		<h2>Add info for documentary ${title}</h2>
		<form:form method="post" action="add_info" class="info"
			modelAttribute="documentaryInfo" enctype="multipart/form-data">
			<table>
				<form:input path="title" type="hidden" value="${title}" />
				<form:input path="rating" type="hidden" value="10" />
				<tr>
					<td style="vertical-align: top"><form:label path="description">Description: </form:label></td>
					<td><form:textarea class="info" cols="60" rows="25"
							path="description" /></td>
				</tr>
				<tr>
					<td><form:label path="imagePath">Add image: </form:label></td>
					<td><input type="file" name="image"/></td>
				</tr>
				<tr>
					<td><form:label path="videoLink">Video link: </form:label></td>
					<td><form:input class="info" path="videoLink" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" id="submit"
						value="Add info" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>