<script setup>
import SmallCateButton from "@/components/molecules/member/list/listform/SmallCateButton.vue";
import Location from "@/components/icons/Location.vue";
import IconMbti from "@/components/icons/IconMbti.vue";
import Img from "@/components/molecules/common/Img.vue";
import {ref} from "vue";
import MiddleContainer from "@/components/molecules/member/list/listform/MiddleContainer.vue";
const props = defineProps({
  memberInfo: Object
});
const localMemberInfo = ref(props.memberInfo);
console.log("[localMemberInfo]"+localMemberInfo.value);

</script>
<template>
  <div class="card" style="width: 17rem; height: 12.5rem;">
    <div class="card-body">
      <div class="card-avatar">
        <Img :content="memberInfo.avatar" class="avatar-img"/>
      </div>
      <div class="card-profile dropdown">
        <div class="card-nickName dropbtn">{{localMemberInfo.nickname}}
          <div class="dropdown-content">
            <a>프로필 상세</a>
            <a>채팅 하기</a>
          </div>
        </div>
        <div class="card-role mb-2 text-body-secondary">
          {{ localMemberInfo.role === 'ROLE_ADMIN' ? 'NPC' : localMemberInfo.role === 'ROLE_USER' ? '플레이어' : localMemberInfo.role }}
        </div>
      </div>
      <div class="card-info">
        <div class="card-mbti"><IconMbti class="mbti-icon"></IconMbti> {{localMemberInfo.mbti}}</div>
        <MiddleContainer :cate-list="localMemberInfo.middleCates" cate-kind="middle"></MiddleContainer>
      </div>
      <div class="card-location"><Location></Location>
        {{localMemberInfo.preferredLocation.locationName ? localMemberInfo.preferredLocation.locationName : '없음'}}
      </div>
      <div class="cate-container">
        <SmallCateButton :cate-list="localMemberInfo.smallCates" cate-kind="small"></SmallCateButton>
      </div>
    </div>
  </div>
</template>
<style scoped>
.card-body {
  display: grid;
  grid-template-columns: auto repeat(2, 1fr);
  grid-template-rows: repeat(3, auto);
  grid-template-areas:
      "avatar avatar header"
      "info info info"
      "location location location"
      "cate cate cate"
      ;
  overflow: auto;
}
.card-avatar {
  grid-area: avatar;
}
.card-profile {
  margin-top:8px;
  margin-right:8px;
  grid-area: header;
  text-align: right;
  position: relative;
}
.card-location{
  margin-top:2px;
  grid-area: location;
}
.card-info {
  grid-area: info;
  display: flex;
  align-items: center;
}
.card-mbti {
  margin-right: 4px;
}
.cate-container {
  margin-top:2px;
  margin-left:7px;
  grid-area: cate;
}
.avatar-img{
  width: 73px;
  margin-bottom:13px;
}

.dropbtn {
  font-size: 16px;
  border: none;
  cursor: pointer;
}
.dropdown {
  display: inline-block;
}
.dropdown-content {
  display: none;
  background-color: #f9f9f9;
  min-width: 80px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  position: absolute;
  top: 100%;
  right: 0;
  text-align:center;
}

.dropdown-content a {
  color: black;
  padding:5px;
  text-decoration: none;
  display: block;
}
.dropdown-content a:hover {background-color: #f1f1f1}
.dropdown:hover .dropdown-content {
  display: block;
}
.mbti-icon{
  margin-left: 4px;
}
</style>
