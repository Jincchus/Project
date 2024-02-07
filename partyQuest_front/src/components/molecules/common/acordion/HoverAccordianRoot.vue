<template>
  <div class="acordion-root" @mouseenter="isFocused=true" @mouseleave="isFocused=false">
    {{props.title}}
      <slot v-if="isFocused"></slot>
  </div>
</template>

<script setup>
  import {ref} from "vue";

  const props = defineProps({
    title: String
  })
  const isFocused = ref(false);
</script>

<style scoped>
  .acordion-root{
    position:relative;
    z-index: 2;
  }
</style>