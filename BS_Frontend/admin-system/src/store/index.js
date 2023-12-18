// 创建Vuex核心 store

// 第一步引入Vuex
import Vuex from "vuex";

// 引入Vue
import Vue from "vue";

// 使用Vuex
Vue.use(Vuex);

// 响应组件中的动作
const actions = {};

// 操作数据（state）
const mutations = {
  // 修改token和用户信息，并将token和用户信息存入localStorage
  changeLogin(state, user) {
    state.Authorization = user.token;
    state.email = user.email;
    state.phone = user.phone;
    state.userId = user.userId;
    state.username = user.username;

    localStorage.setItem("Authorization", user.token);
    localStorage.setItem("email", user.email);
    localStorage.setItem("phone", user.phone);
    localStorage.setItem("userId", user.userId);
    localStorage.setItem("username", user.username);
  },
  // 登出清空用户信息和token
  logout(state) {
    state.Authorization = "";
    state.email = "";
    state.phone = "";
    state.userId = "";
    state.username = "";

    localStorage.removeItem("Authorization");
    localStorage.removeItem("email");
    localStorage.removeItem("phone");
    localStorage.removeItem("userId");
    localStorage.removeItem("username");
  }
};

// 用于存储数据
const state = {
  // 存储用户信息
  Authorization: localStorage.getItem("Authorization") || "", // token
  email: localStorage.getItem("email") || "",
  phone: localStorage.getItem("phone") || "",
  userId: localStorage.getItem("userId") || "",
  username: localStorage.getItem("username") || ""
};

// 创建store
const store = new Vuex.Store({
  actions,
  mutations,
  state
});

// 暴露store 使其他VueComponents可以使用   简称vc组件实例对象
export default store;
