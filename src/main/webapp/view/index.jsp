<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Documentaries</title>
<style>
table.list {
	border-collapse: collapse;
	width: 30%;
}

table.list, table.list td, table.list th {
	border: 1px solid gray;
	padding: 5px;
}
</style>
</head>
<body>
	<h2>Documentaries</h2>
	<c:if test="${!empty documentaries}">
		<table class="list">
			<tr>
				<th align="left">Id</th>
				<th align="left">Documentary name</th>
				<th align="left">Category</th>
			</tr>
			<c:forEach items="${documentaries}" var="doc">
				<tr>
					<td>${doc.id}</td>
					<c:url value="/documentary/${doc.name}" var="link" />
					<td><a href="${link}">${doc.name}</a></td>
					<td>${doc.category}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<h3>Add a new documentary</h3>

	<form:form method="post" action="add" modelAttribute="documentary">
		<table>
			<tr>
				<td><form:label path="name">Name: </form:label></td>
				<td><form:input path="name"/></td>
			</tr>
			<tr>
				<td><form:label path="category">Category: </form:label></td>
				<td><form:input path="category" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Set info" /></td>
			</tr>
		</table>
	</form:form>

	<h3>Remove documentary</h3>
	<form:form method="post" action="remove" modelAttribute="documentary">
		<table>
			<tr>
				<td><form:label path="id">Id: </form:label></td>
				<td><form:input path="id" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Delete" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>