<template>
  <div class="profile-aside">
    <div class="name-and-thumb">
      <div class="name-container">
        <div class="name-div">{{authStore.getUser().value.nickName}}</div>
        <div class="email-div">{{authStore.getUser().value.email}}</div>
      </div>
      <div class="thumb-container">
        <ProfileImg/>
      </div>
    </div>
    <div class="aside-link-container">
      <ProfileLink v-for="profileLink in profileLinks" :title="profileLink.title" @click="navigateToPage(profileLink)"/>
    </div>
  </div>
</template>

<script setup>
import {useAuthStore} from "@/stores/authStore";
import ProfileImg from "@/components/atoms/member/profile/ProfileImg.vue";
import ProfileLink from "@/components/molecules/member/profile/ProfileLink.vue";
import {ref} from "vue";
import {useRouter} from "vue-router";

const router = useRouter();
const authStore = useAuthStore();
const profileLinks = ref([
  {title:'마이프로필',url:'/profile'},
  {title:'나의 모임 리스트',url:'/profileMyStudyPage'},
  {title:'알림',url:'/profileMyMessage'},
  {title:'계정삭제',url:'/profile'}
])

const navigateToPage = (menu) =>{
  router.push(menu.url);
}

</script>

<style scoped>
.profile-aside {
  display: flex;
  flex-direction: column;
  width: 100%;
}
.name-and-thumb{
  padding: 0.5rem 0.5rem;
  padding-top: 2rem;
  display:flex;
}
.name-container{
  padding-top: 5px;
  display:flex;
  flex: 3;
  flex-direction: column;
  justify-content:start;
}
.name-div {
  font-size: 1.5rem;
  line-height: normal;
}
.email-div {
  font-size: 0.8rem;
  color: gray;
  line-height: normal;
}
.thumb-container{
  display:flex;
  flex: 1;
  justify-content:center;
  align-items: start;
}
.thumb-container img {
  width: 4rem;
  border-radius: 100%;
  object-fit: fill;
}
.aside-link-container {
  margin-top:.5rem;
  display: flex;
  gap:0.8rem;
  flex-direction: column;
  align-items: stretch;
  width: 100%;
  padding-top: 1rem;
  padding-bottom: 1rem;
  padding-left:1.5rem;
  border-top: solid 1px rgba(128, 128, 128, 0.3);
  border-bottom: solid 1px rgba(128, 128, 128, 0.3);
}

</style>