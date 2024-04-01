<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<main id="member">
  <div class="join">
    <img src="/kMarket/member/image/member_join_tit_welcome.gif" class="welcome" alt="케이마켓에 오신것을 환영합니다.">
    <div class="type">
      <div class="normal">
        <img src="/kMarket/member/image/member_join_tit_normal.gif" alt="구매회원 가입">
        <div class="txt">
          <img src="/kMarket/member/image/member_join_txt_normal.gif" alt="개인 구매회원">
          <a href="/kMarket/member/signup.do?type=normal">
            <img src="/kMarket/member/image/member_join_btn1.gif" alt="회원가입">
          </a>
        </div>
      </div>
      <div class="seller">
        <img src="/kMarket/member/image/member_join_tit_seller.gif" alt="판매회원 가입">
        <div class="txt">
          <img src="/kMarket/member/image/member_join_txt_seller.gif" alt="판매회원">
          <a href="/kMarket/member/signupSeller.do?type=seller">
            <img src="/kMarket/member/image/member_join_btn2.gif" alt="회원가입">
          </a>
        </div>
      </div>
    </div>
    <img src="/kMarket/member/image/member_join_banner1.jpg" class="banner" alt="신규회원을 위한 기분 좋은 혜택">
    <img src="/kMarket/member/image/member_join_banner2.jpg" class="banner" alt="사업자회원을 위한 기분 좋은 혜택">
  </div>
</main>
<%@ include file="./_footer.jsp" %>