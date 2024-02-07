<script setup>
import { RouterLink, RouterView } from "vue-router";
import Header from "./components/molecules/common/Header.vue";
import Footer from "./components/molecules/common/Footer.vue";
import axios from "axios";
import { reactive } from "vue";
import { ref } from "vue";
import router from "./router";
</script>

<template>
  <Header />
  <router-view></router-view>
  <Footer />
</template>

<style scoped></style>
