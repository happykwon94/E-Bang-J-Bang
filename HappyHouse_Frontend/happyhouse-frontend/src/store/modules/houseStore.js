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
    },
    SET_APT_INFO(state, data) {
      state.houseInfo = data;
    },
    SET_APT_DEAL(state, data) {
      data;
      state.houseDeal = data;
    },
    SET_GET_DATA(state, data) {
      state.isGetData = data;
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
      http
        .get(`/house/gugun`, { params })
        .then((response) => {
          commit("SET_GUGUN_LIST", response.data);
          // commit("SET_APT_DEAL", null);
          commit("SET_APT_LIST", []);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      const params = { gugun: gugunCode };
      http
        .get(`/house/dong`, { params })
        .then((response) => {
          commit("SET_DONG_LIST", response.data);
          // commit("SET_APT_DEAL", null);
          commit("SET_APT_LIST", []);
          commit("SET_GUGUN", gugunCode);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getAptList({ commit }, dongCode, price) {
      const params = { dong: dongCode, maxPrice: price };
      http
        .get(`/house/aptList`, { params })
        .then((response) => {
          commit("SET_GET_DATA", false);
          commit("SET_APT_LIST", response.data);
          commit("SET_DONG", dongCode);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async getHouse({ commit }, house) {
      commit("SET_APT_INFO", house);
      await http
        .get(`/house/aptInfo/${house.aptCode}`)
        .then((response) => {
          commit("SET_LAT_LNG", { lat: house.lat, lng: house.lng });
          commit("SET_APT_DEAL", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouse2({ commit }, houseApi) {
      commit("SET_APT_DEAL", houseApi.houseDeal);
      commit("SET_APT_INFO", houseApi.houseInfo);
    },
    // api 사용
    getAptListYearMonth({ commit }, search) {
      const d = search.date.replace(/-/g, "");
      const SERVICE_URL =
        "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      const params = {
        ServiceKey:
          `${process.env.VUE_APP_DATA_GO_KR_KEY}`,
        LAWD_CD: encodeURIComponent(search.gugunCode),
        DEAL_YMD: encodeURIComponent(d),
        numOfRows: encodeURIComponent(45),
      };
      http
        .get(SERVICE_URL, { params })
        .then((response) => {
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
            alert("이미 북마크 설정!");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getBookMarkList({ commit }, userNo) {
      const params = { userNo: userNo };
      http
        .get(`/house/bookMark`, { params })
        .then((response) => {
          commit("SET_BOOKMARK_LIST", response.data);
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
            // this.getBookMarkList(bookMarkInfo.userNo);
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
    getterBookMarkList(state) {
      return state.bookMarkList;
    }
  },
};

export default houseStore;
