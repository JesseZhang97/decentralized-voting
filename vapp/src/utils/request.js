/*
 * @Date: 2020-03-13 22:21:02
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-13 22:48:34
 * @FilePath: /decentralized-voting/vapp/src/utils/request.js
 * @Description: 
 */
import axios from 'axios' //引入 axios

// 创建 axios 实例
const service = axios.create({
  baseURL: 'localhost:8085', // api 的 base_url
  timeout: 15000, // 请求超时时间
})

export default service