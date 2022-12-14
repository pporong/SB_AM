<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST" />
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl con">
		<div class="container mx-auto px-3">
			<div class="table-box-type-1">
			
				<table class="list_table" align="center">
					<colgroup>
					<col width="30"/>
					<col width="140"/>
					<col width="30"/>
					<col width="100"/>
					<col width="30"/>
					<col width="30"/>
					</colgroup>
					
					<!-- 리스트 타이틀 -->
					<thead class="title">
						<tr class="">
							<th>번 호</th>
							<th>제 목</th>
							<th>작성자</th>
							<th>날 짜</th>
								<th>수 정</th>
								<th>삭 제</th>
						</tr>
					</thead>
					
					<!-- 리스트 바디 -->
					<tbody class="body">
						<c:forEach var="article" items="${articles }">
							<tr>
								<td>${article.id}</td>
								<td><a class="hover:underline" href="../article/detail?id=${article.id}"> ${article.title}</a></td>
								<td>${article.extra__writerName}</td>
								<td>${article.regDate.substring(2, 16)}</td>
								
						
									<td><a class="hover:text-green-600 m-1 " href="../article/modify?id=${article.id }">수정</a></td>
									<td><a class="hover:text-pink-600  m-1" onclick="if(confirm('정말 삭제 하시겠습니까?') == false) return false;"
										   href="../article/doDelete?id=${article.id }">삭제</a></td>

								

							</tr>
						</c:forEach>
					</tbody>
				</table>	
			</div>
			
<!-- 			<div class="btn-box">
				<button type="button" onclick="history.back();">삭 제</button>
			</div> -->
			
		</div>
</section>


<%@ include file="../common/foot.jspf"%>