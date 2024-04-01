<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
        <section id ="cs">
            <div class="${cate}">
                <nav>
                    <div>
                        <p>홈<span>></span>문의하기</p>
                    </div>
                </nav>
                <section class="${type}">
                    <aside>
                        <h2>문의하기</h2>
                        <ul>
                            <li class="${menu1 eq '회원'?'on':'off'}">
                                <a href="${ctxPath}/cs/board/list.do?cate=qna&menu1=회원">회원</a>
                            </li>
                            <li class="${menu1 eq '쿠폰/혜택/이벤트'?'on':'off'}">
                                <a href="${ctxPath}/cs/board/list.do?cate=qna&menu1=쿠폰/혜택/이벤트">쿠폰/이벤트</a>
                            </li>
                            <li class="${menu1 eq '주문/결제'?'on':'off'}">
                                <a href="${ctxPath}/cs/board/list.do?cate=qna&menu1=주문/결제">주문/결제</a>
                            </li>
                            <li class="${menu1 eq '배송'?'on':'off'}">
                                <a href="${ctxPath}/cs/board/list.do?cate=qna&menu1=배송">배송</a>
                            </li>
                            <li class="${menu1 eq '취소/반품/교환'?'on':'off'}">
                                <a href="${ctxPath}/cs/board/list.do?cate=qna&menu1=취소/반품/교환">취소/반품/교환</a>
                            </li>
                            <li class="${menu1 eq '여행/숙박/항공'?'on':'off'}">
                                <a href="${ctxPath}/cs/board/list.do?cate=qna&menu1=여행/숙박/항공">여행/숙박/항공</a>
                            </li>
                            <li class="${menu1 eq '안전거래'?'on':'off'}">
                                <a href="${ctxPath}/cs/board/list.do?cate=qna&menu1=안전거래">안전거래</a>
                            </li>
                        </ul>
                    </aside>
                    <article>
                    <c:choose>
                		<c:when test="${menu1 eq '회원'}">
	                        <nav>
	                            <h1>회원</h1>
	                            <h2>회원관련 문의 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '쿠폰/혜택/이벤트' }">
	                        <nav>
	                            <h1>쿠폰/혜택/이벤트</h1>
	                            <h2>쿠폰/혜택/이벤트 관련 문의 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '주문/결제'}">
	                        <nav>
	                            <h1>주문/결제</h1>
	                            <h2>주문/결제 관련 문의 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '배송' }">
	                        <nav>
	                            <h1>배송</h1>
	                            <h2>배송관련 문의 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '취소/반품/교환' }">
	                        <nav>
	                            <h1>취소/반품/교환</h1>
	                            <h2>취소/반품/교환 관련 문의 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '여행/숙박/항공' }">
	                        <nav>
	                            <h1>여행/숙박/항공</h1>
	                            <h2>여행/숙박/항공 관련 문의 내용입니다.</h2>
	                        </nav>
	                    </c:when>
	                    <c:when test ="${menu1 eq '안전거래' }">
	                        <nav>
	                            <h1>안전거래</h1>
	                            <h2>안전거래 관련 문의 내용입니다.</h2>
	                        </nav>
	                    </c:when>    
                    </c:choose>
                   