<%--
  Created by IntelliJ IDEA.
  User: leedonghan
  Date: 2023/09/17
  Time: 12:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<script>
$(function(){
	
	$('#답변완료').css('color','blue');
	$('#검토중').css('color','grey');
	
	let menu1 = document.getElementById("menu1");
    let menu2 = document.getElementById("menu2");
	
    let data = {
    	"0":[],
        "회원":["가입","탈퇴","회원정보","로그인"],
        "쿠폰/혜택/이벤트":["쿠폰/할인혜택","포인트","제휴","이벤트"],
        "주문/결제":["상품","결제","구매내역","영수증/증빙"],
        "배송":["배송상태/기간", "배송정보확인/변경", "해외배송", "당일배송", "해외직구"],
        "취소/반품/교환":["반품신청/철회", "반품정보확인/변경", "교환 AS신청/철회", "교환정보확인/변경", "취소신청/철회", "취소확인/환불정보"],
        "여행/숙박/항공":["여행/숙박", "항공"],
        "안전거래":["서비스 이용규칙 위반", "지식재산권침해", "법령 및 정책위반 상품", "게시물 정책위반", "직거래/외부거래유도", "표시광고", "청소년 위해상품/이미지"]
    }
    
        
        let selectMenu1 = $('#menu1').val();
    	let selectMenu2 = $('#menu2').val();
    	let selectedMenu2='<c:out value="${menu2}" />';
    	//selectedMenu2는 listcontroller에서 넘어오는 menu2
        console.log("selectedmenu2 :"+ selectedMenu2);
        
   
    // 메뉴1 값 바뀌면 해당값의 data키 리스트를 순회하며 값을 만든당.
    menu1.addEventListener("change",()=>{
    	selectMenu1 = $('#menu1').val();
        location.replace('${ctxPath}/admin/cs/qna/list.do?menu1='+selectMenu1+'&menu2=0');
    });
        while(menu2.firstChild){
            menu2.firstChild.remove();
        }
        
        let newOptionsString = "<option value=0 selected>2차선택</option>";	
        	console.log("selectMenu1 : "+selectMenu1);
        	data[selectMenu1].forEach(el => {
        		//newOptionsString +="<option value="+el+">"+el+"</option>";
        	newOptionsString += "<option value='" + el + "' " + (selectedMenu2 === el ? 'selected' : '') + ">" + el + "</option>";
        	        
        });//while end
        menu2.innerHTML = newOptionsString;
    //메뉴2값에 따라 list가 변한다.
    menu2.addEventListener("change",()=>{
    	selectMenu1 = $('#menu1').val();
    	selectMenu2 = $('#menu2').val();
    	console.log("menu2 change selectMenu1 : "+selectMenu1);
    	console.log("menu2 change selectMenu2 : "+selectMenu2);
    	// 페이지 이동 시 선택한 옵션을 selected로 설정
        $('#menu2 option:selected').removeAttr('selected');
        $('#menu2 option[value="' + selectMenu2 + '"]').attr('selected', 'selected');
       	 location.replace('${ctxPath}/admin/cs/qna/list.do?menu1='+selectMenu1+'&menu2='+selectMenu2);
    });
    
   
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
    
    //게시글 삭제 기능 구현
    let selectDeleteBtn = document.getElementById("delete_selected_btn");
    selectDeleteBtn.addEventListener("click",async (e)=>{
        //step1 : 현재 url 기준으로 끝 부분만 list.do => delete.do로 바꾸기
        let curHref = location.href;
        let lastSlashIdx = curHref.lastIndexOf("/");
        let root = curHref.substring(0, lastSlashIdx);
        let newHref = root + "/delete.do";
        console.log("newHref : "+newHref);
      	//step2 : articleIds 로 체크된거 담기
        let articleIds = [];
        for (const child of checkBoxChildren) {
            if (child.checked) {
                articleIds.push(child.value);
            }
        }
      //step3 : ajax 요청 보내기
        let data = {"articleIds" : articleIds}
        let cNo = $("#cs_checkbox").next().val();
        console.log("articleIds : "+articleIds);
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
                console.log("delete success");
            }
        });
    })
    //선택 삭제 요청 끝
    	
});//end


