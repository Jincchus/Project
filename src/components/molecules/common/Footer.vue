<template>
  <footer class="bg-dark" id="tempaltemo_footer">
    <div class="container">
      <div class="row">
        <div class="col-md-4 pt-5">
          <h3 class="h3 text-light border-bottom pb-3 border-light">
            바로가기
          </h3>
          <ul class="list-unstyled text-light footer-link-list">
            <li><RouterLink to="/studies/search">스터디 모임</RouterLink></li>
            <li><RouterLink to="/communityList">커뮤니티</RouterLink></li>
            <li><RouterLink to="/noticeList">공지사항</RouterLink></li>
          </ul>
        </div>

        <div class="col-md-4 pt-5">
          <h3 class="h3 text-light border-bottom pb-3 border-light">
            고객센터
          </h3>
          <ul class="list-unstyled text-light footer-link-list">
            <li>
              <RouterLink to="/noticeList">공지사항</RouterLink>
            </li>
            <li><RouterLink to="/faqList">자주묻는질문</RouterLink></li>
          </ul>
        </div>
        <div class="col-md-4 pt-5">
          <h3 class="h3 text-light border-bottom pb-3 border-light">
            서비스약관
          </h3>
          <ul class="list-unstyled text-light footer-link-list">
            <li>
              <routerLink to="/TermsServicePolicy" class="text-decoration-none"
                >서비스 이용약관</routerLink
              >
            </li>
            <li>
              <routerLink to="/PrivacyPolicy" class="text-decoration-none"
                >개인정보취급방침</routerLink
              >
            </li>
            <li>
              <routerLink to="/PaymentPolicy" class="text-decoration-none"
                >전자금융거래약관</routerLink
              >
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div class="w-100 bg-black py-3">
      <div class="container footerContainer">
        <div>
          <img class="footerLogo" src="/img/footer_logo.png" />
          <span id="footerInfo" class="text-left">
            파티퀘스트 | 사업자번호: 111-11-11111 사업자 정보 확인<br />
            개인정보보호책임자: 이현정 | 이메일 : info@partyquest.com<br />
            전화번호: 070-1111-1111 | 주소: 부산광역시 부산진구 부전동 C2호
          </span>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
</script>

<style scoped>
h1, h3, td, th, i, div, p, span,li,ul{
  font-family: "Pretendard Variable", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
  font-size : 15px;
}
#tempaltemo_footer > div.container > div > div > h3 {
  font-weight:bold;
}
</style>
