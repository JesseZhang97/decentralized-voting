/*
 * @Date: 2020-03-17 00:33:37
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 01:20:33
 * @FilePath: /decentralized-voting/vapp/src/store/modules/userRegister.js
 * @Description: 
 */
import { register } from '@/api/register'

const userRegister = {
  actions: {
    //注册
    Register({ commit }, userInfo) {
      // const account = userInfo.account.trim()
      return new Promise((resolve, reject) => {
        register(userInfo.account, userInfo.password,userInfo.name, userInfo.organization)
          .then(response => {
            commit('')
            resolve(response)
          }).catch(error => {
            reject(error)
          })
      })
    },
  }
}
export default userRegister