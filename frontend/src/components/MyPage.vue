<template>
  <div>
    <div class="hero overlay">
      <div class="img-bg rellax">
        <img src="images/hero_2.jpg" alt="Image" class="img-fluid" />
      </div>
      <div class="container">
        <div class="row align-items-center justify-content-start">
          <div class="col-lg-6 mx-auto text-center">
            <h1 class="heading" data-aos="fade-up">Mypage</h1>
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
                    disabled
                    type="text"
                    class="form-control"
                    placeholder="Your Name"
                    v-model="userName"
                  />
                </div>
                <div class="col-12 mb-3">
                  <input
                    disabled
                    type="email"
                    class="form-control"
                    placeholder="Your Email"
                    v-model="userEmail"
                  />
                </div>
                <div class="col-12 mb-3">
                  <input type="password" class="form-control" placeholder="Password" />
                </div>
                <div class="col-12 mb-3">
                  <input type="password" class="form-control" placeholder="Confirm Password" />
                </div>
                <div class="col-12 d-flex justify-content-center">
                  <span class="m-1">
                    <button type="button" class="btn btn-primary">Edit</button>
                  </span>
                  <span class="m-1">
                    <button type="button" class="btn btn-primary">Delete Account</button>
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
      userName: sessionStorage.getItem("userName"),
      userEmail: sessionStorage.getItem("userEmail"),
    };
  },
  created() {
    if (!sessionStorage.getItem("userName")) {
      this.$router.push("/login");
    }
  },
};
</script>

<style></style>
