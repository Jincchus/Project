<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title class="title">my bank</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- 외부 스타일 시트 가져오기 -->
	<link rel="stylesheet" href="/css/styles.css">
	<style>

	</style>
</head>
<body>

	<div class="jumbotron text-center banner--img" style="margin-bottom: 0">
		<img alt="MyBank" src="/images/m-cut.png">
	</div>

	<nav class="navbar navbar-expand-sm menu--line">
		<a class="navbar-brand" href="#">MENU</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				
				<c:choose>
					<c:when test='${principal != null}'>
						<li class="nav-item">
							<a class="nav-link nav-font text-center" href="/user/logout">로그아웃</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item" >
							<a class="nav-link nav-font text-center" href="/user/sign-in" >로그인</a>
						</li>
						<li class="nav-item">
							<a class="nav-link nav-font text-center" href="/user/sign-up">회원가입</a>
						</li>
					</c:otherwise>
				</c:choose>

				
			</ul>
		</div>
	</nav>

	<div class="container" style="margin-top: 30px">
		<div class="row aside aside1">
			<div class="col-sm-4">
				
				<div class="nav nav-pills user--info1">
					<div class="card" style="width: 18rem;">
					  <img src="/images/m-cut.png" class="card-img-top" alt="...">
					  <div class="card-body">
					    <h5 class="card-title">${principal.username }님</h5>
					    <p class="card-text"> 반갑습니다 </p>
					  </div>
					</div>
				</div>

				<ul class="nav nav-pills flex-column list-group text-center">
					<li class="list-group-item ">
						<a class="nav-link nav-font2" href="/account/save">계좌 생성</a>
					</li>
					<li class="list-group-item">
						<a class="nav-link nav-font2" href="/account/list">계좌 목록</a>
					</li>
				
					<li class="list-group-item">
						<a class="nav-link nav-font2" href="/account/withdraw">출금</a>
					</li>
					<li class="list-group-item">
						<a class="nav-link nav-font2" href="/account/deposit">입금</a>
					</li>
					<li class="list-group-item">
						<a class="nav-link nav-font2" href="/account/transfer">이체</a>
					</li>
				</ul>
				<hr class="d-sm-none">
			</div>
			<!-- end of header -->