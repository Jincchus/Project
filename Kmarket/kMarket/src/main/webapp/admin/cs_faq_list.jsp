<%--
  Created by IntelliJ IDEA.
  User: leedonghan
  Date: 2023/09/16
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<main>
    <%@ include file="_aside.jsp"%>
    <section id="admin-cs-faq-list" class="admin-cs-list">
        <nav>
            <h3>자주묻는질문 목록</h3>
            <p>
                HOME > 고객센터 > <strong>자주묻는질문</strong>
            </p>
        </nav>
        <section>
            <div>
                <select id="menu1" name="menu1">
                    <option>회원</option>
                    <option>쿠폰/혜택/이벤트</option>
                    <option>주문/결제</option>
                    <option>배송</option>
                    <option>취소/반품/교환</option>
                </select>
                <select id="menu2" name="menu2">
                    <option selected>가입</option>
                    <option>탈퇴</option>
                    <option>회원정보</option>
                    <option>로그인</option>
                </select>
            </div>
            <table>
                <tr>
                    <th><input type="checkbox" name="all"/></th>
                    <th>번호</th>
                    <th>1차유형</th>
                    <th>2차유형</th>
                    <th>제목</th>
                    <th>조회</th>
                    <th>날짜</th>
                    <th>관리</th>
                </tr>
                <tr>
                    <td><input type="checkbox" name="상품코드"/></td>
                    <td>100</td>
                    <td>회원</td>
                    <td>가입</td>
                    <td>드래곤 제목</td>
                    <td>100</td>
                    <td>00.00.00</td>
                    <td>
                        <a href="#">[삭제]</a>
                        <a href="#">[수정]</a>
                    </td>
                </tr>
                <c:forEach var="article" items="${articles}">
                    <tr id="row-${article.cNo}">
                        <td><input type="checkbox" name="상품코드" class="cs_checkbox" value="${article.cNo}"/></td>
                        <td>${article.cNo}</td>
                        <td>${article.menu1}</td>
                        <td>${article.menu2}</td>
                        <td><a href="${ctxPath}/admin/cs/faq/detail.do?cNo=${article.cNo}">${article.title}</a></td>
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
                <input type="button" value="선택삭제" />
                <a href="${ctxPath}/admin/cs/faq/write.do">글작성</a>
            </div>
        </section>
    </section>
    </section>
</main>
<script>
    let menu1 = document.getElementById("menu1");
    let menu2 = document.getElementById("menu2");

    let data = {
        "회원":["2차선택","가입","탈퇴","회원정보","로그인"],
        "쿠폰/혜택/이벤트":["2차선택","쿠폰/할인혜택","포인트","제휴","이벤트"],
        "주문/결제":["2차선택","상품","결제","구매내역","영수증/증빙"],
        "배송":["2차선택","배송상태/기간", "배송정보확인/변경", "해외배송", "당일배송", "해외직구"],
        "취소/반품/교환":["2차선택","반품신청/철회", "반품정보확인/변경", "교환 AS신청/철회", "교환정보확인/변경", "취소신청/철회", "취소확인/환불정보"],
        "여행/숙박/항공":["2차선택","여행/숙박", "항공"],
        "안전거래":["2차선택","서비스 이용규칙 위반", "지식재산권침해", "법령 및 정책위반 상품", "게시물 정책위반", "직거래/외부거래유도", "표시광고", "청소년 위해상품/이미지"]
    }
    // 메뉴1 값 바뀌면 해당값의 data키 리스트를 순회하며 값을 만든당.
    menu1.addEventListener("change",(e)=>{
        while(menu2.firstChild){
            menu2.firstChild.remove();
        }
        let newOptionsString = "";
        data[e.target.value].forEach(el => {
            newOptionsString +="<option>"+el+"</option>";
            console.log(el);
        });
        menu2.innerHTML = newOptionsString;
    })
    menu2.addEventListener("change",(e)=>{
        if (e.target.value !== "2차선택") {
            //step 1: 2차선택값이 초기 선택값('2차선택')이 아닐때만 menu1,menu2값으로 리다이렉트한다.
            const menu1Val = menu1.value;
            const menu2Val = menu2.value;

            let curHref = location.href;
            let lastSlashIdx = curHref.lastIndexOf("/");
            let root = curHref.substring(0, lastSlashIdx);
            let newHref = root + "/list.do?menu1="+menu1Val+"&menu2="+menu2Val;
            location.replace(newHref);
        }
    })
</script>
<%@ include file="_footer.jsp"%>
