<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
  <link rel="stylesheet" href="../css/common.css">
  <link rel="stylesheet" href="../css/member.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
  <style>

  </style>
</head>

<body>
  <div id="wrapper">
    <header>
      <div class="top">
        <div>
        <c:if test="${empty  sessUser}">
          <a href="/Kmarket/member/login.do">로그인</a>
          <a href="/Kmarket/member/register.do">회원가입</a>
       	</c:if>
       	<c:if test="${not empty  sessUser}">
       	  <a href="/Kmarket/member/logout.do">로그아웃</a>
          <a href="#">마이페이지</a>
          <a href="#">
            <i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니
            <span class="material-symbols-outlined"></span>
          </a>
        </c:if>
        </div>
      </div>
      <div class="logo">
        <div>
          <a href="">
            <img src="../image/header_logo.png" alt="헤더 로고" />
          </a>
        </div>
      </div>
    </header>