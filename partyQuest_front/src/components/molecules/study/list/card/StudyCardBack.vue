<template>
  <div class="study-card-back">
    <div class="back-title">
      {{props.studyInfo.title}}
    </div>
    <div class="back-info">
      모집방식 <span>{{recruitOption}}</span>
    </div>
    <div class="back-info">
      모집현황 <span>{{props.studyInfo.curMemberNum}}/{{props.studyInfo.limitMemberNum}}</span>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";

const props = defineProps({
  studyInfo:Object
})
const recruitOption = ref(props.studyInfo.recruitOption === 'PNP' ? '승인제' : '선착순');
</script>

<style scoped>
.back-title {
  width:100%;
  font-size: x-large;
  color: white;
  overflow:hidden;
  text-overflow: ellipsis;
  margin-bottom: 20px;
}
  .study-card-back{
    cursor: pointer;
    background-color: black;
    opacity: 0.8;
    width: 100%;
    aspect-ratio: 5/3;
    color: white;
    position: absolute;
    display: flex;
    flex-direction: column;
    align-items: start;
    justify-content: start;
    padding-left: 10px;
  }
  .back-info{
    font-weight:lighter;
    color:#5ec4d6;
  }
.back-info span{
  margin-left: 2px;
}
</style>