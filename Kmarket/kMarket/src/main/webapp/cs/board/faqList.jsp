<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<jsp:include page="./_aside${cate}.jsp" />
                     	<c:forEach var="menu2" items="${menu2Group}">
                        <div>
                          	<h3>${menu2.key }</h3>
                            <ul>
                            <c:forEach var="list" items="${menu2.value}">
                        		<li>
                             	    <a href="${ctxPath}/cs/board/view.do?cate=${list.cate}&menu1=${list.menu1}&cNo=${list.cNo}">
                                        <span>Q.</span>
                                        ${list.title}
                                    </a>
                                </li>
                              </c:forEach>  
                             <li class="more">
                             <a href="#" >더보기</a>
                             </li>   
                            </ul>
                        </div>
                        </c:forEach>
                    </article>
                </section>
            </div>
        </section>
<%@include file ="../_footer.jsp" %>