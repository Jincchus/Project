<script setup>
import { ref } from "vue";
import axios from "axios";
import { onMounted } from "vue";
import {useAuthStore} from "@/stores/authStore";

const BASE_URL = "http://localhost:8080";
const {user} = useAuthStore();
//hostId로 변수값이 들어와야함.
const hostId = ref(user.hostId);
console.log("user : "+user.value);
const studyWaitingList = ref([]);
const findWaitingList = async () => {
  try {
    const response = await axios.get(
      BASE_URL + `/api/study/studyWaitingListMadeByMe/${hostId.value}`
    );
    studyWaitingList.value = response.data;
    console.log("responseData : ", studyWaitingList.value);
  } catch (error) {
    console.log("error : " + JSON.stringify(error));
  }
};
onMounted(findWaitingList);

const Btnapprove = async (memberId, hostId, studyId) => {
  alert("accepted");
  const url = BASE_URL + "/api/study/changeApplicationStatus";
  console.log("hostId : " + hostId);
  console.log("memberId : " + memberId);
  console.log("studyId : " + studyId);
  // StudyChangeApplicantStatusRequest에 데이터를 담음
  const StudyChangeApplicantStatusRequest = new FormData();
  StudyChangeApplicantStatusRequest.append("hostId", hostId);
  StudyChangeApplicantStatusRequest.append("applicantId", memberId);
  StudyChangeApplicantStatusRequest.append("studyId", studyId);
  StudyChangeApplicantStatusRequest.append("status", "ACCEPTED");

  try {
    const response = await axios.post(url, StudyChangeApplicantStatusRequest, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    console.log("Response data:", response.data);
    if (response.status >= 200 && response.status < 300) {
      // HTTP 상태가 성공인 경우
      // 응답 확인

      // 새로운 데이터를 가져오는 함수 호출
      await findWaitingList();
    } else {
      // HTTP 상태가 실패인 경우
      throw new Error(`HTTP error ! Status: ${response.status}`);
    }
    // 응답 확인
  } catch (error) {
    console.error("Axios error : ", error);
  }
}; //BTN Approve END

const Btnreject = async (memberId, hostId, studyId) => {
  alert("Reject");
  const url = BASE_URL + "/api/study/changeApplicationStatus";
  console.log("hostId : " + hostId);
  console.log("memberId : " + memberId);
  console.log("studyId : " + studyId);
  // StudyChangeApplicantStatusRequest에 데이터를 담음
  const StudyChangeApplicantStatusRequest = new FormData();
  StudyChangeApplicantStatusRequest.append("hostId", hostId);
  StudyChangeApplicantStatusRequest.append("applicantId", memberId);
  StudyChangeApplicantStatusRequest.append("studyId", studyId);
  StudyChangeApplicantStatusRequest.append("status", "REJECTED");

  try {
    const response = await axios.post(url, StudyChangeApplicantStatusRequest, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    console.log("Response data:", response.data);
    if (response.status >= 200 && response.status < 300) {
      // HTTP 상태가 성공인 경우
      // 응답 확인

      // 새로운 데이터를 가져오는 함수 호출
      await findWaitingList();
    } else {
      // HTTP 상태가 실패인 경우
      throw new Error(`HTTP error ! Status: ${response.status}`);
    }
    // 응답 확인
  } catch (error) {
    console.error("Axios error : ", error);
  }
}; //BTN Approve END
</script>
<template>
  <section class="container py-9" style="min-height: 1050px">
    <div class="row text-left pt-3">
      <div class="container mt-5">
        <div v-for="study in studyWaitingList" :key="study.studyId">
          <p id="title1">참여자 대기명단</p>
          <div>
            <p>
              [{{ study.title }}]
              {{ study.onOff === "ON_OFF" ? "[온라인]" : "" }}
              [{{ study.location }}] ({{ study.curMembersSize }}/{{
                study.memberUpperLimit
              }})
            </p>
          </div>
          <table class="table table-bordered">
            <thead>
              <tr class="text-center">
                <th>선택</th>
                <th>참가자</th>
                <th>상태</th>
              </tr>
            </thead>
            <tbody v-for="list in study.waitingList" :key="list.memberId">
              <!-- 대기시 -->
              <tr
                class="text-center"
                v-if="list.applicationStatus == 'PENDING'"
              >
                <td><input type="checkbox" /></td>
                <td>
                  <span class="label label-default">대기중</span>
                  {{ list.memberNickName }}
                </td>
                <td>
                  <button
                    @click.prevent="
                      () =>
                        Btnapprove(list.memberId, study.hostId, study.studyId)
                    "
                    id="Btnapprove"
                    class="btn btn-success"
                  >
                    승인
                  </button>
                  <button
                    @click.prevent="
                      () =>
                        Btnreject(list.memberId, study.hostId, study.studyId)
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
                <td><input type="checkbox" /></td>
                <td>
                  <span class="label label-success">승인</span>
                  {{ list.memberNickName }}
                </td>
                <td>
                  <button
                    @click.prevent="
                      () =>
                        Btnreject(list.memberId, study.hostId, study.studyId)
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
                <td><input type="checkbox" /></td>
                <td>
                  <span class="label label-danger">거절</span>
                  {{ list.memberNickName }}
                </td>
                <td>
                  <button
                    @click.prevent="
                      () =>
                        Btnapprove(list.memberId, study.hostId, study.studyId)
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
          <button>선택 거절</button>
          <button>선택 승낙</button>
        </div>
      </div>
    </div>
  </section>
</template>
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
