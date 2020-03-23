/*
 * @Date: 2020-03-15 00:35:01
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-21 04:05:58
 * @FilePath: /decentralized-voting/vapp/src/main.js
 * @Description: 
 */

import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import axios from 'axios';
Vue.prototype.$axios = axios //全局注册，使用方法为:this.$axios

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
