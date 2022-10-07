<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DEATIL" />
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div class="table-box-type-1">
				<table class="list_table" align="center">
				
					<!--  타이틀 -->
					<colgroup class="">
						<col width="200"/>

					</colgroup>
					
					<!-- 상세보기 바디 -->
					<tbody class="body">
							<tr>
								<th>번 호</th>
								<td>${article.id}</td>		
							</tr>	
							<tr>
								<th>작 성 자</th>
								<td>${article.extra__writerName}</td>		
							</tr>	
							<tr>
								<th>작 성 날 짜</th>
								<td>${article.regDate}</td>		
							</tr>	
							<tr>
								<th>수 정 날 짜</th>
								<td>${article.updateDate}</td>		
							</tr>	
							<tr>
								<th>제 목</th>
								<td>${article.title}</td>		
							</tr>	
							<tr>
								<th>내 용</th>
								<td>${article.body}</td>		
							</tr>	
					</tbody>	
				</table>
			</div>
			<div class="btn-box hover:text-blue-800">
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
			
		</div>
</section>

<%@ include file="../common/foot.jspf"%>