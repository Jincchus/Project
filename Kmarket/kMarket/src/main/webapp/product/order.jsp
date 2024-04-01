<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="_header.jsp" %>
<%@ include file="_aside.jsp" %>

<!-- 
	날짜 : 2023/09/24
	이름 : 박경진
	내용 : order 페이지
 -->
<!-- 주문 페이지 시작-->
<section class="order">
    <!-- 제목, 페이지 네비게이션 -->
<nav>
    <h1>주문결제</h1>
    <p>
      HOME > 장바구니 > <strong>주문결제</strong>
    </p>
</nav>
<form action="#">
    <!-- 주문 상품 목록 -->
 <table>
     <thead>
         <tr>
             <th>상품명</th>
             <th>총수량</th>
             <th>판매가</th>
             <th>배송비</th>
             <th>소계</th>
         </tr>
     </thead>
     <tbody>
     	 <c:if test="${empty carts }">
         <tr class="empty">
             <td colspan="7">장바구니에 상품이 없습니다.</td>
         </tr>
         </c:if>
	     <c:forEach var="cart" items="${carts }">

         <tr>
             <td>
				<input type="hidden" class="uid" value="aaa1">
				<input type="hidden" name="prodNo" value="${cart.prodNo }">
				<input type="hidden" name="prodName" value="${cart.prodName }">
				<input type="hidden" name="thumb2" value="${cart.thumb2 }">
				<input type="hidden" name="productValue" data-descript="${cart.descript }" 
				data-count="${cart.count }" data-price="${cart.price }" 
				data-discount="${cart.discount }" data-delivery="${cart.delivery }"
				data-total="${cart.total }">
                 <article>
                     <a href="${ctxPath }/product/view.do?prodNo=${cart.prodNo}&cate1=${cate1}&cate2=${cate2}">
                     	<img src="${cart.thumb2}" alt="${cart.thumb2}">
                     </a>
                     <div>
                         <h2><a href="${ctxPath }/product/view.do?prodNo=${cart.prodNo}&cate1=${cate1}&cate2=${cate2}">${cart.prodName}</a></h2>
                         <p>${cart.descript}</p>
                     </div>
                 </article>
             </td>
             <td class="count">${cart.count}</td> <!-- count -->
             <td class="price">${cart.price }</td> <!-- price -->
             <td class="delivery">${cart.delivery }</td> <!-- delivery -->
             <td class="total">${cart.total}</td> <!-- total -->

         </tr>

     </tbody>
     </c:forEach>
 </table>
 <!-- 최종 결제 정보 -->
 <div class="final">
     <h2>최종결제 정보</h2>
     <table border="0">
         <tbody>
             <tr>
                 <td>총</td>
                 <td id="productCountAll"></td>
             </tr>
             <tr>
                 <td>상품금액</td>
                 <td id="productPriceAll"></td>
             </tr>
             <tr>
                 <td>할인금액</td>
                 <td id="productDiscountAll"></td>
             </tr>
             <tr>
                 <td>배송비</td>
                 <td id="productDeliveryAll"></td>
             </tr>
             <tr>
                 <td>포인트 할인</td>
                 <td id="pointDiscount"></td>
             </tr>
             <tr>
                 <td>전체주문금액</td>
                 <td id="orderPriceAll"></td>
             </tr>
         </tbody>
     </table>
     <input type="button" name value="결제하기">
 </div>
 <!-- 배송정보 -->
 <article class="delivery">
     <h1>배송정보</h1>
     <table>
         <tbody>
             <tr>
                 <td>주문자</td>
                 <td>
                     <input type="text" name="orderer">
                 </td>
             </tr>
             <tr>
                 <td>휴대폰</td>
                 <td>
                     <input type="text" name="hp"><span>- 포함 입력</span>
                 </td>
             </tr>
             <tr>
                 <td>우편번호</td>
                 <td>
                     <input type="text" name="zip">
                     <input type="button" value="검색">
                 </td>
             </tr>
             <tr>
                 <td>기본주소</td>
                 <td>
                     <input type="text" name="addr1">
                 </td>
             </tr>
             <tr>
                 <td>상세주소</td>
                 <td>
                     <input type="text" name="addr2">
                 </td>
             </tr>
         </tbody>
     </table>
 </article>
 <!-- 할인정보 -->
 <article class="discount">
     <h1>할인정보</h1>
     <div>
         <p>현재 포인트 : <span>7200</span>점</p>
         <label>
             <input type="text" name="point"/>점
             <input type="button" value="적용"/>
         </label>
         <span>포인트 5,000점 이상이면 현금처럼 사용 가능합니다.</span>
     </div>
 </article>
 <!-- 결제방법 -->
 <article class="payment">
     <h1>결제방법</h1>
     <div>
         <span>신용카드</span>
         <p>
             <label>
                 <input type="radio" name="payment" value="type1">신용카드 결제
             </label>
             <label>
                 <input type="radio" name="payment" value="type2">체크카드 결제
             </label>
         </p>
     </div>
     <div>
         <span>계좌이체</span>
         <p>
             <label>
                 <input type="radio" name="payment" value="type3">실시간 계좌이체
             </label>
             <label>
                 <input type="radio" name="payment" value="type4">무통장 입금
             </label>
         </p>
     </div>
     <div>
         <span>기타</span>
         <p>
             <label>
                 <input type="radio" name="payment" value="type5">휴대폰결제
             </label>
             <label>
                 <input type="radio" name="payment" value="type6">카카오페이
                 <img src="${ctxPath }/product/img/ico_kakaopay.gif" alt="카카오페이">
             </label>
         </p>
     </div>
 </article>
 <!-- 경고 -->
        <article class="alert">
            <ul>
                <li><span>케이마켓의 모든 판매자는 안전거래를 위해 구매금액, 결제수단에 상관없이 모든거래에 대하여 케이마켓 유한책임회사의 구매안전서비스(에스크로)를 제공하고 있습니다.</span></li>
                <li><span>케이마켓 유한책임회사의 전자금융거래법에 의해 결제대금예치업 등록번호는 02-006-00008 입니다.</span></li>
                <li><span>등록여부는 금융감독원 홈페이지(www.fss.or.kr)의 업무자료>인허가업무안내>전자금융업등록현황에서 확인하실수 있습니다.</span></li>
            </ul>
        </article>
    </form>
