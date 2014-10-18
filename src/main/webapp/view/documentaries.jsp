<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<td>${doc.name}</td>
					<td>${doc.category}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<h3>Add a new documentary</h3>

	<s:form method="post" action="add">
		<table>
			<tr>
				<td><s:textfield key="name" name="documentary.name" /></td>
			</tr>
			<tr>
				<td><s:textfield key="category" name="documentary.category" /></td>
			</tr>
			<tr>
				<td><s:submit key="add"></s:submit></td>
			</tr>
		</table>
	</s:form>
	
	<h3>Remove documentary</h3>
	<s:form method="post" action="">
		<table>
			<tr>
				<td><s:textfield key="Id" name="documentary.id" /></td>
			</tr>
			<tr>
				<td><s:submit key="remove"></s:submit></td>
			</tr>
		</table>
	</s:form>

</body>
</html>