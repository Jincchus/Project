<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="./_header.jsp" %>
            <section id="cs">
                <div class="main">
                    <h1 class="title">
                        <strong>케이마켓</strong>이 도와드릴게요!
                    </h1>
                    <section class="notice">
                        <h1>
                            공지사항
                            <a href="${ctxPath}/cs/board/list.do?cate=notice&menu1=전체보기">전체보기</a>
                        </h1>                    
                        <ul>
                        <c:forEach var="notice" items="${notices }">
                            <li>
                                <a href="${ctxPath}/cs/board/view.do?cate=notice&menu1=${notice.menu1 }&cNo=${notice.cNo}" class="title">
                                <c:choose>
				                    <c:when test="${empty notice.menu2}">
					                        <!-- list.menu2가 null인 경우 -->
				                    </c:when>
				                    <c:otherwise>
				                        [<c:out value="${notice.menu2}"/>]
				                    </c:otherwise>
			                	</c:choose>
                                ${ notice.title}</a>
                                <span class="date">${notice.rdate }</span>
                            </li>
                        </c:forEach>   
                        </ul>
                    </section>
                    <section class="faq">
                        <h1>faq
                            <a href="${ctxPath}/cs/board/list.do?cate=faq&menu1=회원">전체보기</a>
                        </h1>
                        <ol>
                            <li>
                                <a href="${ctxPath}/cs/board/list.do?cate=faq&menu1=회원">
                                    <span>회원</span>
                                </a>
                            </li>
                            <li>
                                <a href="${ctxPath}/cs/board/list.do?cate=faq&menu1=쿠폰/이벤트">
                                    <span>쿠폰/이벤트</span>
                                </a>
                            </li>
                            <li>
                                <a href="${ctxPath}/cs/board/list.do?cate=faq&menu1=주문/결제">
                                    <span>주문/결제</span>
                                </a>
                            </li>
                            <li>
                                <a href="${ctxPath}/cs/board/list.do?cate=faq&menu1=배송">
                                    <span>배송</span>
                                </a>
                            </li>
                            <li>
                                <a href="${ctxPath}/cs/board/list.do?cate=faq&menu1=취소/반품/교환">
                                    <span>취소/반품/교환</span>
                                </a>
                            </li>
                            <li>
                                <a href="${ctxPath}/cs/board/list.do?cate=faq&menu1=여행/숙박/항공">
                                    <span>여행/숙박/항공</span>
                                </a>
                            </li>
                            <li>
                                <a href="${ctxPath}/cs/board/list.do?cate=faq&menu1=안전거래">
                                    <span>안전거래</span>
                                </a>
                            </li>
                        </ol>
                    </section>
                    <section class="qna">
                        <h1>
                            문의하기
                            <a href="${ctxPath}/cs/board/list.do?cate=qna&menu1=회원">전체보기</a>
                        </h1>
                        <ul>
                        <c:forEach var="qna" items="${qnas }">
                            <li>
                                <a href="${ctxPath}/cs/board/view.do?cate=qna&menu1=${qna.menu1 }&cNo=${qna.cNo}" class="title">
                                <c:choose>
				                    <c:when test="${empty qna.menu2}">
					                        <!-- list.menu2가 null인 경우 -->
				                    </c:when>
				                    <c:otherwise>
				                        [<c:out value="${qna.menu2}"/>]
				                    </c:otherwise>
			                	</c:choose>
                                ${ qna.title}</a>
                                <span>${qna.getMaskingWriter() }</span>
                                <span class="date">${qna.rdate }</span>
                            </li>
                        </c:forEach>  
                        </ul>
                        <c:if test="${not empty sessUser}">
                        <a href="${ctxPath}/cs/board/write.do?cate=qna" class="ask">문의글 작성</a>
                        </c:if>
                    </section>
                    <section class="tel">
                        <h1>1:1상담</h1>
                        <article>
                            <div>
                                <h3>고객센터 이용안내</h3>
                                <p>
                                    <span>일반회원/비회원</span><br>
                                    <strong>1566-0001</strong>
                                    <span>(평일09:00 ~ 18:00)</span>
                                </p>
                                <p>
                                    <span>스마일클럽 전용</span><br>
                                    <strong>1566-0002</strong>
                                    <span>(365일 09:00 ~ 18:00)</span>
                                </p>
                            </div>
                        </article>
                        <article>
                            <div>
                                <h3>판매 상담</h3>
                                <p>
                                    <span>판매고객</span><br>
                                    <strong>1566-5700</strong>
                                    <span>(평일09:00 ~ 18:00)</span>
                                </p>
                                <p>
                                    <a href="#">판매자 가입 및 서류 접수 안내</a>
                                </p>
                            </div>
                        </article>
                        
                    </section>
                </div>
            </section>
<%@include file ="./_footer.jsp" %>            