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
      {{ this.foodStore }}
      {{ this.lifeStore }}
      {{ this.eduStore }}
      <!-- detail한 map 새로 생성 -->
      <div id="mapwrap">
        <div id="map2" style="width: 100%; height: 350px"></div>
        <!-- 지도 위에 표시될 마커 카테고리 -->
        <div class="category">
          <ul>
            <li id="coffeeMenu" @click="changeMarker('음식')">
              <span class="ico_comm ico_coffee"></span>
              음식
            </li>
            <li id="storeMenu" @click="changeMarker('소매')">
              <span class="ico_comm ico_store"></span>
              소매
            </li>
            <li id="carparkMenu" @click="changeMarker('교육')">
              <span class="ico_comm ico_carpark"></span>
              교육
            </li>
          </ul>
        </div>
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
      map: null,
      markers: [],
      isDetail: false,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "houseDeal",
      "houseInfo",
      "houses",
      "foodStore",
      "lifeStore",
      "eduStore",
    ]),
  },
  created() {
    this.markers = [];
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e3f5368c16f60513648c83fa8b24274d";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapMutations(houseStore, ["SET_APT_DEAL"]),
    ...mapActions(houseStore, ["getStoreList"]),
    initMap() {
      //지도를 담을 dom
      const container = document.getElementById("map2");
      var position;
      position = new kakao.maps.LatLng(this.houseInfo.lat, this.houseInfo.lng); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
      var options = {
        center: position,
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);
      new kakao.maps.Marker({
        map: this.map,
        position: position,
      });
      // store array 지정
      this.getStoreList(this.houseInfo.dongName);
    },
    moveHouseList() {
      //houseDeal을 null
      alert("click");
      this.SET_APT_DEAL(null);

      console.log("this.houses");
      console.log(this.houses);
    },
    displayMarker(store) {
      // console.log(house);
      var moveLatLng = new kakao.maps.LatLng(store.lat, store.lng);
      // 마커 생성
      let marker = new kakao.maps.Marker({
        map: this.map,
        position: moveLatLng,
        clickable: true,
      });

      // map에 마커 생성
      marker.setMap(this.map);

      // marker 배열에 넣기
      this.markers.push(marker);
    },
    changeMarker(classes) {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers2 = [];
      switch (classes) {
        case "음식":
          console.log(this.foodStore);
          this.foodStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "소매":
          this.lifeStore.forEach((store) => {
            this.displayMarker(store);
          });
          break;
        case "교육":
          this.eduStore.forEach((store) => {
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

#mapwrap {
  position: relative;
  overflow: hidden;
}
.category,
.category * {
  margin: 0;
  padding: 0;
  color: #000;
}
.category {
  position: absolute;
  overflow: hidden;
  top: 10px;
  left: 10px;
  width: 153px;
  height: 50px;
  z-index: 10;
  border: 1px solid black;
  font-family: "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 12px;
  text-align: center;
  background-color: #fff;
}
.category .menu_selected {
  background: #ff5f4a;
  color: #fff;
  border-left: 1px solid #915b2f;
  border-right: 1px solid #915b2f;
  margin: 0 -1px;
}
.category li {
  list-style: none;
  float: left;
  width: 50px;
  height: 45px;
  padding-top: 5px;
  cursor: pointer;
}
.category .ico_comm {
  display: block;
  margin: 0 auto 2px;
  width: 22px;
  height: 26px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png")
    no-repeat;
}
.category .ico_coffee {
  background-position: -10px 0;
}
.category .ico_store {
  background-position: -10px -36px;
}
.category .ico_carpark {
  background-position: -10px -72px;
}
</style>
