<script setup>
let props = defineProps({
  "header": String
});
</script>

<template>
  <section class="search">
    <div class="container">
      <div class="content">
        <div class="search-input-header">{{header}}</div>
        <div class="search-input">
          <input type="search" placeholder="관심있는 모임을 검색해보세요"/>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
  section.search {
    padding-top: 48px;
    padding-bottom: 24px;
  }
  .container {
    max-width: 1200px;
    padding: 0 32px;
  }
  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    word-break: break-word;
  }
  .search-input-header{
    font-size: x-large;
  }
  .search-input{
    display:flex;
  }
</style>