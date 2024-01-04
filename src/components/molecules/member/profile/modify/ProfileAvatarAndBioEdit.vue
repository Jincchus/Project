<script setup>

import ProfileImg from "@/components/atoms/member/profile/ProfileImg.vue";
import {onMounted, onUnmounted, ref} from "vue";
import ProfileFields from "@/components/atoms/member/profile/ProfileFields.vue";
import {useProfileStore} from "@/stores/memberProfileStore";

let props = defineProps({
});
let profileStore = useProfileStore();
const avatarURL = ref(null);
const fileName = ref(null)
const onNickChangeHandler = (e)=>{
  let value = e.target.value.trim();
  profileStore.setTmpNickName(value);
}
const onBioChangeHandler = (e)=>{
  let value = e.target.value.trim();
  profileStore.setTmpNickbio(value);
}
const getOrDefaultMsg = (target,msg) => {
  if(target=== null) return msg;
  return target;
};
const onAvatarChange = async (files) => {
  fileName.value = files[0];
  profileStore.setTmpAvatar(files[0]);
  await toBase64(fileName.value)
};
const toBase64 = (file) => {
  return new Promise(resolve => {
    let reader = new FileReader();
    reader.onload = e =>{
      resolve(e.target.result)
      avatarURL.value = e.target.result
    }
    reader.readAsDataURL(file)
  })
};
onMounted(()=>{
  profileStore.setTmpAvatar(null);
})
onUnmounted(()=>{
  profileStore.setTmpAvatar(null);
})
</script>

<template>
  <div class="out-container">
    <div class="thumb-container">
      <label for="avatar">
        <ProfileImg :preview="avatarURL"/>
      </label>
      <input type="file" hidden="hidden" id="avatar" @change="onAvatarChange($event.target.files)"/>
    </div>
    <div class="name-bio-container">
      <div class="name-div">
        <input type="text" @change="onNickChangeHandler" :placeholder="profileStore.getProfileInfo().nickName"/>
      </div>
      <div class="bio-fluid-container">
        <textarea @change="onBioChangeHandler" :placeholder="getOrDefaultMsg(profileStore.getProfileInfo().bio,'자기소개를 입력해주세요')"/>
      </div>
    </div>
  </div>
</template>

<style scoped>
.out-container {
  display: flex;
  width: 100%;
  gap: .3rem;
}
.thumb-container {
}

.thumb-container img{
  object-fit: fill;
  width: 8rem;
  height: 8rem;
  cursor:pointer;
  border-radius: 100%;
}
.name-bio-container{
  padding-top: .5rem;
  padding-left: .4rem;
  height:8rem;
  overflow-y:hidden;
}
.name-div{
  font-size:2rem;
}
.bio-fluid-container{
  height: 4.5rem;
  overflow-y: scroll;
}
.bio-div{
  white-space: pre;
  color:dimgray;

}
</style>