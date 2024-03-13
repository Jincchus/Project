<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>학과 리스트</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<%@ include file="/WEB-INF/view/layout/header.jsp"%>
	<!-- ======= 상단 제목부분 ======= -->
	<div class="breadcrumbs" data-aos="fade-in">
		<div class="container">
			<h2>학과 리스트 페이지</h2>

		</div>
	</div>
	<!-- 상단 제목부분 끝 -->
	<div class="container mb-5">
		<!-- 왼쪽 사이드바 너비 만큼 메인 컨텐츠를 이동시킴 -->
		<div class="row">
			<!-- 사이드바 메뉴 -->
			<aside class="sidebar col-md-2 mt-5">
				<div class="card">
					<div class="card-header">메뉴</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">교수명단 조회</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">학생 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">교수 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">직원 등록</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">등록금 고지서 발송</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">휴학 처리</a></li>
						<li class="list-group-item p-3"><a style="color: black;"
							href="#">수강 신청 기간 설정</a></li>
					</ul>
				</div>
			</aside>

			<div class="col-md-10" style="text-align: center;">
				<h1 class="mt-5 mb-4">학과 리스트</h1>
				<!-- main 넣는 시작 부분 -->
				<div class="container">
					<table class="table table hover">
						<thead>
							<tr>
								<th scope="col">단과대학 번호</th>
								<th scope="col">학과 번호</th>
								<th scope="col">학과명</th>
							</tr>
						</thead>
						
						<tbody>
						<c:forEach var="department" items="${departmentList}">
							<tr>
								<th>${department.collegeId}</th> 
								<th>${department.id}</th> 
								<th>${department.name}
								<a href="/department/delete/${department.id}">삭제</a>
								<a href="/department/departmentUpdate/${department.id}">수정</a>
<%-- 								<button type="button" class="btn btn-link" onclick="location.href='/department/departmentUpdate/${department.id}'">수정</button> --%>
								</th>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- main 끝 -->

			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

