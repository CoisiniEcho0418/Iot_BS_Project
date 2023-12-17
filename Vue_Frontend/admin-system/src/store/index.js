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
const mutations = {};

// 用于存储数据
const state = {};

// 创建store
const store = new Vuex.Store({
  actions,
  mutations,
  state
});

// 暴露store 使其他VueComponents可以使用   简称vc组件实例对象
export default store;
