// import 순서에 따라 덮어쓰기 되더라
import "bootstrap";
import "./assets/template/bacchusCss.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "./assets/template/templatemo.css";
import "./assets/template/fontawesome.min.css";
import "./assets/template/dongil.css";

import { createApp } from "vue";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import App from "./App.vue";
import router from "./router/index.js";
import VueKakaoSdk from 'vue-kakao-sdk'

const app = createApp(App);

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);
app.use(pinia);
app.use(router);

app.mount("#app");

const apiKey = 	'c18a5aa9ce00eafcb9281dc0ea9ee10c'
app.use(VueKakaoSdk, {apiKey})