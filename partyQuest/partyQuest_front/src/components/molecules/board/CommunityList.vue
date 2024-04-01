<script setup>
import Banner from "@/components/molecules/board/Banner.vue";
import Img from "@/components/molecules/common/Img.vue";
import CommunityAside from "@/components/molecules/board/CommunityAside.vue";
import {useAuthStore} from "@/stores/authStore";
import {useRoute} from "vue-router";
import {onBeforeMount, onBeforeUnmount, onMounted, onUnmounted, onUpdated, ref} from "vue";
import {getValidatedAxios} from "@/utils/globalAxios";
import dateFormat from "@/modules/community/DateFormat";
import router from "@/router";

const BASE_URL = "/api/community";

const { user, accessToken } = useAuthStore();

// 라우터 인스턴스 가져오기
const route = useRoute();

// 서버 데이터
const communityList = ref({});
const myAxios = getValidatedAxios(accessToken)

const cateName = ref(route.query.cateName);

// update 시 서버에 해당 id json 요청
onMounted(async ()=>{

  // 라우터 파라미터 수신
  const {cateId} = route.params;
  try {
    const response = await myAxios.get(BASE_URL + "/communityList/" + cateId);

    communityList.value = response.data;

    // 업데이트가 처리되었음을 플래그로 표시

  } catch (err) {
    console.log(err);
  }
});



const elapsedText = (date)=>{
  return dateFormat.elapsedText(new Date(date));
}

</script>
<template>
  <main id="main">
    <Banner title="다른 파티의 파티원들과 소통 할 수 있어요!" subTitle="자유게시판, 묻고 답하기에는 여러 파티원들이 북적 북적 !" />
    <section class="community-body">
      <!--왼쪽 어사이드 시작-->
      <CommunityAside></CommunityAside>
      <!--왼쪽 어사이드 끝-->
      <!-- 상단 카테고리 시작 -->
      <div class ="community-body__content">
        <section>
          <div class="status">
            <span style="display : inline-block;">커뮤니티 > <span style="color : tomato; font-size : 20px" > {{cateName ? cateName : '자유게시판'}}</span></span>
            <router-link to="/communityWrite">
              <button class="btn btn-danger" style="float : right" >
            <span class="posts-container-header__button-text">
            글쓰기
            </span>
              </button>
            </router-link>
          </div>
        </section>
        <!-- 상단 카테고리 끝 -->
        <!-- Content List 시작-->
        <hr class="sectionLine" />
        <section class="community-list-container">
          <div class="card qnaList" v-for="list in communityList">
            <div class="card-header">
              <router-link :to="`/communityView/${list.communityId}`">{{ list.title }}</router-link>
            </div>
            <div class="card-body">
              <blockquote class="blockquote mb-0 ">
                <p>{{ list.content.slice(0, 50) + (list.content.length > 50 ? '...' : '') }}</p>

                <footer class="blockquote-footer">{{ list.writer }} · {{ elapsedText(list.rdate) }}</footer>
              </blockquote>
            </div>
          </div>

        </section>
      </div>
        <!-- Content List 끝-->


      <!-- 오른쪽 어사이드 시작
      <sec>
        <div class="qnaRightAside">
          <div class="card QRA1" style="width: 18rem;">
            <div class="card-body">
              <h5 class="card-title">댓글 많은 글</h5>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>
            <div class="card-body" >
              <a>더보기</a>
            </div>
          </div>

          <div class="card " style="width: 18rem;">
            <div class="card-body">
              <h5 class="card-title">많이 본 글</h5>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
                <div class="card-body">
                  <a href="#" class="card-link">작성자 닉네임</a>
                </div>
              </li>
            </ul>          <ul class="list-group list-group-flush">
            <li class="list-group-item">모던 자바 인 액션 스터디 모집합니다
              <div class="card-body">
                <a href="#" class="card-link">작성자 닉네임</a>
              </div>
            </li>
          </ul>
            <div class="card-body">
              <a href="#" class="card-link"></a>
            </div>
          </div>
        </div>
      </sec>

      오른쪽 어사이드 끝-->
    </section>
  </main>
</template>
<style scoped>
* {
  font-family: "Pretendard Variable", Pretendard, -apple-system,
  BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI",
  "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji",
  "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
}
#main {
  min-height: 800px;
}
*, :after, :before {
  box-sizing: inherit;
}
main {
  display: block;
}
/*왼쪽 어사이드*/
.community-left-side {
  margin-top: 44px;
  margin-right: 40px;
  width: 168px;
}
.community-body__navigation {
  flex: 1 0 172px;
  max-width: 172px;
}
ol, ul {
  list-style: none;
}
.community-aside__group:first-of-type {
  margin-top: 0;
}
.community-aside__group-name {
  margin-bottom: 1em;
  height: 20px;
  font-size: 12px;
  font-weight: 500;
  color: #adb5bd;
}
.community-aside__menu {
  padding: 0.5em 0.75em;
}
.community-aside__menu a {
  font-size: 17px;
  color: #343a40;
  font-weight: 400;
  line-height: 1.5;
}
.community-aside__menu--selected a {
  font-weight: 700;
  color: rgba(255, 21, 21, 0.8);
}
/*content*/
.community-body {
  display: flex;
  flex-direction: row;
  margin: 0 auto;
  padding: 0 32px;
  max-width: 1200px;
}
.community-body__content {
  padding: 32px 0 64px;
  max-width: 100%;
  flex: 1;
  display: flex;
  flex-direction: column;
}
.qnaBtn{
  border-radius : 2em;
}
.qnaList{
//border-radius : 0%;
}

/*status 바*/
.status > div {
  display : inline-block;
}
.ac-button.is-text {
  border: unset;
  background-color: unset;
  color: #495057;
  font-weight: 500;
}
.ac-button.is-md {
  padding: 0 12px;
  height: 40px;
  line-height: 1.43;
  font-size: 14px;
  letter-spacing: -.3px;
}
.ac-button {
  position: relative;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border-radius: 4px;
  line-height: 1.47;
  font-weight: 500;
  cursor: pointer;
  -webkit-appearance: none;
  padding: 0 12px;
  height: 40px;
  line-height: 1.43;
  font-size: 14px;
  letter-spacing: -.3px;
  border: 1px solid;
  color: #fff;
}

/* 오른쪽 어사이드 */
sec{
  margin-left : 4%;
  margin-top : 5%
}

.QRA1 {
  margin-bottom : 5%;
}
</style>