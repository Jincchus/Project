<template>
  <div class="middle-cate">{{props.cateName}}</div>
</template>

<script setup>
const props = defineProps({
  "cateName":String,
  "bgColor":String
})
</script>

