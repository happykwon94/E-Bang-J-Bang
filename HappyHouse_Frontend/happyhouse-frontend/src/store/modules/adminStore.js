import { getUserList, kickOutUser } from "@/api/admin.js";

const adminStore = {
  namespaced: true,
  state: {
    userList: [],
    isDeleteUser: false,
  },
  mutations: {
    SET_USER_LIST(state, list) {
      state.userList = list;
    },
    SET_IS_DELETE_USER(state, isDeleteUser) {
      state.isDeleteUser = isDeleteUser;
    },
  },
  actions: {
    async getUserListAction({ commit }) {
      await getUserList(
        (response) => {
          commit("SET_USER_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async kickOutUserAction({ commit }, id) {
      await kickOutUser(
        id,
        (response) => {
          if (response.data == "success") {
            alert("회원이 추방되었습니다.");
            commit("SET_IS_DELETE_USER", true);
          } else {
            alert("삭제 중 문제가 발생했습니다.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  getters: {
    getUsetListGetter(state) {
      return state.userList;
    },
  },
};

export default adminStore;
