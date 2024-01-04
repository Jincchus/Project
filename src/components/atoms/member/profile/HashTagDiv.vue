<script setup>
import {ref} from "vue";

const props = defineProps({
  value : String,
  color: String,
  backgroundColor: String,
  mode: String
});
const sliceNameFromKeySet = (keySet)=>{
  return keySet.split(" ").slice(0, -1).join(' ');
}
const isFocused = ref(false);
</script>

<template>
  <div class="hash-tag" @mouseenter="isFocused=true" @mouseleave="isFocused=false">
    {{sliceNameFromKeySet(props.value)}} <span v-if="mode==='edit' &&isFocused" class="ribbon">삭제</span>
  </div>
</template>

<style scoped>
.hash-tag {
  border-radius: 0.2rem;
  padding: 1px .2rem;
  color: v-bind('props.color');
  background: v-bind('props.backgroundColor');
  display: inline-flex;
  margin-right: 0.3rem;
  position:relative;
}
.hash-tag:hover {
  opacity: .8;
}
.ribbon{
  width: 2rem;
  font-size: .8rem;
  padding: .01rem;
  position: absolute;
  right: -5px;
  top: -12px;
  text-align: center;
  border-radius: .2rem;
  /*transform: rotate(20deg);*/
  background-color: #c02300;
  color: white;
  z-index:2;
}
</style>