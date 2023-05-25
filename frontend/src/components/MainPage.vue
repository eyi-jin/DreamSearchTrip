<template>
  <div>
    <div class="hero overlay">
      <div class="img-bg rellax">
        <img src="images/hero_1.jpg" alt="Image" class="img-fluid" />
      </div>

      <div class="container">
        <div class="row align-items-center justify-content-start">
          <div class="col-lg-5">
            <h1 class="heading" data-aos="fade-up">It's a Big World Out There, Go Explore</h1>
            <p class="mb-5" data-aos="fade-up">
              The world is so wide and so beautiful that everywhere I go is full of surprises.
              Mountains and seas, cities and villages, culture and the richness of history are all
              waiting for us. When we open the door of travel, we can meet new experiences,
              adventures, love and growth. We are all travelers, and our stories fill and enrich the
              world.
            </p>

            <!-- <div data-aos="fade-up">
              <a
                href="https://www.youtube.com/watch?v=5n-e6lOhVq0"
                class="play-button align-items-center d-flex glightbox3"
              >
                <span class="icon-button me-3">
                  <span class="icon-play"></span>
                </span>
                <span class="caption">Watch Video</span>
              </a>
            </div> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    if (!sessionStorage.getItem("userName")) {
      this.$router.push("/login");
    }
  },
};
</script>

<style></style>
