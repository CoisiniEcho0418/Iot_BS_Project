// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router";
import "./assets/css/global.css";
// 导入 ElementUI
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
//  导入font-awesome （图标库）
import "font-awesome/css/font-awesome.min.css";
// 全局引入 echarts
import * as echarts from "echarts";
// 引入 axios
import axios from "axios";
// 引入moment（获取当前时间的工具）
import moment from "moment";
// 引入vue-baidu-map，（百度地图SDK）
import BaiduMap from "vue-baidu-map";

// 使用百度地图SDK
Vue.use(BaiduMap, { ak: "9oFarDYWLF2rtgQ1zp0BCIxcjihqsqda" });

// 将 axios 挂载到 Vue 上
Vue.prototype.$axios = axios;
Vue.config.productionTip = false;

// 使用 ElementUI
Vue.use(ElementUI);
// 将 echarts 挂载到 Vue 上
Vue.prototype.$echarts = echarts;

// 将moment挂载到Vue上
Vue.prototype.$moment = moment;

// 设置接口请求的前缀地址
axios.defaults.baseURL = "/admin-system";
// 全局设置token
axios.interceptors.request.use(function(config) {
  let token = sessionStorage.getItem("token");
  if (token) {
    config.headers["token"] = token;
  }
  return config;
});
/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  components: { App },
  template: "<App/>"
});
