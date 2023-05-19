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
                            {{ userName }}님 여행지를 검색해보세요
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="mx-3">
            <search-trip-form></search-trip-form>
            <kakao-map></kakao-map>
        </div>
    </div>
</template>

<script>
import KakaoMap from "./ui/KakaoMap.vue";
import SearchTripForm from "./ui/SearchTripForm.vue";

export default {
    components: { KakaoMap, SearchTripForm },
    data() {
        return {
            message: "",
            userName: sessionStorage.getItem("userName"),
        };
    },
    created() {},
    methods: {},
};
</script>

<style scoped></style>
