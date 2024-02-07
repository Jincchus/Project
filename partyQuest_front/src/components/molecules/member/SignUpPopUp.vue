<template>
  <div class="my-popup-container">
    <div class="popup-imogi">🎉</div>
    <div class="popup-greeting">
      <article><span class="nickName-span">{{nickName}}</span> {{greeting1}}</article>
      <article>{{greeting2}}</article>
    </div>
    <div class="popup-info">
      <article>{{info1}}</article>
      <article>{{info2}}</article>
      <article>{{info3}}</article>
      <article>{{info4}}</article>
    </div>
    <div class="popup-btnGroup">
      <button @click="onProfileClickHandler" id="profile-btn" class="redirect-btn">프로필 설정하러 가기</button>
      <button @click="onStudyClickHandler" id="studyList_btn" class="redirect-btn">스터디모임 살펴보러 가기</button>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {useRouter} from "vue-router";
import {useAuthStore} from "@/stores/authStore";

const props = defineProps({
  userNickName: String
});
const router = useRouter();
const authStore = useAuthStore();

const greeting1 = ref('모험가님, 회원가입을 축하드려요!');
const greeting2 = ref('저희 파티퀘스트에 오신걸 환영해요');
const info1 = ref('다양한 스터디 모임부터 공모전 참여까지');
const info2 = ref('파타퀘스트에서 함께할 파티원을 모집하세요');
const info3 = ref('프로필에 본인 소개를 더하면 확률이 UP!');
const info4 = ref('바로 프로필 설정을 하러 가볼까요?');

let nickName = ref(authStore.getUser().value.nickName);

const onStudyClickHandler = () => {
  router.push("/studies/search");
};
const onProfileClickHandler = () => {
  router.push("/profile");
};
</script>
<style scoped>
  .my-popup-container{
    width:100%;
    z-index: 1;
    display:grid;
    grid-template-columns: repeat(4,1fr);
    grid-template-rows: repeat(4,max-content);
    border-radius: 20px;
    border:none;
    box-shadow: 6px 6px 2px 1px rgba(125, 125, 125, .2);
    padding: 2rem 3rem;
  }
  .popup-imogi{
    font-size:4rem;
    grid-column: span 4;
  }
  .popup-greeting{
    line-height: 1.2;
    width: max-content;
    grid-column: span 4;
    font-size: 2.5rem;
    display:flex;
    flex-direction: column;
    margin-bottom:2rem;
  }
  .popup-info{
    width: max-content;
    font-size: 1.5rem;
    color: #737170;
    grid-column: span 4;
    display:flex;
    justify-content: start;
    flex-direction: column;
    margin-bottom:2rem;
  }
  .popup-btnGroup{
    grid-column: span 4;
    display:flex;
    justify-content:center;
    align-items: center;
    padding: 0px 5px;
    gap:5px;
  }
  .redirect-btn{
    height: 40px;
    padding:5px;
    color:white;
    border:none;
    border-radius: 10px;
    font-size: 20px;
  }
  .nickName-span{
    font-size: 2.8rem;
    font-weight:bold;
  }
  #profile-btn{
    flex-grow:1;
    display:inline-block;
    background:tomato;
  }
  #profile-btn:hover{
    background: rgba(256,99,71,0.9);
  }
  #studyList_btn{
    flex-grow:1;
    display:inline-block;
    background-color: #979594;
  }
  #studyList_btn:hover {
    background: rgba(125, 125, 125, 0.5);
  }
</style>