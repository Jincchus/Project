<script setup>
import UnAuthenticatedHeader from "@/components/molecules/auth/UnAuthenticatedHeader.vue";
import AuthenticatedHeader from "@/components/molecules/auth/AuthenticatedHeader.vue";
import {useAuthStore} from "@/stores/authStore";
import {axiValid} from "@/utils/globalAxios";
import {onMounted, ref} from "vue";
import HoverAcordianRoot from "@/components/molecules/common/acordion/HoverAccordianRoot.vue";
import StudyAccordionContainer from "@/components/molecules/common/acordion/study/StudyAccordionContainer.vue";
import {useCateStore} from "@/stores/cateStore";

const auth = useAuthStore();
const myAxios = axiValid;
const cates = ref({

})

onMounted(async ()=>{
  await myAxios({
    url: "api/category/cached/allCate",
    method: "get",
    responseType: "json",
  })
      .then((response) => {
        console.log(response.data);
        response.data.forEach((element) => {
          const majorKey = element["majorName"];
          const tempObj = {};
          element["middleCates"].forEach((mid) => {
            const middleKey = mid["middleName"];
            const smallCates = mid["smallCates"];
            const tempList = [];

            smallCates.forEach((sm) => {
              tempList.push(sm["smallName"] + " " + sm["id"]);
            });
            tempObj[middleKey] = tempList;
          });
          cates.value[majorKey] = tempObj;
          // cates의 key(major cate)들을 먼저 등록 해준다.
          //major.value.majors = Object.keys(cates.value);
        });
        console.log("my cate objs", cates.value);
        let cateStore = useCateStore();
        cateStore.setCate(cates.value);
      })
      .catch((err) => {
        console.log(err);
      });
})

</script>
<template>
  <nav
    class="navbar navbar-expand-lg bg-dark navbar-light d-none d-lg-block"
    id="templatemo_nav_top"
  >
    <div class="container text-light">
      <div class="w-100 d-flex justify-content-between">
        <div>
          <i class="fa fa-envelope mx-2"></i>
          <a
            class="navbar-sm-brand text-light text-decoration-none"
            href="mailto:info@company.com"
            >info@partyquest.com</a
          >
          <i class="fa fa-phone mx-2"></i>
          <a
            class="navbar-sm-brand text-light text-decoration-none"
            href="tel:010-020-0340"
            >010-000-0000</a
          >
        </div>
        <div>
          <a
            class="text-light"
            href="https://fb.com/templatemo"
            target="_blank"
            rel="sponsored"
            ><i class="fab fa-facebook-f fa-sm fa-fw me-2"></i
          ></a>
          <a
            class="text-light"
            href="https://www.instagram.com/"
            target="_blank"
            ><i class="fab fa-instagram fa-sm fa-fw me-2"></i
          ></a>
          <a class="text-light" href="https://twitter.com/" target="_blank"
            ><i class="fab fa-twitter fa-sm fa-fw me-2"></i
          ></a>
          <a class="text-light" href="https://www.linkedin.com/" target="_blank"
            ><i class="fab fa-linkedin fa-sm fa-fw"></i
          ></a>
        </div>
      </div>
    </div>
  </nav>
  <!-- Close Top Nav -->

  <!-- Header -->
  <nav class="navbar navbar-expand-md navbar-light shadow ml-3 d-flex justify-content-start" style="padding-left: 80px; height: 100px;">
    <div class="d-flex justify-content-center align-items-center w-75 container">
      <router-link to="/index"><img class="logo" src="/img/logo_with_name3.png"/></router-link>
      <button
        class="navbar-toggler border-0"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#templatemo_main_nav"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div
        class="align-self-center collapse navbar-collapse d-flex justify-content-start w-auto"
        id="templatemo_main_nav"
      >
        <div class="flex-fill px-5 d-flex">
          <ul class="d-flex w-100 align-items-center align-self-center mb-0 justify-content-start flex-wrap gap-4 mx-auto" style="list-style : none">
            <li class="nav-li">
              <HoverAcordianRoot title="스터디 모임">
                <StudyAccordionContainer :study-cate="cates"/>
              </HoverAcordianRoot>
            </li>
            <li class="nav-li">
              <router-link class="fs-6" to="/memberList">파티원</router-link>
            </li>
            <li class="nav-li">
              <router-link class="fs-6" to="/communityList/1">커뮤니티</router-link>
            </li>
            <li class="nav-li">
              <router-link to="/noticeList">고객센터</router-link>
            </li>
          </ul>
        </div>
<!--        LEARN: 바인딩 처리 해주려면 이렇게 직접 참조 하도록 한다.-->
        <div class="flex-fill">
          <div v-if="auth.getAccessToken().length >0">
            <AuthenticatedHeader :member="auth.user"/>
          </div>
          <div v-else class="">
            <UnAuthenticatedHeader/>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.logo{
  width: 150px;
}
.nav-li{
  padding:10px;
  border-radius:10px;
}
.nav-li:hover{
  box-shadow: 6px 6px 2px 1px rgba(125, 125, 125, .2);
  cursor:pointer;
  color:tomato;
}
.nav-li > a:hover{
  color:tomato;
}
</style>
