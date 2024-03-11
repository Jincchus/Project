<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <!-- Favicons -->
  <link href="/img/favicon.png" rel="icon">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type=number] {
  -moz-appearance: textfield;
}

</style>
</head>
<body>

	<div class="login--div">
	<div>
	<h3>CyberUniversity 학사관리시스템</h3>

	</div>



		<form action="/login" method="post" class="main--container">
			<div class="login--container">
				<div class="id--container">
					<div class="login--id">
						<label for="userId"><span class="material-symbols-outlined">person</span></label> <input type="number" max="2147483647" name="id" id="userId" placeholder="아이디를 입력하세요" required value="${cookie.id.value}">
						<c:choose>
							<c:when test="${cookie.id == null}">
								<div class="checkbox--id">
									<input type="checkbox" name="rememberId">&nbsp;ID 저장
								</div>
							</c:when>
							<c:otherwise>
								<div class="checkbox--id">
									<input type="checkbox" name="rememberId" checked="checked">&nbsp;ID 저장
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="pwd--container">
					<div class="login--pwd">
						<label for="password"><span class="material-symbols-outlined">lock</span></label> <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요" required>
					</div>

				</div>
			</div>
			<div>
				<input type="submit" value="로그인" id="input--submit">
			</div>
			<ul class="login--info">
				<li><a href="/find/id" onclick="window.open(this.href, '_blank', 'width=500, height=300'); return false;"> ID 찾기 </a></li>
				<li><a href="/find/password" onclick="window.open(this.href, '_blank', 'width=500, height=350'); return false;"> 비밀번호 찾기 </a></li>
			</ul>
		</form>
	</div>

	<div class="login-div">

	<h4>테스트용 아이디</h4>
            	<p>학생: 2024000001(박경진 학생)</p>
            	<p>교수: 24000001(장명근 교수)</p>
            	<p>관리자: 240001(이준혁 교직원)</p>
            	<br/>
            	<h4>테스트용 비밀번호</h4>
            	<p>123123</p>

	</div>



</body>
</html>