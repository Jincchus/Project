<template>
  <span class="cate-pill">{{props.cateName}}</span>
</template>

<script setup>
  const props = defineProps({
    "cateName":String,
    "bgColor":String
  })
</script>

<style scoped>
  .cate-pill{
    display: inline-block;
    padding: 2px 3px;
    background-color: v-bind('props.bgColor');
    border-radius: 10px;
    white-space: nowrap;
    font-size: 15px;
    font-weight: lighter;
  }
</style>