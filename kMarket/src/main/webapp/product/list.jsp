<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="_header.jsp" %>
<%@ include file="_aside.jsp" %>

            <section class="list">
                <!-- 제목, 페이지 네비게이션 -->
                <nav>
                    <h1>상품목록</h1>
                    	<c:forEach var="product" items="${products }">
                    <p>
                        HOME > <span>${product.prodCate1 }</span> > <strong>남성의류</strong>
                    </p>
                    </c:forEach>
                </nav>
                <!-- 정렬 메뉴 -->
                <ul class="sort">
                    <li>
                        <a href="#" class="on">판매많은순</a>
                    </li>
                    <li>
                        <a href="#">낮은가격순</a>
                    </li>
                    <li>
                        <a href="#">높은가격순</a>
                    </li>
                    <li>
                        <a href="#">평점높은순</a>
                    </li>
                    <li>
                        <a href="#">후기많은순</a>
                    </li>
                    <li>
                        <a href="#">최근등록순</a>
                    </li>
                </ul>
                <!-- 상품목록 -->
                <table border="0">
                    <tbody>
                    	<c:forEach var="product" items="${products }">
	                        <tr>
	                            <td>
	                                <a href="${ctxPath }/product/view.do?prodNo=${product.prodNo }&cate1=${cate1}&cate2=${cate2}" class="thumb">
	                                    <img src="${ctxPath }/thumb/${product.thumb1}" alt="${product.prodName }">
	                                </a>
	                            </td>
	                            <td>
	                                <h3 class="name">${product.prodName}</h3>
	                                <a href="${ctxPath }/product/view.do?prodNo=${product.prodNo }" class="desc">${product.descript }</a>
	                            </td>
	                            <td>
	                                <ul>
	                                    <li><ins class="dis-price"></ins></li> <!-- org-price에 discount적용한 가격 프론트에서 해결 --> 
	                                    <li>
	                                        <del class="org-price" >${product.price }</del>
	                                        <input type='hidden' id='or_price' value="${product.price }">
	                                        <span class="discount" >${product.discount }%</span>
	                                        <input type='hidden' id='disc' value="${product.discount }">
	                                    </li>
	                                    <li><span class="free-delivery">무료배송</span></li>
	                                </ul>
	                            </td>
	                            <td>
	                                <h4 class="seller"><i class="fas fa-home"></i>&nbsp;${product.seller}</h4>
	                                <!-- !!! 판매자 등급 확인 join 해야 할 듯 !!! -->
	                                <h5 class="badge power">판매자등급</h5>
	                                <!-- !!! 상품평 : score 에 따라 class star값 달라짐 확인 !!! -->
	                                <h6 class="rating star1">상품평</h6>
	                            </td>
	                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="paging">
		        	<c:if test="${pageGroupStart > 1}">
	                    <span class="prev">
	                        <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2 }&pg=${pageGroupStart - 1}"><&nbsp;이전</a>
	                    </span>
		            </c:if>
		            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
	                    <span class="num ">
	                        <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2 }&pg=${i}" class="${currentPage == i?'on':'off'}">${i}</a>
	                    </span>
		            </c:forEach>
		            <c:if test="${pageGroupEnd < lastPageNum}">
	                    <span class="next">
	                        <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2 }&pg=${pageGroupEnd + 1}">다음&nbsp;></a>
	                    </span>
		            </c:if>
                </div>
            </section>
        </main>
<script>

    // price, discount value 받아오기
    let price = document.getElementById("or_price").value;
    let discount = document.getElementById("disc").value;
    
   // window.onload = function(){
        // price에서 discount 가격 계산
        const disPrice = price - ((price * discount) / 100 );
        console.log(disPrice);
        
        document.getElementsByClassName("dis-price")[0].innerText= disPrice ;
	
    //};


</script>
<%@ include file="_footer.jsp" %>
