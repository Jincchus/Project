<template>
  <button class="small-cate-btn">
    <span v-if="isActivated" class="checked-icon">
      <IconChecked/>
    </span>
    {{getSmallCateName()}}
  </button>
</template>

<script setup>
import IconChecked from "@/components/icons/IconChecked.vue";

let props = defineProps({
  smallCateKey: String,
  isActivated: Boolean
});
const getSmallCateName = () => {
  return props.smallCateKey.split(' ').slice(0, -1).join(' ');
};
</script>

<style scoped>
.checked-icon {
  width: 20px;
  margin-right: 4px;
}
.small-cate-btn {
  border: solid 1px gray;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 5px;
  border-radius: 5px;
  background: white;
  cursor: pointer;
}

.small-cate-btn:hover {
  z-index: 2;
  background: rgba(100, 100, 100, 0.3);
}
</style>