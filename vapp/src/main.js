/*
 * @Date: 2020-03-15 00:35:01
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-04-15 23:47:01
 * @FilePath: /decentralized-voting/vapp/src/main.js
 * @Description:
 */

import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./plugins/element.js";
import axios from "axios";
Vue.prototype.$axios = axios; //全局注册，使用方法为:this.$axios

Vue.config.productionTip = false;

// router.beforeEach((to, from, next) => {
//   if (to.path === "/home") {
//     // 要做登录限制的路由
//     if (localStorage.getItem("userId")) {
//       // 判断登录状态
//       next();
//     } else {
//       next();
//       next("/login");
//     }
//   } else {
//     next();
//     next("/404");
//   }
// });

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
