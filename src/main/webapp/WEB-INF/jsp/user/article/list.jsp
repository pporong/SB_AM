<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LIST</title>
</head>
<body>
<style>
	h1 {
		text-align: center;
		color : navy;
	}
	.list_table {
		text-align: center;
		padding : 4px;
		width: 500px;
		margin-top: 20px;
	}
</style>
	<h1>LIST</h1>
	
	<hr />

	<table class="list_table" border="1" bordercolor="pink" align="center">
	
		<thead class="title">
			<tr>
				<th>번 호</th>
				<th>날 짜</th>
				<th>제 목</th>
				<th>작성자</th>
			</tr>
		</thead>
		
			<tbody class="body">
				<c:forEach var="article" items="${articles }">
					<tr> 
						<td>${article.id}</td>
						<td>${article.regDate.substring(0, 10)}</td>
						<td><a href="#"> ${article.title}</a></td>
						<td>${article.extra__writer}</td>
					</tr>
				</c:forEach>
			</tbody>
			
	</table>






</body>
</html>