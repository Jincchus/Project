<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<!-- 메인페이지 -->
<div class="col-sm-8">
	<div class="p-md-5 text-center">
		<h2>계좌 상세 보기(인증)</h2>
		<h5>어서오세요 환영합니다</h5>

		<br />
		<!-- if accountList null or not null -->
		<div class="table--back text-center">
			
				<table class="table text-center table--1 table-fixed w-full border-separate rounded-[20px] overflow-hidden">
					<thead class="table--2">
						<tr>
							<th>${principal.username }님의 계좌</th>
							<th>계좌 번호 : ${account.number }</th>
							<th>잔액 : ${account.formatBalance() }</th>
						</tr>
					</thead>
					<tbody class="table--2">
						<tr>
							<td><a href="/account/detail/${account.id}">전체 조회</a></td>
							<td><a href="/account/detail/${account.id}?type=deposit">입금 조회</a></td>
							<td><a href="/account/detail/${account.id}?type=withdraw">출금 조회</a></td>
						</tr>
					</tbody>
				</table>
			</div>
	
			<div class="bg-light text-center">
					<table class="table text-center table--1 table-fixed w-full border-separate rounded-[20px] overflow-hidden">
						<thead class="table--2">
							<tr >
								<th>날짜</th>
								<th>보낸이</th>
								<th>받은이</th>
								<th>입출금 금액</th>
								<th>계좌 잔액</th>
							</tr>
						</thead>
						<tbody class="table--2">
						<c:forEach var="history" items="${historyList}">
							<tr>
								<td>${history.formatCreatedAt()}</td>
								<td>${history.sender }</td>
								<td>${history.receiver }</td>
								<td>${history.formatAmount()}</td>
								<td>${history.formatBalance() }</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
		</div>
		</div>
</div>
</div>
</br>
</div>


<!-- footer -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
