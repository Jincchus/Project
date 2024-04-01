<script setup>
import StudyCard from "@/components/molecules/study/list/card/StudyCard.vue";

const props = defineProps({
  studies: Array,
  title: String
});
</script>

<template>
  <div class="my-container">
    <div class="container-title">{{props.title}}</div>
    <div class="study-list-carousel">
      <div v-for="study in props.studies" class="study-iter">
        <StudyCard :studyInfo="study"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.my-container {
  width: 90vw;
}
  .container-title {
    padding-top: 20px;
    font-size: 27px;
    margin-bottom: 30px;
    font-weight: 500;
  }
  .study-list-carousel {
    display: grid;
    grid-gap: 20px 10px;
    grid-template-columns: repeat(5, minmax(220px,1fr));
    grid-template-rows: repeat(2, 1fr);
    grid-auto-flow: column;
    grid-auto-columns: minmax(330px,1fr);
    place-content: start start;
    overflow-x: hidden;
    margin-bottom:30px;
  }

</style>