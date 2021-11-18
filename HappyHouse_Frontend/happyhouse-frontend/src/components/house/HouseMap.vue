<template>
  <!-- 지도 -->
  <!-- Kakao Map start -->
  <div
    id="map1"
    style="width: 100%; height: 500px; margin: 0 auto"
  ></div>
  <!-- Kakao Map end -->
</template>

<script>
export default {
  name: "HouseMap",
  data() {
    return {
      map1: null,
      markerPositions1: [],
      markerPositions2: [],
      markerPosidions3: [],
      markers: [],
      infowindow: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map1");
      let lat, lng, position;
      // GeoLocation을 이용해서 접속 위치를 얻어옵니다
      navigator.geolocation.getCurrentPosition((pos) => {
        lat = pos.coords.latitude; // 위도
        lng = pos.coords.longitude; // 경도
        // console.log(lat+" "+lng);
        position = new kakao.maps.LatLng(lat, lng); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        const options = {
          center: position,
          level: 3,
        };
        this.map1 = new kakao.maps.Map(container, options);
        this.displayMarker(position, "현재위치");
      });
    },
    // displayMarker(position, string) {
    //   let marker = new kakao.maps.Marker({
    //     map: this.map1,
    //     position: position,
    //   })

    // },
  },
};
</script>

<style>
.col-8{
    padding: 0px;
    margin: 0px;
}
</style>
