<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <title>케이마켓::대한민국 1등 온라인 쇼핑몰</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script src="${ctxPath }/product/js/category.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <link rel="stylesheet" href="${ctxPath }/product/css/common.css"/>
    <link rel="stylesheet" href="${ctxPath }/product/css/product.css"/>
    <style>
    </style>
    <script>
        
    </script>
</head>
<body>
    <div id="wrapper">
        <header>
            <div class="top">
                <div>
            <a href="${ctxPath }/member/login.do">로그인</a>
            <a href="${ctxPath }/member/join.do">회원가입</a>
            <a href="#">마이페이지</a>
            <a href="${ctxPath }/admin/index.do">관리자페이지</a>
            <a href="#"
              ><i class="fa fa-shopping-cart" aria-hidden="true"></i
              >&nbsp;장바구니</a
            >
          </div>
            </div>
            <div class="logo">
                <div>
                    <a href="#">
                        <img src="${ctxPath }/product/img/header_logo.png" alt="로고">
                    </a>
                    <form action="#">
                        <input type="text" name="keyword">
                        <button>
                            <i class="fa fa-search" aria-hidden="true"></i>
                        </button>
                    </form>
                </div>
            </div>
            <div class="menu">
                <div>
                    <ul>
                        <li>
                            <a href="#">히트상품</a>
                        </li>
                        <li>
                            <a href="#">추천상품</a>
                        </li>
                        <li>
                            <a href="#">최신상품</a>
                        </li>
                        <li>
                            <a href="#">인기상품</a>
                        </li>
                        <li>
                            <a href="#">할인상품</a>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <a href="#">쿠폰존</a>
                        </li>
                        <li>
                            <a href="#">사용후기</a>
                        </li>
                        <li>
                            <a href="#">개인결제</a>
                        </li>
                        <li>
                           <a href="${ctxPath }/cs/index.do">고객센터</a>
                        </li>
                        <li>
                            <a href="${ctxPath }/cs/board/list.do?cate=faq&menu1=회원">FAQ</a>
                        </li>
                    </ul>
                </div>
            </div>
        </header>