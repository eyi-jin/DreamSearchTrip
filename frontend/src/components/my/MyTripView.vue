<template>
  <div class="section">
    <div class="container">
      <div class="row">
        <div class="col-12" data-aos="fade-up" data-aos-delay="0">
          <h2 class="heading mb-5">My Trips</h2>
        </div>
      </div>
      <div class="row align-items-stretch">
        <div
          v-for="(fav, index) in list"
          :key="index"
          class="col-6 col-sm-6 col-md-6 col-lg-3"
          data-aos="fade-up"
          data-aos-delay="100"
        >
          <div class="media-entry">
            <a href="#">
              <img
                :src="fav.favImgUrl"
                alt="Image"
                class="img-fluid"
                style="height: 200px"
              />
            </a>
            <div class="bg-white m-body">
              <span class="date">May 14, 2020</span>
              <button
                class="position-absolute top-50 end-0 m-3 px-1"
                @click="myTripDelete(fav.favId)"
              >
                X
              </button>
              <h3>
                <a href="#">{{ fav.favName }}</a>
              </h3>
              <p><b>주소:</b> {{ fav.favAddr }}</p>
              <p><b>Tel:</b> {{ fav.favTel }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/components/common/axios";

export default {
  data() {
    return { list: [] };
  },
  mounted() {
    this.getFavList();
  },
  methods: {
    async getFavList() {
      let url = `/fav/favView`;

      let response = await http.get(url);
      let { data } = response;
      console.log(data);
      this.list = data;
    },
    async myTripDelete(favId) {
      let url = `/fav/favDelete`;
      let urlParams = `/` + favId;

      let response = await http.delete(url + urlParams);
      let { data } = response;
      console.log(data);

      this.getFavList();
    },
  },
};
</script>

<style scoped></style>
