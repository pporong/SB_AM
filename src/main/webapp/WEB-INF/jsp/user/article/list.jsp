<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LIST</title>
</head>
<body>

		<div>list</div>

		<hr />

		<table border="1">
				<thead>
						<tr>
								<th>번호</th>
								<th>날짜</th>
								<th>제목</th>
								<th>작성자</th>
						</tr>

				</thead>
				<tbody>
						<c:forEach var="article" items="${articles }">
								<tr>
										<td>${article.id}</td>
										<td>${article.regDate}</td>
										<td><a href="#"> ${article.title}</a></td>
										<td>${article.extra__writer}</td>
								</tr>
						</c:forEach>
				</tbody>

		</table>






</body>
</html>