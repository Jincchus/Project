<template>
  <input v-model="searchValue" class="searchBox" type="search" placeholder="ex)닉네임, 상세 스킬명" maxlength="10">
</template>

<script setup>

import {ref} from "vue";

const emit = defineEmits(['searchValue']);
const searchValue = ref("");
const defaultNull = ref(null);

emit('searchValue', searchValue);
</script>
<style scoped>
.searchBox {
  border: 1px solid darkgray;
  border-radius: 2px;
  height: 35px;
  width: 250px;
}
</style>