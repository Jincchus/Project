<%--
  Created by IntelliJ IDEA.
  User: leedonghan
  Date: 2023/09/16
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<main>
    <%@ include file="_aside.jsp"%>
    <section class="write">
        <nav>
            <h3>자주묻는질문 작성</h3>
            <p>
                HOME > 고객센터 > <strong>자주묻는질문</strong>
            </p>
        </nav>
        <article>
            <form action="${ctxPath}/admin/cs/faq/write.do" method="post">
                <input type="hidden" name="cate" value="faq"/>
                <table>
                    <tr>
                        <td>유형</td>
                        <td><select id="menu1" name="menu1">
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
                            <button id="modify_menu">유형수정</button>
                        </td>
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
                    <a href="${ctxPath}/admin/cs/faq/list.do" >취소</a>
                    <input type="submit" value="작성완료">
                </div>
            </form>
        </article>
    </section>
</main>
<script>
    let menu1 = document.getElementById("menu1");
    let menu2 = document.getElementById("menu2");


    const data = {
        "회원": ["가입", "탈퇴", "회원정보", "로그인"],
        "쿠폰/혜택/이벤트": ["쿠폰/할인혜택", "포인트", "제휴", "이벤트"],
        "주문/결제": ["상품", "결제", "구매내역", "영수증/증빙"],
        "배송": ["배송상태/기간", "배송정보확인/변경", "해외배송", "당일배송", "해외직구"],
        "취소/반품/교환": ["반품신청/철회", "반품정보확인/변경", "교환 AS신청/철회", "교환정보확인/변경", "취소신청/철회", "취소확인/환불정보"],
        "여행/숙박/항공": ["여행/숙박", "항공"],
        "안전거래": ["서비스 이용규칙 위반", "지식재산권침해", "법령 및 정책위반 상품", "게시물 정책위반", "직거래/외부거래유도", "표시광고", "청소년 위해상품/이미지"]
    };
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
</script>
<%@ include file="_footer.jsp"%>

