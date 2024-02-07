<template>
  <input v-model="searchValue" type="search" name="filter-search" id="filter-search" placeholder="찾고 싶은 스터디를 검색하세요"/>
</template>

<script setup>

import {ref} from "vue";

const emit = defineEmits(['searchValue']);
const searchValue = ref("");
const defaultNull = ref(null);

emit('searchValue', searchValue);
</script>

<style scoped>
#filter-search{
  width: max-content;
  display:flex;
}
</style>