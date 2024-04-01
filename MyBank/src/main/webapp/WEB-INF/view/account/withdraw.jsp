<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<!-- 메인페이지 -->
<div class="col-sm-8">
	<div class="p-md-5">
		<h2>출금 페이지(인증)</h2>
		<h5>어서오세요 환영합니다</h5>
		<form action="/account/withdraw" method="post">
			<div class="form-group">
				<label for="amount">출금 금액 : </label> <input type="text"
					name="amount" class="form-control" placeholder="Enter amount"
					id="amount" value="1000">
			</div>

			<div class="form-group">
				<label for="wAccountNumber">출금 계좌번호 : </label> <input type="text"
					name="wAccountNumber" class="form-control" placeholder="출금 계좌번호 입력"
					id="wAccountNumber" value="1111">
			</div>

			<div class="form-group">
				<label for="wAccountPassword">출금 계좌 비밀번호:</label> <input
					type="password" name="wAccountPassword" class="form-control"
					placeholder="출금 계좌 비밀번호 입력" id="wAccountPassword" value="1234">
			</div>

			<button type="submit" class="btn btn-primary">출금</button>
		</form>
	</div>
</div>
</div>
</div>
</br>
</div>






<!-- footer -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
