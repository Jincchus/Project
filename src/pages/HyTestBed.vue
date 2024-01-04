<template>
<div class="father">
  <div class="child">1</div>
  <div class="child">2</div>
  <div class="child">3</div>
  <div class="child">4</div>
</div>
</template>
<style scoped>
.father{
  display: grid;
  grid-template-columns: 100px 200px;
  grid-template-rows: 200px 100px;
  gap:10px
}
.child{
  background-color: tomato;
  color: white;
  display: flex;
  font-size: 28px;
  align-items: center;
  justify-content: center;
}
</style>