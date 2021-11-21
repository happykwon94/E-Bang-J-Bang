<template>
  <!-- 지도 -->
  <!-- Kakao Map start -->
  <div id="map1" style="width: 100%; height: 550px; margin: 0 auto"></div>
  <!-- Kakao Map end -->
</template>

<script language="JavaScript">
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseMap",
  data() {
    return {
      map1: null,
      markers: [],
      isDetail: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "houseInfo"]),
  },
  watch: {
    houses() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
      if (this.houses) {
        this.houses.forEach((house) => {
          this.displayMarker(house);
        });
      }
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e3f5368c16f60513648c83fa8b24274d&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(houseStore, ["getHouse"]),
    initMap() {
      //지도를 담을 dom
      const container = document.getElementById("map1");
      var lat, lng, position;
      // GeoLocation을 이용해서 접속 위치를 얻어옵니다
      navigator.geolocation.getCurrentPosition((pos) => {
        lat = pos.coords.latitude; // 위도
        lng = pos.coords.longitude; // 경도
        // console.log(lat+" "+lng);
        position = new kakao.maps.LatLng(lat, lng); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        var options = {
          center: position,
          level: 3,
        };
        this.map1 = new kakao.maps.Map(container, options);
        new kakao.maps.Marker({
          map: this.map1,
          position: position,
        });
        if (this.houses != null) {
          this.houses.forEach((house) => {
            this.displayMarker(house);
          });
          // console.log("this.houseInfo");
          // console.log(this.houseInfo);
          this.map1.setCenter(
            new kakao.maps.LatLng(this.houseInfo.lat, this.houseInfo.lng)
          );
        }
      });
    },
    //houses가 바뀔 때마다 새로 좌표찍기
    displayMarker(house) {
      // console.log("house");
      // console.log(house);
      var moveLatLng = new kakao.maps.LatLng(house.lat, house.lng);
      console.log(moveLatLng);
      // 마커 생성
      let marker = new kakao.maps.Marker({
        map: this.map1,
        position: moveLatLng,
        clickable: true,
      });

      // marker 배열에 넣기
      this.markers.push(marker);

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
      c2.innerHTML = house.aptName;
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
      c4.innerHTML =
        "주소 : " +
        house.sidoName +
        " " +
        house.gugunName +
        " " +
        house.dongName +
        " " +
        house.jibun;
      c3.appendChild(c4);
      let c5 = document.createElement("div");
      c5.className = "desc";
      c5.innerHTML = "건축년도 : " + house.buildYear + "년";
      c3.appendChild(c5);
      let c6 = document.createElement("div");
      c6.className = "desc";
      c6.innerHTML = "최근 매매가 : " + house.recentPrice + "만원";
      c3.appendChild(c6);
      let c7 = document.createElement("button");
      c7.className = "btn-sm btn-outline-dark";
      c7.innerHTML = "자세히 보기";
      c7.onclick = () => {
        this.getHouse(house);
      };
      c3.appendChild(c7);

      overlay.setContent(content);

      // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
      kakao.maps.event.addListener(marker, "click", () => {
        overlay.setMap(this.map1);
        this.map1.panTo(moveLatLng);
      });

      kakao.maps.event.addListener(this.map1, "click", () => {
        overlay.setMap(null);
      });

      // 지도 중심좌표를 접속위치로 변경합니다
      this.map1.panTo(moveLatLng);
    },
  },
};
</script>

<style>
@charset "UTF-8";

/* .info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #2b2d36
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
  font-size: 14px;
  font-weight: bold;
} */

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
.info .link {
  color: #5085bb;
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
ul {
  list-style: none;
}
</style>
