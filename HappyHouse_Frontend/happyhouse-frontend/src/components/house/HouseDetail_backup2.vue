<template>
  <div class="detail">
    <div class="row-12">
      <div class="row">
        <div class="col">
          <ul class="infohead">
            <li>
              <p>거래금액</p>
              <div id="price">{{ houseDeal.dealAmount }}</div>
            </li>
            <li>
              <p>전용면적</p>
              <div id="area">{{ houseDeal.area }}㎡</div>
            </li>
          </ul>
        </div>
        <div class="col">
          <button class="btn btn-outline-primary" @click="moveHouseList">
            지도보기
          </button>
        </div>
      </div>
      <div class="row-12 detail-info">
        <div class="row">
          <div class="col-4">
            주소: {{ houseDeal.sidoName }} {{ houseDeal.gugunName }}
            {{ houseDeal.dongName }} {{ houseInfo.jibun }}
          </div>
          <div class="col-4">아파트 이름: {{ houseDeal.aptName }}</div>
          <div class="col-4">해당층: {{ houseDeal.floor }}층</div>
        </div>
        <div class="row">
          <div class="col-4">
            거래일: {{ houseDeal.dealYear }}년 {{ houseDeal.dealMonth }}월
            {{ houseDeal.dealDay }}일
          </div>
          <div class="col-4">건축년도: {{ houseInfo.buildYear }}년</div>
        </div>
      </div>
      <!-- detail한 map 새로 생성 -->
      <div class="map_wrap">
        <div
          id="map2"
          style="
            width: 100%;
            height: 100%;
            position: relative;
            overflow: hidden;
          "
        ></div>
        <!-- 지도 위에 표시될 마커 카테고리 0은 classDtail 1은 class-->
        <ul id="category">
          <li id="BK9" @click="selCategory(`부동산`, '0')">
            <span class="category_bg bank"></span>
            부동산
          </li>
          <li id="MT1" @click="selCategory(`소매`, '1')">
            <span class="category_bg mart"></span>
            소매
          </li>
          <li id="PM9" @click="selCategory(`생활서비스`, '2')">
            <span class="category_bg pharmacy"></span>
            생활서비스
          </li>
          <li id="OL7" @click="selCategory(`스포츠`, '3')">
            <span class="category_bg oil"></span>
            스포츠
          </li>
          <li id="CE7" @click="selCategory(`카페`, '4')">
            <span class="category_bg cafe"></span>
            카페
          </li>
          <li id="CS2" @click="selCategory(`음식`, '4')">
            <span class="category_bg store"></span>
            음식
          </li>
          <li id="CS2" @click="selCategory(`교육`, '4')">
            <span class="category_bg store"></span>
            교육
          </li>
          <li id="CS2" @click="selCategory(`여가`, '4')">
            <span class="category_bg store"></span>
            여가
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
const houseStore = "houseStore";
export default {
  date() {
    return {
      map2: null,
      markers2: [{ test: "안녕" }],
      order: "",
      orderCnt: 0,
      isDetail: false,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "houseDeal",
      "houseInfo",
      "houses",
      "landStore",
      "lifeStore",
      "serviceStore",
      "sportStore",
      "cafeStore",
      "foodStore",
      "eduStore",
      "playStore",
    ]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=e3f5368c16f60513648c83fa8b24274d&libraries=services";
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      document.head.appendChild(script);
    }
  },
  created() {
    this.markers2 = [];
    // store array 지정
    this.getStoreList(this.houseInfo.dongName);
  },
  methods: {
    ...mapActions(houseStore, "getStoreList"),
    ...mapMutations(houseStore, ["SET_APT_DEAL"]),
    initMap() {
      //지도를 담을 dom
      const container = document.getElementById("map2");
      var position = new kakao.maps.LatLng(
        this.houseInfo.lat,
        this.houseInfo.lng
      ); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
      var options = {
        center: position,
        level: 3,
      };
      this.map2 = new kakao.maps.Map(container, options);
      new kakao.maps.Marker({
        map: this.map2,
        position: position,
      });
    },
    moveHouseList() {
      //houseDeal을 null
      // alert("click");
      this.SET_APT_DEAL(null);

      // console.log("this.houses");
      // console.log(this.houses);
    },
    displayMarker(store) {
      var moveLatLng = new kakao.maps.LatLng(store.y, store.x);
      // console.log(moveLatLng);

      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, this.orderCnt * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: moveLatLng, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map2); // 지도 위에 마커를 표출합니다

      console.log("this.markers2");
      console.log(this.markers2);
      this.markers2.push(marker); // 배열에 생성된 마커를 추가합니다
    },
    selCategory(order, orderCnt) {
      this.order = order;
      this.orderCnt = orderCnt;
      this.markers2.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers2 = [];
      switch (this.order) {
        case "부동산":
          this.landStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "소매":
          this.lifeStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "생활서비스":
          this.serviceStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "스포츠":
          this.sportStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "카페":
          this.cafeStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "음식":
          this.foodStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "교육":
          this.eduStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "여가":
          this.playStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;

        default:
          break;
      }
    },
  },
};
</script>

<style>
.infohead > li + li {
  padding-left: 28px;
  margin-left: 28px;
  border-left: 1px solid grey;
}

.find-infos {
  padding-top: 50px;
  padding-bottom: 50px;
  /* margin-bottom: 30px; */
}

.detail {
  border: 1px solid rgb(149, 149, 149);
  padding: 30px;
}

.detail-info {
  margin-bottom: 30px;
}

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
