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
              This page is where we express ourselves and build our inner world. It contains our
              dreams and passion and becomes our own tranquil resting place. This is our own little
              world and dream.
            </p>
          </div>
        </div>
      </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row d-flex justify-content-center">
          <div class="col-10" data-aos="fade-up" data-aos-delay="200">
            <router-view></router-view>
          </div>

          <!-- 사이드바 시작 -->
          <div class="col-2 sidebar">
            <div class="sidebar-box">
              <div class="categories">
                <h3>Categories</h3>
                <li>
                  <router-link to="/my"> My page </router-link>
                </li>
                <li>
                  <router-link to="/my/trip"> My Trip </router-link>
                </li>
                <li>
                  <router-link to="/my/todo"> My Todo </router-link>
                </li>
              </div>
            </div>
          </div>
          <!-- 사이드바 끝 -->
        </div>
      </div>
    </div>

    <!-- /.untree_co-section -->
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  created() {
    if (!sessionStorage.getItem("userName")) {
      this.$router.push("/login");
    }
  },
};
</script>

<style></style>
