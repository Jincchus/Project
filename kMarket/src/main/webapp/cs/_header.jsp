<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>케이마켓고객센터</title>
    <link rel="shortcut icon" type="images/x-icon" href="/kMarket/cs/images/favicon.ico">
    <link rel="stylesheet" href="/kMarket/cs/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
        <div id ="wrapper">
            <header>
                <div class="top">
		          <div>
		          		<p>
		          	  	<c:if test="${empty sessUser }">
				            <a href="${ctxPath} ">홈</a>
				            <a href="${ctxPath}/member/login.do">로그인</a>
				            <a href="${ctxPath}/member/join.do">회원가입</a>
			            </c:if>
		             	<c:if test="${not empty sessUser }">
			          	  	<a>${sessUser.name }님 안녕하세요.</a>
				            <a href="${ctxPath} ">홈</a>
				            <a href="#">마이페이지</a>
				            <a href="#"><i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니</a>
					        <a href="${ctxPath}/member/logout.do">로그아웃</a>
			            <c:if test="${sessUser.type eq 2}">
			            	<a href="${ctxPath}/admin/index.do">판매자페이지</a>
			            </c:if>
			            <c:if test="${sessUser.level eq '5' }">
			            	<a href="${ctxPath}/admin/index.do">관리자페이지</a>
			             	</c:if>
		                </c:if>
		                </p>
		          </div>
		        </div>
                <div class="logo">
                    <div>
                    <a href="${ctxPath}/cs/index.do"><img src="/kMarket/cs/images/logo.png" alt="로고">고객센터</a>
                    </div>
                </div>    
            </header>