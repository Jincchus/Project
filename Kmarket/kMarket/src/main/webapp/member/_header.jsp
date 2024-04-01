<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
  <link rel="stylesheet" href="/kMarket/member/css/common.css">
  <link rel="stylesheet" href="/kMarket/member/css/member.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <style>

  </style>
</head>

<body>
  <div id="wrapper">
    <header>
      <div class="top">
          <div>
          	  	<c:if test="${empty sessUser }">
		            <a href="/kMarket/member/login.do">로그인</a>
		            <a href="/kMarket/member/join.do">회원가입</a>
	             </c:if>
             <c:if test="${not empty sessUser }">
          	  	<a>${sessUser.name }님 안녕하세요.</a>
	            <a href="#">마이페이지</a>
	            <a href="#"><i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니</a>
		        <a href="${ctxPath}/member/logout.do">로그아웃</a>
	            <c:if test="${sessUser.type eq 2}">
	            <a href="/kMarket/admin/index.do">판매자페이지</a>
	            </c:if>
	            <c:if test="${sessUser.level eq '5' }">
	            <a href="/kMarket/admin/index.do">관리자페이지</a>
	             	</c:if>
                </c:if>
          </div>
        </div>
      <div class="logo">
        <div>
          <a href="${ctxPath}">
            <img src="/kMarket/member/image/header_logo.png" alt="헤더 로고" />
          </a>
        </div>
      </div>
    </header>