/*
 * @Date: 2020-04-17 19:12:28
 * @Author: zhen
 * @LastEditTime: 2020-04-25 00:27:22
 * @Description:
 */
import Vue from 'vue'
import axios from 'axios'
import ViewUI from 'view-design'
import VCharts from 'v-charts'
// import VeBar from 'v-charts/lib/bar.common'
// import VePie from 'v-charts/lib/pie.common'
// import GeminiScrollbar from 'vue-gemini-scrollbar'
import App from './App'
import store from './store'
import router from './router'
import 'view-design/dist/styles/iview.css'
import './permission'

// Vue.component('VeBar', VeBar)
// Vue.component('VePie', VePie)
Vue.use(VCharts)
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