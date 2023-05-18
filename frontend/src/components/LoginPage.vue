<template>
  <div>
    <div class="hero overlay">
      <div class="img-bg rellax">
        <img src="images/hero_2.jpg" alt="Image" class="img-fluid" />
      </div>
      <div class="container">
        <div class="row align-items-center justify-content-start">
          <div class="col-lg-6 mx-auto text-center">
            <h1 class="heading" data-aos="fade-up">Login</h1>
            <p data-aos="fade-up">
              A small river named Duden flows by their place and supplies it with the necessary
              regelialia. It is a paradisematic country, in which roasted parts of sentences fly
              into your mouth.
            </p>
          </div>
        </div>
      </div>
    </div>

    <div class="section">
      <div class="container">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-8" data-aos="fade-up" data-aos-delay="200">
            <form action="#">
              <div class="row">
                <div class="col-12 mb-3">
                  <input
                    v-model="userEmail"
                    type="email"
                    class="form-control"
                    placeholder="Your Email"
                  />
                </div>
                <div class="col-12 mb-3">
                  <input
                    v-model="userPassword"
                    type="password"
                    class="form-control"
                    placeholder="Password"
                  />
                </div>
                <div class="col-12 d-flex justify-content-center">
                  <span class="m-1">
                    <button @click="login" type="button" class="btn btn-primary">Login</button>
                  </span>
                  <span class="m-1">
                    <button type="button" class="btn btn-primary">Sign Up</button>
                  </span>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- /.untree_co-section -->
  </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);
import http from "@/components/common/axios.js";
export default {
  data() {
    return {
      userEmail: "mu@ya.ho",
      userPassword: "qwe1234!",
    };
  },
  methods: {
    // axios 비동기 통신
    async login() {
      try {
        let response = await http.post("/login", {
          userEmail: this.userEmail,
          userPassword: this.userPassword,
        });
        let { data } = response;
        console.log(data);
        if (data.result == "success") {
          sessionStorage.setItem("userName", data.userName);
          sessionStorage.setItem("userEmail", data.userEmail);
          this.$emit("call-parent-loginSuccess", {
            userName: data.userName,
            userProfileImageUrl: data.userProfileImageUrl,
          });
          this.$router.push("/");
        }
      } catch (error) {
        console.error(error);
        this.$alertify.error("로그인 과정에서 오류가 발생했습니다.");
      }
    },
    logout() {
      sessionStorage.removeItem("userEmail");
      sessionStorage.removeItem("userName");
    },
  },
  created() {
    this.logout();
  },
};
</script>

<style></style>
