<script setup>
import { ref } from "vue";
import axios from "axios";
import { onMounted } from "vue";
import {useAuthStore} from "@/stores/authStore";
import {useRoute, useRouter} from "vue-router";
import Img from "@/components/molecules/common/Img.vue";
import {getValidatedAxios} from "@/utils/globalAxios";
import StudyCreateBtn from "@/components/molecules/study/list/StudyCreateBtn.vue";
import StudyListBtn from "@/components/molecules/study/list/StudyListBtn.vue";

const BASE_URL = "http://localhost:8080";
const {user , accessToken} = useAuthStore();
const myAxios = getValidatedAxios(accessToken);
const hostId = ref(user.email);
const router = useRouter();
//내가 만든 스터디 script 모음

const myPg = ref(0);
const myStudyList = ref([]);
const mySize = ref(0);
const fetchedStudyId = ref(null);

const findStudyMadeByMe = async () => {
  const url = `${BASE_URL}/api/study/findStudyMadeByMe`;
  const StudyMyPageRequest = {
    hostId: hostId.value,
    pg: myPg.value,
  };
  try {
    const response = await axios.post(url, StudyMyPageRequest, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    myStudyList.value = response.data;
    mySize.value = response.data.length;

  } catch (error) {
    console.log("error : " + JSON.stringify(error));
  }
};

const BtnNextMyStudy = () => {
  myPg.value = myPg.value + 3;
  findStudyMadeByMe();
};
const BtnPrevMyStudy = () => {
  // 이전 버튼 클릭 시 pg 값을 3 감소시키고 데이터를 새로고침
  myPg.value = myPg.value - 3;
  findStudyMadeByMe();
};

//내가 참여한 스터디 script모음
const attendPg = ref(0);
const studyIAttend = ref([]);
const attendSize = ref(0);

const findStudyIAttended = async () => {
  const url = `${BASE_URL}/api/study/findStudyIAttended`;
  const StudyMyPageRequest = {
    hostId: hostId.value,
    pg: attendPg.value,
  };
  try {
    const response = await axios.post(url, StudyMyPageRequest, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    studyIAttend.value = response.data;
    attendSize.value = response.data.length;

  } catch (error) {
    console.log("error : " + JSON.stringify(error));
  }
};

const BtnNextAttend = () => {
  attendPg.value = attendPg.value + 3;
  findStudyIAttended();
};
const BtnPrevAttend = () => {
  // 이전 버튼 클릭 시 pg 값을 3 감소시키고 데이터를 새로고침
  attendPg.value = attendPg.value - 3;
  findStudyIAttended();
};

//내가 좋아요한 스터디 script모음
const likePg = ref(0);
const studyIlike = ref([]);
const likeSize = ref(0);
const findLikeStudy = async () => {
  const url = `${BASE_URL}/api/study/findLikeList`;
  const StudyMyPageRequest = {
    hostId: hostId.value,
    pg: likePg.value,
  };
  try {
    const response = await axios.post(url, StudyMyPageRequest, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    studyIlike.value = response.data;
    likeSize.value = response.data.length;

  } catch (error) {
    console.log("error : " + JSON.stringify(error));
  }
};

const BtnNextILike = () => {
  likePg.value = likePg.value + 3;
  findLikeStudy();
};
const BtnPrevILike = () => {
  // 이전 버튼 클릭 시 pg 값을 3 감소시키고 데이터를 새로고침
  likePg.value = likePg.value - 3;
  findLikeStudy();
};
//대기 중인 스터디 모음

const waitPg = ref(0);
const studywaitingList = ref([]);
const waitSize = ref(0);
const findWaitingList = async () => {
  const url = `${BASE_URL}/api/study/findMyWaitingList`;
  const StudyMyPageRequest = {
    hostId: hostId.value,
    pg: waitPg.value,
  };
  try {
    const response = await axios.post(url, StudyMyPageRequest, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    studywaitingList.value = response.data;
    waitSize.value = response.data.length;

  } catch (error) {
    console.log("error : " + JSON.stringify(error));
  }
};

const BtnNextWaitingList = () => {
  waitPg.value = waitPg.value + 3;
  findWaitingList();
};
const BtnPrevWaitingList = () => {
  // 이전 버튼 클릭 시 pg 값을 3 감소시키고 데이터를 새로고침
  waitPg.value = waitPg.value - 3;
  findWaitingList();
};

//거절당한 스터디 모음
const rejectPg = ref(0);
const studyRejectedList = ref([]);
const rejectSize = ref(0);
const findRejectedList = async () => {
  const url = `${BASE_URL}/api/study/findMyRejectedList`;
  const StudyMyPageRequest = {
    hostId: hostId.value,
    pg: rejectPg.value,
  };
  try {
    const response = await axios.post(url, StudyMyPageRequest, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    studyRejectedList.value = response.data;
    rejectSize.value = response.data.length;

  } catch (error) {
    console.log("error : " + JSON.stringify(error));
  }
};

const BtnNextRejectedList = () => {
  rejectPg.value = rejectPg.value + 3;
  findRejectedList();
};
const BtnPrevRejectedList = () => {
  // 이전 버튼 클릭 시 pg 값을 3 감소시키고 데이터를 새로고침
  rejectPg.value = rejectPg.value - 3;
  findRejectedList();
};

////////////////////////
////////////////////////
onMounted(() => {
  findStudyMadeByMe();
  findStudyIAttended();
  findLikeStudy();
  findWaitingList();
  findRejectedList();
});
//참가자 명단 보기
const BtnMemberList =  (studyId) => {
  router.push(`studyMember/${studyId}`);


};
//글수정 삭제 하기
const BtnmodifyStudy= (studyId) =>{
  router.push(`/modifyStudy/${studyId}`);
}

/* const BtnDeleteStudy= async (studyId) =>{
  const requestData = {
    hostId : user.hostId,
    studyId : studyId,
  }
  try {
    const response = await myAxios.delete(BASE_URL+"/api/study/deleteStudy/"+studyId,{data:requestData});

  } catch (err){
    console.error("error : " + err);
  }
}*/

const BtnDeleteStudy= async (studyId) =>{
  // 확인 창 표시
  const isConfirmed = window.confirm("정말로 삭제하시겠습니까?");

  if (isConfirmed) {
    const requestData = {
      hostId: user.hostId,
      studyId: studyId,
    };

    try {
      const response = await myAxios.delete(BASE_URL + "/api/study/deleteStudy/" + studyId, {data: requestData});

      alert("파티가 해체되었습니다 T^T");
    } catch (err) {
      console.error("error : " + err);
    }
  }
}


//page script
(function ($) {
  "use strict";
  $(function () {
    //Event carousel
    $("#events").owlCarousel({
      loop: true,
      margin: 0,
      autoPlay: 3000,
      responsive: {
        0: {
          items: 1,
        },
        768: {
          items: 2,
        },
        979: {
          items: 3,
        },
      },
      singleItem: false,
      dots: false,
      nav: true,
      navText: ["", ""],
    });
  });
});
</script>

<template>
  <body>
    <!-- <script
      src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
      integrity="sha256-pTxD+DSzIwmwhOqTFN+DB+nHjO4iAsbgfyFq5K5bcE0="
      crossorigin="anonymous"
    ></script> -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
      integrity="sha256-UhQQ4fxEeABh4JrcmAJ1+16id/1dnlOEVCFOxDef9Lw="
      crossorigin="anonymous"
    />
    <div class="container">
      <div class="row">
        <div class="col-12">
          <!-- 내가 만든 스터디 -->
          <div id="events" class="event-list owl-carousel owl-loaded owl-drag">
            <div class="owl-stage-outer">
              <p class="title">내가 만든 스터디</p>
              <div class="owl-stage">
                <div v-if="myStudyList.length == 0">
                  <div class="notice">
                  <p class="context">생성한 스터디가 없습니다.</p>
                  <p>함께 공부할 파티원을 모집해보세요</p>
                    <StudyCreateBtn @click="router.push('/createStudy')"/>

                  </div>
                </div>
                <div v-else>
                  <div
                    class="owl-item"
                    style="width: 33.3%"
                    v-for="study in myStudyList"
                    :key="study.studyId"
                  >
                    <div class="event-item">
                      <Img :content="study.thumb"
                          class="img-fluid border"
                      />
                      <p class="event-title">
                        {{ study.title }}
                      </p>
                      <p class="event-content">
                        {{ study.onOff === "ON_OFF" ? "[온라인]" : "" }}[{{
                          study.location
                        }}]({{ study.curMembersSize }}/{{
                          study.memberUpperLimit
                        }})<br>({{ study.studyStartDate }}~{{ study.studyEndDate }})
                      </p>
                      <div>
                        <button class="BtnStudyPage" @click="() => BtnMemberList(study.studyId)">
                          [<span class="applicant">참여자현황</span>]
                        </button>
                      <div class="BtnStudyStatus">
                        <button class="BtnStudyPage" @click="()=>BtnmodifyStudy(study.studyId)">[<span class=" statusModify">수정</span>]</button>
                        <button class="BtnStudyPage" @click="()=>BtnDeleteStudy(study.studyId)">[<span class=" statusDelete">삭제</span>]</button>
                      </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="owl-nav">
                <button
                  type="button"
                  role="presentation"
                  class="owl-prev"
                  v-if="myPg !== 0"
                  @click="BtnPrevMyStudy"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      class="feather feather-chevron-left text-primary"
                    >
                      <polyline points="15 18 9 12 15 6"></polyline>
                    </svg>
                  </div>
                </button>
                <button
                  type="button"
                  role="presentation"
                  class="owl-next"
                  v-if="mySize === 3"
                  @click="BtnNextMyStudy"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      class="feather feather-chevron-right text-primary"
                    >
                      <polyline points="9 18 15 12 9 6"></polyline>
                    </svg>
                  </div>
                </button>
              </div>
            </div>
          </div>
          <!-- 내가 참여하는 스터디 -->
          <div id="events" class="event-list owl-carousel owl-loaded owl-drag">
            <div class="owl-stage-outer">
              <p class="title">내가 참여한 스터디</p>
              <div class="owl-stage">
                <div v-if="studyIAttend.length == 0">
                  <div class="notice">
                    <p class="context">참여하는 스터디가 없습니다.</p>
                    <p>파티에 가입해서 스터디원들과 함께 공부해 보세요.</p>
                    <StudyListBtn/>
                  </div>
                </div>
                <div v-else>
                  <div
                    class="owl-item"
                    style="width: 33.3%"
                    v-for="study in studyIAttend"
                    :key="study.studyId"
                  >
                    <div class="event-item">
                      <Img :content="study.thumb"
                           class="img-fluid border"
                      />
                      <p class="event-title">
                        {{ study.title }}
                      </p>
                      <p class="event-content">
                        {{ study.onOff === "ON_OFF" ? "[온라인]" : "" }}[{{
                          study.location
                        }}]({{ study.curMembersSize }}/{{
                          study.memberUpperLimit
                        }})<br>({{ study.studyStartDate }}~{{ study.studyEndDate }})
                      </p>
                      <div>
                        <button class="BtnStudyPage" @click="() => BtnMemberList(study.studyId)">
                          [<span class="applicant">참여자현황</span>]
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="owl-nav">
                <button
                  type="button"
                  role="presentation"
                  class="owl-prev"
                  v-if="attendPg !== 0"
                  @click="BtnPrevAttend"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      class="feather feather-chevron-left text-primary"
                    >
                      <polyline points="15 18 9 12 15 6"></polyline>
                    </svg>
                  </div>
                </button>
                <button
                  type="button"
                  role="presentation"
                  class="owl-next"
                  v-if="attendSize === 3"
                  @click="BtnNextAttend"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      class="feather feather-chevron-right text-primary"
                    >
                      <polyline points="9 18 15 12 9 6"></polyline>
                    </svg>
                  </div>
                </button>
              </div>
            </div>
          </div>
          <!-- 내가 좋아요한 스터디 -->
          <div id="events" class="event-list owl-carousel owl-loaded owl-drag">
            <div class="owl-stage-outer">
              <p class="title">내가 좋아요한 스터디</p>
              <div class="owl-stage">
                <div v-if="studyIlike.length == 0">
                  <div class="notice">
                    <p class="context">종아요한 스터디가 없습니다.</p>
                    <p>여러 스터디를 구경해보세요.</p>
                    <StudyListBtn/>
                  </div>
                </div>
                <div v-else>
                  <div
                    class="owl-item"
                    style="width: 33.3%"
                    v-for="study in studyIlike"
                    :key="study.studyId"
                  >
                    <div class="event-item">
                      <Img :content="study.thumb"
                           class="img-fluid border"
                      />
                      <p class="event-title">
                        {{ study.title }}
                      </p>
                      <p class="event-content">
                        {{ study.onOff === "ON_OFF" ? "[온라인]" : "" }}[{{
                          study.location
                        }}]({{ study.curMembersSize }}/{{
                          study.memberUpperLimit
                        }})<br>({{ study.studyStartDate }}~{{ study.studyEndDate }})
                      </p>
                      <div>
                        <button class="BtnStudyPage" @click="() => BtnMemberList(study.studyId)">
                          [<span class="applicant">참여자현황</span>]
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="owl-nav">
                <button
                  type="button"
                  role="presentation"
                  class="owl-prev"
                  v-if="likePg !== 0"
                  @click="BtnPrevILike"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      class="feather feather-chevron-left text-primary"
                    >
                      <polyline points="15 18 9 12 15 6"></polyline>
                    </svg>
                  </div>
                </button>
                <button
                  type="button"
                  role="presentation"
                  class="owl-next"
                  v-if="likeSize === 3"
                  @click="BtnNextILike"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="2"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      class="feather feather-chevron-right text-primary"
                    >
                      <polyline points="9 18 15 12 9 6"></polyline>
                    </svg>
                  </div>
                </button>
              </div>
            </div>
          </div>
          <!-- 대기 중인 스터디 -->
          <div id="events" class="event-list owl-carousel owl-loaded owl-drag">
            <div class="owl-stage-outer">
              <p class="title">대기 중인 스터디</p>
              <div class="owl-stage">
                <div v-if="studywaitingList.length == 0">
                  <div class="notice">
                    <p class="content">참여 대기중인 스터디가 없습니다.</p>
                    <p>파티에 가입해서 스터디원들과 함께 공부해 보세요.</p>
                    <StudyListBtn/>
                  </div>
                </div>
                <div v-else>
                  <div
                      class="owl-item"
                      style="width: 33.3%"
                      v-for="waitList in studywaitingList"
                      :key="waitList.studyId"
                  >
                    <div class="event-item">
                      <Img :content="waitList.thumb"
                           class="img-fluid border"
                      />
                      <p class="event-title">
                        {{ waitList.title }}
                      </p>
                      <p class="event-content">
                        {{ waitList.onOff === "ON_OFF" ? "[온라인]" : "" }}[{{
                          waitList.location
                        }}]({{ waitList.curMembersSize }}/{{
                          waitList.memberUpperLimit
                        }})<br>({{ waitList.studyStartDate }}~{{ waitList.studyEndDate }})
                      </p>
                      <div>
                        <button class="BtnStudyPage" @click="() => BtnMemberList(waitList.studyId)">
                          [<span class="applicant">참여자현황</span>]
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="owl-nav">
                <button
                    type="button"
                    role="presentation"
                    class="owl-prev"
                    v-if="waitPg !== 0"
                    @click="BtnPrevWaitingList"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="24"
                        height="24"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="2"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        class="feather feather-chevron-left text-primary"
                    >
                      <polyline points="15 18 9 12 15 6"></polyline>
                    </svg>
                  </div>
                </button>
                <button
                    type="button"
                    role="presentation"
                    class="owl-next"
                    v-if="waitSize === 3"
                    @click="BtnNextWaitingList"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="24"
                        height="24"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="2"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        class="feather feather-chevron-right text-primary"
                    >
                      <polyline points="9 18 15 12 9 6"></polyline>
                    </svg>
                  </div>
                </button>
              </div>
            </div>
          </div>
          <!--    거절당한 스터디      -->
          <div id="events" class="event-list owl-carousel owl-loaded owl-drag">
            <div class="owl-stage-outer">
              <p class="title">참여 거절된 스터디</p>
              <div class="owl-stage">
                <div v-if="studyRejectedList.length == 0">
                  <div class="notice">
                    <p class="context">거절된 스터디가 없습니다.</p>
                    <p>파티에 가입해서 스터디원들과 함께 공부해 보세요.</p>
                    <StudyListBtn/>
                  </div>
                </div>
                <div v-else>
                  <div
                      class="owl-item"
                      style="width: 33.3%"
                      v-for="rejectList in studyRejectedList"
                      :key="rejectList.studyId"
                  >
                    <div class="event-item">
                      <Img :content="rejectList.thumb"
                           class="img-fluid border"
                      />
                      <p class="event-title">
                        {{ rejectList.title }}
                      </p>
                      <p class="event-content">
                        {{ rejectList.onOff === "ON_OFF" ? "[온라인]" : "" }}[{{
                          rejectList.location
                        }}]({{ rejectList.curMembersSize }}/{{
                          rejectList.memberUpperLimit
                        }})<br>({{ rejectList.studyStartDate }}~{{ rejectList.studyEndDate }})
                      </p>
                      <div>
                        <button class="BtnStudyPage" @click="() => BtnMemberList(rejectList.studyId)">
                          [<span class="applicant">참여자현황</span>]
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="owl-nav">
                <button
                    type="button"
                    role="presentation"
                    class="owl-prev"
                    v-if="rejectPg !== 0"
                    @click="BtnPrevRejectedList"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="24"
                        height="24"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="2"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        class="feather feather-chevron-left text-primary"
                    >
                      <polyline points="15 18 9 12 15 6"></polyline>
                    </svg>
                  </div>
                </button>
                <button
                    type="button"
                    role="presentation"
                    class="owl-next"
                    v-if="rejectSize === 3"
                    @click="BtnNextRejectedList"
                >
                  <div class="owl-nav-wrapper bg-soft-primary">
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="24"
                        height="24"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="2"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        class="feather feather-chevron-right text-primary"
                    >
                      <polyline points="9 18 15 12 9 6"></polyline>
                    </svg>
                  </div>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>
<style scoped>

.notice{
  margin : 0 auto;
  text-align: center;
}

.notice>.context{
  font-weight: bold;
}

.title{
  margin-top:1rem;
  font-size: 20px;
  font-weight: bold;
}

.BtnStudyStatus{
  float: right;
}

.BtnStudyPage > .applicant {
  color : olive;
}

.statusModify {
  color : lightseagreen;
}

.statusDelete {
  color : #d62600;
}
.BtnStudyPage {
  border : 3px solid white;
  background: white;
}

.img-fluid{
  height:10rem;
}
</style>
