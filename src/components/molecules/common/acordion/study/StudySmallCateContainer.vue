<template>
  <div class="small-container">
    <div v-for="smallKey in smallCates">
      <div @click="onSmallClickHandler(smallKey)" class="accordion-menu">{{smallKey.split(" ").slice(0,-1).join(" ")}}</div>
    </div>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import {onMounted} from "vue";

const router = useRouter();
const props = defineProps({
  smallCates : Array,
  curMiddleCateId : String,
  curMajorKeySet: String,
  curMiddleKeySet: String
});
const onSmallClickHandler = (smallCateKey)=>{
  const STUDY_LIST_URL = "/studies/search"
  const splitted = smallCateKey.split(' ');
  const smallCateId = splitted[splitted.length - 1];
  //LEARN: router.push() query string,
  // query param으로 넘김
  router.push({path: STUDY_LIST_URL, query: {
    middleCateId: Number(props.curMiddleCateId),
      smallCateIds: Number(smallCateId),
      curMajorKeySet:props.curMajorKeySet,
      curMiddleKeySet:props.curMiddleKeySet
  }}).then(res=>router.go()).catch(err=>console.log(err));
}
onMounted(()=>{
  console.log('뭔교',props.curMajorKeySet)
})
</script>

<style scoped>
.small-container {
  margin-top: 11px;
  width: 140px;
  height: 100%;
  background-color: white;
  border-width: 1px 1px 1px 0px;
  border-style: solid;
  border-color: lightgray;
  font-weight: lighter;
  font-size: small;
  padding-top: 15px;
  padding-left: 15px;
  z-index: 2;
}
.accordion-menu:hover {
  color: #ce0f0f;
}
</style>