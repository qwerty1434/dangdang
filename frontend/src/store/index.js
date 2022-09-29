import { createStore } from "vuex";
import Vue from "vue";
import createPersistedState from "vuex-persistedstate"

export default createStore({
  plugins: [createPersistedState()],
  state: {
    Authorization: "",
    user: { isAdmin: false, email: "", nickname: "" },
  },
  getters: {},
  mutations: {
    registUser(state, payload) {
      state.user.email = payload.email;
      state.user.isAdmin = payload.isAdmin;
      state.user.nickname = payload.nickname;
      state.Authorization = 'Bearer '+ payload.accessToken;
    },
    SET_CHANGENICK(state, payload){
      state.user.nickname = payload
    },

  },
  actions: {},
  modules: {},
});
