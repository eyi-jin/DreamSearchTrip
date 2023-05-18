<template>
  <div>
    <nav-bar v-bind:isLogin="isLogin" v-bind:userInfo="userInfo"></nav-bar>
    <router-view v-on:call-parent-loginSuccess="loginSuccess"></router-view>
    <footer-ui></footer-ui>
  </div>
</template>

<script>
import NavBar from "./components/layout/NavBar.vue";
import FooterUi from "./components/layout/FooterUi.vue";
// import Router from "vue-router";
// import Vue from "vue";
// Vue.use(Router);
export default {
  components: { NavBar, FooterUi },
  data() {
    return {
      isLogin: false,
      userInfo: {},
    };
  },
  methods: {
    loginSuccess(userInfo) {
      this.isLogin = true;
      this.userInfo = userInfo;
    },
    loginCheck() {
      if (sessionStorage.getItem("userName")) {
        this.isLogin = true;
      } else {
        this.isLogin = false;
      }
    },
  },
  created() {
    this.loginCheck();
  },
  watch: {
    $route(to, form) {
      if (to.path !== form.patch && to.path == "/login") {
        this.isLogin = false;
      }
    },
  },
};
</script>

<style scoped></style>
