<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<jsp:include page="./_aside${dto.cate}.jsp" />
                        <nav>
                            <h2 class="title"><span>${dto.cate eq 'faq'?'Q.':''}</span>${dto.title }</h2>
	                         <c:choose>   
	                            <c:when test="${dto.cate eq 'qna' }">
	                            <p>${dto.getMaskingWriter() }
	                            ${dto.getRdate()}</p>
	                            </c:when>
	                         </c:choose>   
                        </nav>
                        <div class="content">
                            <p>
								${dto.content}
                              </p>
                        </div>
                         <c:if test ="${cate eq 'qna'}">
                         <div class="answer">
                         <h3 class="answer-heading">답변</h3>
                         <c:choose>
                         	<c:when test="${dto.comment eq 0}">
                         		<p class="reply-content">등록된 답변이 없습니다.</p>
                         	</c:when>
                         	<c:otherwise>
                         		<p>${reply.rdate}</p>
                         		<p class="reply-content">${reply.content}</p>
                         	</c:otherwise>
                         </c:choose>
                         </div>
                         </c:if>
                        <a href="${ctxPath }/cs/board/list.do?cate=${dto.cate}&menu1=${dto.menu1}" class="btnList">목록보기</a>
                    </article>   
                </section> 
            </div>
        </section>
<%@include file ="../_footer.jsp" %>