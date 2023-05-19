<template>
    <div class="d-flex">
        <!-- 사이드바 -->
        <div class="col-sm-2 sidebar" id="placesList">
            <!-- <div class="sidebar-box">
                <img
                    src="images/person_1.jpg"
                    alt="Free Website Template by Free-Template.co"
                    class="img-fluid mb-4 w-50 rounded-circle"
                />
                <h3 class="text-black">About The Author</h3>
                <p>
                    Even the all-powerful Pointing has no control about the blind texts it is an
                    almost unorthographic life One day however a small line of blind text by the
                    name of Lorem Ipsum decided to leave for the far World of Grammar.
                </p>
                <p><a href="#" class="btn btn-primary btn-md text-white">Read More</a></p>
            </div> -->
            <div class="sidebar-box">
                <h3 class="text-black">Paragraph</h3>
                <p>
                    When she reached the first hills of the Italic Mountains, she had a last view
                    back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet
                    Village and the subline of her own road, the Line Lane. Pityful a rethoric
                    question ran over her cheek, then she continued her way.
                </p>
            </div>

            <div class="sidebar-box">
                <h3 class="text-black">Paragraph</h3>
                <p>
                    When she reached the first hills of the Italic Mountains, she had a last view
                    back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet
                    Village and the subline of her own road, the Line Lane. Pityful a rethoric
                    question ran over her cheek, then she continued her way.
                </p>
            </div>
        </div>
        <div class="col-sm-10 sidebar" id="map"></div>
    </div>
</template>

<script>
export default {
    name: "KakaoMap",
    components: {},
    data() {
        return {
            map: null,
            positions: [],
            markers: [],
        };
    },
    props: {
        trips: [],
    },
    watch: {
        trips() {
            console.log("여행지", this.trips);
            this.positions = [];
            this.trips.forEach((trip) => {
                let obj = {};
                obj.title = trip.title;
                obj.addr = trip.addr1;
                obj.img = trip.firstimage;
                obj.tel = trip.tel;
                obj.latlng = new kakao.maps.LatLng(trip.mapy, trip.mapx);

                this.positions.push(obj);
            });
            this.loadMarker();
        },
    },
    created() {},
    mounted() {
        // api 스크립트 소스 불러오기 및 지도 출력
        if (window.kakao && window.kakao.maps) {
            this.loadMap();
        } else {
            this.loadScript();
        }
    },
    methods: {
        // api 불러오기
        loadScript() {
            const script = document.createElement("script");
            script.src =
                "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
                process.env.VUE_APP_KAKAO_MAP_API_KEY +
                "&autoload=false";
            /* global kakao */
            script.onload = () => window.kakao.maps.load(this.loadMap);

            document.head.appendChild(script);
        },
        // 맵 출력하기
        loadMap() {
            const container = document.getElementById("map");
            const options = {
                center: new window.kakao.maps.LatLng(33.450701, 126.570667),
                level: 3,
            };

            this.map = new window.kakao.maps.Map(container, options);
            //   this.loadMarker();
        },
        getListItem(places) {
            var el = document.createElement("li"),
                itemStr =
                    '<span class="markerbg marker_' +
                    '"></span>' +
                    '<div class="info">' +
                    "   <h5>" +
                    places.title +
                    "</h5>";

            if (places.addr) {
                itemStr += "    <span>" + places.addr + "</span> ";
            } else {
                itemStr += "    <span>" + places.addr + "</span>";
            }

            itemStr += '  <span class="tel">' + places.tel + "</span>" + "</div>";

            el.innerHTML = itemStr;
            el.className = "item";

            return el;
        },
        // 지정한 위치에 마커 및 장소 불러오기
        loadMarker() {
            var listEl = document.getElementById("placesList");
            var fragment = document.createDocumentFragment();

            // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
            this.deleteMarker();

            // 검색 결과 목록에 추가된 항목들을 제거합니다
            this.removeAllChildNods(listEl);

            // 마커를 생성합니다
            this.markers = [];
            this.positions.forEach((position) => {
                // information window 생성
                const infowindow = new kakao.maps.InfoWindow({
                    removable: true,
                    content: `
              <div class="card">
                <img src="${position.img}" class="card-img-top" style="height: 10rem" alt="...">
                <div class="card-body">
                  <h5 class="card-title fw-bold">${position.title}</h5>
                  <p class="card-text"> 주소 : ${position.addr}</p>
                  <p class="card-text"> 전화번호 : ${position.tel}</p>
                </div>
              </div>
                    `,
                });
                const marker = new kakao.maps.Marker({
                    map: this.map, // 마커를 표시할 지도
                    position: position.latlng, // 마커를 표시할 위치
                    title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    //   image: markerImage, // 마커의 이미지
                });
                // 이벤트 등록
                //  kakao.maps.event.addListener(marker, "click", () => {infowindow.open(this.map, marker);});
                kakao.maps.event.addListener(marker, "mouseover", () => {
                    infowindow.open(this.map, marker);
                });
                kakao.maps.event.addListener(marker, "mouseout", () => {
                    infowindow.close(this.map, marker);
                });

                this.markers.push(marker);

                //=====================사이드 바에 관광지 리스트 만들기===================

                let obj = {
                    title: position.title,
                    addr: position.addr,
                    tel: position.tel,
                };
                const itemEl = this.getListItem(obj);

                // 이벤트 등록
                itemEl.onmouseover = () => {
                    infowindow.open(this.map, marker);
                };

                itemEl.onmouseout = () => {
                    infowindow.close(this.map, marker);
                };

                fragment.appendChild(itemEl);
            });

            // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
            listEl.appendChild(fragment);

            console.log("마커수 :" + this.markers.length);

            // 4. 지도를 이동시켜주기
            // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
            const bounds = this.positions.reduce(
                (bounds, position) => bounds.extend(position.latlng),
                new kakao.maps.LatLngBounds()
            );

            this.map.setBounds(bounds);
        },
        deleteMarker() {
            console.log("마커 지우기", this.markers.length);
            if (this.markers.length > 0) {
                this.markers.forEach((item) => {
                    console.log(item);
                    item.setMap(null);
                });
            }
        },
        //검색결과 목록의 자식 Element를 제거하는 함수입니다
        removeAllChildNods(el) {
            while (el.hasChildNodes()) {
                el.removeChild(el.lastChild);
            }
        },
    },
};
</script>

<style scoped>
#map {
    width: 100%;
    height: 700px;
}
</style>
