<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- header -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>


<!-- 메인페이지 -->
<div class="col-sm-8">
	<h2>로그인</h2>
	<h5>어서오세요 환영합니다</h5>
	<form action="/user/sign-in" method="post">
		<div class="form-group">
			<label for="username">username :</label> 
			<input type="text"	name="username" class="form-control" placeholder="Enter username" id="username" value="카스">
		</div>
		
		<div class="form-group">
			<label for="pwd">password :</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="pwd" value="1234">
		</div>
		<div>
			<a href="/user/find-pw">비밀번호 찾기</a>
			<button type="submit" class="btn btn-primary">로그인</button>
		</div>
		<br>
		<hr>
		<div class="login--OAuth" >
			<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=0842db6850200ca9991957bd6d138279&redirect_uri=http://localhost/user/kakao-callback">
				<img alt="" src="/images/kakaotalk_sharing_btn_small.png" width="40" height="40">
			</a>
			<a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=VDvXzrjcWZiTsSVAyXLb&state=STATE_STRING&redirect_uri=http://localhost/user/naver-callback">
				<img alt="" src="/images/naver_login_btn.png" width="40" height="40">
			</a>
		</div>
	</form>
</div>
</div>
</br>
</div>






<!-- footer -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