</section>
<!-- 주문 페이지 끝-->
</main>

<script>
	$(document).ready(function(){
		
       	// 상품 정보 초기화
       	var count = 0;
		var price = 0;
		var discount = 0;
		var delivery = 0;
		var total = 0;
		
		// 총 상품 정보 초기화
    	var productCountAll = 0; 
       	var productPriceAll = 0;
       	var productDiscountAll = 0; 
       	var productDeliveryAll = 0;
       	var orderPriceAll = 0;
       	
		
		// 상품별 value
        $("input[name='productValue']").each(function () {
			count = parseFloat($(this).data("count"));
			price = (parseFloat($(this).data("price")))*count;
			discount = (price * (parseFloat($(this).data("discount"))))/100; 
			delivery = (parseFloat($(this).data("delivery"))) * count;
			total = price - discount + delivery;
			

			$('.price').text(price-discount);
			console.log('count : '+count);
			console.log('price : '+price);
			console.log('discount : '+discount);
			console.log('delivery : '+delivery);
			console.log('total : '+total);
		// 총 상품 정보 갱신
    	productCountAll += count; 
       	productPriceAll += price;
       	productDiscountAll += discount; 
       	productDeliveryAll += delivery*count;
       	console.log('productCountAll : ' + productCountAll)
       	console.log('productPriceAll : ' + productPriceAll)
       	console.log('productDiscountAll : ' + productDiscountAll)
       	console.log('productDeliveryAll : ' + productDeliveryAll) //
       	orderPriceAll += (productPriceAll - productDiscountAll + productDeliveryAll);
       	// 이 부분이 돌면서 중첩됨. 이유를 모르겠음
		});
		
    		
    	$('#productCountAll').text(productCountAll);
    	$('#productPriceAll').text(productPriceAll);
    	$('#productDiscountAll').text(productDiscountAll);
    	$('#productDeliveryAll').text(productDeliveryAll);
    	$('#orderPriceAll').text(orderPriceAll);

	});
</script>

<%@ include file="_footer.jsp" %>
