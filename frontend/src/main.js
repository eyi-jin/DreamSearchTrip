import AOS from "aos";
import Vue from "vue";
import App from "./App.vue";
import router from "@/routers/routers";
Vue.config.productionTip = false;

new Vue({
  created() {
    AOS.init();
  },
  render: (h) => h(App),
  router,
}).$mount("#app");
