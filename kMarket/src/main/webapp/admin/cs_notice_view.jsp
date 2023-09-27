<%--
  Created by IntelliJ IDEA.
  User: 82102
  Date: 2023-09-19
  Time: 오후 4:30
  To change this template use File | Settings | File Templates.
--%>
<%--TODO: ajax처리로 삭제 구현하기--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp" %>
<main>
    <%@ include file="_aside.jsp" %>
    <section id="admin-cs-notice-view">
        <nav>
            <h3>공지사항 보기</h3>
            <p>
                HOME > 고객센터 > <strong>공지사항</strong>
            </p>
        </nav>
        <section>
            <table>
                <tr>
                    <th>유형</th>
                    <td>${article.menu1} </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>${article.title}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>${article.content}</td>
                </tr>
            </table>

            <div>
                <input id="delete_btn" type="button" value="삭제" />
                <input id="modify_btn" type="button" value="수정" />
                <input id="list_btn" type="button" value="목록" />
            </div>
        </section>
    </section>
</main>
<script>
    let deleteBtn = document.getElementById("delete_btn");
    let modifyBtn = document.getElementById("modify_btn");
    let listBtn = document.getElementById("list_btn");
    //TODO: reimplementation with ajax request
    deleteBtn.addEventListener("click", async ()=>{
        location.replace("/admin/cs/notice/delete.do?cNo="+${article.cNo});
    })
    modifyBtn.addEventListener("click",()=>{
        location.replace("/admin/cs/notice/modify.do?cNo="+${article.cNo});
    })
    listBtn.addEventListener("click",()=>{
        location.replace("/admin/cs/notice/list.do");
    })
</script>
<%@ include file="_footer.jsp" %>