</script>
<main>
    <%@ include file="_aside.jsp"%>
    <section id="admin-cs-faq-list" class="admin-cs-list">
        <nav>
            <h3>문의하기 목록</h3>
            <p>
                HOME > 고객센터 > <strong>문의하기</strong>
            </p>
        </nav>
        <section>
            <div>
                <select id="menu1" name="menu1" >
                                            <option value="0" ${menu1 eq '0'?'selected':''} >전체</option>
                                            <option value="회원"${menu1 eq '회원'?'selected':''} >회원</option>
                                            <option value="쿠폰/혜택/이벤트" ${menu1 eq '쿠폰/혜택/이벤트'?'selected':''} >쿠폰/이벤트</option>
                                            <option value="주문/결제" ${menu1 eq '주문/결제'?'selected':''}>주문/결제</option>
                                            <option value="배송" ${menu1 eq '배송'?'selected':''} >배송</option>
                                            <option value="취소/반품/교환" ${menu1 eq '취소/반품/교환'?'selected':''} >취소/반품/교환</option>
                                            <option value="여행/숙박/항공" ${menu1 eq '여행/숙박/항공'?'selected':''} >여행/숙박/항공</option>
                                            <option value="안전거래" ${menu1 eq '안전거래'?'selected':''} >안전거래</option>
                                        </select>
                                        <select id="menu2"  name="menu2" >
                                            <option selected value="0">2차 선택</option>
                                        </select>
            </div>
            <table>
                <tr>
                    <th><input type="checkbox" name="all"/></th>
                    <th>번호</th>
                    <th>1차유형</th>
                    <th>2차유형</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>상태</th>
                </tr>
                <c:forEach var="list" items="${lists}">
                <tr>
                    <td><input type="checkbox" name="상품코드"/></td>
                    <td>${list.cNo }</td>
                    <td>${list.menu1 }</td>
                    <td>${list.menu2 }</td>
                    <td>
                    <a href="${ctxPath}/admin/cs/qna/answer.do?cNo=${list.cNo}">${list.title }</a>
                    </td>
                    <td>${list.getMaskingWriter() }</td>
                    <td>${list.rdate }</td>
                    <c:choose>
			                			<c:when test="${list.comment eq 1}">
			                				<td id="답변완료">답변완료</td>
			                			</c:when>
			                			<c:otherwise>
			                					<td id="검토중">검토중</td>
			                			</c:otherwise>
			                		</c:choose>
                </tr>
                </c:forEach>
                
            </table>
            <div class="notice_list_button">
                <input type="button" value="선택삭제" />
            </div>
            <div class="paging">
             <c:if test="${pageGroupStart>1 }">
           		<span class="prev">
				<a href="${ctxPath }/admin/cs/qna/list.do?pg=${pageGroupStart-1}&cate=${cate }&menu1=${menu1}&menu2=${menu2}">이전</a>
				 </span>
			</c:if>
			<c:forEach var="i" begin="${pageGroupStart}" end ="${pageGroupEnd }">
				<span class="num"> 
					<a href="${ctxPath }/admin/cs/qna/list.do?pg=${i}&cate=${cate }&menu1=${menu1}&menu2=${menu2}" class="${currentPage==i?'on':''}">${i}</a>
				 </span>
			</c:forEach>
			<c:if test ="${pageGroupEnd < lastPageNum }">     
				 <span class="next">
					<a href="${ctxPath }/admin/cs/qna/list.do?pg=${pageGroupEnd+1}&cate=${cate}&menu1=${menu1}&menu2=${menu2}">다음</a>
				</span>
			</c:if>
            </div>
        </section>
    </section>
    </section>
</main>
<%@ include file="_footer.jsp"%>
