<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="_header.jsp"%>
<%@ include file="_aside.jsp"%>

<!-- 
	이름 : 박경진
	내용 : Product cart(상품 장바구니) 구현 
	날짜 : 2023/09/21
 -->
<!-- 장바구니 페이지 시작-->
<section class="cart">
	<!-- 제목, 페이지 네비게이션 -->
	<nav>
		<h1>장바구니</h1>
		<p>
			HOME > <span>패션·의류·뷰티</span> > <strong>장바구니</strong>
		</p>
	</nav>
	<form id="cartForm" action="${ctxPath }/product/order.do" method="post">
		<!-- 장바구니 목록 -->
		<table>
			<thead>
				<tr>
					<th><input type="checkbox" name="all" id="selectAll">
					</th>
					<th>상품명</th>
					<th>총수량</th>
					<th>판매가</th>
					<th>할인</th>
					<th>포인트</th>
					<th>배송비</th>
					<th>소계</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${list eq null }">
					<tr class="empty">
						<td colspan="7">장바구니에 상품이 없습니다.</td>
						<input>
					</tr>
				</c:if>
				<c:forEach var="cart" items="${carts}">
					<tr>
						<td>
						
							<input type="hidden" class="uid" value="aaa1">
							<input type="hidden" name="prodNo" value="${cart.prodNo }">
							<input type="hidden" name="prodName" value="${cart.prodName }">
							<input type="hidden" name="descript" value="${cart.descript }">
							<input type="hidden" name="thumb2" value="${cart.thumb2 }">
							
							<input type="checkbox" name="chkbox" class="chkbox"
							value="${cart.cartNo }" data-count="${cart.count }"
							data-price="${cart.price}" data-discount="${cart.discount}"
							data-delivery="${cart.delivery}" data-point="${cart.point}"
							data-total="${cart.total }">
						</td>
						<td>
							<article>
								<a href="#">
									<img src="${cart.thumb2 }" alt="${cart.thumb2 }">
								</a>

								<div>
									<h2>
										<a href="#">${cart.prodName }</a>
									</h2>
									<p>${cart.descript }</p>
								</div>
							</article>
						</td>
						<td>${cart.count }</td>
						<td>${cart.price }</td>
						<td>${cart.discount }%</td>
						<td>${cart.point }</td>
						<td>${cart.delivery }</td>
						<td>${cart.total }</td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
		<input type="button" name="del" value="선택삭제">
		<!-- 장바구니 전체 합계 -->
		<div class="total">
			<h2>전체합계</h2>
			<table border="0">
				<tbody>
					<tr>
						<td>상품수</td>
						<td id="productCount">0</td>
					</tr>
					<tr>
						<td>상품금액</td>
						<td id="productPrice">0</td>
					</tr>
					<tr>
						<td>할인금액</td>
						<td id="productDiscount">0</td>
					</tr>
					<tr>
						<td>배송비</td>
						<td id="productDelivery">0</td>
					</tr>
					<tr>
						<td>포인트</td>
						<td id="productPoint">0</td>
					</tr>
					<tr>
						<td>전체주문금액</td>
						<td id="productTotal"></td>
					</tr>
				</tbody>
			</table>

			<input type="submit" value="주문하기">
		</div>

	</form>
</section>
<!-- 장바구니 페이지 끝-->
</main>

