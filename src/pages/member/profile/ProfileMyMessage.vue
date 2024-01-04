<template>
  <div class="profile-container">
    <ProfileAside/>
    <div class="profile-body">
      <Message/>
    </div>
  </div>
</template>

<script setup>
import ProfileAside from "@/components/molecules/member/profile/ProfileAside.vue";
import Message from "@/components/molecules/member/Message.vue";

</script>

<style scoped>
.profile-container {
  max-width: 75rem;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 4fr;
}
.profile-body{
  min-height: 600px;
  box-sizing: border-box;
}
.profile-body> .messageBody{
  height:auto;
}
</style>