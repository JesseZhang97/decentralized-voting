/*
 * @Date: 2020-03-13 22:22:04
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-13 22:47:50
 * @FilePath: /decentralized-voting/vapp/src/api/login.js
 * @Description: 
 */

import request from '@/utils/request' //引入封装好的 axios 请求
 
export function login(account, password) { //登录接口
  return request({ //使用封装好的 axios 进行网络请求
    url: '/admin/login',
    method: 'post',
    data: { //提交的数据
      account,
      password
    }
  })
}
