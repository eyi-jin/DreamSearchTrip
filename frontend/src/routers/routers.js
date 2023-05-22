import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

import MainPage from "@/components/MainPage.vue";
import SearchPage from "@/components/SearchPage.vue";
import MyPage from "@/components/MyPage";
import LoginPage from "@/components/LoginPage.vue";
import HotplacePage from "@/components/HotplacePage.vue";
import BoardPage from "@/components/BoardPage.vue";
import SignupPage from "@/components/SignupPage.vue";
import WeatherPage from "@/components/WeatherPage.vue";

export default new VueRouter({
  routes: [
    {
      name: "MainPage",
      path: "/",
      component: MainPage,
    },
    {
      name: "BoardPage",
      path: "/board",
      component: BoardPage,
    },
    {
      name: "LoginPage",
      path: "/login",
      component: LoginPage,
    },
    {
      name: "HotplacePage",
      path: "/hotplace",
      component: HotplacePage,
    },
    {
      name: "MyPage",
      path: "/my",
      component: MyPage,
    },
    {
      name: "SearchPage",
      path: "/search",
      component: SearchPage,
    },
    {
      name: "SignupPage",
      path: "/signup",
      component: SignupPage,
    },
    {
      name: "WeatherPage",
      path: "/weather",
      component: WeatherPage,
    },
  ],
});
