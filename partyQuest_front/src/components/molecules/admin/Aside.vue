<script setup>
import {ref} from "vue";

const authMenu = ref(["나의 프로필", "나의 모임 리스트", "계정 관리"]);
const handlerToPage = (menu) => {
  // 각 메뉴에 대한 라우팅 경로를 설정
  let routePath = "/";
  switch (menu) {
    case "나의 프로필":
      routePath = "/profile";
      alert("나의 프로필 클릭" + routePath);
      break;
    case "나의 모임 리스트":
      routePath = "/myStudyPage";
      alert("마이스터디 클릭" + routePath);
      break;
    case "계정 관리":
      routePath = "/account";
      alert("계정 관리" + routePath);
      break;
  }
  router.push(routePath);
};
</script>
<template>
  <main class>
    <section>
      <div class="css-1t4blpu">
        <div class="flex-shrink-0 p-3" style="width: 280px;">
          <ul class="list-unstyled ps-0">
            <li class="mb-1" v-for="menu in authMenu" :key="menu">
              <button @click="handlerToPage(menu)" class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" aria-expanded="false">
                {{menu}}
              </button>
            </li>
            <div class="collapse" id="account-collapse">
              <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">New...</a></li>
                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Profile</a></li>
                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Settings</a></li>
                <li><a href="#" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Sign out</a></li>
              </ul>
            </div>
            <li class="border-top my-3"></li>
          </ul>
        </div>
      </div>
    </section>
  </main>
</template>
<style scoped>

.css-1t4blpu {
  display: flex;
  flex: 1 1 0px;
  padding: 32px;
  margin: 0px auto;
  max-width: 1200px;
}
.p-3 {
  padding: 1rem!important;
}
.flex-shrink-0 {
  flex-shrink: 0!important;
}
*, ::after, ::before {
  box-sizing: border-box;
}
div {
  display: block;
}
.text-decoration-none {
  text-decoration: none!important;
}
.pb-3 {
  padding-bottom: 1rem!important;
}
.mb-3 {
  margin-bottom: 1rem!important;
}
.align-items-center {
  align-items: center!important;
}
.border-bottom {
  border-bottom: var(--bs-border-width) var(--bs-border-style) var(--bs-border-color)!important;
}
.d-flex {
  display: flex!important;
}
.ps-0 {
  padding-left: 0!important;
}
.list-unstyled {
  padding-left: 0;
  list-style: none;
}
dl, ol, ul {
  margin-top: 0;
  margin-bottom: 1rem;
}
.bi {
  vertical-align: -0.125em;
  fill: currentColor;
}
.pe-none {
  pointer-events: none!important;
}
.me-2 {
  margin-right: 0.5rem!important;
}
img, svg {
  vertical-align: middle;
}
*, ::after, ::before {
  box-sizing: border-box;
}
svg:not(:root) {
  overflow-clip-margin: content-box;
  overflow: hidden;
}
.bi {
  vertical-align: -0.125em;
  fill: currentColor;
}
.pe-none {
  pointer-events: none!important;
}
.mb-1 {
  margin-bottom: 0.25rem!important;
}
.list-unstyled {
  padding-left: 0;
  list-style: none;
}

element.style {
}
[type=button]:not(:disabled), [type=reset]:not(:disabled), [type=submit]:not(:disabled), button:not(:disabled) {
  cursor: pointer;
}
.btn-toggle {
  padding: 0.25rem 0.5rem;
  font-weight: 600;
  color: var(--bs-emphasis-color);
  background-color: transparent;
}
.rounded {
  border-radius: var(--bs-border-radius)!important;
}
.align-items-center {
  align-items: center!important;
}
.border-0 {
  border: 0!important;
}
.d-inline-flex {
  display: inline-flex!important;
}
*, ::after, ::before {
  box-sizing: border-box;
}
.fw-normal {
  font-weight: 400!important;
}
.pb-1 {
  padding-bottom: 0.25rem!important;
}
.list-unstyled {
  padding-left: 0;
  list-style: none;
}
.small, small {
  font-size: .875em;
}
.btn-toggle-nav a {
  padding: 0.1875rem 0.5rem;
  margin-top: 0.125rem;
  margin-left: 1.25rem;
}
.rounded {
  border-radius: var(--bs-border-radius)!important;
}
.text-decoration-none {
  text-decoration: none!important;
}
.d-inline-flex {
  display: inline-flex!important;
}
</style>