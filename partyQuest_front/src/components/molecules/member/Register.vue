<script setup>
import {useAuthStore} from "@/stores/authStore";
import {axiClient, getValidatedAxios} from "@/utils/globalAxios";
import { ref } from 'vue';
import {Form, Field, ErrorMessage} from 'vee-validate';
import {useRouter, useRoute} from "vue-router";
import * as yup from 'yup';
import {AUTH_CONST} from "@/constants/authConst";

/**
 * 유효성 검증에 사용된 라이브러리 : Vee-validate 4.0.0 & Yup
 * @type {Router}
 */
const router = useRouter();
const BASE_URL = "/api/member";
const authStore = useAuthStore();
const myAxios = axiClient;
const kakaoClientId = import.meta.env.VITE_APP_KAKAO_CLIENT_ID;
const kakaoRedirectUri = import.meta.env.VITE_APP_KAKAO_REDIRECT_URI;

const registerInfo = ref({
  name:"",
  email: "",
  confirmationCode:"",
  password: "",
  passwordConfirmation: "",
  phone:"",
})
const validationErrors = ref({});
const isValid = ref(true); // 유효성 검사 선언
const countEmail = ref(""); // 이메일 중복 검사를 위한 결과 선언
const authKey = ref(""); // 서버에서 전달해온 키를 위한 변수 선언


