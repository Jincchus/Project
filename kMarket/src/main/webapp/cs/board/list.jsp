<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<jsp:include page="./_aside${cate}.jsp" />
<script>
$(function(){
	
	$('#답변완료').css({'color':'blue'});
	$('#검토중').css({'color':'grey'});
});//end


</script>
                       <table>
                       <c:forEach var="list" items="${lists}">
                            <tr>
                                <td><a href="${ctxPath}/cs/board/view.do?cate=${list.cate}&menu1=${list.menu1}&cNo=${list.cNo}">
                                <c:choose>
				                    <c:when test="${empty list.menu2}">
					                        <!-- list.menu2가 null인 경우 -->
				                    </c:when>
				                    <c:otherwise>
				                        [<c:out value="${list.menu2}"/>]
				                    </c:otherwise>
			                	</c:choose>
			                	${list.title }</a></td>
			                	<c:choose>
			                		<c:when test="${list.cate eq 'qna' }">
			                		<c:choose>
			                			<c:when test="${list.comment eq 1}">
			                				<td id="답변완료">답변완료</td>
			                			</c:when>
			                			<c:otherwise>
			                					<td id="검토중">검토중</td>
			                			</c:otherwise>
			                		</c:choose>
			                		</c:when>
			                	</c:choose>
	               				<td>${list.getMaskingWriter() }</td>
                                <td>${list.rdate}</td>
                            </tr>
                        </c:forEach>    
                        </table>
                        <div class="page">
                        <c:if test="${pageGroupStart>1 }">
                            <a href="${ctxPath}/cs/board/list.do?pg=${pageGroupStart-1}&cate=${cate }&menu1=${menu1}" class="prev">이전</a>
                       </c:if>
                       <c:forEach var="i" begin="${pageGroupStart}" end ="${pageGroupEnd }"> 
                            <a href="${ctxPath}/cs/board/list.do?pg=${i}&cate=${cate }&menu1=${menu1}" class="num ${currentPage==i?'on':''}">${i}</a>
                       </c:forEach>
                       <c:if test ="${pageGroupEnd < lastPageNum }">     
                            <a href="${ctxPath}/cs/board/list.do?pg=${pageGroupEnd+1}&cate=${cate}&menu1=${menu1}" class="next">다음</a>
                        </c:if>
                        </div>
                            <c:if test ="${cate eq 'qna'}">
	                        	<c:if test="${not empty sessUser}">
	                        		<a href="${ctxPath}/cs/board/write.do?cate=${cate }&menu1=${menu1}" class="btnWrite">작성하기</a>
                    			</c:if>
                    		</c:if>
                    </article>
                </section>
            </div>
        </section>
<%@include file ="../_footer.jsp" %>