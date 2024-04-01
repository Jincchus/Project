<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="_header.jsp" %>
<%@ include file="_aside.jsp" %>

	<!-- 
		이름 : 박경진
		
		날짜 : 2023/09/17
		내용 : view 기능구현
		
		날짜 : 2023/09/19
		내용 : view 리뷰
		
		날짜 : 2023/09/20
		내용 : js처리
	 -->

            <section class="view">
                <!-- 제목, 페이지 네비게이션 -->
                <nav>
                    <h1>상품보기</h1>
                    <p>
                        HOME > <span>${product.prodCate1}</span> > <strong>${cate2 }</strong>
                    </p>
                </nav>
                <!-- 상품 전체 정보 내용 -->
                <article class="info">
                    <div class="image">
                        <img src="${ctxPath }/product/thumb/${product.thumb2}" alt="${product.prodName }">
                    </div>
                    <div class="summary">
                        <nav>
                            <h1>${product.seller }</h1>
                            <h2>상품번호&nbsp;:&nbsp;<span>${product.prodNo }</span></h2>
                        </nav>
                        <nav>
                            <h3>${product.prodName }</h3>
                            <p>${product.descript }</p>
                            <!-- star 갯수 확인 -->
                            <h5 class="rating star4">
                            		<!-- 상품리뷰 db join 해야 할 듯 확인하기  -->
                                <a href="#">상품평보기</a>
                            </h5>
                        </nav>
                        <nav>
                        <!-- price 가격이 org_price인지 dis_price인지, 할인율이랑 합쳐서 계산 어떻게하는지 확인 -->
                            <div class="org_price">
	                       		<input type='hidden' id='or_price' value="${product.price }">
                                <del>${product.price }</del>
                                <span>${product.discount }%</span>
	                            <input type='hidden' id='disc' value="${product.discount }">
                            </div>
                            <div class="dis_price"><ins></ins></div>
                        </nav>
                        <nav>
                            <span class="delivery">무료배송</span>
                            <span class="arrival">모레(금) 7/8 도착예정</span>
                            <span class="desc">본 상품은 국내배송만 가능합니다.</span>
                        </nav>
                        <nav>
                            <span class="card cardfree"><i>아이콘</i>무이자할부</span>&nbsp;&nbsp;
                            <span class="card cardadd"><i>아이콘</i>카드추가혜택</span>
                        </nav>
                        <nav>
                            <span class="origin">원산지-상세설명 참조</span>
                        </nav>
                        <img src="${ctxPath }/product/img/vip_plcc_banner.png" alt="100원만 결제해도 1만원 적립!" class="banner">
                        <div class="count">
                            <button class="decrease">-</button>
                            <input type="number" name="num" min="1" value="1" readonly>
                            <button class="increase">+</button>
                        </div>
                        <div class="total">
                            <span>35,000</span>
                            <em>총 상품금액</em>
                        </div>
                        <form id="sendForm" action="#" method="post">
                        	<input type="text" name="uid" value="aaa1"><!-- value값에 ${sessUser.uid}넣어주기 -->
                        	<input type="text" name="prodNo" value="${product.prodNo}">
                        	<input type="text" name="prodName" value="${product.prodName}">
                        	<input type="text" name="descript" value="${product.descript}">
                        	<input type="text" name="price" value="${product.price}"> <!-- ori-price -->
		                	<input type="text" name="num" value="1">
		                	<input type="text" name="total" value=""> <!-- ori-price랑 discount 가격 계산한것 -->
		                	<input type="text" name="discount" value="${product.discount }">                
		                	<input type="text" name="point" value="${product.point }">                
		                	<input type="text" name="delivery" value="${product.delivery}">
		                	<input type="text" name="final" value=""> <!-- total가격 + delivery가격 --> 
		                	<input type="text" name="thumb2" value="${product.thumb2 }"> <!-- total가격 + delivery가격 --> 
                        </form>
                        <div class="button">
                            <input type="button" class="cart" value="장바구니">
                            <input type="button" class="order" value="구매하기">
                        </div>
                    </div>
                </article>
                <!-- 상품 정보 내용 -->
                <article class="detail">
                    <nav>
                        <h1>상품정보</h1>
                    </nav>
                    <img src="${ctxPath }/product/thumb/${product.detail}" alt="상세페이지1">
                </article>
                <!-- 상품 정보 제공 고시 내용 -->
                <article class="notice">
                    <nav>
                        <h1>상품 정보 제공 고시</h1>
                        <p>[전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록된 정보입니다.</p>
                    </nav>
                    <table border="0">
                        <tbody>
                            <tr>
                                <td>상품번호</td>
                                <td>${product.prodNo }</td>
                            </tr>
                            <tr>
                                <td>상품상태</td>
                                <td>${product.status }</td>
                            </tr>
                            <tr>
                                <td>부가세 면세여부</td>
                                <td>${product.duty }</td>
                            </tr>
                            <tr>
                                <td>영수증발행</td>
                                <td>${product.receipt }</td>
                            </tr>
                            <tr>
                                <td>사업자구분</td>
                                <td>${product.bizType }</td>
                            </tr>
                            <tr>
                                <td>브랜드</td>
                                <td>${product.company }</td>
                            </tr>
                            <tr>
                                <td>원산지</td>
                                <td>${product.origin }</td>
                            </tr>
                        </tbody>
                    </table>
                    <table border="0">
                        <tbody>
                            <tr>
                                <td>제품소재</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>색상</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>치수</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>제조자/수입국</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>제조국</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>취급시 주의사항</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>제조연월</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>품질보증기준</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>A/S 책임자와 전화번호</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td>주문후 예상 배송기간</td>
                                <td>상세정보 직접입력</td>
                            </tr>
                            <tr>
                                <td colspan="2">구매, 교환, 반품, 배송, 설치 등과 관련하여 추가비용, 제한조건 등의 특이사항이 있는 경우</td>
                            </tr>
                        </tbody>
                    </table>
                    <p class="notice">
                        소비자가 전자상거래등에서 소비자 보호에 관한 법률 제 17조 제1항 또는 제3항에 따라 청약철회를 하고
                        동법 제 18조 제1항 에 따라 청약철회한 물품을 판매자에게 반환하였음에도 불구 하고 결제 대금의
                        환급이 3영업일을 넘게 지연된 경우, 소비자 는 전자상거래등에서 소비자보호에 관한 법률 제18조
                        제2항 및 동법 시행령 제21조 2에 따라 지연일수에 대하여 전상법 시행령으로 정하는 이율을 곱하여
                        산정한 지연이자(“지연배상금”)를 신청할 수 있습니다. 아울러, 교환∙반품∙보증 및 결제대금의
                        환급신청은 [나의쇼핑정보]에서 하실 수 있으며, 자세한 문의는 개별 판매자에게 연락하여 주시기 바랍니다.
                    </p>
                </article>
                <!-- 상품 리뷰 내용 -->
                <article class="review">
                    <nav>
                        <h1>상품리뷰</h1>
                    </nav>
                   	<c:forEach var="review" items="${reviews}">
	                    <ul>
	                        <li>
	                            <div>
	                                <h5 class="rating star4">상품평</h5>
	                                <span>${review.uid } ${review.rdate }</span> <!-- uid 3자 이후 ****처리, 날짜 0000-00-00 처리 -->
	                            </div>
	                            <h3>${review.prodName}/BLUE/L</h3>
	                            <p>
	                                ${review.content}
	                            </p>
	                        </li>
	                    </ul>
		            </c:forEach>
	                     <div class="paging">
				        	<c:if test="${pageGroupStart > 1}">
			                    <span class="prev">
			                        <a href="${ctxPath}/product/view.do?cate1=${product.prodCate1}&cate2=${product.prodCate2 }&pg=${pageGroupStart - 1}&prodNo=${product.prodNo }"><&nbsp;이전</a>
			                    </span>
				            </c:if>
				            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
			                    <span class="num ">
			                        <a href="${ctxPath}/product/view.do?cate1=${product.prodCate1}&cate2=${product.prodCate2 }&pg=${i}&prodNo=${product.prodNo }" class="${currentPage == i?'on':'off'}">${i}</a>
			                    </span>
				            <c:if test="${pageGroupEnd < lastPageNum}">
			                    <span class="next">
			                        <a href="${ctxPath}/product/view.do?cate1=${product.prodCate1}&cate2=${product.prodCate2 }&pg=${pageGroupEnd + 1}&prodNo=${product.prodNo }">다음&nbsp;></a>
			                    </span>
				            </c:if>
	                    </div>
                    </c:forEach>
                </article>
            </section>
        </main>
    <script>
	/* discount price 계산 */
    // price, discount value 받아오기
    $(function(){
	    const price = ${product.price};
	    const discount = ${product.discount};
	    const delivery = ${product.delivery};
	    let num = parseInt($('input[name=num]').val()); //min 값, value 값 = 1
	    
	    function updateTotal() {
	        const disPrice = price - ((price * discount) / 100);
	        $('.dis_price ins').text(disPrice); // <ins> 태그 내용 업데이트
	        
	        const total = num * disPrice;
			// input[name=total]의 값을 업데이트
	        const inputTotal = $('input[name=total]').val(total);
	        $('input[name=total]').innerText = total.toLocaleString();
	        // <span> 태그 내용 업데이트
	        $('.total span').text(total); // <span> 태그 내용 업데이트
	        
			const finalValue = delivery + total;
		    // final input 요소의 value 값을 설정
		    $('input[name=final]').val(finalValue);
	        
	    }

	    updateTotal(); // 초기 값으로 한 번 호출

	    
	     $('.decrease').on('click', function(){
	    	 if(num > 1){
	    		 num--;
	             $('input[name=num]').val(num); // <input name="num"> 값을 업데이트
	             updateTotal(); // num이 변경될 때마다 total 업데이트
	    	 }
	     });
	     $('.increase').on('click', function(){
	    	 if(num >= 1){
	    		 num++;
	             $('input[name=num]').val(num); // <input name="num"> 값을 업데이트
	             updateTotal(); // num이 변경될 때마다 total 업데이트
	    	 }
	     });
	     
        $('.order').on('click', function (e) {
            e.preventDefault()
            $('#sendForm').attr("action","${ctxPath}/product/order.do");
            $('#sendForm').submit();
        })


        $('.cart').on('click', function (e) {
        	
        	
	    	//if(${sessUser == null}){
	    	//	alert('로그인이 필요합니다.');
	    	//	return false;
	    	//}
	    	
            e.preventDefault();
            $('#sendForm').attr("action","${ctxPath}/product/view.do");
            $('#sendForm').submit();
        })
	     
	 });
     
     


	</script>

<%@ include file="_footer.jsp" %>
