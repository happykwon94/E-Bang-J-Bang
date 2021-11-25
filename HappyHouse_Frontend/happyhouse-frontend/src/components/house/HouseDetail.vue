<template>
  <div class="house-detail-body">
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
            <button class="btn btn-outline-warning" @click="addBookMarker">
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
              {{ houseDeal.dongName }} {{ houseDeal.jibun }}
            </div>
            <div class="col-3">아파트 이름: {{ houseDeal.aptName }}</div>
            <div class="col-3">해당층: {{ houseDeal.floor }}층</div>
          </div>
          <div class="row deal">
            <div class="col-5">
              거래일: {{ houseDeal.dealYear }}년 {{ houseDeal.dealMonth }}월
              {{ houseDeal.dealDay }}일
            </div>
            <div class="col-3">건축년도: {{ houseDeal.buildYear }}년</div>
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
          <li id="BK9" data-order="0" @click="searchCategory('BK9', 0)">
            <span class="category_bg bank"></span>
            은행
          </li>
          <li id="MT1" data-order="1" @click="searchCategory('MT1', 1)">
            <span class="category_bg mart"></span>
            마트
          </li>
          <li id="PM9" data-order="2" @click="searchCategory('PM9', 2)">
            <span class="category_bg pharmacy"></span>
            약국
          </li>
          <li id="OL7" data-order="3" @click="searchCategory('OL7', 3)">
            <span class="category_bg oil"></span>
            주유소
          </li>
          <li id="CE7" data-order="4" @click="searchCategory('CE7', 4)">
            <span class="category_bg cafe"></span>
            카페
          </li>
          <li id="CS2" data-order="5" @click="searchCategory('CS2', 5)">
            <span class="category_bg store"></span>
            편의점
          </li>
        </ul>
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
  data() {
    return {
      map2: null,
      markers2: [],
      order: 0,
      key: "",
      isDetail: false,
      aptCode: this.$route.params.aptCode,
      loadMap: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["houseDeal", "houseInfo", "houses", "isGetData"]),
    ...mapState(userStore, ["userInfo"]),
  },
  mounted() {
    if (this.loadMap) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e3f5368c16f60513648c83fa8b24274d&libraries=services";
      document.head.appendChild(script);
      this.loadMap = true;
    }
  },
  created() {
    this.markers2 = [];
    // console.log("선택한 집");
    // console.log(3);
    // console.log(this.houseDeal);
    this.getHouse({ aptCode: this.aptCode });
  },
  methods: {
    ...mapActions(houseStore, ["getStoreList", "setBookMark", "getHouse"]),
    ...mapMutations(houseStore, ["SET_APT_DEAL"]),
    initMap() {
      //지도를 담을 dom

      const container = document.getElementById("map2");
      // alert(container);
      let position = new kakao.maps.LatLng(
        this.houseDeal.lat,
        this.houseDeal.lng
      ); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
      let options = {
        center: position,
        level: 3,
      };
      this.map2 = new kakao.maps.Map(container, options);

      var imageSrc = require("@/assets/marker.png"), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      new kakao.maps.Marker({
        map: this.map2,
        position: position,
        image: markerImage,
      });

      var ps = new kakao.maps.services.Places(this.map2);

      kakao.maps.event.addListener(
        this.map2,
        "idle",
        () => {
          ps.categorySearch(this.key, (data, status, pagination) => {
            console.log(data);
            console.log(status);
            console.log(pagination);

            if (status === kakao.maps.services.Status.OK) {
              this.markers2.forEach((marker) => {
                marker.setMap(null);
              });
              data.forEach((store) => {
                this.displayMarker(store, this.order);
              });
            } else {
              console.log(status);
            }
          });
        },
        { useMapBounds: true }
      );
      kakao.maps.event.addListener(
        this.map2,
        "idle",
        () => {
          ps.categorySearch(this.key, (data, status, pagination) => {
            console.log(data);
            console.log(status);
            console.log(pagination);

            if (status === kakao.maps.services.Status.OK) {
              this.markers2.forEach((marker) => {
                marker.setMap(null);
              });
              data.forEach((store) => {
                this.displayMarker(store, this.order);
              });
            } else {
              console.log(status);
            }
          });
        },
        { useMapBounds: true }
      );

      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map2.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
    },
    searchCategory(key, order) {
      this.key = key;
      this.order = order;
    },
    displayMarker(store, order) {
      console.log(store);
      console.log(order);
      var moveLatLng = new kakao.maps.LatLng(store.y, store.x);

      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
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
          map: this.map2,
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
      c4.innerHTML = "주소 : " + store.address_name;
      c3.appendChild(c4);
      let c5 = document.createElement("div");
      c5.className = "desc";
      c5.innerHTML = "가게명 : " + store.place_name;
      c3.appendChild(c5);
      let c7 = document.createElement("button");
      c7.className = "btn-sm btn-outline-dark";
      c7.innerHTML = "kakao 검색";
      c7.onclick = () => {
        window.open(store.place_url);
      };
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
    addBookMarker() {
      if (!this.userInfo) {
        alert("로그인해주세요");
        this.$router.push({ name: "SignIn" });
      }
      if (!this.isGetData) {
        this.setBookMark({
          userNo: this.userInfo.no,
          housedealNo: this.houseDeal.no,
        });
      } else {
        this.setBookMark({
          userNo: this.userInfo.no,
          housedealNo: this.houseDeal.일련번호,
        });
      }
    },
  },
};
</script>

<style>
.house-detail-body {
  /* margin-top: 40px; */
  /* padding: 50px; */
  margin: 70px;
}

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

.desc {
  overflow: auto;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding-bottom: 5px;
}

.deal {
  font-size: 13px;
  padding: 5px;
  border-bottom: 0.7px solid rgb(218, 218, 218);
}
</style>
