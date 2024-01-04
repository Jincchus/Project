<template>
  <div class="d-flex dropdown">
    <div
      class="dropdown-toggle"
      id="head"
      data-bs-toggle="dropdown"
      aria-expanded="false"
    >
      <MemberAvatar :height="25" :width="25"/>
    </div>
    <ul class="dropdown-menu" aria-labelledby="head">
      <li class="member-greeting"><span class="member-nickName">{{member.nickName}}</span>님 안녕하세요</li>
      <li v-for="menu in authMenu"  :key="menu" class="dropdown-item">
        <button class="dropdown-item"
                :class="{ 'custom-css-class': isNotification(menu) }" @click="navigateToPage(menu)">
          {{ menu }}
          <span v-if="isNotification(menu) && unreadMessageSize >= 1">✉</span>
        </button>
      </li>
      <li class="dropdown-divider"><hr /></li>
      <li>
        <button class="dropdown-item" @click="onClickHandler">로그아웃</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import {onMounted, ref,  watch} from "vue";
import { useAuthStore } from "@/stores/authStore";
import { logout } from "@/utils/fetch/auth";
import { useRouter } from "vue-router";
import MemberAvatar from "@/components/icons/MemberAvatar.vue";
import {useMessageStore} from "@/stores/messageStore";

defineProps({
  member: Object,
});
let authStore = useAuthStore();
const authMenu = ref(["마이페이지", "마이스터디","알림", "좋아요"]);
const router = useRouter();

const messageStore = useMessageStore();
const onClickHandler = async () => {
  await logout(authStore.accessToken)
      .catch(err => {
        if (err.response) {
          console.log(err.response);
        }
      });
  authStore.invalidateUser();
  router.forward("/index");
};

//authMenu 클릭시 이동하게 만드는 경로
const navigateToPage = (menu) => {
  // 각 메뉴에 대한 라우팅 경로를 설정
  let routePath = "/";
  switch (menu) {
    case "마이페이지":
      routePath = "/profile";
      break;
    case "마이스터디":
      routePath = "/myStudyPage";
      break;
    case "좋아요":
      routePath = "/likes";
      break;
    case "알림":
      routePath="/message";
      break;
  }
  // 페이지 이동
  router.push(routePath);
};

//메세지 여부 확인
const user = useAuthStore();
const memberId =ref(user.user.email);
const unreadMessageSize = ref(0);

const isNotification = (menu) => {
  const condition1 = unreadMessageSize.value >= 1;
  const condition2 = menu.trim().includes("알림");
  const result = condition1 && condition2;
  return result;
};

onMounted(async()=>{
  await messageStore.findUnreadMessage(memberId.value);
  unreadMessageSize.value = messageStore.unReadMessageSize;

});

// 스토어의 값이 변경될 때마다 갱신
watch(() => messageStore.unReadMessageSize, (newValue) => {
  unreadMessageSize.value = newValue;
});


</script>

<style scoped>
.member-greeting{
  font-weight: lighter;
  padding-left: 3px;
}
.member-nickName {
  font-weight: bold;
  font-size: large;
  padding-left: 3px;
  margin-right: 2px;
}
#head {
  border-radius: 10px;
  display: inline-block;
  padding: 10px;

}
#head:hover{
  box-shadow: 6px 6px 2px 1px rgba(125, 125, 125, .2);
  cursor:pointer;
}

.custom-css-class {
  color: #d62600;
}
</style>
