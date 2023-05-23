<template>
  <div class="container d-flex justify-content-center">
    <nav class="mt-5" aria-label="Page navigation example" data-aos="fade-up" data-aos-delay="100">
      <ul class="custom-pagination pagination">
        <li v-if="prev" class="page-item">
          <a class="page-link" aria-label="Previous" @click="paginationChanged(startPageIndex - 1)">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li
          v-for="index in endPageIndex - startPageIndex + 1"
          :key="index"
          v-bind:class="{
            active: startPageIndex + index - 1 == currentPageIndex,
          }"
          class="page-item"
        >
          <a @click="paginationChanged(startPageIndex + index - 1)" class="page-link">{{
            startPageIndex + index - 1
          }}</a>
        </li>
        <li v-if="next" class="page-item">
          <a class="page-link" aria-label="Next" @click="paginationChanged(endPageIndex + 1)">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  props: ["listRowCount", "pageLinkCount", "currentPageIndex", "totalListItemCount"],
  computed: {
    pageCount: function () {
      return Math.ceil(this.totalListItemCount / this.listRowCount);
    },
    startPageIndex: function () {
      if (this.currentPageIndex % this.pageLinkCount == 0) {
        return this.currentPageIndex - this.pageLinkCount + 1;
      } else {
        return Math.floor(this.currentPageIndex / this.pageLinkCount) * this.pageLinkCount + 1;
      }
    },

    endPageIndex: function () {
      let ret = 0;
      if (this.currentPageIndex % this.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret = this.currentPageIndex;
      } else {
        ret =
          Math.floor(this.currentPageIndex / this.pageLinkCount) * this.pageLinkCount +
          this.pageLinkCount;
      }
      return ret > this.pageCount ? this.pageCount : ret;
    },

    prev: function () {
      if (this.currentPageIndex <= this.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },

    next: function () {
      if (
        Math.floor(this.pageCount / this.pageLinkCount) * this.pageLinkCount <
        this.currentPageIndex
      ) {
        return false;
      } else {
        return true;
      }
    },
  },

  methods: {
    paginationChanged(pageIndex) {
      this.$emit("call-parent-move-page", pageIndex);
    },
  },
};
</script>

<style scoped></style>
