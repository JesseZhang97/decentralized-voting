/*
 * @Date: 2020-03-17 00:54:59
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 00:54:59
 * @FilePath: /decentralized-voting/vapp/src/store/index.js
 * @Description: 
 */
import Vue from 'vue' //引入 Vue
import Vuex from 'vuex' //引入 Vuex
import user from './modules/user' //引入 user module
import userRegister from './modules/userRegister'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user, //使用 user.js 中的 action
    userRegister
  }
})

export default store