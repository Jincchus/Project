<%--
  Created by IntelliJ IDEA.
  User: leedonghan
  Date: 2023/09/16
  Time: 6:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%--TODO:1. 전체 선택하기, 선택 삭제 구현하기, 2. 검색하기 구현--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_header.jsp" %>
<main>
    <%@ include file="_aside.jsp" %>
    <section id="admin-cs-notice-list" class="admin-cs-list">
        <nav>
            <h3>공지 사항 목록</h3>
            <p>
                HOME > 고객센터 > <strong>공지사항</strong>
            </p>
        </nav>
        <section>
            <div>
                <select name="filter_by_menu1" class="filter_by_menu1">
                    <option ${selectedValue.equals("전체") ? "selected" : ""}>전체</option>
                    <option ${selectedValue.equals("고객서비스") ? "selected" : ""}>고객서비스</option>
                    <option ${selectedValue.equals("안전거래") ? "selected" : ""}>안전거래</option>
                    <option ${selectedValue.equals("위해상품") ? "selected" : ""}>위해상품</option>
                    <option ${selectedValue.equals("이벤트 당첨") ? "selected" : ""}>이벤트 당첨</option>
                </select>
            </div>
            <table>
                <tr>
                    <th><input type="checkbox" name="all" id="all_or_none"/></th>
                    <th>번호</th>
                    <th>유형</th>
                    <th>제목</th>
                    <th>조회</th>
                    <th>날짜</th>
                    <th>관리</th>
                </tr>
                <c:forEach var="article" items="${articles}">
                    <tr id="row-${article.cNo}">
                        <td><input type="checkbox" name="상품코드" class="cs_checkbox" value="${article.cNo}"/></td>
                        <td>${article.cNo}</td>
                        <td>${article.menu1}</td>
                        <td><a href="${ctxPath}/admin/cs/notice/detail.do?cNo=${article.cNo}">${article.title}</a></td>
                        <td>${article.hit}</td>
                        <td>${article.rdate}</td>
                        <td>
                            <a href="#" class="delete_aTag" id="delete_aTag_${article.cNo}">[삭제]</a>
                            <a href="${ctxPath}/admin/cs/notice/modify.do?cNo=${article.cNo}">[수정]</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="notice_list_button">
                <input type="button" value="선택삭제" id="delete_selected_btn"/>
                <a href="${ctxPath}/admin/cs/notice/write.do">글작성</a>
            </div>
            <div class="paging">
                <c:if test="${pageGroupStart>1 }">
                        <span class="prev">
                            <a href="${ctxPath}/admin/cs/notice/list.do?pg=${pageGroupStart-1}&menu1=${selectedValue}"><&nbsp;이전</a>
                        </span>
                </c:if>
                <span class="num">
                    <c:forEach var="i" begin="${pageGroupStart}" end ="${pageGroupEnd }">
                        <a href="${ctxPath}/admin/cs/notice/list.do?pg=${i}&menu1=${selectedValue}" class="${currentPage==i?'on':''}">${i}</a>
                    </c:forEach>
                        </span>
                <span class="next">
                            <a href="${ctxPath}/admin/cs/notice/list.do?pg=${pageGroupStart+1}&menu1=${selectedValue}">다음&nbsp;></a>
                        </span>
            </div>
        </section>
    </section>
    </section>
</main>
<script>
    //전체 선택
    let allOrNone = document.getElementById("all_or_none");
    let checkBoxChildren = document.querySelectorAll(".cs_checkbox");
    allOrNone.addEventListener("click",(e)=>{
        if(allOrNone.checked){
            for (const child of checkBoxChildren) {
                child.checked = true;
            }
        }else{
            for (const child of checkBoxChildren) {
                child.checked = false;
            }
        }
    })
    //전체 선택 끝

    //선택 삭제 요청
    //LEARN: location.href로 현재 위치를 알 수 있다.
    //const deleteSelectedArticles = fetch()
    let selectDeleteBtn = document.getElementById("delete_selected_btn");
    selectDeleteBtn.addEventListener("click",async (e)=>{
        //step1 : 현재 url 기준으로 끝 부분만 list.do => delete.do로 바꾸기
        let curHref = location.href;
        let lastSlashIdx = curHref.lastIndexOf("/");
        let root = curHref.substring(0, lastSlashIdx);
        let newHref = root + "/delete.do";
        //step2 : articleIds 로 체크된거 담기
        let articleIds = [];
        for (const child of checkBoxChildren) {
            if (child.checked) {
                articleIds.push(child.value);
            }
        }
        //step3 : ajax 요청 보내기
        let data = {articleIds}
        let options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data)
        };
        await fetch(newHref, options).then(()=>{
            for (const id of articleIds) {
                let target = document.getElementById("row-"+id);
                target.remove();
            }
        });
    })
    //선택 삭제 요청 끝

    //공지사항 메뉴로 리스트 조회 하기 시작
    let filter = document.querySelector(".filter_by_menu1");
    filter.addEventListener("change",(e)=>{
        alert(e.target.value);
        location.replace('${ctxPath}/admin/cs/notice/list.do?menu1='+e.target.value+"&pg=1");
    })
    //공지사항 메뉴로 리스트 조회 하기 끝

    //list에서 '삭제' 버튼 클릭시 confirm이후 삭제 처리하기
    let delete_aTags = document.querySelectorAll(".delete_aTag");
    for (const aTag of delete_aTags) {
        aTag.addEventListener("click",(e)=>{
            e.preventDefault();
            if (confirm("정말로 삭제를 하세요?")) {
                let lastUnderIdx = e.target.id.lastIndexOf("_");
                let targetId = e.target.id.substring(lastUnderIdx + 1);
                let curHref = location.href;
                let lastSlashIdx = curHref.lastIndexOf("/");
                let root = curHref.substring(0, lastSlashIdx);
                let newHref = root + "/delete.do?cNo="+targetId;

                location.replace(newHref);
            }
        })
    }

</script>
<%@ include file="_footer.jsp" %>
