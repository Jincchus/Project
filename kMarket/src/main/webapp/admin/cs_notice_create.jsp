<%--
  Created by IntelliJ IDEA.
  User: leedonghan
  Date: 2023/09/16
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%--TODO: 글작성시 작성자 등록해주기--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<main>
    <%@ include file="_aside.jsp"%>
    <section class="write">
        <nav>
            <h3>공지사항 작성</h3>
            <p>
                HOME > 고객센터 > <strong>공지사항</strong>
            </p>
        </nav>
        <article>
            <form action="${ctxPath}/admin/cs/notice/write.do" method="post">
                <input type="hidden" name="cate" value="notice"/>
                <table>
                    <tr>
                        <td>유형</td>
                        <td><select name="menu1">
                            <option selected>전체</option>
                            <option>고객서비스</option>
                            <option>안전거래</option>
                            <option>위해상품</option>
                            <option>이벤트 당첨</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td>제목</td>
                        <td><input type="text" name="title" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea name="content" required></textarea>
                        </td>
                    </tr>
                </table>
                <div class="btnGroup">
                    <a href="${ctxPath}/admin/cs/notice/list.do" >취소</a>
                    <input type="submit" value="작성완료">
                </div>
            </form>
        </article>
    </section>
</main>
<%@ include file="_footer.jsp"%>

