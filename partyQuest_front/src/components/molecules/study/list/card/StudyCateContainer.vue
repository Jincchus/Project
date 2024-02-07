<template>
  <div class="cate-container">
    <div v-for="cate in props.cateList">
      <StudyCate :bg-color="(props.cateKind === 'middle') ? '#65b57b' : '#8cd1bb'" :cate-name="cate.name" :key="cate.id"/>
    </div>
  </div>
</template>

<script setup>
import StudyCate from "@/components/molecules/study/list/card/StudyCate.vue";
const props = defineProps({
  cateList: Array,
  cateKind: String
});
</script>

<style scoped>
  .cate-container {
    width: 100%;
    display: flex;
    gap: 0px 3px;
    overflow-x: auto;
    margin: 5px 0px;
    font-weight: bold;
  }

</style>