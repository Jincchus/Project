<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<!-- 메인페이지 -->
<div class="col-sm-8">
	<h2>나의 계좌 목록</h2>
	<h5>어서오세요 환영합니다</h5>

	<br />
	<div class="bg-light">
	<!-- if accountList null or not null -->
	<c:choose>

		<c:when test="${accountList != null }">
			<table class="table">
				<thead>
					<tr>
						<th>번호</th>
						<th>계좌번호</th>
						<th>잔액</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="account" items="${accountList}">
						<tr>
							<td>${account.id }</td>
							<td>${account.number}</td>
							<td>${account.formatBalance() }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>

		<c:otherwise>
			<p>아직 생성된 계좌가 없습니다</p>
			<a href="/account/save">계좌 생성 바로가기</a>
		</c:otherwise>

	</c:choose>
	</div>


</div>
</div>
</br>
</div>


<!-- footer -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
