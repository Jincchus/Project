<template>
  <div class="align-self-center d-flex justify-content-start w-100 gap-2 ">
    <div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
      <div class="input-group">
        <input
            type="text"
            class="form-control"
            id="inputMobileSearch"
            placeholder="Search ..."
        />
        <div class="input-group-text">
          <i class="fa fa-fw fa-search"></i>
        </div>
      </div>
    </div>
    <button type="button" class="btn btn-light" @click="onClickLogin()">
      로그인
    </button>
    <button
        type="button"
        class="btn btn-danger"
        @click="onClickRegister()"
    >
      회원가입
    </button>
  </div>
</template>

<script setup>
import router from "@/router";

const onClickLogin = function () {
  router.push("/login");
};
const onClickRegister = function () {
  router.push("/register");
};
</script>

<style scoped>

</style>