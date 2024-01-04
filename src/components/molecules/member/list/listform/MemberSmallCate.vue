<template>
  <button type="button" class="btn btn-outline-secondary">{{props.cateName}}</button>
</template>

<script setup>
const props = defineProps({
  cateName:String,
})

</script>
<style scoped>
.btn{
  margin-right: 3px;
  padding: 5px 10px;
  font-size: 11px;
  height: 25px;
  text-align: center;
  white-space: nowrap;
  width: auto;
}
.btn:hover {
  background-color: transparent;
  color: black
}
</style>