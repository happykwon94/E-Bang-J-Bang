import http from "@/util/http-common.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ sidoCode: "", sidoName: "시/도" }],
    guguns: [{ gugunCode: "", gugunName: "구/군" }],
    dongs: [{ dongCode: "", dongName: "동" }],
    gugunCode: "",
    dongCode: "",
    houses: null,
    houseDeal: null,
    houseInfo: null,
    address: null,
    jibun: null,
    store: null,
    landStore: [],
    lifeStore: [],
    serviceStore: [],
    sportStore: [],
    cafeStore: [],
    foodStore: [],
    eduStore: [],
    playStore: [],
    everyStore: [],
    isGetData: false,
    lat: "",
    lng: "",
    bookMarkList: [],
    price: 0,
  },
  mutations: {
    SET_SIDO_LIST(state, data) {
      state.sidos = [];
      data.forEach((sido) => {
        state.sidos.push({ sidoCode: sido.sidoCode, sidoName: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, data) {
      state.guguns = [];
      data.forEach((gugun) => {
        state.guguns.push({
          gugunCode: gugun.gugunCode,
          gugunName: gugun.gugunName,
        });
      });
    },
    SET_DONG_LIST(state, data) {
      state.dongs = [];
      data.forEach((dong) => {
        state.dongs.push({ dongCode: dong.dongCode, dongName: dong.dongName });
      });
    },
    SET_SIDO(state, data) {
      state.gugunCode = data;
    },
    SET_GUGUN(state, data) {
      state.gugunCode = data;
    },
    SET_DONG(state, data) {
      state.dongCode = data;
    },
    SET_APT_LIST(state, data) {
      state.houses = data;
      console.log(state.houses);
    },
    SET_APT_INFO(state, data) {
      state.houseInfo = data;
    },
    SET_APT_DEAL(state, data) {
      state.houseDeal = data;
    },
    SET_LAND_STORE(state, data) {
      state.landStore = data;
    },
    SET_LIFE_STORE(state, data) {
      state.lifeStore = data;
    },
    SET_SERVICE_STORE(state, data) {
      state.serviceStore = data;
    },
    SET_SPORT_STORE(state, data) {
      state.sportStore = data;
    },
    SET_CAFE_STORE(state, data) {
      state.cafeStore = data;
    },
    SET_FOOD_STORE(state, data) {
      state.foodStore = data;
    },
    SET_EDU_STORE(state, data) {
      state.eduStore = data;
    },
    SET_PLAY_STORE(state, data) {
      state.playStore = data;
    },
    SET_EVERY_STORE(state, data) {
      state.everyStore = data;
    },
    SET_GET_DATA(state, data) {
      state.isGetData = data;
      console.log("state.isGetData");
      console.log(state.isGetData);
    },
    SET_ADDRESS_NAME(state, data) {
      state.address =
        data.sidoName +
        " " +
        data.gugunName +
        " " +
        data.dongName +
        " " +
        data.jibun;
    },
    SET_LAT_LNG(state, data) {
      state.lat = data.lat;
      state.lng = data.lng;
    },
    SET_BOOKMARK_LIST(state, data) {
      state.bookMarkList = data;
    },
    SET_PRICE(state, data) {
      state.price = data;
    },
  },
  actions: {
    getSido({ commit }) {
      http
        .get("/house/sido")
        .then((response) => {
          commit("SET_SIDO_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      console.log("시도 :" + sidoCode);
      http
        .get(`/house/gugun`, { params })
        .then((response) => {
          commit("SET_GUGUN_LIST", response.data);
          commit("SET_APT_DEAL", null);
          commit("SET_APT_LIST", []);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      console.log("구군: " + gugunCode);
      const params = { gugun: gugunCode };
      http
        .get(`/house/dong`, { params })
        .then((response) => {
          commit("SET_DONG_LIST", response.data);
          commit("SET_APT_DEAL", null);
          commit("SET_APT_LIST", []);
          commit("SET_GUGUN", gugunCode);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getAptList({ commit }, dongCode, price) {
      console.log("동: " + dongCode + " 가격: " + price);
      const params = { dong: dongCode, maxPrice: price };
      http
        .get(`/house/aptList`, { params })
        .then((response) => {
          // console.log(response.data);
          commit("SET_GET_DATA", false);
          commit("SET_APT_LIST", response.data);
          commit("SET_APT_DEAL", null);
          commit("SET_APT_INFO", null);
          commit("SET_DONG", dongCode);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouse({ commit }, house) {
      const params = { aptCode: house.aptCode };
      http
        .get(`/house/aptInfo`, { params })
        .then((response) => {
          console.log(response.data);
          commit("SET_APT_DEAL", response.data);
          commit("SET_APT_INFO", house);
          commit("SET_LAT_LNG", { lat: house.lat, lng: house.lng });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouse2({ commit }, houseApi) {
      commit("SET_APT_DEAL", houseApi.houseDeal);
      commit("SET_APT_INFO", houseApi.houseInfo);
    },
    getStoreList({ commit }, dongName) {
      var params = { dongName: dongName, classDetail2: "'부동산중개'" };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_LAND_STORE", response.data);
      });
      params = {
        dongName: dongName,
        classDetail2: "'수퍼마켓','할인점','식료품점'",
      };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_LIFE_STORE", response.data);
      });
      params = {
        dongName: dongName,
        classDetail2:
          "'세탁소/빨래방','비만/피부관리','여성미용실','남성미용실','발/네일케어'",
      };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_SERVICE_STORE", response.data);
      });
      params = {
        dongName: dongName,
        classDetail2:
          "'헬스클럽','기타운영설비','실내골프연습장','기타실내운동시설','학원-스포츠/재즈댄스'",
      };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_SPORT_STORE", response.data);
      });
      params = { dongName: dongName, classDetail2: "'커피전문점/카페/다방'" };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_CAFE_STORE", response.data);
      });
      params = {
        dongName: dongName,
        classDetail2:
          "'한식/백반/한정식','패스트푸드','족발/보쌈전문','곱창/양구이전문','토스트전문','삼계탕전문','국수/만두/칼국수','정통양식/경양식','부대찌개/섞어찌개','후라이드/양념치킨','라면김밥분식','떡볶이전문','기사식당','갈비/삼겹살','스파게티전문점'",
      };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_FOOD_STORE", response.data);
      });
      params = {
        dongName: dongName,
        classDetail2:
          "'학원-입시', '피아노/바이올린/기타','독서실','학원(종합)','학원-외국어/어학'",
      };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_EDU_STORE", response.data);
      });
      params = {
        dongName: dongName,
        classDetail2:
          "'인터넷PC방','노래방','연극/음악/예술관린기타','볼링장','당구장'",
      };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_PLAY_STORE", response.data);
      });
      params = {
        dongName: dongName,
        classDetail2: "'편의점'",
      };
      http.get(`/house/store`, { params }).then((response) => {
        commit("SET_EVERY_STORE", response.data);
      });
    },
    // getAptListPrice({ commit }, search) {
    //   const params = { dong: search.dongCode, maxPrice: search.price };
    //   http
    //     .get(`/house/aptList`, { params })
    //     .then((response) => {
    //       // 가격 검색
    //       commit("SET_GET_DATA", false); // api 사용 안함
    //       commit("SET_APT_LIST", response.data); // aptlist 초기화
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
    // api 사용
    getAptListYearMonth({ commit }, search) {
      const d = search.date.replace(/-/g, "");
      const SERVICE_URL =
        "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      const params = {
        ServiceKey:
          "3q5vt8ofkG59GUwMPmoqxI3FuUidmjWfRI8RFp0+ACnR6abveZrQPMiq/KIj+0Uxstbc7/PC2ynj+9lBRltPVw==",
        LAWD_CD: encodeURIComponent(search.gugunCode),
        DEAL_YMD: encodeURIComponent(d),
        numOfRows: encodeURIComponent(45),
      };
      http
        .get(SERVICE_URL, { params })
        .then((response) => {
          console.log(response.data.response.body.items.item);
          commit("SET_GET_DATA", true);
          commit("SET_APT_LIST", response.data.response.body.items.item);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async addressName({ commit }, dong) {
      const params = {
        dongName: dong.dongName,
        jibun: dong.jibun,
      };
      await http
        .get(`/house/addressName`, { params })
        .then((response) => {
          commit("SET_ADDRESS_NAME", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    setBookMark({ commit }, bookmark) {
      // console.log(bookmark.housedealNo);
      // console.log(bookmark.userNo);

      const newBookMark = {
        userNo: bookmark.userNo,
        housedealNo: bookmark.housedealNo,
      };
      http
        .post(`/house/bookMark`, JSON.stringify(newBookMark))
        .then((response) => {
          console.log(commit);
          if (response.data === "SUCCESS") {
            alert("북마크 등록 성공!");
          } else {
            alert("북마크 등록 실패!");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getBookMarkList({ commit }, userNo) {
      // alert("북마크");
      const params = { userNo: userNo };
      http
        .get(`/house/bookMark`, { params })
        .then((response) => {
          commit("SET_BOOKMARK_LIST", response.data);
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async deleteBookMark({ commit }, bookMarkInfo) {
      const params = { userNo: bookMarkInfo.userNo, aptNo: bookMarkInfo.aptNo };
      await http
        .delete(`/house/bookMark`, { params })
        .then((response) => {
          console.log(commit);
          if (response.data === "SUCCESS") {
            alert("북마크 삭제");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  modules: {},
  getters: {
    getterAptList(state) {
      console.log(state.price);
      if (!state.price) {
        // 가격이 설정 X면 전체
        return state.houses;
      } else {
        if (!state.isGetData) {
          // api 사용 X
          return state.houses.filter((house) => {
            return parseInt(house.recentPrice) <= parseInt(state.price);
          });
        } else {
          // api 사용
          return state.houses.filter((house) => {
            return (
              parseInt(house.거래금액.trim().replace(/,/g, "")) <=
              parseInt(state.price)
            );
          });
        }
      }
    },
  },
};

export default houseStore;
