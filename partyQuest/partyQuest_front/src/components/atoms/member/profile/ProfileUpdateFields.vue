<script setup>
import {onMounted, onUnmounted, ref} from "vue";
import HashTagDiv from "@/components/atoms/member/profile/HashTagDiv.vue";
import IconLink from "@/components/icons/IconLink.vue";
import ProfileSelectContainer from "@/components/molecules/member/profile/modify/ProfileSelectContainer.vue";
import { useProfileStore} from "@/stores/memberProfileStore";
import {useAuthStore} from "@/stores/authStore";
import {getMbtis, getPartyLocationForUpdateProfile} from "@/utils/fetch/memberFetch";

const profileStore = useProfileStore();
let authStore = useAuthStore();
const profileFields = ref({
  favoriteLocations: ['서울특별시','부산광역시'],
  mbtis: ['INTP','INTJ','ENTP','ENTJ'],
  favoriteFields: ['백엔드 12','프론트엔드 11'],
  favoriteTechs: ['Spring boot 1201','JPA 1202','Vue 1101'],
  link1: '',
  link2: ''
})
const onPreLocationChange = (e) => {
  let value = e.target.value.trim();
  profileStore.setTmpLocation(value);
};
const onMbtiChange = (e) => {
  let value = e.target.value.trim();
  profileStore.setTmpMbti(value);
};
const onTechDeleteHandler = (hashTag)=>{
  let target = profileStore.getTmpProfileInfo().value.favoriteTechs;
  target.delete(hashTag);
  profileStore.setIsSmallChanged(true);
}
const onFieldDeleteHandler = (hashTag)=>{
  let target = profileStore.getTmpProfileInfo().value.favoriteFields;
  target.delete(hashTag);
  profileStore.setIsMiddleChanged(true);
}
const onLink1ChangeHandler = (e)=>{
  let value = e.target.value.trim();
  profileStore.setTmplink1(value);
}
const onLink2ChangeHandler = (e)=>{
  let value = e.target.value.trim();
  profileStore.setTmplink2(value);
}
const getLinkOrDefault = (data)=>{
  if(data === null) return "포트폴리오, 블로그등의 링크를 공유하세요"
  return data;
}
onMounted(async () => {
  let locations = await getPartyLocationForUpdateProfile(authStore.getAccessToken());
  let mbtis = await getMbtis();
  profileFields.value.favoriteLocations = locations;
  profileFields.value.mbtis = mbtis;
});
onUnmounted(()=>{
  profileStore.setIsMiddleChanged(false);
  profileStore.setIsSmallChanged(false);
})
</script>

<template>
  <div class="profile-fields-container">
    <div class="profile-field-container">
      <div class="field-name-div">선호지역</div>
      <div class="field-content-div">
        <select name="currentLocation" @change="onPreLocationChange">
          <option :selected="profileStore.getTmpProfileInfo().value.preferredLocation===String(location.locationName)" v-for="location in profileFields.favoriteLocations" :value="location.id">
            {{location.locationName}}
          </option>
        </select>
      </div>
    </div>
    <div class="profile-field-container">
      <div class="field-name-div">MBTI</div>
      <div class="field-content-div">
        <select name="currentMbti" @change="onMbtiChange">
          <option :selected="profileStore.getTmpProfileInfo().value.mbti===String(mbti)" v-for="mbti in profileFields.mbtis" :value="mbti">
            {{mbti}}
          </option>
        </select>
      </div>
    </div>
    <div class="profile-field-container-tech">
      <div class="fr1">
        <div class="field-name-div">나의 관심 분야 <ProfileSelectContainer select-type="middle"/>
        </div>
        <div class="field-content-div">
          <HashTagDiv v-for="field in profileStore.getTmpProfileInfo().value.favoriteFields" :value="field" color="white" background-color="#008B8B"
                      mode="edit"
          @click="onFieldDeleteHandler(field)"/>
        </div>
      </div>
    </div>
    <div class="profile-field-container-tech">
      <div class="fr1">
        <div class="field-name-div">나의 관심 기술 <ProfileSelectContainer select-type="small"/>
        </div>
        <div class="field-content-div">
          <HashTagDiv v-for="field in profileStore.getTmpProfileInfo().value.favoriteTechs" :value="field" color="white" background-color="#1CAC78" @click="onTechDeleteHandler(field)"
                      mode="edit"/>
        </div>
      </div>
    </div>
    <div class="profile-field-container">
      <div class="field-name-div">포트폴리오/URL</div>
      <div class="field-content-div">
        <div class="link-content">
          <IconLink/> <input type="text" @change="onLink1ChangeHandler" :placeholder="getLinkOrDefault(profileStore.getTmpProfileInfo().value.link1)"/>
        </div>
        <div class="link-content">
          <IconLink/> <input type="text" @change="onLink2ChangeHandler" :placeholder="getLinkOrDefault(profileStore.getTmpProfileInfo().value.link2)"/>/>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile-fields-container{
  padding-top: 1rem;
  padding-left:2rem;
}
.profile-field-container-tech{
  display:flex;
  border-top: solid 1px rgba(128, 128, 128, 0.5);
  margin-bottom: 1.5rem;
  padding-top: .3rem;
}
.profile-field-container{
  border-top: solid 1px rgba(128, 128, 128, 0.5);
  margin-bottom: 1.5rem;
  padding-top: .3rem;
}
.field-name-div{
  font-weight: 600;
  font-size:1.2rem;
  margin-bottom: .8rem;
  display:flex;
  gap: 1rem;
}
.link-content{
  display:flex;
  gap: .5rem;
}
</style>