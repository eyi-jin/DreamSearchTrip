<template>
  <div>
    <div class="section">
      <div class="container">
        <div class="row align-items-stretch">
          <div
            v-for="(board, index) in list"
            :key="index"
            class="col-6 col-sm-6 col-md-6 col-lg-3 mb-4"
            data-aos="fade-up"
            data-aos-delay="100"
          >
            <div class="media-entry">
              <div class="bg-white m-body">
                <span class="date">date : {{ ListDate(board.regDt.date) }}</span>
                <h3>
                  {{ board.title }}
                </h3>
                <p v-html="board.content"></p>
                <p>
                  writer : {{ board.userName }}<br />
                  views : {{ board.readCount }}<br />
                </p>
                <a
                  class="more d-flex align-items-center float-start"
                  @click="boardDetail(board.boardId)"
                >
                  <span class="label">Read More</span>
                  <span class="arrow"><span class="icon-keyboard_arrow_right"></span></span>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- <pagination-ui></pagination-ui> -->
  </div>
</template>

<script>
import util from "@/components/common/util.js";
// import PaginationUi from "./PaginationUi.vue";
export default {
  // components: { PaginationUi },
  props: ["list"],
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    ListDate: function (data) {
      return util.makeDateStr(data.year, data.month, data.day, ".");
    },
    boardDetail(boardId) {
      this.$emit("call-parent-detail", boardId);
    },
  },
};
</script>

<style scoped></style>
