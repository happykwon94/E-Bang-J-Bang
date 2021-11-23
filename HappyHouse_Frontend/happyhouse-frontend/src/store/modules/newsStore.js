import { getHeadlineList } from "@/api/news.js";

const newsStore = {
  namespaced: true,
  state: {
    headlines: [],
  },
  getters: {},
  mutations: {
    SET_HEADLINES(state, list) {
      state.headlines = list;
    },
  },
  actions: {
    getHeadline({ commit }) {
      getHeadlineList(
        (response) => {
          commit("SET_HEADLINES", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default newsStore;
