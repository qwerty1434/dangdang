import { createStore } from "vuex";

export default createStore({
  state: {
    Authorization: "",
    user: { isAdmin: false, email: "", nickname: "" },
  },
  getters: {},
  mutations: {
    joinUser(state, payload) {
      state.user.email = payload.email;
      state.user.isAdmin = payload.isAdmin;
      state.user.nickname = payload.nickname;
      state.Authorization = payload.accessToken;
    },
  },
  actions: {},
  modules: {},
});
