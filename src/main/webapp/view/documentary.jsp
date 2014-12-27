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
</style>
</head>
<body>
	<h2>${title}</h2>
	<br>
	<br>
	<img style="width: 150px; height: 125px;" src="${imgpath}" align="left"
		hspace="5" vspace="10">
	<div>
		<div>${description}</div>
		<div style="font-weight: bold;">
			<a href="${videolink}"><big>Watch it!</big></a>
		</div>
		<div>
			<h3>Rating: ${rating}/10</h3>
		</div>
	</div>
	<div>Comments:</div>
</body>
</html>