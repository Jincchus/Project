<template>
  <div class="paging-div">{{props.pg}}</div>
</template>

<script setup>
const props = defineProps({
  pg: Number
});
</script>

<style scoped>
  .paging-div {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 30px;
    height: 30px;
    padding: 10px;
    border-radius: 3px;
    color: black;
    border:1px solid darkgray;

  }
  .paging-div:hover{
    background-color:tomato;
  }
</style>