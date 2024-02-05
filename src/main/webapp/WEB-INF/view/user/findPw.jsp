<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<!-- 메인페이지 -->
<div class="col-sm-8">
	<h5>비밀번호 찾기</h5>
	<div id="emailScreen">
	
	<form action="/user/find-pw" method="post">
		<div class="form-group">
			<label for="username">username:</label> 
			<input type="text"	name="username" class="form-control" placeholder="Enter username" id="username" value="카스">
		</div>
		
		<div class="form-group">
			<label for="email">e-mail:</label> 
			<input type="email" name="email" class="form-control" placeholder="Enter E-mail" id="email" value="chopoo2001@gmail.com">
		</div>
		
		<button type="submit" class="btn btn-primary" style="width : 100px">비밀번호 찾기</button>
	</form>
	
	</div>

	<div id="passwordScreen" style="display: none;">
		<div class="l_email_info">
			<p>입력하신 이메일로 임시 비밀번호가 전송됩니다.</p>
		</div>
		<div class="k_input_button">
			<button type="" name="sendPassword" id="sendPassword">임시
				비밀번호 전송</button>
		</div>
	</div>

</div>
</div>
</br>
</div>

<!-- footer -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
