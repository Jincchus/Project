<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>
<jsp:include page="./_aside${cate}.jsp" />

                        <form action="#" id="sendForm" method ="Post">
                            <input type="hidden" name="cate" value="${cate}">
                            <input type="hidden" name="writer" value="${sessUser.uid}">
                            <table>
                               <tbody>
                               	<tr>
                                    <td>문의유형</td>
                                    <td>
                                    	 <select id="menu1" name="menu1" >
                                            <option selected value="0" >1차 선택</option>
                                            <option value="회원">회원</option>
                                            <option value="쿠폰/혜택/이벤트">쿠폰/이벤트</option>
                                            <option value="주문/결제">주문/결제</option>
                                            <option value="배송">배송</option>
                                            <option value="취소/반품/교환">취소/반품/교환</option>
                                            <option value="여행/숙박/항공">여행/숙박/항공</option>
                                            <option value="안전거래">안전거래</option>
                                        </select>
                                        <select id="menu2"  name="menu2" >
                                            <option selected value="0">2차 선택</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>문의제목</td>
                                    <td><input type="text" name="title" placeholder="제목을 입력하시오."></td>
                                </tr>
                                <tr>
                                    <td>문의내용</td>
                                    <td>
                                        <textarea name="content" placeholder="내용을 입력하시오."></textarea>
                                    </td>
                                </tr>
                               </tbody>
                            </table>
                            <div>
                                <a href="${ctxPath}/cs/index.do" class="btnList">취소하기</a>
                                <input type="button" class="btnSubmit" value="등록하기">
                            </div>
                        </form>
<script>
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
    // 메뉴1 값 바뀌면 해당값의 data키 리스트를 순회하며 값을 만든당.
    menu1.addEventListener("change",(e)=>{
        while(menu2.firstChild){
            menu2.firstChild.remove();
        }
        let newOptionsString = "<option value=0>2차선택</option>";	
        data[e.target.value].forEach(el => {
            newOptionsString +="<option value="+el+">"+el+"</option>";
        });
        menu2.innerHTML = newOptionsString;
    })
    //작성완료 버튼을 누를 때
    	$('.btnSubmit').click(function(e){
    		e.preventDefault();
			const menu1 = $('#menu1').val();
			const menu2 = $('#menu2').val();
			console.log("menu1 :" + menu1);
			console.log("menu2 :" + menu2);
			
			var confirm= true;
			if(menu1==0){
				alert('1차 카테고리를 선택하시오.');
				confirm = false;
			}else if(menu2==0){
				alert('2차 카테고리를 선택하시오.');
				confirm = false;
			}
			if(confirm==true){
				$('#sendForm').attr("action","${ctxPath}/cs/board/write.do");
				$('#sendForm').submit();
			}
		});
		
		
		

</script>                        
                    </article>
                </section>
            </div>
        </section>
<%@include file ="../_footer.jsp" %>