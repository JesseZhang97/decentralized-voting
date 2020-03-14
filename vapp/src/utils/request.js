/*
 * @Date: 2020-03-15 03:39:41
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-15 03:42:23
 * @FilePath: /decentralized-voting/vapp/src/utils/request.js
 * @Description: 
 */
import axios from 'axios' //引入 axios
//import baseUrl from '../api/baseUrl' //使用环境变量 + 模式的方式定义基础URL

// 创建 axios 实例
const service = axios.create({
  baseURL: 'http://localhost:8085', // api 的 base_url
  timeout: 15000, // 请求超时时间
})

export default service
