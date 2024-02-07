<script setup>
import {onMounted, ref,watch} from "vue";
import {getPagedStudies, searchStudy} from "@/utils/fetch/studyFetch";
import {useRouter} from "vue-router";
import StudyCard from "@/components/molecules/study/list/card/StudyCard.vue";
import Paging from "@/components/molecules/study/list/Paging.vue";
import {useCateStore} from "@/stores/cateStore";
import SmallCateBtn from "@/components/molecules/study/list/SmallCateBtn.vue";
import SearchInput from "@/components/molecules/study/list/filter/SearchInput.vue";
import IconSearch from "@/components/icons/IconSearch.vue";
import StudyCreateBtn from "@/components/molecules/study/list/StudyCreateBtn.vue";
const router = useRouter();
  const props = defineProps({
    middleCateId : Number,
    smallCateIds : Number,
    curMajorKeySet: String,
    curMiddleKeySet: String
  });
  const curKey = ref({
    major: "",
    middle: ""
  })
const isFirstFetched = ref(false);
const dataFetchFlag = ref(0);
const searchCond = ref({
    middleCateId: props.middleCateId,
    smallCateIds: isNaN(props.smallCateIds)? new Set() : new Set([String(props.smallCateIds)]),
    page: null,
    title: null,
    sort: null
  })
const pageData = ref({});
const cateStore = useCateStore();
const smallCates = ref(null)

const onSearchCondUpdateHandler = async ()=>{
    pageData.value = null;
    pageData.value = await searchStudy(searchCond.value)
}
watch(dataFetchFlag,onSearchCondUpdateHandler)
const onPagingHandler = (num)=>{
  isFirstFetched.value = false;
  searchCond.value.page = num;
}
const findMatchingKey = (targetMajor,targetMiddle) => {
  let majorKeys = Object.keys(cateStore.getCate());
  for (const majorKey of majorKeys) { // javascript Array 순회는 for ... of
    let keySet = majorKey.split(" ");
    if (keySet[keySet.length-1] === targetMajor) {
      curKey.value.major = majorKey;
      break;
    }
  }
  let middleKeys = Object.keys(cateStore.getCate()[curKey.value.major]);
  for (const middleKey of middleKeys) {
    let keySet = middleKey.split(" ");
    if (keySet[keySet.length-1] === targetMiddle) {
      curKey.value.middle = middleKey;
      break;
    }
  }
};
const extractIdFromKeySet = (smallKeySet) => {
  let keyList = smallKeySet.split(" ");
  return keyList[keyList.length - 1];
};
const extractTitleFromKeySet = (smallKeySet) => {
  let keyList = smallKeySet.split(' ').slice(0,-1);
  return keyList.join(' ');
};
const toggleSmallKeyIdFromSet = (rawSmallKey) => {
  let targetSmallId = extractIdFromKeySet(rawSmallKey);
  if (searchCond.value['smallCateIds'].has(targetSmallId)) {
    searchCond.value['smallCateIds'].delete(targetSmallId);
  }else{
    searchCond.value['smallCateIds'].add(targetSmallId);
  }
  dataFetchFlag.value++;
  console.log(searchCond.value);
};
const isActivatedSmallBtn = (rawSmallKey) => {
  let targetSmallId = extractIdFromKeySet(rawSmallKey);
  if (searchCond.value['smallCateIds'].has(targetSmallId)) {
    return true;
  }else{
    return false;
  }
};
const onSubmitSearchCondHandler = async () => {
  if (searchCond.value.title === null || searchCond.value.title.length === 0) {
    searchCond.value.title = null;
  }
  dataFetchFlag.value++;
};
onMounted(async ()=>{
  console.log('내가 요청하는 params',searchCond.value)
  if(isNaN(searchCond.value.middleCateId)){
    pageData.value = await getPagedStudies();
  }else{
    pageData.value = await searchStudy(searchCond.value);
  }
  isFirstFetched.value = true;
  findMatchingKey('1',String(props.middleCateId))
  smallCates.value = cateStore.globalCate[curKey.value.major][curKey.value.middle]
})
</script>

<template>
  <div class="grid-container">
    <div class="breadcrumb-container">
      <span>{{extractTitleFromKeySet(curMajorKeySet)}}</span> > <span>{{extractTitleFromKeySet(curMiddleKeySet)}}</span>
    </div>
    <div class="filter-container">
      <form @submit.prevent="onSubmitSearchCondHandler" class="search-container">
        <SearchInput @search-value="(msg)=> searchCond.title = msg"/>
        <button type="submit" class="search-btn"><IconSearch/></button>
      </form>
      <div class="small-cate-wrapper">
        <SmallCateBtn v-for="smallKey in smallCates" :small-cate-key="smallKey"
                      :isActivated="isActivatedSmallBtn(smallKey)"
                      @click="toggleSmallKeyIdFromSet(smallKey)"
        />
      </div>
    </div>
    <div class="create-study-btn-container">
      <StudyCreateBtn @click="router.push('/createStudy')"/>
    </div>
    <div v-if="pageData===null">로딩중...</div>
    <div v-else v-for="data in pageData.content">
      <StudyCard :study-info="data"/>
    </div>
    <div v-if='pageData!==null' class="paging-container">
      <div class="paging-wrapper" @click="dataFetchFlag++" >
        <Paging v-for="num in pageData.totalPages" :pg="num"
        @click="onPagingHandler(num)"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
  .grid-container {
    display: grid;
    grid-gap: 20px 10px;
    grid-template-columns: repeat(4, minmax(220px, 250px));
    grid-auto-flow: row;
    place-content: start center;
  }
  .breadcrumb-container{
    margin-top:20px;
    font-size:24px;
    grid-column: span 4;
    display: flex;
    gap: 10px;
    color: #818085
  }
  .breadcrumb-container > span:hover{
    color: black;
    font-weight:bold;
  }
  .filter-container {
    grid-column: span 4;
    display:flex;
    gap: 10px;
  }
  .search-container{
    display:flex;
  }
  .search-btn {
    background: white;
    border: solid;
    border-width: 1px 1px 1px 0px;
    padding: 5px;
  }
  .search-btn:hover {
    background: rgba(155,155,155,0.2);
  }
  .create-study-btn-container{
    grid-column: span 4;
    display:flex;
    justify-content: end;
  }
  .paging-container {
    grid-column: span 4;
    margin: 20px 0px;
    place-items: center center;
    gap: 10px;
  }
  .paging-wrapper {
    display: flex;
    justify-content: center;
    gap: 3px;
  }
  .small-cate-wrapper{
    display: flex;
    gap: 5px;
    font-weight: lighter;
  }
</style>