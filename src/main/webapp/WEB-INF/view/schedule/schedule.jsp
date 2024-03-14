<%@page import="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<style>
body > div{
	padding-top: 100px;
}
main {
	height: 1000px;
}

</style>

<div class="d-flex justify-content-center align-items-start" style="min-width: 100em;">
	<div class="sub--menu">
		<div class="sub--menu--top">
			<h2>학사정보</h2>
		</div>
		<div class="sub--menu--mid">
			<table class="sub--menu--table" border="1">
				<tr>
					<td><a href="/notice">공지사항</a></td>
				</tr>
				<tr>
					<td><a href="/schedule" class="selected--menu">학사일정</a></td>
				</tr>
				<c:if test="${principal.userRole.equals(\"staff\") }">
					<tr>
						<td><a href="/schedule/list"> 학사일정 등록</a></td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>

	<main>
		<h1>학사일정</h1>
		<div class="container">
		<div></div>
			
		<table  class="room--table">
			<tbody>
				<c:forEach var ="schedule" items ="${schedule}">
					<tr>
						<td class ="month" width ="100px;">${schedule.months}월</td>
						 <td class = "line">${schedule.startDay}~${schedule.endDay}</td>
						 <td class = "line" >${schedule.information}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</main>
</div>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>