//////////////// 유효성 검증////////////////
const schema = yup.object({
  name: yup.string().matches(/^[가-힣]{2,5}$/, '한글로 입력해주십시오.').min(2, "이름은 최소 2자 이상이어야 합니다.").required("이름을 입력해주십시오."),
  email: yup.string().email("올바른 이메일 형식이 아닙니다.").required("이메일을 입력해주십시오."),
  password: yup.string().matches(/^(?=.*[A-Za-z\d])|(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/, '비밀번호는 영문,숫자 혼합 최소 8자 이상이어야 합니다.').required("비밀번호를 입력해주십시오."),
  passwordConfirmation: yup.string().oneOf([yup.ref('password'), null], '비밀번호가 일치하지 않습니다.').required('비밀번호 확인은 필수 항목입니다.'),
  phone: yup.string().matches(/^\d{3}-\d{3,4}-\d{4}$/, '올바른 휴대폰 번호 형식이 아닙니다.').required('휴대폰 번호는 필수 항목입니다.'),
});

////////////////최종 전송 폼////////////////

const handlerSignUp = async () => {
  try {
    await schema.validate(registerInfo.value,{ abortEarly: false }); // validation 을 한꺼번에 진행하고, 모두 끝난 뒤에 결과 리턴
    isValid.value = true;

    await myAxios.post(BASE_URL + '/sign-up', registerInfo.value)
        .then(res => {
          if (res.status == 200) {
            validationErrors.value = {};
            alert('성공적으로 회원가입이 되었습니다.');
            let accessToken = res.headers.get(AUTH_CONST.AUTH_HEADER);
            authStore.accessTokenHandler(accessToken);
            let user = authStore.getUser();
            let userNickName = user.value.nickName;
            router.push({path:'/register/popup',query:{userNickName:userNickName}})
          }
        });
  } catch (e) {
    isValid.value = false;
    // 발생한 오류가 유효성 검사(Yup)통해서 된 건지 확인
    if (e instanceof yup.ValidationError) {
      validationErrors.value = {};

      // 유효성 검증의 에러가 발생한 경우 배열 행태로 저장하고 표시
      // ex) email: 올바른 형식 x, password: 비밀번호는 필수 ...
      e.inner.forEach((err) => {
        validationErrors.value[err.path] = err.message;
      });
      // 에러 메시지 출력
      const errorMessages = Object.values(validationErrors.value);
      alert("유효하지 않은 정보가 있습니다. 다시 한 번 확인해주십시오."+ errorMessages.join(', '));
    } else {
      // 유효성 검증 외의 오류 발생
      //console.error(e);
    }
  }
};

////////////////이메일 중복확인 및 코드 전송///////////////
// TODO: 회원가입 한 직후 로그인된 상태로 유지하기
const btnEmailAuth = async ()=>{
  try {
    const response = await myAxios.post(BASE_URL + "/email-auth/"+ registerInfo.value.email);
    // 이메일 중복 확인 후 값 할당
    countEmail.value = response.data.countEmail;
    // 키 할당
    authKey.value = response.data.key;
    if (countEmail.value === 0) {

    } else if (response.status === 409) {
      countEmail.value =1;

    }
  }catch (err){
    //console.log(err);
  }
}
///////////// 인증코드 확인 //////////////
const keyErrorMessage = ref('');
const keyConfirmMessage = ref('');
const btnKeyConfirm = () => {
  if (registerInfo.value.confirmationCode !== authKey.value) {
    keyErrorMessage.value = '인증번호를 다시 확인해주십시오.';
  } else {
    keyConfirmMessage.value = '인증이 완료되었습니다.';
  }
};

//////////// kakao oauth //////////////
const kakaoLogin = () => {
  const url = `https://kauth.kakao.com/oauth/authorize?client_id=${kakaoClientId}&redirect_uri=${kakaoRedirectUri}&response_type=code&scope=account_email profile_nickname`;

  showSocialLoginPopup(url);
};

///// 팝업창 //////
const showSocialLoginPopup = (url) => {
  const popupHeight = '500';
  const popupWidth = '500';
  let popupOptions = `height=${popupHeight},width=${popupWidth},left=--popupX--,top=--popupY--,scrollbars=yes,resizable=yes`;

  popupOptions = popupOptions.replace('--popupX--', window.innerWidth / 2 - parseInt(popupWidth) / 2);
  popupOptions = popupOptions.replace('--popupY--', window.innerHeight / 2 - parseInt(popupHeight) / 2);

  openPopup(url, popupOptions);
  return false;
};

const openPopup = (url, options) => {
  window.open(url, '_blank', options);
};
</script>
<template>
  <!-- Start Register -->
  <section class="section1">
    <div class="container py-5 d-flex justify-content-end" id="register" >
      <div class="row py-5" id="registerDiv">
        <Form class="col-md-11 m-auto" method="post" :validation-schema="schema">
          <section class="registerSec" id="registerSec">
            <label class="form-label">이름</label>
            <div id="txtBoxDiv">
              <Field
                  name="name"
                  type="text"
                  id="regiNameBox"
                  class="form-control"
                  v-model="registerInfo.name"
              />
            </div>
            <ErrorMessage class="error-message" name="name"/><br>
            <label class="form-label">이메일</label>
            <div id="txtBoxDiv">
              <Field
                  name="email"
                  type="text"
                  id="regiEmailBox"
                  class="form-control"
                  v-model="registerInfo.email"
              />
              <button class="checkEmail btn btn-outline-secondary" @click="btnEmailAuth">인증번호 받기</button>
            </div>
            <span v-if="countEmail === 1" class="error-message">이미 사용 중인 이메일 입니다.</span>
            <span v-else-if="countEmail === 0" class="confirm-message">사용 가능한 아이디 입니다.</span>
            <ErrorMessage class="error-message" name="email"/><br>
            <label class="form-label">인증번호 입력</label>
            <div id="txtBoxDiv">
              <Field
                  name="confirmationCode"
                  type="text"
                  class="form-control"
                  v-model="registerInfo.confirmationCode"
              />
              <button class="checkKey btn btn-outline-secondary" @click="btnKeyConfirm">확인</button>
            </div>
            <span v-if="keyErrorMessage" class="error-message">{{ keyErrorMessage }}</span>
            <span v-if="keyConfirmMessage" class="confirm-message">{{ keyConfirmMessage }}</span><br>
            <label class="form-label">비밀번호</label>
            <div id="txtBoxDiv">
              <Field
                  name="password"
                  type="password"
                id="regiPassBox"
                class="form-control"
                v-model="registerInfo.password"
              />

            </div>
            <ErrorMessage class="error-message" name="password"/><br>
            <label class="form-label">비밀번호 확인</label>
            <div id="txtBoxDiv">
              <Field
                  name="passwordConfirmation"
                  type="password"
                  id="regiPassBox2"
                  class="form-control"
                  v-model="registerInfo.passwordConfirmation"
              />

            </div>
            <ErrorMessage class="error-message" name="passwordConfirmation"/><br>
            <label class="form-label">휴대폰 번호</label>
            <div id="txtBoxDiv">
              <Field
                  name="phone"
                  type="text"
                  id="regiPhoneBox"
                  class="form-control"
                  v-model="registerInfo.phone"
                  placeholder="- 를 포함하여 입력하십시오"
              />
            </div>
            <ErrorMessage class="error-message" name="phone"/><br>

            <hr />
            <div class="rowCreate d-flex justify-content-center">
              <div class="d-grid gap-2" id="createCharacter">
                <button class="btn btn-danger" type="button" @click="handlerSignUp">
                  캐릭터 생성
                </button>
              </div>
            </div>
            <section id="terms">
              <label class="form-label" id="registerTerm">
                가입 시, 통합 계정으로 파티퀘스트가 제공하는 서비스를 모두
                이용하실 수 있습니다.<br />
                <a>통합 계정</a> 및
                <routerLink to="/TermsServicePolicy" class="terms">서비스 이용약관</routerLink
                >,
                <routerLink to="PrivacyPolicy"  class="terms">개인정보처리방침</routerLink>에
                동의합니다.<br />
              </label>
              <div id="termsSpan" class="form-text">
                <input type="checkbox" /> 파티 퀘스트의 혜택 및 유용한 소식을
                받아볼래요.
              </div>
            </section>
            <hr class="sectionLine" />
            <section id="social">
              <div class="simpleRegister d-flex justify-content-center">
                <label class="form-label"> 간편 회원가입 </label>
              </div>
              <div class="d-flex justify-content-center">
                <a href="#"
                  ><img class="google" src="/img/Google_logo.png" alt="#"
                /></a>

                <a @click="kakaoLogin"
                  ><img class="kakao" src="/img/kakaotalk_logo.png" alt="#"
                /></a>
              </div>
            </section>
          </section>
        </Form>
      </div>
      <hr class="sectionLine" />
    </div>
  </section>
  <!-- End register -->
</template>
<style scoped>
.terms {
  text-decoration: underline ;
  margin-right: -0.1px;
  margin-left: -2px;
}
.error-message {
  color: red;
  font-size: 12px;
  margin-top: 5px;
}
.confirm-message{
  color: green;
  font-size: 12px;
  margin-top: 5px;
}
#txtBoxDiv {
  display: flex;
  align-items: center;
}
.form-label{
  margin-top:5px;
}
.checkEmail {
  margin-left: 10px;
}
button.checkEmail {
  white-space: nowrap;
}
.checkKey{
  margin-left: 10px;
  white-space: nowrap;
}
label, span, button, div{
  font-weight: bold;
}
</style>
