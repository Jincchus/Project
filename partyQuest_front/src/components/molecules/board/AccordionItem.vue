<script setup>
import { ref } from 'vue';
const props = defineProps(['headingId', 'collapseId', 'title', 'date', 'content', 'menu', 'boardThumb']);
const isActive = ref(false);
</script>

<template>
  <div class="accordion-item">
    <h2 class="accordion-header" :id="headingId">
      <button
          class="accordion-button collapsed"
          type="button"
          data-bs-toggle="collapse"
          :data-bs-target="`#${collapseId}`"
          :aria-expanded="isActive ? 'true' : 'false'"
          :aria-controls="collapseId"
          @click="toggleAccordion"
      >
        <div>
          <h5>[{{ menu }}] {{ title }}</h5>
          <span>{{ date }}</span>
        </div>
      </button>
    </h2>
    <div :id="collapseId" class="accordion-collapse collapse" :aria-labelledby="headingId" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">
        {{boardThumb}}
        {{ content }}
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Add your custom styles here */
* {
  font-family: "Pretendard Variable", Pretendard, -apple-system,
  BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI",
  "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji",
  "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
}
.accordion-button {
  background-color: #ffffff;
  color: #000;
}
.accordion-button:hover{
  background-color: #ffffff;
}
.accordion-item {
  margin-bottom: 10px;
}

/* Hide radio inputs */
.accordion-button input {
  display: none;
}
span{
  font-size:14px;
}

</style>