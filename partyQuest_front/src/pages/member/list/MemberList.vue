<script setup>
import {ref, defineProps, onMounted, watch, watchEffect,computed} from "vue";
import IconSearch from "@/components/icons/IconSearch.vue";
import MemberBox from "@/components/molecules/member/list/listform/MemberBox.vue";
import Banner from "@/components/molecules/board/Banner.vue";
import { useRouter } from "vue-router";
import Paging from "@/components/molecules/study/list/Paging.vue";
import {getLocations, getPagedMembers, searchMember} from "@/utils/fetch/memberFetch";
import {useCateStore} from "@/stores/cateStore";
import MemberSearchInput from "@/components/molecules/member/list/listform/MemberSearchInput.vue";

const router = useRouter();
const props = defineProps({
  middleCateId: Number,
  smallCateIds: Number,
});
const isFirstFetched = ref(false);
const dataFetchFlag = ref(0);
const searchCond = ref({
  preferredLocation:"",
  middleCateId:"",
  pg: null,
  nickname: null,
  sort: null
})
const pageData = ref({});
const cateStore = useCateStore();

/// 지역 리스트 ///
const locations = ref([]);
const fetchLocations = async () => {
  try {
    const response = await getLocations();
    locations.value = response;
  } catch (error) {
    console.error(error);
  }
};


/// 카테고리 리스트///
const middleCates = ref([]);

const fetchMiddleCates = async () => {
  try {
    const response = await cateStore.getCate();

    const allMiddleCategories = response["프로그래밍 1"];

    const selectedMiddleCates = [];

    for (const [categoryKey, subCategories] of Object.entries(allMiddleCategories)) {
      if (categoryKey.includes("프론트앤드") || categoryKey.includes("백앤드")) {
        const [middleId, middleName] = categoryKey.split(" ").reverse();
        selectedMiddleCates.push({ id: middleId, middleName });
      }
    }

    middleCates.value = selectedMiddleCates;
  } catch (error) {
    console.error(error);
  }
};


/// 서치 핸들러 ///

const onSubmitSearchCondHandler = async () => {
  if (searchCond.value.nickname === null || searchCond.value.nickname.length === 0) {
    searchCond.value.nickname = null;
  }
  pageData.value = null;
  pageData.value =  await searchMember({ nickname: searchCond.value.nickname});
};
const onSearchChange = async () => {
  try {
    pageData.value = null;
    pageData.value = await searchMember(searchCond.value)
  } catch (error) {
    console.error('Error in search request:', error);
  }
};

//// 페이징 ////
const onPagingHandler = async (num)=>{
  isFirstFetched.value = false;
  searchCond.value.pg = num;
}
watch(dataFetchFlag,onSearchChange)

onMounted(async () => {
  await fetchLocations();
  await fetchMiddleCates();
  pageData.value = await getPagedMembers();
  isFirstFetched.value = true;
});
const maxDisplayedPages = 10;

const computedPages = computed(() => {
  const totalPages = pageData.value.totalPages;
  return Math.min(totalPages, maxDisplayedPages);
});

const displayedPages = ref([]);

watchEffect(() => {
  if (pageData.value !== null) {
    const totalPages = pageData.value.totalPages;
    const currentPage = pageData.value.number + 1;
    const startPage = Math.max(1, currentPage - Math.floor(maxDisplayedPages / 2));
    const endPage = Math.min(totalPages, startPage + maxDisplayedPages - 1);

    displayedPages.value = Array.from({ length: endPage - startPage + 1 }, (_, i) => startPage + i);
  }
});
const showNextButton = computed(() => {
  return pageData.value !== null && pageData.value.number + 1 < pageData.value.totalPages;
});

const onNextButtonClick = () => {
  onPagingHandler(pageData.value.number + 2);
};
</script>
<template>
  <Banner title="파티퀘스트에는 어떤 파티원들이 있을까요?" subTitle="다양한 파티원을 찾고, 직접 채팅까지!" />
  <main class="grid-container">
    <div class="peopleSearchOption">
      <h1>
        <span class="smTxt">파티크루</span>
        <span class="bgTxt">파티원 찾기</span>
      </h1>
      <div class="peopleSearchOption">
        <div class="flex-container">
          <div class="optionWrap">
            <select class="dropdown1" v-model="searchCond.preferredLocation" @change="onSearchChange">
              <option disabled value="">지역 선택</option>
              <option v-for="location in locations" :key="location.id" :value="location.id">{{ location.locationName }}</option>
            </select>
            <select class="dropdown2" v-model="searchCond.middleCateId" @change="onSearchChange">
              <option disabled value="">관심 기술</option>
              <option v-for="middleCate in middleCates" :key="middleCate.id" :value="middleCate.id">
                {{ middleCate.middleName }}
              </option>
            </select>
          </div>
          <div class="inputSearch">
            <form @submit.prevent="onSubmitSearchCondHandler" class="search-container">
              <MemberSearchInput @search-value="(msg)=> searchCond.nickname = msg"/>
              <button class="buttonComponents">
                <IconSearch/>
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="memberGrid" >
      <div v-if="pageData===null">로딩중...</div>
      <div v-else v-for="data in pageData.content"  >
        <MemberBox :member-info="data" class="memberComponent"></MemberBox>
      </div>
    </div>
    <div v-if='pageData!==null' class="paging-container">
      <div class="paging-wrapper " @click="dataFetchFlag++">
        <Paging v-for="num in pageData.totalPages" :pg="num"
                @click="onPagingHandler(num)"/>
        <button v-if="showNextButton" @click="onNextButtonClick" class="next-button">></button>
      </div>
    </div>
  </main>
</template>
<style scoped>
.grid-container{
  display: grid;
  grid-gap: 20px 10px;
  grid-template-columns: repeat(5, 1fr);
}
.peopleSearchOption{
  grid-column: 2 / 3;
}
h1 span{
  display: block;
}
.flex-container{
  display: flex;
}

.optionWrap{
  text-align: center;
  display: flex;
}
.inputSearch form {
  display: flex;
  align-items: center;
}
.inputSearch button {
  width: 30px;
  height: 35px;
}
.dropdown1,
.dropdown2 {
  display: inline-block;
  margin-right: 10px;
  width: 100px;
  border: 1px solid darkgray;
}
.memberGrid{
  margin-top: 20px;
  margin-bottom: 100px;
  display: grid;
  height:auto;
  grid-template-columns: repeat(auto-fit, minmax(275px,0fr));
  grid-template-rows: repeat(4, 1fr);
  grid-column: 2 / 5;
  gap: 10px;
}
.smTxt {
  margin-top: 50px;
  margin-bottom: 4px;
  font-size: 18px;
  color: tomato;
}
.bgTxt{
  font-size: 32px;
}
.paging-container {
  grid-column: 2/ 5;
  margin: 20px 0px;
  place-items: center center;
}
.paging-wrapper {
  display: flex;
  justify-content: center;
  gap: 3px;
}
.next-button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 16px;
  margin-left:5px;
}
</style>
