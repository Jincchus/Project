<template>
  <div class="tabs is-boxed">
    <ul id="router_li_ul">
      <li>
        <router-link to="/noticeList">
          <span class="icon is-small"><IconNotice></IconNotice></span>
          <span>공지사항</span>
        </router-link>
      </li>
      <li>
        <router-link to="/faqList">
          <span class="icon is-small"><IconQuestion></IconQuestion></span>
          <span>자주 묻는 질문</span>
        </router-link>
      </li>
      <li>
        <router-link to="/termsServicePolicy">
          <span class="icon is-small"><IconTerms></IconTerms></span>
          <span>서비스 이용약관</span>
        </router-link>
      </li>
      <li>
        <router-link to="/privacyPolicy">
          <span class="icon is-small"><IconTerms></IconTerms></span>
          <span>개인정보취급방식</span>
        </router-link>
      </li>
      <li>
        <router-link to="/paymentPolicy">
          <span class="icon is-small"><IconTerms></IconTerms></span>
          <span>전자금융거래약관</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>
<script setup>
import IconQuestion from "@/components/icons/IconQuestion.vue";
import IconNotice from "@/components/icons/IconNotice.vue";
import IconTerms from "@/components/icons/IconTerms.vue";

</script>
<style scoped>
* {
  font-family: "Pretendard Variable", Pretendard, -apple-system,
  BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI",
  "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji",
  "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
}
.tabs {
  -webkit-overflow-scrolling: touch;
  align-items: stretch;
  display: flex;
  font-size: 1rem;
  justify-content: space-between;
  overflow: hidden;
  overflow-x: auto;
  white-space: nowrap;
}
.tabs a {
  align-items: center;
  border-bottom: 1px solid #dbdbdb;
  color: #454545;
  display: flex;
  justify-content: center;
  margin-bottom: -1px;
  padding: 0.5em 1em;
  vertical-align: top;
}
.tabs ul {
  align-items: center;
  border-bottom: 1px solid #dbdbdb;
  display: flex;
  flex-grow: 1;
  flex-shrink: 0;
  justify-content: flex-start;
}
.tabs li {
  display: block;
}
.tabs.is-boxed a {
  border: 1px solid transparent;
  border-radius: 4px 4px 0 0;
}
#app > main > section > div > div.tabs.is-boxed > ul > li > a > span{
  margin-bottom: 10px;
  margin-right:5px;
}
/**
 문제_) li 에 클래스 값 할당 + a 태그에 라우터 이동 방식이라 더블클릭할 시에 css 가 적용이 되었음
 해결_) router 자체에 active 값이 있기 때문에 li가 아닌 a 에 클래스 지시자를 할당 해준다!
 */
.tabs.is-boxed li a.router-link-active {
  background-color: #fff;
  border-color: #dbdbdb;
  border-bottom-color: transparent!important;
  color: rgba(255, 21, 21, 0.8)
}
span {
  font-weight: bold;
  font-style: inherit;
}
*, :after, :before {
  box-sizing: inherit;
}



</style>
