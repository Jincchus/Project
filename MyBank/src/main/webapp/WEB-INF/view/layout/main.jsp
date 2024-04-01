<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/view/layout/mainHeader.jsp"%>

<!-- 메인페이지 -->
<div class="col-12 main--page">
	<div class="p-md-5 text-center">
		<section class="main--section">
			<div class="left--div">
				<div>
					<h1>이미 모두의 은행</h1>
					<h1>지금은 <span class="main--mybank">마이뱅크</span></h1>
				</div>
				<div>
					<h5>한 사람, 한사람</h5>
					<h5>개인을 위해 존재하는 <span class="main--mybank">마이뱅크</span></h5>
				</div>
				<br />
				<div>
					<h5>보내고, 받고, 모으고, 쓰는</h5>
					<h5>당신의 모든 일이 바뀌고 있습니다</h5>
				</div>
				<div class="main--btn">
					<button onclick="window.location.href='/user/sign-up'">회원가입</button>
					<button onclick="window.location.href='/user/sign-in'">로그인</button>
				</div>
			</div>
			<div class="right--div">
				<img alt="" src="/images/mainPhoto.png">
			</div>
		</section>
	</div>
</div>
</div>
</br>
</div>
<!-- footer -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>