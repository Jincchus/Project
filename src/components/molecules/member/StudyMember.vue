<template>
  <section class="container py-9" style="min-height: 1050px">
    <div class="row text-left pt-3">
      <div class="container mt-5">
        <div>
          <!-- 스터디 기본 정보-->
          <div>
            <p>
              [{{ memberList.title }}]
              {{ memberList.onOff === "ON_OFF" ? "[온라인]" : "" }}
              [{{ memberList.location }}] ({{ memberList.curMembersSize }}/{{
                memberList.memberUpperLimit
              }})
            </p>
          </div >
          <!-- 스터디 참여자 모임 -->
          <div>
            <p id="title1">참여자 명단</p>
            <div v-if="Array.isArray(memberList.curMember) && memberList.curMember.length === 0">
              <p>현재 참여한 사람이 없습니다.</p>
            </div>
            <div v-else>
              <table class="table table-bordered">
                <thead v-if="memberList.hostId===user.email">
                <tr class="text-center">
                  <th>참가자</th>
                  <th>상태</th>
                </tr>
                </thead>
                <thead v-else>
                <tr class="text-center">
                  <th>참가자</th>
                </tr>
                </thead>
                <tbody v-for="list in memberList.curMember" :key="list.memberId">
                <!-- 승인시 -->
                <tr
                    class="text-center"
                >
                  <td>
                    <span class="label label-success">승인</span>
                    {{ list.memberNickName }}
                  </td>
                  <td v-if="memberList.hostId === user.email">
                    <button
                        @click.prevent="
                        () =>
                          Btnreject(list.memberId)
                      "
                        id="Btnreject"
                        class="btn btn-danger"
                    >
                      거절
                    </button>
                  </td>
                </tr>

                </tbody>
              </table>
            </div>
          </div>
          <div v-if="memberList.hostId===user.email">

          <!--참여자 대기명단-->
          <div v-if="memberList.recruitOption=='PNP'">
            <p id="title1">참여자 대기명단</p>
            <div v-if="Array.isArray(memberList.waitingList)&&memberList.waitingList.length===0">
              <p>스터디 신청한 사람이 없습니다.</p>
            </div>
            <div v-else>
              <table class="table table-bordered">
                <thead>
                <tr class="text-center">
                  <th>참가자</th>
                  <th>상태</th>
                </tr>
                </thead>
                <tbody v-for="list in memberList.waitingList" :key="list.memberId">
                <!-- 대기시 -->
                <tr
                    class="text-center"
                    v-if="list.applicationStatus == 'PENDING'"
                >
                  <td>
                    <span class="label label-default">대기중</span>
                    {{ list.memberNickName }}
                  </td>
                  <td>
                    <button
                        @click.prevent="
                        () =>
                          Btnapprove(list.memberId)
                      "
                        id="Btnapprove"
                        class="btn btn-success"
                    >
                      승인
                    </button>
                    <button
                        @click.prevent="
                        () =>
                          Btnreject(list.memberId)
                      "
                        id="Btnreject"
                        class="btn btn-danger"
                    >
                      거절
                    </button>
                  </td>
                </tr>
                <!-- 승인시 -->
                <tr
                    class="text-center"
                    v-else-if="list.applicationStatus == 'ACCEPTED'"
                >
                  <td>
                    <span class="label label-success">승인</span>
                    {{ list.memberNickName }}
                  </td>
                  <td>
                    <button
                        @click.prevent="
                        () =>
                          Btnreject(list.memberId)
                      "
                        id="Btnreject"
                        class="btn btn-danger"
                    >
                      거절
                    </button>
                  </td>
                </tr>
                <!-- 거절시 -->
                <tr class="text-center" v-else>
                  <td>
                    <span class="label label-danger">거절</span>
                    {{ list.memberNickName }}
                  </td>
                  <td>
                    <button
                        @click.prevent="
                        () =>
                          Btnapprove(list.memberId)
                      "
                        id="Btnapprove"
                        class="btn btn-success"
                    >
                      재승인
                    </button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          </div>
          <div v-else>

          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {useAuthStore} from "@/stores/authStore";
import {useRoute} from "vue-router";
import axios from "axios";

const BASE_URL = "http://localhost:8080";
const  route = useRoute();
const { studyId } = route.params;
const { user, accessToken } = useAuthStore();

const memberList = ref([]);

const findMemberList = async ()=>{
  const url = `${BASE_URL}/api/study/studyMember`;
  const StudyMyStudyMemberRequest ={
    memberId: user.email,
    studyId: studyId
  };
  try {
    const response = await axios.post(url, StudyMyStudyMemberRequest, {
      headers: {
        "Content-Type": "application/json",
      },
    });
      memberList.value = response.data;
  }catch (error){
    console.error("error : "+JSON.stringify(error));
  }
};

const Btnapprove = async (memberId) => {
  if(!confirm("승인하시겠습니까?")) {
    alert("승인이 취소되었습니다.");
  }else {


    const url = BASE_URL + "/api/study/changeApplicationStatus";
    // StudyChangeApplicantStatusRequest에 데이터를 담음
    const StudyChangeApplicantStatusRequest = new FormData();
    StudyChangeApplicantStatusRequest.append("hostId", user.email);
    StudyChangeApplicantStatusRequest.append("applicantId", memberId);
    StudyChangeApplicantStatusRequest.append("studyId", studyId);
    StudyChangeApplicantStatusRequest.append("status", "ACCEPTED");

    try {
      const response = await axios.post(url, StudyChangeApplicantStatusRequest, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      if (response.status >= 200 && response.status < 300) {
        // HTTP 상태가 성공인 경우
        // 응답 확인

        // 새로운 데이터를 가져오는 함수 호출
        await findMemberList();
      } else {
        // HTTP 상태가 실패인 경우
        throw new Error(`HTTP error ! Status: ${response.status}`);
      }
      // 응답 확인
    } catch (error) {
      console.error("Axios error : ", error);
    }
  }
}; //BTN Approve END

const Btnreject = async (memberId) => {
  if (!confirm("거절하시겠습니까?")) {
    alert("취소 되었습니다.");
  } else {
    const url = BASE_URL + "/api/study/changeApplicationStatus";
    // StudyChangeApplicantStatusRequest에 데이터를 담음
    const StudyChangeApplicantStatusRequest = new FormData();
    StudyChangeApplicantStatusRequest.append("hostId", user.email);
    StudyChangeApplicantStatusRequest.append("applicantId", memberId);
    StudyChangeApplicantStatusRequest.append("studyId", studyId);
    StudyChangeApplicantStatusRequest.append("status", "REJECTED");

    try {
      const response = await axios.post(url, StudyChangeApplicantStatusRequest, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      if (response.status >= 200 && response.status < 300) {
        // HTTP 상태가 성공인 경우
        // 응답 확인

        // 새로운 데이터를 가져오는 함수 호출
        await findMemberList();
      } else {
        // HTTP 상태가 실패인 경우
        throw new Error(`HTTP error ! Status: ${response.status}`);
      }
      // 응답 확인
    } catch (error) {
      console.error("Axios error : ", error);
    }
  }
}
onMounted(()=>{
  findMemberList();
});
</script>

<style scoped>
.label-success {
  background-color: #5cb85c;
}
.label-danger {
  background-color: #d9534f;
}
.label-default {
  background-color: #777;
}

.label {
  display: inline;
  padding: 0.2em 0.6em 0.3em;
  font-size: 75%;
  font-weight: 700;
  line-height: 1;
  color: #fff;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  border-radius: 0.25em;
}
</style>