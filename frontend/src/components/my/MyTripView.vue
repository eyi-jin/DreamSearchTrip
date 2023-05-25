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
                                @error="replaceImg"
                                alt="Image"
                                class="img-fluid"
                                style="height: 200px"
                            />
                        </a>
                        <div class="bg-white m-body">
                            <span class="date">
                                {{ fav.favDate | yyyyMMdd }}
                            </span>
                            <button
                                class="position-absolute top-50 end-0 m-3 px-1 btn-sm btn-close"
                                @click="myTripDelete(fav.favId)"
                            ></button>
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
        replaceImg(e) {
            e.target.src = require("@/assets/img_" +
                Math.floor(Math.random() * 3 + 1) +
                ".jpg");
        },
    },
    filters: {
        // filter로 쓸 filter ID 지정
        yyyyMMdd: function (value) {
            // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
            if (value == undefined) return "";

            let stringDate = [];
            stringDate.push(value.substr(0, 4));
            stringDate.push(value.substr(4, 2));
            stringDate.push(value.substr(6, 2));

            // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
            var js_date = new Date(stringDate).toLocaleDateString("en-us", {
                year: "numeric",
                month: "short",
                day: "numeric",
            });
            return js_date;
        },
    },
};
</script>

<style scoped></style>
