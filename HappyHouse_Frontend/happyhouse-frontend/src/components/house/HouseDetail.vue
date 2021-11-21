<template>
  <div class="detail">
    <div class="row-12">
      <div class="row">
        <div class="col-6">
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
          <div class="col-6">
            <button class="btn btn-outline-primary" @click="moveHouseList">
              지도보기
            </button>
          </div>
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
      <div id="mapwrap">
        <div id="map2" style="width: 100%; height: 350px" class="mb-3"></div>
        <!-- 지도 위에 표시될 마커 카테고리 -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
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
    ...mapState(houseStore, ["houseDeal", "houseInfo"]),
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
    },
  },
  moveHouseList() {
    //houseDeal을 null
    alert("click");
    // this.SET_APT_DEAL(null);
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
</style>
