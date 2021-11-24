<template>
  <div class="">
    <div class="row-12">
      <div v-if="this.isGetData">
        <div class="row">
          <div class="col">
            <ul class="infoH">
              <li>
                <p>거래금액</p>
                <div id="price">{{ houseDeal.거래금액 }}</div>
              </li>
              <li>
                <p>전용면적</p>
                <div id="area">{{ houseDeal.전용면적 }}㎡</div>
              </li>
            </ul>
          </div>
          <div class="col-2">
            <button class="btn btn-outline-primary" @click="moveHouseList">
              지도보기
            </button>
          </div>
          <div class="col-2">
            <button class="btn btn-outline-warning" @click="moveHouseList">
              관심
            </button>
          </div>
        </div>
        <div class="row-12 detail-info">
          <div class="row deal">
            <div class="col-4">
              주소: {{ houseDeal.도로명 }} {{ houseDeal.지번 }}
            </div>
            <div class="col-3">아파트 이름: {{ houseDeal.아파트 }}</div>
            <div class="col-3">해당층: {{ houseDeal.층 }}층</div>
          </div>
          <div class="row deal">
            <div class="col-5">
              거래일: {{ houseDeal.년 }}년 {{ houseDeal.월 }}월
              {{ houseDeal.일 }}일
            </div>
            <div class="col-3">건축년도: {{ houseDeal.건축년도 }}년</div>
          </div>
        </div>
      </div>
      <div v-else>
        <div class="row">
          <div class="col">
            <ul class="infoH">
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
          <div class="col" style="margin-left: auto">
            <div class="row" style="margin-bottom: 5px">
              <button class="btn btn-outline-primary" @click="moveHouseList">
                지도보기
              </button>
            </div>
            <div class="row">
              <button class="btn btn-outline-warning" @click="addBookMarker">
                관심
              </button>
            </div>
          </div>
        </div>
        <div class="row-12 detail-info">
          <div class="row deal">
            <div class="col-5">
              주소: {{ houseDeal.sidoName }} {{ houseDeal.gugunName }}
              {{ houseDeal.dongName }} {{ houseInfo.jibun }}
            </div>
            <div class="col-3">아파트 이름: {{ houseDeal.aptName }}</div>
            <div class="col-3">해당층: {{ houseDeal.floor }}층</div>
          </div>
          <div class="row deal">
            <div class="col-5">
              거래일: {{ houseDeal.dealYear }}년 {{ houseDeal.dealMonth }}월
              {{ houseDeal.dealDay }}일
            </div>
            <div class="col-3">건축년도: {{ houseInfo.buildYear }}년</div>
          </div>
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
        <ul class="category">
          <li id="BK9" @click="selCategory('부동산', '0')">
            <span class="category_bg bank"></span>
            부동산
          </li>
          <li id="MT1" @click="selCategory('소매', '1')">
            <span class="category_bg mart"></span>
            소매
          </li>
          <li id="PM9" @click="selCategory('생활서비스', '2')">
            <span class="category_bg pharmacy"></span>
            생활서비스
          </li>
          <li id="OL7" @click="selCategory('스포츠', '3')">
            <span class="category_bg oil"></span>
            스포츠
          </li>
          <li id="CE7" @click="selCategory('카페', '4')">
            <span class="category_bg cafe"></span>
            카페
          </li>
          <li id="CE7" @click="selCategory('편의점', '4')">
            <span class="category_bg cafe"></span>
            편의점
          </li>
          <li id="CS2" @click="selCategory('음식', '4')">
            <span class="category_bg store"></span>
            음식
          </li>
          <li id="CS2" @click="selCategory('교육', '4')">
            <span class="category_bg store"></span>
            교육
          </li>
          <li id="CS2" @click="selCategory('여가', '4')">
            <span class="category_bg store"></span>
            여가
          </li>
          <li id="CS2" @click="selCategory('여가', '4')">
            <span class="category_bg store"></span>
            보건소 (선별진료소)
          </li>
        </ul>
        <ul class="category"></ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";
