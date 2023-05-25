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
              Hello {{ userName }}! Let's search for travel destinations
            </p>
          </div>
        </div>
      </div>
    </div>

    <div class="mx-3">
      <search-trip-form></search-trip-form>
    </div>
  </div>
</template>

<script>
import SearchTripForm from "./ui/SearchTripForm.vue";

export default {
  components: { SearchTripForm },
  data() {
    return {
      message: "",
      userName: sessionStorage.getItem("userName"),
    };
  },
  created() {
    if (!sessionStorage.getItem("userName")) {
      this.$router.push("/login");
    }
  },
  methods: {},
};
</script>

<style scoped></style>
