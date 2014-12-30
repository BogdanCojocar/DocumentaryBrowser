<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Documentary</title>
<style>
.blank_row {
	height: 10px !important; /* Overwrite any previous rules */
	background-color: #FFFFFF;
}
</style>
</head>
<body>
	<h2>${title}</h2>
	<br>
	<br>
	<img style="width: 150px; height: 125px;"
		src="<c:url value="${imgpath}" />" align="left" hspace="5" vspace="10" />
	<div>
		<div>${description}</div>
		<br> <br>
		<div style="font-weight: bold;">
			<a href="${videolink}"><big>Watch it!</big></a>
		</div>
		<div>
			<h3>Rating: ${rating}/10</h3>
		</div>
	</div>

	<h3>Add a new comment</h3>

	<form:form method="post" action="add_comment/${title}"
		modelAttribute="comment">
		<table>
			<tr>
				<td><form:label path="author">Author: </form:label></td>
				<td><form:input path="author" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email: </form:label></td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td style="vertical-align: top"><form:label path="text">Text: </form:label></td>
				<td><form:textarea cols="30" rows="5" path="text" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add comment" /></td>
			</tr>
		</table>
	</form:form>

	<h3>Comments:</h3>
	<c:if test="${!empty comments}">
		<table class="list">
			<c:forEach items="${comments}" var="comment">
				<tr>
					<td>Author: ${comment.author}</td>
				</tr>
				<tr>
					<td>Email: ${comment.email}</td>
				</tr>
				<tr>
					<td>Text: ${comment.text}</td>
				</tr>
				<tr class="blank_row">
					<td></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>