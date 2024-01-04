<template>
  <body class="messageBody">
  <link
      rel="stylesheet"
      type="text/css"
      href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
  />
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="main-box no-header clearfix" v-for="message in messageList">
          <div class="main-box-body clearfix" >
            <div class="table-responsive" >
              <table class="table user-list" >
                <tbody>
                <tr>
                  <td>
                    <p v-if="message.status =='ACCEPTED'">
                      <span class="label label-success" >승인</span>
                      <span class="messageDate">{{ elapsedText(message.rdate)}}</span>
                    </p>
                    <p v-else>
                      <span class="label label-danger" >거절</span>
                      <span class="messageDate">{{ elapsedText(message.rdate)}}</span>
                    </p>
                    <p v-if="message.status =='ACCEPTED'">{{ message.studyTitle }}에 승인되었습니다.</p>
                    <p v-else>{{ message.studyTitle }}에 거절되었습니다.</p>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </body>
</template>

<script setup>
import {useAuthStore} from "@/stores/authStore";
import { onMounted, ref} from "vue";
import axios from "axios";
import {useMessageStore} from "@/stores/messageStore";
import dateFormat from "@/modules/community/DateFormat";


const BASE_URL = "http://localhost:8080";
const {user , accessToken} = useAuthStore();
const memberId = ref(user.email);
const messageList = ref([]);
const messageStore = useMessageStore();

const findMessageList = async () =>{
  const url = `${BASE_URL}/api/member/findMessageList/${memberId.value}`;
  try{
    const response = await axios.get(url);
    messageList.value = response.data;
  }catch (error){
    console.error("error : "+JSON.stringify(error));
  }
}

const elapsedText = (date)=>{
  return dateFormat.elapsedText(new Date(date));
}

onMounted(async () => {
  await findMessageList();
  await messageStore.findUnreadMessage(memberId.value);
  // 스토어에 값을 저장
  messageStore.unReadMessageSize = messageStore.unReadMessageSize;
});



</script>

<style  scoped>
.messageBody{
  box-sizing: border-box;
  min-height: 600px;

}

.main-box {
  width: 40%;
  margin: 0 auto;
  border: 1px solid black;
  /* background: #ffffff; */
  -webkit-box-shadow: 1px 1px 2px 0 black;
  -moz-box-shadow: 1px 1px 2px 0 black;
  -o-box-shadow: 1px 1px 2px 0 black;
  -ms-box-shadow: 1px 1px 2px 0 black;
  box-shadow: 1px 1px 2px 0 black;
  margin-bottom: 16px;
  -webikt-border-radius: 3px;
  -moz-border-radius: 3px;
  border-radius: 3px;
}
.table tbody tr td {
  font-size: 12px;
  vertical-align: middle;
  padding: 5px 5px;
}
.table tbody tr td p:nth-child(1) {
  font-size: 14px;
  font-weight: 300;
}
.user-list {
  border: 0px ;
  width: 100%;
}
a:hover {
  text-decoration: none;
}
.table{
  margin-bottom: 0rem;
}

.container{
  margin-top:20px;
}
.label-success {
  background-color: #5cb85c;
}
.label-danger {
  background-color: #d9534f;
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

.messageDate{
margin-left: 10px;
}
</style>