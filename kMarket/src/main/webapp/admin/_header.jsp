<%--
  Created by IntelliJ IDEA.
  User: leedonghan
  Date: 2023/09/16
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <meta charset="UTF-8">
    <title>케이마켓::관리자</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="${ctxPath }/admin/js/gnb.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctxPath }/admin/css/admin.css" type="text/css">
</head>
<body>
<div id="admin-wrapper">
    <header>
        <div>
            <a href="${ctxPath}/admin/index.do" class="logo"><img src="${ctxPath}/admin/img/admin_logo.png" alt="admin_logo"/></a>
            <p>
                <span>홍길동님 반갑습니다.</span>
               

                <a href="${ctxPath}">INDEX |</a>
                <a href="${ctxPath}/admin/index.do">HOME |</a>
                <a href="${ctxPath}/member/logout.do">로그아웃 |</a>
                <a href="#">고객센터</a>
            </p>
        </div>
    </header>