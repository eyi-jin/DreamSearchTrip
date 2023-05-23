<template>
  <div>
    <div class="hero overlay">
      <div class="img-bg rellax">
        <img src="images/hero_2.jpg" alt="Image" class="img-fluid" />
      </div>
      <div class="container">
        <div class="row align-items-center justify-content-start">
          <div class="col-lg-5">
            <h1 class="heading" data-aos="fade-up">Board</h1>
            <p class="mb-5" data-aos="fade-up">
              A small river named Duden flows by their place and supplies it with the necessary
              regelialia. It is a paradisematic country, in which roasted parts of sentences fly
              into your mouth.
            </p>
          </div>
        </div>
      </div>
    </div>
    <!-- board -->
    <div class="container mt-3">
      <div class="input-group mb-3">
        <input
          id="inputSearchWord"
          type="text"
          class="form-control"
          placeholder="Search"
          v-model="searchWord"
        />
        <button @click="boardList" class="btn btn-primary" type="button">Search</button>
      </div>
      <card-view v-bind:list="list" @call-parent-detail="boardDetail"></card-view>
      <!-- <table class="table table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>title</th>
            <th>writer</th>
            <th>date</th>
            <th>views</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(board, index) in list" :key="index" @click="boardDetail(board.boardId)">
            <td>{{ board.boardId }}</td>
            <td>{{ board.title }}</td>
            <td>{{ board.userName }}</td>
            <td>{{ ListDate(board.regDt.date) }}</td>
            <td>{{ board.readCount }}</td>
          </tr>
        </tbody>
      </table> -->
      <button class="btn btn-sm btn-primary ms-2" @click="showInsertModal">글쓰기</button>

      <pagination-ui
        v-bind:listRowCount="listRowCount"
        v-bind:pageLinkCount="pageLinkCount"
        v-bind:currentPageIndex="currentPageIndex"
        v-bind:totalListItemCount="totalListItemCount"
        v-on:call-parent-move-page="movePage"
      ></pagination-ui>

      <insert-modal v-on:call-parent-insert="closeAfterInsert"></insert-modal>
      <detail-modal
        v-bind:board="board"
        v-on:call-parent-change-to-update="changeToUpdate"
        v-on:call-parent-change-to-delete="changeToDelete"
      ></detail-modal>
      <update-modal
        v-bind:board="board"
        v-on:call-parent-change-to-update="closeAfterUpdate"
      ></update-modal>
    </div>
  </div>
</template>

<script>
import http from "@/components/common/axios";
import util from "@/components/common/util.js";

import DetailModal from "./modals/DetailModal.vue";
import InsertModal from "./modals/InsertModal.vue";
import UpdateModal from "@/components/modals/UpdateModal.vue";
import PaginationUi from "@/components/ui/PaginationUi.vue";
import CardView from "@/components/ui/CardView.vue";
import { Modal } from "bootstrap";
export default {
  components: { InsertModal, DetailModal, UpdateModal, PaginationUi, CardView },
  data() {
    return {
      // modal
      insertModal: null,
      detailModal: null,
      updateModal: null,
      // list
      list: [],
      limit: 12,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,
      totalListItemCount: 0,

      // detail
      board: {
        boardId: 0,
        title: "",
        content: "",
        userName: "",
        regDate: "",
        regTime: "",
        readCount: 0,
        fileList: [],
        sameUser: false,
      },
    };
  },
  methods: {
    async boardList() {
      let params = {
        limit: this.limit,
        offset: this.offset,
        searchWord: this.searchWord,
      };
      let response = await http.get("/boards", { params });
      let { data } = response;
      console.log(data);
      if (data.result == "login") {
        this.$router.push("/login");
      } else {
        this.list = data.list;
        this.totalListItemCount = data.count;
      }
    },
    ListDate: function (data) {
      return util.makeDateStr(data.year, data.month, data.day, ".");
    },
    movePage(pageIndex) {
      this.offset = (pageIndex - 1) * this.listRowCount;
      this.currentPageIndex = pageIndex;
      this.boardList();
    },
    async boardDetail(boardId) {
      let response = await http.get("/boards/" + boardId);
      let { data } = response;
      console.log("boardDetail");
      console.log(data);
      if (data.result == "login") {
        this.$router.push("/login");
      } else {
        let { regDt } = data.dto;
        let boardNew = {
          regDate: util.makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, "."),
          regTime: util.makeTimeStr(regDt.time.hour, regDt.time.minute, regDt.time.second, ":"),
          ...data.dto,
        };
        this.board = boardNew;
        this.detailModal.show();
        this.boardList();
      }
    },
    async boardDelete() {
      try {
        let response = await http.delete("/boards/" + this.board.boardId);
        let { data } = response;
        console.log(data);

        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          this.$alertify.success("글이 삭제되었습니다.");
          this.boardList();
        }
      } catch (error) {
        console.error(error);
      }
    },
    showInsertModal() {
      this.insertModal.show();
    },
    closeAfterInsert() {
      this.insertModal.hide();
      this.boardList();
    },
    changeToUpdate() {
      this.detailModal.hide();
      this.updateModal.show();
    },
    closeAfterUpdate() {
      this.updateModal.hide();
      this.boardList();
    },
    changeToDelete() {
      this.detailModal.hide();

      var $this = this; // 바깥쪽 this를 쓰기 위해서 변수에 저장
      this.$alertify.confirm(
        "이 글을 삭제하시겠습니까?",
        function () {
          $this.boardDelete();
        },
        function () {
          console.log("cancel");
        }
      );

      // this.boardDelete();
      // this.boardList();
    },
  },
  created() {
    if (sessionStorage.getItem("userName")) {
      console.log("here");

      this.boardList();
    } else {
      this.$router.push("/login");
    }
  },
  mounted() {
    // bootstrap 5 show modal
    this.insertModal = new Modal(document.querySelector("#insertModal"));
    this.detailModal = new Modal(document.querySelector("#detailModal"));
    this.updateModal = new Modal(document.querySelector("#updateModal"));
  },
};
</script>

<style scoped></style>
