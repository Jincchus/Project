<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<script>

$(function(){

	$('.off ').click(function(e){
		${menu1}
		
	});


});

</script>
        <section id ="cs">
            <div class="${cate}">
                <nav>
                    <div>
                        <p>홈<span>></span>공지사항</p>
                    </div>
                </nav>
                <section class="${type}">
                    <aside>
                        <h2>공지사항</h2>
                        <ul>
                            <li class="${menu1 eq '전체보기'?'on':'off'}" >
                                <a href="${ctxPath}/cs/board/list.do?cate=notice&menu1=전체보기">전체</a>
                            </li>
                            <li class="${menu1 eq  '고객서비스'?'on':'off'}" >
                                <a href="${ctxPath}/cs/board/list.do?cate=notice&menu1=고객서비스">고객서비스</a>
                            </li>
                            <li class="${menu1 eq '안전거래'?'on':'off'}" >
                                <a href="${ctxPath}/cs/board/list.do?cate=notice&menu1=안전거래">안전거래</a>
                            </li>
                            <li class="${menu1 eq '유해상품'?'on':'off'}" >
                                <a href="${ctxPath}/cs/board/list.do?cate=notice&menu1=위해상품">위해상품</a>
                            </li>
                            <li class="${menu1 eq '이벤트당첨'?'on':'off'}" >
                                <a href="${ctxPath}/cs/board/list.do?cate=notice&menu1=이벤트당첨">이벤트당첨</a>
                            </li>

                        </ul>
                    </aside>
                    <article>
                 
                		<c:choose>
                		<c:when test="${menu1 eq '전체보기'}">
	                        <nav>
	                            <h1>전체</h1>
	                            <h2>공지사항 전체 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '고객서비스' }">
	                        <nav>
	                            <h1>고객서비스</h1>
	                            <h2>고객서비스 전체 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '안전거래' }">
	                        <nav>
	                            <h1>안전거래</h1>
	                            <h2>안전거래 전체 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '위해상품' }">
	                        <nav>
	                            <h1>위해상품</h1>
	                            <h2>위해상품 전체 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '이벤트당첨' }">
	                        <nav>
	                            <h1>이벤트당첨</h1>
	                            <h2>이벤트당첨 전체 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    </c:choose>
	                  