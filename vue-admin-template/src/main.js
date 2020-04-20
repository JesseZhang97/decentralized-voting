/*
 * @Date: 2020-04-17 19:12:28
 * @Author: zhen
 * @LastEditTime: 2020-04-17 21:07:05
 * @Description:
 */
import Vue from 'vue'
import axios from 'axios'
import ViewUI from 'view-design'
// import GeminiScrollbar from 'vue-gemini-scrollbar'
import App from './App'
import store from './store'
import router from './router'
import 'view-design/dist/styles/iview.css'
import './permission'

Vue.config.productionTip = false
Vue.use(ViewUI)
// Vue.use(GeminiScrollbar)

// // 设置基础URL
// axios.defaults.baseURL = ''
// 设置请求超时时间
axios.defaults.timeout = 5000

Vue.prototype.$axios = axios

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App),
})