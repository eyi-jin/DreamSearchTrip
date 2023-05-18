<template>
  <div class="container">
    <nav class="mt-5" aria-label="Page navigation example" data-aos="fade-up" data-aos-delay="100">
      <ul class="custom-pagination pagination">
        <li class="page-item prev">
          <a class="page-link" href="#">Previous</a>
        </li>
        <li class="page-item active"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item next"><a class="page-link" href="#">Next</a></li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  components: {},
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
