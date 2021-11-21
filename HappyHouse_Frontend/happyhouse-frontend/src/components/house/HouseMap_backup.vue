<template>
  <!-- 지도 -->
  <!-- Kakao Map start -->
  <div id="map1" style="width: 100%; height: 550px; margin: 0 auto"></div>
  <!-- Kakao Map end -->
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
      markers: [],
      markerPositions1: [], // 기존 검색시 나온 좌표들
      markerPositions2: [], // 상세보기 좌표들
      markerPosidions3: [],
      infowindow: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  watch: {
    houses() {
      this.displayMarker();
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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e3f5368c16f60513648c83fa8b24274d";
      document.head.appendChild(script);
    }
  },
  methods: {
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
        this.map = new kakao.maps.Map(container, options);
        let marker = new kakao.maps.Marker({
          map: this.map,
          position: position,
        });
        console.log(marker);
        // marker.setMap(this.map);
      });
    },
    //houses가 바뀔 때마다 새로 좌표찍기
    displayMarker() {
      // alert("displayMarker");
      // 이전의 마커 삭제
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];

      this.houses.forEach((house) => {
        let moveLatLng = new kakao.maps.LatLng(house.lat, house.lng);
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: moveLatLng,
        });
        this.markers.push(marker);
        this.map.panTo(moveLatLng);

        // 마커 위에 커스텀오버레이를 표시
        let overlay = new kakao.maps.CustomOverlay({
          map: this.map,
          position: moveLatLng,
        });

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
        closeBtn.onclick = function () {
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
        c7.className = "btn btn-primary";
        c7.innerHTML = "자세히 보기";
        c3.appendChild(c7);

        overlay.setContent(content);

        // 커스텀 오버레이 숨기기
        overlay.setMap(null);

        // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
        kakao.maps.event.addListener(marker, "click", function () {
          // alert("click");
          console.log("click");
          overlay.setMap(this.map);
        });
      });
    },
  },
};
</script>

<style>
@charset "UTF-8";
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
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
  overflow: hidden;
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
  overflow: hidden;
  padding: 15px;
}
.desc {
  overflow: hidden;
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
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
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

.infohead > li + li {
  padding-left: 28px;
  margin-left: 28px;
  border-left: 1px solid grey;
}

.infos > li {
  display: inline-flex;
  width: 30%;
  height: 50px;
  border-bottom: grey;
  line-height: 50px;
}

li,
p {
  vertical-align: baseline;
}

.infos {
  border-top: 2px solid grey;
}

.infos-a {
  margin-right: 10px;
  /* width: 100px; */
  font-size: 14px;
}

.find-infos {
  display: flex;
  -webkit-box-pack: start;
  justify-content: flex-start;
  -webkit-box-align: center;
  align-items: center;
  width: 1200px;
  height: 58px;
  margin: auto 0px;
  padding: 10px;
  z-index: 2;
}
</style>
