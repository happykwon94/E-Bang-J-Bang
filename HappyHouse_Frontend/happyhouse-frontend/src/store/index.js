import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import houseStore from "@/store/modules/houseStore.js";
import userStore from "@/store/modules/userStore.js";
import newsStore from "@/store/modules/newsStore.js";

const store = new Vuex.Store({
  modules: {
    houseStore,
    userStore,
    newsStore,
  },
  plugins: [
    createPersistedState({
      // 새로 고침이나 뒤로 가기를 하더라도 데이터가 초기화되지 않도록 함
      storage: sessionStorage,
    }),
  ],
});

export default store;
