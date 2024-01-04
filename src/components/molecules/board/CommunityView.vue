<script setup>
import Banner from "@/components/molecules/board/Banner.vue";
import Img from "@/components/molecules/common/Img.vue";
import CommunityAside from "@/components/molecules/board/CommunityAside.vue";
import {useAuthStore} from "@/stores/authStore";
import {useRoute, useRouter} from "vue-router";
import {getValidatedAxios} from "@/utils/globalAxios";
import {onMounted, ref} from "vue";
import dateFormat from "@/modules/community/DateFormat";
import router from "@/router";

const BASE_URL = "/api/community";

const { user, accessToken } = useAuthStore();

// 라우터 인스턴스 가져오기
const route = useRoute();


// 서버 데이터
const communityView = ref({});
const fetchedCommunityId = ref(null);
const myAxios = getValidatedAxios(accessToken);

// mount 전 서버에 해당 id json 요청
onMounted(async ()=>{
  // 라우터 파라미터 수신
  const {communityId} = route.params;
  try {
    const response = await myAxios.get(BASE_URL+"/communityView/"+communityId);
    communityView.value = response.data;
  }catch (err){
    console.log(err);
  }finally {
    fetchedCommunityId.value = communityId;
  }
});

const elapsedText = (date)=>{
  return dateFormat.elapsedText(new Date(date));
}

// communityView.rdate를 원하는 형식으로 가공하는 함수
const formatDate = (rawDate) => {
  const dateObject = new Date(rawDate);
  const formattedDate = dateObject.toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
  });
  return formattedDate;
};

//글수정 삭제 하기
const btnModifyCommunity= (communityId) =>{
  router.push(`/communityModify/${communityId}`);
}
const btnDeleteCommunity= async (communityId) =>{
  const requestData = {
    writeId : user.hostId,
    communityId : communityId,
  }
  try {
    const response = await myAxios.delete(BASE_URL+"/deleteCommunity/"+communityId,{data:requestData});

    alert("삭제가 완료되었습니다.");
    router.push({path:`/communityList/1`})

  } catch (err){
    console.error("error : " + err);
  }
}



</script>
<template>
  <main id="main">
    <Banner title="다른 파티의 파티원들과 소통 할 수 있어요!" subTitle="자유게시판, 묻고 답하기에는 여러 파티원들이 북적 북적 !" />
    <section class="community-body">
      <!--왼쪽 어사이드 시작-->
      <CommunityAside></CommunityAside>
      <!--왼쪽 어사이드 끝-->
      <!-- 상단 카테고리 시작 -->
      <div class ="community-body__content">
        <!-- 상단 카테고리 끝 -->
        <!-- Content View 시작-->
        <section class="community-list-container">
          <span style="float : right"> 커뮤니티 > <span style="font-weight : bold; color : tomato;"> {{communityView.cateName}}</span></span><br/>
          <div class="card">
            <div class="card-body">
                <h2 class="card-title">{{ communityView.title }}</h2>
                <h6 class="communityViewDate">{{ formatDate(communityView.rdate) }}
                  <p class="card-text">
                    <small class="text-body-secondary">{{ elapsedText(communityView.rdate) }}</small><br/>
                    <small class="text-body-secondary">{{communityView.writer}}</small>
                  </p>



                </h6>
                <hr class="sectionLine" />
              </div>
            <div v-if="communityView.file != null ">
              <Img :content="'community/'+communityView.file" class="card-img img-fluid" id="communityFile" />
            </div>
              <p class="card-text">{{communityView.content}}</p>


            </div>

        </section>g

        <section>
          <div class="status" style="margin-top:1%;" v-if="communityView.writeId === user.hostId">

            <button class="btn btn-danger" style="float : right" @click="()=>btnModifyCommunity(communityView.communityId)" >글수정</button>
            <button class="btn btn-outline-danger" style="float : right; margin-right : 1%;" @click="()=>btnDeleteCommunity(communityView.communityId)" >글삭제</button>

          </div>

        </section>
        <hr class="sectionLine" />

        <!-- 댓글 시작 -->
        <section class="comment">
          <form class="commentForm">
            <fieldset>
              <legend>답변</legend>
              <div class="mb-3">
                <input type="text" id="disabledTextInput" class="form-control" placeholder="(글쓴)님, 답변을 작성해보세요.">
              </div>
              <button type="submit" class="btn btn-danger" style="float:right">등록</button>
              <button type="submit" class="btn btn-outline-danger" style="float:right; margin-right:1%;">취소</button>
            </fieldset>
          </form>
        </section>

        <section class="community-list-container" style="margin-top:4%;">

          <div class="card commentOutLine">
            <div class="card-body">
              <div class="commentInfo" >
                <Img
                    :content="communityView.file"
                    class="card-img img-fluid"
                    id="communityFile"
                    style="height : 40px; width: 40px"
                />
                <h5 class="card-title">삼순</h5>
                <h6 class="communityViewDate">23.11.25</h6>

                <div class="commentModDel">
                  <a>수정</a> | <a>삭제</a>
                </div>
              </div>
              <hr class="sectionLine commentSectionLine" />
              <p class="card-text">희희</p>

            </div>
          </div>
          <div class="card commentOutLine">
            <div class="card-body">
              <div class="commentInfo">
                <Img
                    :content="communityView.file"
                    class="card-img img-fluid"
                    style="height : 40px; width: 40px"
                />
                <h5 class="card-title">박카스</h5>
                <h6 class="communityViewDate">23.12.07</h6>

                <div class="commentModDel">
                  <a>수정</a> | <a>삭제</a>
                </div>
              </div>

              <hr class="sectionLine commentSectionLine" />
              <p class="card-text">이제 겨울이네여</p>

            </div>
          </div>
        </section>
        <!-- 댓글 끝 -->
        <!-- Content List 끝 -->
      </div>


    </section>
  </main>
