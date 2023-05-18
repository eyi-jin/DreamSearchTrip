<template>
  <div class="d-flex m-3 justify-content-center">
    <form class="d-flex my-3">
      <select id="area1List" class="form-select me-3">
        <option value="">시도를 선택하세요</option>
      </select>
      <select id="area2List" class="form-select me-3">
        <option value="">구군을 선택하세요</option>
      </select>

      <select id="cat1List" class="form-select me-3">
        <option value="">대분류 선택하세요</option>
      </select>
      <select id="cat2List" class="form-select me-3">
        <option value="">중분류 선택하세요</option>
      </select>
      <select id="cat3List" class="form-select me-3">
        <option value="">소분류 선택하세요</option>
      </select>
    </form>
    <button id="btnSearch" type="button" class="btn btn-primary mt-3 mb-3">검색</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      numOfRows: 10,
      pageNo: 1,
      areaCode: "",
      sigunguCode: "",
      cat1: "",
      cat2: "",
      cat3: "",
    };
  },
  async mounted() {
    await this.getArea1List();
    await this.getCat1List();

    let $this = this;
    document.querySelector("#btnSearch").onclick = function () {
      $this.sigunguCode = document.querySelector("#area2List").value;
      $this.cat3 = document.querySelector("#cat3List").value;
      $this.getList();
    };
  },
  methods: {
    getList: async function () {
      let url = `trip/list`;
      let urlParams =
        "?numOfRows=" +
        this.numOfRows +
        "&pageNo=" +
        this.pageNo +
        "&areaCode=" +
        this.areaCode +
        "&sigunguCode=" +
        this.sigunguCode +
        "&cat1=" +
        this.cat1 +
        "&cat2=" +
        this.cat2 +
        "&cat3=" +
        this.cat3;

      let response = await fetch(url + urlParams);
      let data = await response.json();
      // this.makeCard(data);
      console.log(data);

      let itemList = data.response.body.items.item;
      console.log(itemList);
    },

    getArea1List: async function () {
      let $this = this;
      let url = `trip/areaCode`;
      let urlParams = "?numOfRows=" + this.numOfRows + "&pageNo= " + this.pageNo;

      let response = await fetch(url + urlParams);
      let data = await response.json();
      console.log(data);

      let codeList = data.response.body.items.item;
      console.log(codeList);

      let listHtml = `<option value=''>시도를 선택하세요</option>`;
      codeList.forEach((el) => {
        listHtml += "<option value=" + el.code + ">" + el.name + "</option>";
      });
      document.querySelector("#area1List").innerHTML = listHtml;

      // change event handler
      document.querySelector("#area1List").onchange = function (e) {
        $this.areaCode = e.target.value;
        $this.getArea2List();
      };
    },

    getArea2List: async function () {
      let url = `trip/areaCode`;
      let urlParams =
        "?numOfRows=" + this.numOfRows + "&pageNo=" + this.pageNo + "&areaCode=" + this.areaCode;

      let response = await fetch(url + urlParams);
      let data = await response.json();
      console.log(data);

      let codeList = data.response.body.items.item;
      console.log(codeList);

      let listHtml = `<option value=''>구군을 선택하세요</option>`;
      codeList.forEach((el) => {
        listHtml += "<option value=" + el.code + ">" + el.name + "</option>";
      });
      document.querySelector("#area2List").innerHTML = listHtml;
    },

    getCat1List: async function () {
      let $this = this;
      let url = `trip/categoryCode`;
      let urlParams = "?numOfRows=" + this.numOfRows + "&pageNo=" + this.pageNo;

      let response = await fetch(url + urlParams);
      let data = await response.json();
      console.log(data);

      let codeList = data.response.body.items.item;
      console.log(codeList);

      let listHtml = `<option value=''>대분류 선택하세요</option>`;
      codeList.forEach((el) => {
        listHtml += "<option value=" + el.code + ">" + el.name + "</option>";
      });
      document.querySelector("#cat1List").innerHTML = listHtml;

      // change event handler
      document.querySelector("#cat1List").onchange = function (e) {
        $this.cat1 = e.target.value;
        $this.getCat2List();
      };
    },

    getCat2List: async function () {
      let $this = this;
      let url = `trip/categoryCode`;
      let urlParams =
        "?numOfRows=" + this.numOfRows + "&pageNo=" + this.pageNo + "&cat1=" + this.cat1;

      let response = await fetch(url + urlParams);
      let data = await response.json();
      console.log(data);

      let codeList = data.response.body.items.item;
      console.log(codeList);

      let listHtml = `<option value=''>중분류 선택하세요</option>`;
      codeList.forEach((el) => {
        listHtml += "<option value=" + el.code + ">" + el.name + "</option>";
      });
      document.querySelector("#cat2List").innerHTML = listHtml;

      // change event handler
      document.querySelector("#cat2List").onchange = function (e) {
        $this.cat2 = e.target.value;
        $this.getCat3List();
      };
    },

    getCat3List: async function () {
      let url = `trip/categoryCode`;
      let urlParams =
        "?numOfRows=" +
        this.numOfRows +
        "&pageNo=" +
        this.pageNo +
        "&cat1=" +
        this.cat1 +
        "&cat2=" +
        this.cat2;

      let response = await fetch(url + urlParams);
      let data = await response.json();
      console.log(data);

      let codeList = data.response.body.items.item;
      console.log(codeList);

      let listHtml = `<option value=''>소분류 선택하세요</option>`;
      codeList.forEach((el) => {
        listHtml += "<option value=" + el.code + ">" + el.name + "</option>";
      });
      document.querySelector("#cat3List").innerHTML = listHtml;
    },
  },
};
</script>

<style scoped></style>
