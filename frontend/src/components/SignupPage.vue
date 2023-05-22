<template>
  <div>
    <div class="hero overlay">
      <div class="img-bg rellax">
        <img src="images/hero_2.jpg" alt="Image" class="img-fluid" />
      </div>
      <div class="container">
        <div class="row align-items-center justify-content-start">
          <div class="col-lg-6 mx-auto text-center">
            <h1 class="heading" data-aos="fade-up">Sign up</h1>
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
                  <div class="d-flex">
                    <input
                      type="email"
                      class="form-control"
                      placeholder="Your Email"
                      v-model="userEmail"
                    />
                    <span class="ms-3">
                      <button type="button" class="btn btn-primary" @click="confirmEmail">
                        Check
                      </button>
                    </span>
                  </div>
                  <div v-if="checkEmail || emailConfirm === 3">email address is invalid</div>
                  <div v-if="emailConfirm === 1">email address already in use</div>
                  <div v-if="emailConfirm === 2">email address is available</div>
                </div>
                <div class="col-12 mb-3">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Your Name"
                    v-model="userName"
                  />
                </div>
                <div class="col-12 mb-3">
                  <input
                    type="password"
                    class="form-control"
                    placeholder="Password"
                    v-model="userPassword"
                  />
                  <div v-if="pwdTest">
                    Please enter a password of 8 or more in a combination of letters and numbers.
                  </div>
                </div>

                <div class="col-12 mb-3">
                  <input
                    type="password"
                    class="form-control"
                    placeholder="Confirm Password"
                    v-model="userPasswordCheck"
                  />
                  <div v-if="checkPwd">Passwords do not match</div>
                </div>
                <div class="col-12 d-flex justify-content-center">
                  <span class="m-1">
                    <button @click="loginMove" type="button" class="btn btn-primary">Login</button>
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
export default {
  data() {
    return {
      userName: "",
      userEmail: "",
      userPassword: "",
      userPasswordCheck: "",
      checkEmail: false,
      checkPwd: false,
      pwdTest: false,
      emailConfirm: 0,
    };
  },
  methods: {
    loginMove() {
      this.$router.push("/login");
    },
    emailCheck() {
      const reg_email = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
      if (!this.userEmail || reg_email.test(this.userEmail)) {
        this.checkEmail = false;
      } else {
        this.checkEmail = true;
      }
    },
    checkPassword() {
      if (this.userPassword === this.userPasswordCheck) {
        return true;
      } else {
        return false;
      }
    },
    passwordTest() {
      const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
      if (!this.userPassword || passwordRegex.test(this.userPassword)) {
        this.pwdTest = false;
      } else {
        this.pwdTest = true;
      }
    },
    confirmEmail() {
      // 0일때는 누르지 않았을 때
      // 1일때는 이미 사용중인 이메일
      // 2일때는 사용가능한 이메일
      // 3일때는 이메일 공백이거나 형식이 잘 못 됨
      // 아이디 중복체크하기
      if (this.checkEmail === false) {
        this.emailConfirm = 2;
      } else {
        this.emailConfirm = 3;
      }
    },
  },
  watch: {
    userEmail() {
      this.emailCheck();
    },
    userPassword() {
      this.passwordTest();
    },
    userPasswordCheck() {
      if (this.userPassword !== "" && !this.checkPassword()) {
        this.checkPwd = true;
      } else {
        this.checkPwd = false;
      }
    },
  },
};
</script>

<style></style>
