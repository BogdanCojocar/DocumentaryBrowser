<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Documentary</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
	<div id="container">
		<h2 class="title">${title}</h2>
		<br class="big_space">
		<div>
			<div id="floated">
				<img class="doc" src="<c:url value="${imgpath}" />" />
				${description}
			</div>
			<div>
				<br class="big_space"> <a href="${videolink}">Watch it!</a>
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
					<td colspan="2"><input type="submit" id="submit"
						value="Add comment" /></td>
				</tr>
			</table>
		</form:form>

		<h3>Comments:</h3>
		<c:if test="${!empty comments}">
			<table class="comment">
				<c:forEach items="${comments}" var="comment">
					<tr class="header">
						<td>${comment.author}(${comment.email})</td>
					</tr>
					<tr class="text">
						<td><p>${comment.text}</p></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>