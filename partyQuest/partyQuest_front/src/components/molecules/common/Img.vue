<template>
  <img :src="myImgProp.src"/>
</template>

<script setup>
import { ref,onMounted } from "vue";

const props = defineProps({
  content: String,
});
const defaultImagePath = "/img/baseImg.png";
const myImgProp = ref({
  src: (props.content === undefined || props.content===null) ? defaultImagePath : import.meta.env.VITE_APP_BASES3_RESOURCE_URL + props.content,
});

</script>

<style scoped>
</style>
