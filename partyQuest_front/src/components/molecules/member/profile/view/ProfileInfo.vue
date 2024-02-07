<script setup>

import ProfileAvatarAndBio from "@/components/molecules/member/profile/view/ProfileAvatarAndBio.vue";
import ProfileFields from "@/components/atoms/member/profile/ProfileFields.vue";
import ProfileAvatarAndBioEdit from "@/components/molecules/member/profile/modify/ProfileAvatarAndBioEdit.vue";
import {useRouter} from "vue-router";
import {onBeforeMount, onMounted, ref} from "vue";
import ProfileUpdateFields from "@/components/atoms/member/profile/ProfileUpdateFields.vue";
import {useProfileStore} from "@/stores/memberProfileStore";
import {useAuthStore} from "@/stores/authStore";
import {getPartyLocationForUpdateProfile, getProfile, updateAvatar, updateProfile} from "@/utils/fetch/memberFetch";

const router = useRouter();
const props = defineProps({
  state: String
});
let authStore = useAuthStore();
let profileStore = useProfileStore();
const updateRequest = ref({
  nickName: '',
  bio: '',
  favoriteLocation:'',
  mbti:'',
  favoriteFields: [],
  favoriteTechs:[],
  links:[]
})

const onModifyRouteBtnClick = ()=>{
  router.push('/profile/update');
}
const onModifyBtnClick = async ()=>{
  let curData = await profileStore.getTmpProfileInfo().value;
  let toUpdate = {...curData}
  toUpdate.favoriteFields = [];
  toUpdate.favoriteTechs = [];
  if(profileStore.getIsMiddleChanged()) toUpdate.favoriteFields = Array.from(curData.favoriteFields).map(el=>mapToId(el));
  if(profileStore.getIsSmallChanged()) toUpdate.favoriteTechs = Array.from(curData.favoriteTechs).map(el=>mapToId(el));

  delete toUpdate.preferredLocation; // 프로필 업데이트시 불필요한 필드라서 삭제처리한다.
  await updateProfile(toUpdate, authStore.getAccessToken());
  if(profileStore.getTmpAvatar() !== null){
    const formData = new FormData();
    formData.append('avatar', profileStore.getTmpAvatar());
    await updateAvatar(formData,authStore.getAccessToken())
  }
  await router.push("/profile")
}
const onCancelBtnClick = ()=>{
  router.push('/profile')
}
const onValid = ()=>{
}
const mapToId = (data)=>{
  let splitted = data.split(" ");
  return Number(splitted[splitted.length-1]);
}
onBeforeMount(async ()=>{
  let profile = await getProfile(authStore.getAccessToken());
  console.log(profile)
  profileStore.setProfileInfo(profile);
})
</script>

<template>
  <div v-if="props.state === 'modify'" class="profile-main-container">
    <ProfileAvatarAndBioEdit
    @nick-name="nick=> updateRequest.nickName=nick"
    @bio = "bio=>updateRequest.bio=bio"
    />
    <ProfileUpdateFields/>
    <div class="btn-container">
      <div class="myBtn" @click="onModifyBtnClick">업데이트</div>
      <div class="myBtn cancel-btn" @click="onCancelBtnClick">취소하기</div>
      <div class="myBtn cancel-btn" @click="onValid">확인</div>
    </div>

  </div>
  <div v-else class="profile-main-container">
    <ProfileAvatarAndBio/>
    <ProfileFields/>
    <div class="btn-container">
      <div class="myBtn" @click="onModifyRouteBtnClick">프로필 수정하기</div>
    </div>
  </div>
</template>

<style scoped>
.profile-main-container{
  margin-top: 1rem;
  margin-bottom: 3rem;
  padding-left: 2rem;
}
.btn-container{
  margin-left:2rem;
  display:flex;
  gap: .3rem;
}
.myBtn {
  cursor: pointer;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  padding: 0.2rem 0.5rem;
  background: #3c4142;
  color: white;
  border-radius: 0.4rem;
  font-size: 1.2rem;
}
.cancel-btn{
  background: #a9333e;
}
.route-to-modify:hover{
  cursor:pointer;
  background: dimgrey;
}
</style>