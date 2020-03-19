/*
 * @Date: 2020-03-15 00:35:01
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 20:47:02
 * @FilePath: /decentralized-voting/vapp/src/main.js
 * @Description: 
 */

import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
var axios = require('axios')
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