</template>
<style scoped>
* {
  font-family: "Pretendard Variable", Pretendard, -apple-system,
  BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI",
  "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji",
  "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
}
#main {
  min-height: 800px;
}
*, :after, :before {
  box-sizing: inherit;
}
main {
  display: block;
}
/*왼쪽 어사이드*/
.community-left-side {
  margin-top: 44px;
  margin-right: 40px;
  width: 168px;
}
.community-body__navigation {
  flex: 1 0 172px;
  max-width: 172px;
}
ol, ul {
  list-style: none;
}
.community-aside__group:first-of-type {
  margin-top: 0;
}
.community-aside__group-name {
  margin-bottom: 1em;
  height: 20px;
  font-size: 12px;
  font-weight: 500;
  color: #adb5bd;
}
.community-aside__menu {
  padding: 0.5em 0.75em;
}
.community-aside__menu a {
  font-size: 17px;
  color: #343a40;
  font-weight: 400;
  line-height: 1.5;
}
.community-aside__menu--selected a {
  font-weight: 700;
  color: rgba(255, 21, 21, 0.8);
}
/*content*/
.community-body {
  display: flex;
  flex-direction: row;
  margin: 0 auto;
  padding: 0 32px;
  max-width: 1200px;
}
.community-body__content {
  padding: 32px 0 64px;
  max-width: 100%;
  flex: 1;
  display: flex;
  flex-direction: column;
}
.qnaBtn{
  border-radius : 2em;
}
.qnaList{
//border-radius : 0%;
}

/*status 바*/
.status > div {
  display : inline-block;
}
.ac-button.is-text {
  border: unset;
  background-color: unset;
  color: #495057;
  font-weight: 500;
}
.ac-button.is-md {
  padding: 0 12px;
  height: 40px;
  line-height: 1.43;
  font-size: 14px;
  letter-spacing: -.3px;
}
.ac-button {
  position: relative;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border-radius: 4px;
  line-height: 1.47;
  font-weight: 500;
  cursor: pointer;
  -webkit-appearance: none;
  padding: 0 12px;
  height: 40px;
  line-height: 1.43;
  font-size: 14px;
  letter-spacing: -.3px;
  border: 1px solid;
  color: #fff;
}

/* 오른쪽 어사이드 */
sec{
  margin-left : 4%;
  margin-top : 5%
}

.QRA1 {
  margin-bottom : 5%;
}

/* comment */
.commentOutLine {
  border-radius : 0.1em;

}
.commentInfo > *{
  display:inline-block;
  margin-left : 1%;
}
.commentModDel{
  float : right;
  margin-right : 2%;
  margin-top : 1%
}
.commentSectionLine {
  margin-top : 1%;
}
</style>