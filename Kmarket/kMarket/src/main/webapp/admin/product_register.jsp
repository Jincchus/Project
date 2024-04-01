<%--
  Created by IntelliJ IDEA.
  User: leedonghan
  Date: 2023/09/17
  Time: 3:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%--TODO: file 등록시 미리보기 구현하기--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="_header.jsp"%>
<main>
    <%@ include file="_aside.jsp"%>
    <section class="write">
        <nav>
            <h3>상품등록</h3>
            <p>
                HOME > 상품관리 > <strong>상품등록</strong>
            </p>
        </nav>
        <article style="overflow-y: scroll; height: 60%;">
            <form action="${ctxPath}/admin/product/register.do" method="post" enctype="multipart/form-data">
                <h4>기본정보</h4>
                <table>
                    <tr>
                        <td>상품분류</td>
                        <td>
                            <select name="prodCate1" id="cate1">
                                <option selected>브랜드패션</option>
                                <option>패션의류·잡화·뷰티</option>
                                <option>유아동</option>
                                <option>식품·생필품</option>
                                <option>홈데코·문구·취미·반려</option>
                                <option>컴퓨터·디지털·가전</option>
                                <option>스포츠·건강·렌탈</option>
                                <option>자동차·공구</option>
                                <option>여행·도서·티켓·e쿠폰</option>
                            </select>
                            <select name="prodCate2" id="cate2">
                                <option selected>브랜드 여성의류</option>
                                <option>브랜드 남성의류</option>
                                <option>브랜드 진/캐쥬얼</option>
                                <option>브랜드 신발/가방</option>
                                <option>브랜드 쥬얼리/시계</option>
                                <option>브랜드 아웃도어</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>상품명</td>
                        <td><input type="text" name="prodName" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>기본설명</td>
                        <td><input type="text" name="descript" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>제조사</td>
                        <td><input type="text" name="company" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>판매자</td>
                        <td><input type="text" name="seller" value="s1234"required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>상품금액</td>
                        <td><input type="text" name="price" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>할인율</td>
                        <td><input type="text" name="discount" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>포인트</td>
                        <td><input type="text" name="point" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>재고수량</td>
                        <td><input type="text" name="stock" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>배송비</td>
                        <td><input type="text" name="delivery" required placeholder="제목을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <td>상품이미지</td>
                        <td>
                            <div style="display: flex; flex-direction: column; flex-wrap: wrap; gap: 10px 0px">
                                <div style="display: flex"><input style="width: 30%" type="file" name="thumb1"/> <div style="display: flex; align-items: center; border-left: 2px solid #0d3a46; padding-left: 4px">상품 목록 섬네일(190px X 190px)</div></div>
                                <div style="display: flex"><input style="width: 30%" type="file" name="thumb2"/> <div style="display: flex; align-items: center; border-left: 2px solid #0d3a46; padding-left: 4px">상품 메인 이미지(230px X 230px)</div></div>
                                <div style="display: flex"><input style="width: 30%" type="file" name="thumb3"/> <div style="display: flex; align-items: center; border-left: 2px solid #0d3a46; padding-left: 4px">상품 상세 이미지(456px X 456px)</div></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>상품 상세정보</td>
                        <td>
                            <div style="display: flex"><input style="width: 30%" type="file" name="detail"/><div style="display: flex; align-items: center; border-left: 2px solid #0d3a46; padding-left: 4px">상품 상세정보 (가로 940px, 높이제한 없음, 최대 1MB)</div> </div>
                        </td>
                    </tr>
                </table>
                <h4 style="margin-top: 10px">상품정보 제공고시</h4>
                <table>
                    <tr>
                        <td>상품상태</td>
                        <td><input type="text" value="새상품" name="status"/></td>
                    </tr>
                    <tr>
                        <td>부가세 면세여부</td>
                        <td><input type="text" value="과세상품" name="duty"/></td>
                    </tr>
                    <tr>
                        <td>영수증 발행</td>
                        <td><input type="text" value="발행가능-신용카드전표,온라인 영수증" name="receipt" /></td>
                    </tr>
                    <tr>
                        <td>사업자 구분</td>
                        <td><input type="text" value="사업자,판매자" name="bizType"/></td>
                    </tr>
                    <tr>
                        <td>원산지</td>
                        <td><input type="text" value="국내산" name="origin"/></td>
                    </tr>
                </table>
                <div class="btnGroup">
                    <a href="/admin/product/list.do" >취소</a>
                    <input type="submit" value="작성완료">
                </div>
            </form>
        </article>
    </section>
</main>
<script>
    let cate1 = document.getElementById("cate1");
    let cate2 = document.getElementById("cate2");

    let data = {
    "브랜드패션":["브랜드 여성의류","브랜드 남성의류","브랜드 진/캐쥬얼","브랜드 신발/가방","브랜드 쥬얼리/시계","브랜드 아웃도어"],
    "패션의류·잡화·뷰티":["여성의류", "남성의류", "언더웨어", "신발", "가방/잡화", "쥬얼리/시계", "화장품/향수", "바디/헤어"],
    "유아동":["출산/육아", "장난감/완구", "유아동 의류", "유아동 신발/잡화"],
    "식품·생필품":["신선식품", "가공식품", "건강식품", "커피/음료", "생필품", "바디/헤어"],
    "홈데코·문구·취미·반려":["가구/DIY", "침구/커튼", "조명/인테리어", "생활용품", "주방용품", "문구/사무용품", "사무기기", "악기/취미", "반려동물용품"],
    "컴퓨터·디지털·가전":["노트북/PC", "모니터/프린터", "PC주변기기", "모바일/태블릿", "카메라", "게임", "영상가전", "주방가전", "계절가전", "생활/미용가전", "음향가전", "건강가전"],
    "스포츠·건강·렌탈":["스포츠의류/운동화", "휘트니스/수영", "구기/라켓", "골프", "자전거/보드/기타레저", "캠핑/낚시", "등산/아웃도어", "건강/의료용품", "건강식품", "렌탈서비스"],
    "자동차·공구":["자동차용품", "공구/안전/산업용품"],
    "여행·도서·티켓·e쿠폰":["여행/항공권", "도서/음반/e교육", "공연티켓", "e쿠폰", "상품권"]
    }
    // 메뉴1 값 바뀌면 해당값의 data키 리스트를 순회하며 값을 만든당.
    cate1.addEventListener("change",(e)=>{
        while(cate2.firstChild){
            cate2.firstChild.remove();
        }
        let newOptionsString = "";
        let cate2List = data[e.target.value];
        for (let i = 0; i < cate2List.length; i++) {
            let cate2Name = cate2List[i];
            newOptionsString += "<option value=" + (i+10) + ">" + cate2Name + "</option>";
        }
        cate2.innerHTML = newOptionsString;
    })
</script>
<%@ include file="_footer.jsp"%>
