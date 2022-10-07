import { createStore } from "vuex";
import Vue from "vue";
import createPersistedState from "vuex-persistedstate"

export default createStore({
  plugins: [createPersistedState()],
  state: {
    Authorization: "",
    user: { isAdmin: false, email: "", nickname: "" },
    fundingDetail: {},
    supporters:[],
    history: {},
    searchFundings: [],
  },
  getters: {},
  mutations: {
    registUser(state, payload) {
      state.user.email = payload.email;
      state.user.isAdmin = payload.isAdmin;
      state.user.nickname = payload.nickname;
      state.Authorization = 'Bearer '+ payload.accessToken;
    },
    logout(state){
      state.user.email = "";
      state.user.isAdmin = "";
      state.user.nickname = "";
      state.Authorization = "";
    },
    SET_CHANGENICK(state, payload){
      state.user.nickname = payload
    },
    setFundingDetail(state, payload) {
      state.fundingDetail = payload;
    },
    setSupporters(state, payload) {
      state.supporters = payload;
    },
    setHistory(state, payload) {
      state.history = payload;
    },
    deleteData(state){
      state.fundingDetail = {};
      state.supporters = [];
      state.history = {};
    },
    SearchFunding(state, payload){
      state.searchFundings = payload
    }
    
  },
  actions: {},
  modules: {},
});
