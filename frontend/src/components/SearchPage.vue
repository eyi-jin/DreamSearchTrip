<template>
  <div>
    <div class="hero overlay">
      <div class="img-bg rellax">
        <img src="images/hero_2.jpg" alt="Image" class="img-fluid" />
      </div>
      <div class="container">
        <div class="row align-items-center justify-content-start">
          <div class="col-lg-6 mx-auto text-center">
            <h1 class="heading" data-aos="fade-up">Search</h1>
            <p class="mb-4" data-aos="fade-up">
              A small river named Duden flows by their place and supplies it with the necessary
              regelialia. It is a paradisematic country, in which roasted parts of sentences fly
              into your mouth.
            </p>

            <div data-aos="fade-up">
              <a
                href="https://www.youtube.com/watch?v=5n-e6lOhVq0"
                class="play-button glightbox3 w-100 text-center"
              >
                <div class="d-inline-flex align-items-center">
                  <span class="icon-button me-3">
                    <span class="icon-play"></span>
                  </span>
                  <span class="caption">Watch Video</span>
                </div>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <search-trip-form></search-trip-form>
      <kakao-map></kakao-map>
      <card-view></card-view>
    </div>
  </div>
</template>

<script>
import CardView from "./ui/CardView.vue";
import KakaoMap from "./ui/KakaoMap.vue";
import SearchTripForm from "./ui/SearchTripForm.vue";

export default {
  components: { CardView, KakaoMap, SearchTripForm },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
