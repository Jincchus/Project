<script setup>
import {ref} from "vue";

const props = defineProps({
  link: String,
  title: String
});
const isActivated = ref(false);
</script>

<template>
  <div @mouseenter="isActivated=true" @mouseleave="isActivated=false" class="activate-container">
    <div :class="!isActivated?'status-dot':'status-dot activated'"></div>
    <div :class="!isActivated?'link-title':'link-title activated-title'">{{props.title}}</div>
  </div>
</template>

<style scoped>
.activate-container {
  display: flex;
  align-items:center;
  width: 100%;
  font-size: 1rem;
}
.status-dot {
  width: 4px;
  height: 4px;
  background: gray;
  border-radius: 50%;
  margin-right: 0.4rem;
}
.activated {
  width: 4px;
  height: 4px;
  background: #00C471;
  border-radius: 50%;
}
.link-title{
  color:gray;
}
.activated-title {
  color: black;
  font-weight: bold;
}
</style>