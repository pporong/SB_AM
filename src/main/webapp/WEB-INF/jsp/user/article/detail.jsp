<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DETAIL</title>
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
	.detailP {
		width : 500px;
		margin : 0 auto;
		margin-top : 15px;
		text-overflow: ellipsis;
		overflow : hidden; 
		/* white-space: nowrap; */
		word-spacing : 5px;
		/* text-align : center; */
		border : 2px solid black;
		padding: 4px 10px 10px 33px;
	}
</style>
	<h1>DETAIL</h1>
	
	<hr />

<div class="detailP">

	<h3 class="detailHeadText"> ${article.id}번 글 상세보기 </h3>
	
		<c:forEach var="article" items="${articles }">
			<div>* 번  호 : ${article.id} </div>
			<div>* 작성자 : ${article.extra__writer} %> </div>
			<div>* 날  짜 : ${article.regDate.substring(0, 10)}> </div>
			<div>* 제  목 :<a href="#"> ${article.title} </a></div>
			<div>* 내  용 : ${article.body}</div>
		</c:forEach>
	<br />
	<br />

</div>





</body>
</html>