export default {
  name: "HouseDetail",
  date() {
    return {
      map2: null,
      markers2: [],
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
      "everyStore",
      "isGetData",
    ]),
    ...mapState(userStore, ["userInfo"]),
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
  },
  methods: {
    ...mapActions(houseStore, ["getStoreList", "setBookMark"]),
    ...mapMutations(houseStore, ["SET_APT_DEAL"]),
    async initMap() {
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
      // store array 지정
      if (!this.isGetData) {
        await this.getStoreList(this.houseInfo.dongName);
      }
    },
    moveHouseList() {
      this.SET_APT_DEAL(null);
    },
    displayMarker(store) {
      var moveLatLng = new kakao.maps.LatLng(store.lat, store.lng);
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
          clickable: true,
        });

      marker.setMap(this.map2); // 지도 위에 마커를 표출합니다

      this.markers2.push(marker); // 배열에 생성된 마커를 추가합니다

      // 마커 위에 커스텀오버레이를 표시
      let overlay = new kakao.maps.CustomOverlay({
        map: this.map1,
        position: moveLatLng,
        clickable: true,
      });

      // 커스텀 오버레이 숨기기
      overlay.setMap(null);

      // 커스텀 오버레이
      let content = document.createElement("div");
      content.className = "wrap";
      let c1 = document.createElement("div");
      c1.className = "info";
      content.appendChild(c1);
      let c2 = document.createElement("div");
      c2.className = "title";
      c2.innerHTML = store.storeName;
      c1.appendChild(c2);
      let closeBtn = document.createElement("button");
      closeBtn.className = "close";
      // x 버튼 클릭시 커스텀 오버레이 창닫기
      closeBtn.onclick = () => {
        overlay.setMap(null);
      };
      c2.appendChild(closeBtn);
      let c3 = document.createElement("div");
      c3.className = "body";
      c1.appendChild(c3);
      let c4 = document.createElement("div");
      c4.className = "desc";
      c4.innerHTML = "주소 : " + store.address;
      c3.appendChild(c4);
      let c5 = document.createElement("div");
      c5.className = "desc";
      c5.innerHTML = "분류1 : " + store.classDetail1;
      c3.appendChild(c5);
      let c6 = document.createElement("div");
      c6.className = "desc";
      c6.innerHTML = "분류2 : " + store.classDetail2;
      c3.appendChild(c6);
      let c7 = document.createElement("button");
      c7.className = "btn-sm btn-outline-dark";
      c7.innerHTML = "자세히 보기";
      c3.appendChild(c7);

      overlay.setContent(content);

      // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
      kakao.maps.event.addListener(marker, "click", () => {
        overlay.setMap(this.map2);
        // this.map2.panTo(moveLatLng);
      });

      kakao.maps.event.addListener(this.map2, "click", () => {
        overlay.setMap(null);
      });

      // 지도 중심좌표를 접속위치로 변경합니다
      // this.map2.panTo(moveLatLng);
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
        case "편의점":
          this.everyStore.forEach((store) => {
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
    addBookMarker() {
      if(!this.userInfo){
        alert("로그인해주세요");
        this.$router.push({ name: "SignIn" });
      }
      this.setBookMark({
        userNo: this.userInfo.no,
        housedealNo: this.houseDeal.no,
      });
    },
  },
};
</script>

<style>
ul li {
  list-style-type: none;
  float: left;
}

.infoH {
  font-weight: bold;
}

.infoH > li + li {
  padding-left: 28px;
  margin-left: 28px;
  border-left: 1px solid grey;
}

.infoH {
  font-size: 23px;
}

.find-infos {
  padding-top: 50px;
  padding-bottom: 50px;
  /* margin-bottom: 30px; */
}

.detail-info {
  border-top: 1px solid black;
  margin-top: 20px;
  padding-top: 20px;
  margin-bottom: 50px;
  margin-left: 22px;
}

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 11px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}
.category {
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
.category li {
  float: left;
  list-style: none;
  width: 60px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
.category li.on {
  background: #eee;
}
.category li:hover {
  background: #c2d4dcb3;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
.category li:last-child {
  margin-right: 0;
  border-right: 0;
}
.category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
.category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
.category li .bank {
  background-position: -10px 0;
}
.category li .mart {
  background-position: -10px -36px;
}
.category li .pharmacy {
  background-position: -10px -72px;
}
.category li .oil {
  background-position: -10px -108px;
}
.category li .cafe {
  background-position: -10px -144px;
}
.category li .store {
  background-position: -10px -180px;
}
.category li.on .category_bg {
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

.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  margin-left: -144px;
  text-align: left;
  overflow: auto;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: auto;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: auto;
  padding: 15px;
}
.desc {
  overflow: auto;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding-bottom: 5px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: auto;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
}

.info-body {
  width: 1000px;
}
.infohead {
  display: flex;
  align-items: center;
  width: 100%;
  padding-top: 35px;
  margin-bottom: 35px;
}

.deal {
  font-size: 13px;
  padding: 5px;
  border-bottom: 0.7px solid rgb(218, 218, 218);
}
</style>
