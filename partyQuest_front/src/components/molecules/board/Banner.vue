<script setup>
const props = defineProps(['title', 'subTitle']);
</script>

<template>
  <section class="community-header">
    <div class="community-header_content">
      <h2 class="community-header__title">{{ title }}</h2>
      <p class="community-header__sub-title">{{ subTitle }}</p>
    </div>
  </section>
</template>

<style scoped>
h1, h2, td, th, i, div, p{
  font-family: "Pretendard Variable", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
}
.community-header{
  width: 100%;
  background-color: rgb(223, 80, 80, 0.4);
  padding: 0;

}
.community-header_content{
  margin: 0 auto;
  padding: 24px ;
  max-width: 1200px;
}
.community-header__title{
  font-size: 26px;
  font-weight: 700;
  line-height: 1.2;
  color: #fff;

}
.community-header__sub-title{
  margin-top: 4px;
  font-size: 16px;
  font-weight: 400;
  line-height: 1.5;
  color: #fff;
}
</style>