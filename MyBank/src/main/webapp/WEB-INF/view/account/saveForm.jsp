<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<!-- 메인페이지 -->
<div class="col-sm-8">
	<div class="p-md-5">
		<h2>계좌 생성 페이지(인증)</h2>
		<h5>어서오세요 환영합니다</h5>
		<form action="/account/save" method="post">
			<div class="form-group">
				<label for="number">계좌 번호 : </label> <input type="text"
					name="number" class="form-control" placeholder="Enter number"
					id="number" value="5555">
			</div>

			<div class="form-group">
				<label for="pwd">계좌 비밀번호:</label> <input type="password"
					name="password" class="form-control" placeholder="Enter password"
					id="pwd" value="1234">
			</div>

			<div class="form-group">
				<label for="balance">입금 금액:</label> <input type="text"
					name="balance" class="form-control" placeholder="Enter balance"
					id="balance" value="2000">
			</div>

			<button type="submit" class="btn btn-primary">계좌 생성</button>
		</form>
	</div>
</div>
</div>
</br>
</div>






<!-- footer -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
