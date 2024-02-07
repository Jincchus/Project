<template>
  <div class="smallCate">
    <div v-for="cate in props.cateList" :key="cate.id">
      <MemberSmallCate :cate-name="cate.name" :key="cate.id"/>
    </div>
  </div>
</template>
<script setup>
import MemberSmallCate from "@/components/molecules/member/list/listform/MemberSmallCate.vue";

const props = defineProps({
  cateList: Array,
  cateKind: String
});
</script>
<style scoped>
.smallCate{
  display: flex;
}
</style>
