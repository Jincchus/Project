<script setup>
import Banner from "@/components/molecules/board/Banner.vue";
import CommunityAside from "@/components/molecules/board/CommunityAside.vue";
import {useAuthStore} from "@/stores/authStore";
import {ref, onMounted, reactive, onBeforeMount} from "vue";
import {getValidatedAxios} from "@/utils/globalAxios";
import {useRoute} from "vue-router";
import router from "@/router";

const BASE_URL = "/api/community";
const { user, accessToken } = useAuthStore();
const selectModifyCommunity = ref({});
const categories = ref({});
const selectedCate = ref([]);
const modifyCommunity = reactive({});
const fetchedCommunityId = ref(null);

const route = useRoute();

const myAxios = getValidatedAxios(accessToken);

onBeforeMount(async ()=>{
  const {communityId} = route.params;
  try {
    const response = await myAxios.get(BASE_URL + "/modifyCommunity/"+ communityId);
    selectModifyCommunity.value = response.data;

    // 서버에서 받아온 카테고리 값이 있으면 설정
    if(selectModifyCommunity.value.cateId){
      const selectedCategory = categories.value.find(cate=> cate.id === selectModifyCommunity.value.cateId);
      selectCate(selectedCategory);
    }
  }catch (err){
    console.log(err);
  }finally {
    fetchedCommunityId.value = communityId;
  }
});

onMounted(()=>{
  fetchCommunityCates();
});
;

// 카테고리 가져오기
const fetchCommunityCates = async()=>{
  try {
    const response = await myAxios.get(BASE_URL + "/communityCate");
    categories.value = response.data;
    } catch(err){
    console.log(err);
  }
};

/* 카테고리 선택 시 버튼 변경*/
const selectCate = (cate)=>{
  selectedCate.value = cate;
}

{
  categories,
  selectedCate,
  selectCate
};

/* 첨부파일 */
// 파일 업로드에 필요한 변수 선언
const fileRef = ref();
const formData = new FormData();

const onFileUploadHandler = (e) => {
  let file = e.target.files[0];
  if (file){
    formData.append("file", file);
  }
}

/* Article Form */
const submitForm = async () => {
  modifyCommunity.communityId = fetchedCommunityId.value;
  modifyCommunity.title = selectModifyCommunity.value.title;
  modifyCommunity.content = selectModifyCommunity.value.content;
  modifyCommunity.writerId = user.hostId; // 이후 user.hostId로 변경
  // modifyCommunity.file = selectModifyCommunity.value.file; 파일이 안들어감 다시확인!!
  try {
    // 카테고리 값 전송
    if (!selectedCate.value) {
      alert("카테고리를 선택해 주세요!");
      return;
    }

    modifyCommunity.cateId = selectedCate.value.id;
    formData.append("cateId", selectedCate.value.id)


    // 서버로 데이터 전송
    const updateResponse = await myAxios.post(BASE_URL+'/modifyCommunity', modifyCommunity )
    const modifyCommunityId = updateResponse.data.communityId;
    modifyCommunity.communityId = modifyCommunityId;

    // 파일이 선택되었을 때만 formData에 파일 업로드 추가
    if (fileRef.value && fileRef.value.files.length > 0) {
      formData.append("file", fileRef.value.files[0]);
    }

    // 파일이 첨부되어 있는 경우에만 파일업로드
    if(formData.has("file")){
      const uploadResponse = await myAxios.post(
          BASE_URL + "/uploadFile",
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },

          }
      );
    };
    // 성공적으로 전송되었을 때의 처리
    alert("게시물 수정이 성공적으로 완료되었습니다.");
    router.push({path:`/communityView/${modifyCommunityId}`}); // 서버에서 반환한 데이터
  }catch (error) {
    // 전송 중 에러 발생시의 처리
    console.error("게시물 등록 중 오류가 발생했습니다.", error);
    // 추가적으로 필요한 에러 처리 로직을 여기에 추가
  }
}
//
// // 파일 업로드 취소
// const deleteFile = async ()=>{
//   try {
//     // 서버에 파일 삭제 요청
//     await myAxios.post(BASE_URL+"/deleteFile/"+communityId)
//
//     // 파일 삭제 후 클라이언트에서도 업데이트
//     savedFileName.value = "";
//   }catch (err){
//     console.error("파일 삭제 오류",err);
//   }
// }
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
          <form @submit.prevent="submitForm">
            <div class="input-group mb-3">
              <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                {{ selectedCate ? selectedCate.cateName : '게시판 선택' }}
              </button>
              <ul class="dropdown-menu">

                <li v-for="cate in categories" :key="fetchCommunityCates.id"><a class="dropdown-item" @click="selectCate(cate)">{{ cate.cateName }}</a></li>

              </ul>
              <input type="text" class="form-control" aria-label="Text input with dropdown button"  v-model="selectModifyCommunity.title">
            </div>
            <div >
              <label for="exampleFormControlTextarea1" class="form-label"></label>
              <textarea class="form-control writeContent" id="exampleFormControlTextarea1" rows="3" v-model="selectModifyCommunity.content"></textarea>

              <div class="mb-3">
                <label for="formFile" class="form-label"></label>
                <input ref="fileRef" class="form-control" type="file" id="formFile" name="formFile" @change="onFileUploadHandler">
                <div v-if="selectModifyCommunity.file">현재 파일 : {{selectModifyCommunity.file}}
                  <button type="button" @click="deleteFile" style="border : none">x</button>
                </div>
              </div>

              <button type="submit" class="btn btn-danger" style="float:right">등록</button>
              <button type="submit" class="btn btn-outline-danger" style="float:right; margin-right:1%;">취소</button>

            </div>
          </form>
        </section>


        <!-- Content List 끝 -->
      </div>

      <!-- 오른쪽 어사이드 시작-->
      <sec></sec>
      <!-- 오른쪽 어사이드 끝-->


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

.writeContent{
  height : 500px;
  resize : none;
}

</style>