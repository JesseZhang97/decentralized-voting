/*
 * @Date: 2020-03-13 22:00:00
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-13 22:42:23
 * @FilePath: /decentralized-voting/vapp/src/store/index.js
 * @Description: 
 */
import Vue from 'vue'
import Vuex from 'vuex'
import user from './module/user' 

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user
  }
})
