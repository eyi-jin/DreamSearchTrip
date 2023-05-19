<template>
    <div class="d-flex">
        <!-- 사이드바 -->
        <div class="col-sm-2 sidebar" id="placesList" style="background-color: #f7f7f7; z-index: 2">
            <div class="sidebar-box bg-white p-4 rounded job-position m-3">
                <div class="row">
                    <div class="">
                        <h5>Product Designer</h5>
                        <div class="align-items-center">
                            <div class="d-flex align-items-center me-4">
                                <span class="icon-room me-2"></span> <span>Remote</span>
                            </div>
                            <div class="d-flex align-items-center">
                                <span class="icon-clock-o me-2"></span> <span>Fulltime</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sidebar-box bg-white p-4 rounded job-position m-3">
                <div class="row">
                    <div class="col-sm-2">
                        <h5>Product Designer</h5>
                        <div class="align-items-center">
                            <div class="d-flex align-items-center me-4">
                                <span class="icon-room me-2"></span> <span>Remote</span>
                            </div>
                            <div class="d-flex align-items-center">
                                <span class="icon-clock-o me-2"></span> <span>Fulltime</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12 sidebar" id="map"></div>
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
            var el = document.createElement("div"),
                itemStr = ` <div class="sidebar-box bg-white p-4 rounded job-position m-3">
	                    <div class="row">
		                  <div class="col-lg-12">
			                <h5>${places.title}</h5> `;

            if (places.addr) {
                itemStr += ` <div class="d-flex align-items-center">
  					                 <span>주소: ${places.addr}</span>
  				                </div> `;
            } else {
                itemStr += `<div class="d-flex align-items-center">
					                   <span>주소: 표기 없음</span>
				                  </div> `;
            }
            itemStr += `  <div class="d-flex align-items-center">
					                  <span>전화번호: ${places.tel}</span>
				                  </div> `;
            itemStr += ` </div>
                  		</div>
                  	</div>
                  </div> `;

            el.innerHTML = itemStr;

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
