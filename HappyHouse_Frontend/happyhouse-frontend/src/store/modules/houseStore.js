import http from "@/util/http-common.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ sidoCode: "", sidoName: "시/도" }],
    guguns: [{ gugunCode: "", gugunName: "구/군" }],
    dongs: [{ dongCode: "", dongName: "동" }],
    houses: [],
    housespos: [],
    house: null,
  },
  mutations: {
    SET_SIDO_LIST(state, data) {
      state.sidos = [{ sidoCode: "", sidoName: "시/도" }];
      data.forEach((sido) => {
        state.sidos.push({ sidoCode: sido.sidoCode, sidoName: sido.sidoName });
      });
      //   console.log(state.sidos);
    },
    SET_GUGUN_LIST(state, data) {
      state.guguns = [{ gugunCode: "", gugunName: "구/군" }];
      data.forEach((gugun) => {
        state.guguns.push({
          gugunCode: gugun.gugunoCode,
          gugunName: gugun.gugunName,
        });
      });
    },
    SET_DONG_LIST(state, data) {
      state.dongs = [{ dongCode: "", dongName: "동" }];
      data.forEach((dong) => {
        state.dongs.push({ dongCode: dong.dongCode, dongName: dong.dongName });
      });
    },
    SET_APT_LIST(state, data) {
      state.houses = data;
      data.forEach((house) => {
        state.housespos.push({ lat: house.lat, lng: house.lng });
      });
      console.log(state.housespos);
    },
  },
  actions: {
    getSido({ commit }) {
      http
        .get("/house/sido")
        .then((response) => {
          //   console.log(response);
          // console.log(commit);
          commit("SET_SIDO_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      console.log(sidoCode);
      http
        .get(`/house/gugun`, { params })
        .then((response) => {
          // console.log(response);
          commit("SET_GUGUN_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      console.log(gugunCode);
      const params = { gugun: gugunCode };
      http
        .get(`/house/dong`, { params })
        .then((response) => {
          console.log(response.data);
          commit("SET_DONG_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getAptList({ commit }, dongCode) {
      //   console.log(dongCode);
      const params = { dong: dongCode };
      http
        .get(`/house/aptList`, { params })
        .then((response) => {
          console.log(response.data);
          commit("SET_APT_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  modules: {},
};

export default houseStore;
