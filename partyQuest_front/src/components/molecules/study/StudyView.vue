<template>
  <!-- Open Content -->
  <section class="bg-light">
    <div class="container pb-5">
      <div class="row">
        <div class="col-lg-5 mt-5">
          <div class="card mb-3" v-if="studyView.thumb != null">
            <Img
              :content="studyView.thumb"
              class="card-img img-fluid"
              id="studyThumb"
            />
          </div>
        </div>

        <!-- col end -->
        <div class="col-lg-7 mt-5">
          <div class="card" id="studyBase">
            <div class="card-body">
              <h1 class="h1">{{ studyView.title }}</h1>

              <div class="row pb-3" id="studyHostInfo">
                <div class="col-lg-5 mt-5" v-if="studyView.avatar != null">
                  <Img :content="studyView.avatar" id="hostAvatar" />
                </div>
                <div class="col-lg-7 mt-5">
                  <div id="viewHostNickBio">
                    <h3>{{ studyView.hostNickName }}</h3>
                    <h5>{{ studyView.hostBio }}</h5>
                  </div>
                </div>
              </div>

              <div class="row pb-3" id="skillSection">
                <label> 요구 기술 스택</label>
                <div
                  class="col-lg-2 d-grid"
                  v-for="skill in studyView.smallCate"
                >
                  <button
                    class="btn btn-block btn-outline-danger"
                    style="
                      white-space: nowrap;
                      text-overflow: ellipsis;
                      margin-top: 10px;
                      margin-right: 5px;
                    "
                  >
                    {{ "#" + skill.smallCateName }}
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Close Content -->

  <!-- Start Detail Content-->
  <section class="py-5">
    <div class="container pb-5"  >
      <div class="row" >
        <div class="col-lg-9 mt-5 " >
          <div
            :innerHTML="studyView.description"
            style="font-size: large; "
          />
        </div>
        <div class="col-lg-3 mt-5 side-nav" >
          <div class="description" >
            <h6></h6>
            <ul class="list-unstyled pb-3" id="studyInfo">
              <li>
                참여인원 :
                <ol>
                  <!--현재인원 / 총원-->
                  {{
                    studyView.curMembersSize
                  }}
                  /
                  {{
                    studyView.memberUpperLimit
                  }}
                </ol>
              </li>
              <li>승인제 or 선착순</li>
              <ol v-if="studyView.recruitOption === 'PNP'">
                승인제
              </ol>
              <ol v-else>
                선착순
              </ol>
              <li>모임 기간</li>
              <ol>
                {{
                  studyView.studyStartDate
                }}
                <div style="margin-left: 30%">~</div>
                {{
                  studyView.studyEndDate
                }}
              </ol>
              <li>모임 장소</li>
              <ol>
                {{
                  studyView.partyLocation
                }}
              </ol>
            </ul>
            <div class="icons">
              <a class="icon-mail"><IconEnvelope></IconEnvelope></a>&nbsp;
              <a class="icon-heart" @click="updateLike"
                ><IconHeart v-if="!isFilled"></IconHeart
                ><IconHeartFill v-else></IconHeartFill></a
              >&nbsp; <a class="icon-angry"><IconAngry></IconAngry></a>&nbsp;
            </div>
            <button class="btn-accept" @click="applyStudy">참가하기</button>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- End Detail Content-->

  <!-- Start Member Info -->
  <section class="py-5">
    <div class="container pb-5">
      <h4>파티원 소개</h4>
      <div
        class="row"
        v-for="studyMember in studyView.studyMemberInfo"
        :key="studyMember.memberId"
      >
        <div class="col-lg-2"></div>
        <div class="col-lg-2" id="partyMemberMain">
          <Img :content="studyView.avatar" id="memberAvatar" />
        </div>
        <div class="col-lg-5">
          <h3>{{ studyMember.memberNickName }}</h3>
          <h4>{{ studyMember.memberBio }}</h4>
          <div
            class="favoritCate"
            style="float: left; margin-right: 5px"
            v-for="favoritCate in studyMember.smallMember"
          >
            <button
              class="btn btn-block btn-outline-danger"
              style="white-space: nowrap; text-overflow: ellipsis"
            >
              {{ favoritCate.smallCateName }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>

  <!-- End Member Info -->
  <!-- Start Article -->

  <section class="container py-9">
    <div class="row text-left pt-3">
      <div class="col-lg-12 m-auto">
        <!--
        <p id="title1">비슷한 모임</p>
      </div>
    </div>
    <div class="container">
      <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4">
        <div class="col">
          <div class="card px-4 py-2">
            <div class="div1 row py-2 px-2">
              <img
                src="http://via.placeholder.com/400.jpg"
                height="130px"
                width="auto"
                alt=""
              />
            </div>
            <div class="py-2">
              <p id="desc">모임 title</p>

              <div class="d-flex">
                <button
                  class="btnArticle d-flex ml-auto px-3 font-weight-bold darkWhite"
                >
                  바로가기
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card px-4 py-2">
            <div class="div1 row py-2 px-2">
              <img
                src="http://via.placeholder.com/400.jpg"
                height="130px"
                width="auto"
                alt=""
              />
            </div>
            <div class="py-2">
              <p id="desc">모임 title</p>

              <div class="d-flex">
                <button
                  class="btnArticle d-flex ml-auto px-3 font-weight-bold darkWhite"
                >
                  바로가기
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card px-4 py-2">
            <div class="div1 row py-2 px-2">
              <img
                src="http://via.placeholder.com/400.jpg"
                height="130px"
                width="auto"
                alt=""
              />
            </div>
            <div class="py-2">
              <p id="desc">모임 title</p>
              <div class="d-flex">
                <button
                  class="btnArticle d-flex ml-auto px-3 font-weight-bold darkWhite"
                >
                  바로가기
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card px-4 py-2">
            <div class="div1 row py-2 px-2">
              <img
                src="http://via.placeholder.com/400.jpg"
                height="130px"
                width="auto"
                alt=""
              />
            </div>
            <div class="py-2">
              <p id="desc">모임 title</p>

              <div class="d-flex">
                <button
                  class="btnArticle d-flex ml-auto px-3 font-weight-bold darkWhite"
                >
                  바로가기
                </button>
              </div>
            </div>
          </div>
        </div>-->
      </div>
    </div>
  </section>
  <!-- End Article -->
</template>

<script setup>
import { onMounted } from "vue";
import { ref } from "vue";
import { useRoute } from "vue-router";
import IconHeart from "@/components/icons/IconHeart.vue";
import IconEnvelope from "@/components/icons/IconEnvelope.vue";
import IconAngry from "@/components/icons/IconAngry.vue";
import IconHeartFill from "@/components/icons/IconHeartFill.vue";
import { useAuthStore } from "@/stores/authStore";
import Img from "@/components/molecules/common/Img.vue";
import { getValidatedAxios } from "@/utils/globalAxios";

const BASE_URL = "/api/study";

const { user, accessToken } = useAuthStore();

// 라우터 인스턴스 가져오기
const route = useRoute();

// 서버 데이터
const studyView = ref({});
const fetchedStudyId = ref(null);
const myAxios = getValidatedAxios(accessToken);

//before mount 서버에 해당 id studyView json 요청
onMounted(async () => {
  // 라우터 파라미터 수신
  const { studyId } = route.params;
  try {
    const response = await myAxios.get(BASE_URL + "/studyView/" + studyId);

    // 좋아요 한 멤버 리스트 조회
    const likedStudyMembers = response.data.likedStudyMembers || [];
    // 멤버 리스트에서 아이디 값들 가져 오기
    const likedMemberIds = likedStudyMembers.map(member => member.memberId);

    // 로그인 한 유저의 아이디가 멤버 아이디 리스트에 있으면 filled heart로 출력
    if (likedMemberIds.includes(user.hostId)) {
      isFilled.value = true;
    }
    studyView.value = response.data;
  } catch (err) {
    console.log(err);
  } finally {
    fetchedStudyId.value = studyId;
  }
});

// 좋아요 기능
const isFilled = ref(false);

const updateLike = async () => {
  isFilled.value = !isFilled.value;

  const requestData = {
    memberId: user.hostId,
    studyId: fetchedStudyId.value,

  };
  try {
    const response = await myAxios.put(`${BASE_URL}/updateLike`, requestData);

  } catch (error) {
    console.error(error);
  }
};


const applyStudy = async () => {
  const confirmation = confirm("신청하시겠습니까?");
  if (confirmation) {
    const StudyMemberJoinRequest = {
      applicantId: user.email,
      studyId: fetchedStudyId.value,
    };
    try {
      const response = await myAxios.post(
        `${BASE_URL}/ApplyStudy`,
        StudyMemberJoinRequest
      );
      console.log(response.data);
    } catch (error) {
      console.error(error);
      alert("신청이 되지 않았습니다.");
    }
  } else {
    alert("신청이 취소되었습니다.");
  }
};
</script>

<style scoped>
* {
  font-family: "Pretendard Variable", Pretendard, -apple-system,
    BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI",
    "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;
}
</style>