<script>

	$(document).ready(function() {
		// 초기 로드 시 업데이트 함수 호출
		updateTotal();

		/* 체크 박스 */
		// selectAll 체크박스 요소 가져오기
		let $selectAllCheckbox = $("#selectAll");

		// 모든 개별 체크박스 요소 가져오기
		let $checkboxes = $(".chkbox");

		// selectAll 체크박스가 클릭될 때 실행할 함수 정의
		$selectAllCheckbox.click(function() {
			// selectAll 체크박스의 상태 가져오기
			let isChecked = $selectAllCheckbox.prop("checked");

			// 모든 개별 체크박스에 상태 적용
			$checkboxes.prop("checked", isChecked);

			// 업데이트 함수 호출
			updateTotal();
		});

		// 개별 체크박스가 클릭될 때 실행할 함수 정의
		$checkboxes.click(function() {
			// 개별 체크박스가 모두 선택되어 있는지 확인
			let allChecked = true;
			$checkboxes.each(function() {
				if (!$(this).prop("checked")) {
					allChecked = false;
				}
			});

			// selectAll 체크박스의 상태 업데이트
			$selectAllCheckbox.prop("checked", allChecked);

			// 업데이트 함수 호출
			updateTotal();
		});

		// 선택된 체크박스를 감지하고 합계 계산 및 표시를 업데이트하는 함수
		function updateTotal() {
			var productCount = 0;
			var productPrice = 0;
			var productDiscount = 0;
			var productDelivery = 0;
			var productPoint = 0;
			var productTotal = 0;

			$(".chkbox:checked").each(function() {
				
				// 추가
				var prodNo = parseFloat($(this).data("prodNo"));
				var prodName = $(this).data("prodName");
				var thumb2 = $(this).data("thumb2");
				
				var count = parseFloat($(this).data("count"));
				var price = parseFloat($(this).data("price"));
				var discount = parseFloat($(this).data("discount"));
				var delivery = parseFloat($(this).data("delivery"));
				var point = parseFloat($(this).data("point"));
				var total = parseFloat($(this).data("total"));

				productCount += count;
				productPrice += (price*count);
				productDiscount += ((price * discount) / 100)*count;
				productDelivery += delivery;
				productPoint += point;
				productTotal += total;
			});

			$("#productCount").text(productCount);
			$("#productPrice").text(productPrice);
			$("#productDiscount").text(productDiscount);
			$("#productDelivery").text(productDelivery);
			$("#productPoint").text(productPoint);

			var totalAmount = productTotal + productDelivery;
			$("#productTotal").text(totalAmount);
		}


	    // 주문하기 버튼이 클릭될 때 form 데이터 전송
	    $("#cartForm").submit(function(event) {

	    	  // 사용자에게 확인 메시지 표시
	        var confirmation = confirm("정말로 주문하시겠습니까?");
	        
	        // 확인 버튼을 누르지 않으면 폼 전송을 중단
	        if (!confirmation) {
	            event.preventDefault();
	            return;
	        }

	        // 선택된 상품 정보를 배열로 저장
	        var cartToOrder = [];
	        $(".chkbox:checked").each(function() {
	            var product = {
	                count: $(this).data("count"),
	                price: $(this).data("price"),
	                discount: $(this).data("discount"),
	                delivery: $(this).data("delivery"),
	                point: $(this).data("point"),
	                total: $(this).data("total"),
					prodName : $(this).siblings('[name="prodName"]').val(),
	                prodNo: $(this).siblings('[name="prodNo"]').val(), // prodNo 추가
	                descript: $(this).siblings('[name="descript"]').val(), // descript 추가
	                thumb2: $(this).siblings('[name="thumb2"]').val() // thumb2 추가
	            };
	            cartToOrder.push(product);
	        });
	        
	        // 선택된 상품 정보를 JSON 문자열로 변환하여 form에 추가
	        $("#cartForm").append('<input type="hidden" name="cartToOrder" value=\'' + JSON.stringify(cartToOrder) + '\' />');
	    }); 
/*
	        // 선택된 상품 정보를 JSON 문자열로 변환
	        var jsonData = JSON.stringify(selectedProducts);

	        // Ajax 요청 보내기
	        $.ajax({
	            type: "POST", // 또는 "GET", "PUT" 등 HTTP 메서드 선택
	            url: "/kMarket/product/order.do", // 서버 엔드포인트 URL을 여기에 입력
	            data: { selectedProducts: jsonData }, // 데이터 전송
	            dataType: "json", // 서버 응답 데이터 형식 (JSON 예상)
	            success: function(response) {
	                // Ajax 요청이 성공한 경우 처리할 코드
	                console.log("서버 응답:", response);
	                // 필요한 작업을 수행하세요.
	            },
	            error: function(xhr, status, error) {
	                // Ajax 요청이 실패한 경우 처리할 코드
	                console.error("Ajax 요청 실패:", error);
	                // 오류 처리를 수행하세요.
	            }
	        });

	        // 폼 제출 방지
	        event.preventDefault();
	    }
	    */
	});
</script>

<%@ include file="_footer.jsp"%>