<%--
  Created by IntelliJ IDEA.
  User: leedonghan
  Date: 2023/09/17
  Time: 12:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<main>
    <%@ include file="_aside.jsp"%>
<script>

	$(function(){
		$('#delete_button').click(function(){
			
			var menu1 = "${list.menu1}";
            var menu2 = "${list.menu2}";
            var ctxPath="${ctxPath}";
			var cNo =	"${list.cNo}";
			if(confirm('정말로 삭제하시겠습니까?')){
				window.location.href=ctxPath+"/cs/board/delete.do?menu1="+menu1+"&menu2="+menu2+"&cNo="+cNo;
			}else{
				event.preventDefault();
			}
		});
		
	});

</script>    
    
    <section class="write">
        <nav>
            <h3>문의하기 답변</h3>
            <p>
                HOME > 고객센터 > <strong>문의하기</strong>
            </p>
        </nav>
        <article>
            <form action="${ctxPath }/admin/cs/qna/answer.do" method="post">
                <input type="hidden"  value="${list.menu1 }" id="menu1" name="menu1"></input>
                <input type="hidden"  value="${list.menu2 }" id="menu2" name="menu2" ></input>
                <input type="hidden"  value="${list.cNo}" id="cNo" name="cNo" ></input> 
                <table>
                    <tr>
                        <td id="type">유형</td>
                        <td><div>${list.menu1 }-${list.menu2 }</div></td>
                    </tr>
                    <tr>
                        <td>제목</td>
                        <td><input type="text" name="title" readonly value="${list.title }"></td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content" readonly>${list.content}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>답변하기</td>
                         <c:choose>
                        <c:when test="${list.comment  eq 0}">
	                        <td>
	                            <textarea name="reply" required placeholder="답변을 입력하시오."></textarea>
	                        </td>
                        </c:when>
                        <c:otherwise>
	                        <td>
	                            <textarea name="reply" readonly >${reply.content }</textarea>
	                        </td>
                        </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
                <div class="btnGroupQna">
                    <a id="delete_button" href="#" >삭제하기</a>
                    <a href="${ctxPath}/admin/cs/qna/list.do?menu1=${list.menu1 }&menu2=${list.menu2}" >목록으로</a>
                    <input type="submit" value="답변하기" id="reply_button">
                </div>
            </form>
        </article>
    </section>
</main>
<script>

</script>
<%@ include file="_footer.jsp"%>

