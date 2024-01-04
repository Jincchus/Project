<template>
  <div class="accordion-container">
    <div class="arrow-container">
      <div v-for="majorKey in Object.keys(studyCate)">
        <div class="accordion-menu" @mouseenter="curMajor=majorKey">{{majorKey.split(" ")[0]}}</div>
      </div>
    </div>
    <StudyMiddleCateContainer v-if="curMajor.length>0" :middle-cates="studyCate[curMajor]" @middle="(mid)=> curMiddle=mid"
                              :cur-major-key-set="curMajor"/>
  </div>
</template>

<script setup>
import {ref} from "vue";
import StudyMiddleCateContainer from "@/components/molecules/common/acordion/study/StudyMiddleCateContainer.vue";

const props = defineProps({
  studyCate : Object
});
const curMajor = ref("");
const curMiddle = ref("");
</script>

<style scoped>
.arrow-container::before {
  content: "";
  border-color: transparent transparent #fff;
  border-style: solid;
  border-width: 0 12px 12px;
  float: left;
  height: 0;
  margin-left: 10px;
  width: 0;
  display: block;
  z-index:2;
  position: absolute;
  top: -12px;
}
.arrow-container::after {
  margin-left: 9px;
  position: absolute;
  content: "";
  border-color: transparent transparent lightgray;
  border-style: solid;
  border-width: 0 13px 13px;
  float: left;
  height: 0;
  width: 0;
  display: block;
  z-index: 1;
  top: -13px;

}
.arrow-container {
  margin-top: 11px;
  width: 140px;
  height: 100%;
  position: relative;
  background-color: white;
  border-width: 1px;
  border-style: solid;
  border-color: lightgray;
  font-weight: lighter;
  font-size: small;
  padding-top: 15px;
  padding-left: 15px;
}
 .accordion-container {
   position: absolute;
   display: flex;
   height: 380px;
   top: calc(100%);
   z-index: 1;
   color: black;
   font-weight: bold;
 }
 .accordion-menu:hover {
   color: #ce0f38;
 }
</style>