<template>

  <div class="community-left-side">
    <nav class="community-body__navigation">
      <ul class="community-aside">
        <li class="community-aside__group">
          <ul class="community-aside__menu-list">

            <!--community-aside__menu--selected-->
            <li class="community-aside__menu" v-for="cate in categories" :key="fetchCommunityCates.id" >
              <!--<router-link :to="`/communityList/${cate.id}`" @change=""><span class="hoverTag" @click="cateNameToCommunityList(cate.cateName)">{{ cate.cateName }}</span></router-link>-->
              <router-link :to="{ path: `/communityList/${cate.id}`, query: { cateName: cate.cateName } }">
                <span class="hoverTag" @click="cateNameToCommunityList(cate.cateName)">{{ cate.cateName }}</span>
              </router-link>
            </li>

          </ul>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script setup>

import {useAuthStore} from "@/stores/authStore";
import {useRoute} from "vue-router";
import {onBeforeMount, ref} from "vue";
import {getValidatedAxios} from "@/utils/globalAxios";
import router from "@/router";

const BASE_URL = "/api/community";
const { user, accessToken } = useAuthStore();
const categories = ref({});

// 라우터 인스턴스 가져오기
const route = useRoute();

// 서버 데이터
const myAxios = getValidatedAxios(accessToken);

onBeforeMount(()=>{
  fetchCommunityCates();
});

// 라우터 파라미터 수신
const fetchCommunityCates = async()=> {
  try {
    const response = await myAxios.get(BASE_URL + '/communityCate');
    categories.value = response.data;
  } catch (err) {
    console.log(err);
  }
}

</script>

<style scoped>

.hoverTag:hover {
  color: tomato;
}
</style>