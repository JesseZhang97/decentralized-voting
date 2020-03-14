/*
 * @Date: 2020-03-13 22:22:46
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-13 22:41:29
 * @FilePath: /decentralized-voting/vapp/src/store/module/user.js
 * @Description: 
 */
import { login } from '@/api/login'//引入登录 api 接口
 
const user = {
  actions: {
    // 登录
    Login({ commit }, userInfo) { //定义 Login 方法，在组件中使用 this.$store.dispatch("Login") 调用
      const account = userInfo.account.trim()
      return new Promise((resolve, reject) => { //封装一个 Promise
        login(account, userInfo.password).then(response => { //使用 login 接口进行网络请求
          commit('') //提交一个 mutation，通知状态改变
          resolve(response) //将结果封装进 Promise
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}

export default user