<script setup>
import {onMounted, onUpdated, ref} from "vue";

const DEFAULT_AVATAR_URL = '/img/profile_simple.png'
const props = defineProps({
  content: String,
  preview: String
});
const myImgProp = ref({
  src: (props.content === undefined || props.content===null || props.content === '') ? DEFAULT_AVATAR_URL : import.meta.env.VITE_APP_BASES3_RESOURCE_URL + props.content,
});
const getURL = ()=>{
  if(props.preview === undefined || props.preview === null || props.content === '') return myImgProp.value.src;
  return props.preview
}
onMounted(()=>{
  console.log(props.preview,props.content,myImgProp.value)
})
</script>

<template>
  <img :src="getURL()"/>
</template>

<style scoped>

</style>