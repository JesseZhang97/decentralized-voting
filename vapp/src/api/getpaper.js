/*
 * @Date: 2020-03-21 04:15:51
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-21 04:29:44
 * @FilePath: /decentralized-voting/vapp/src/api/getpaper.js
 * @Description: 
 */
import request from '@/utils/request'

export function getPaper(id) {
  return request({
    url:'/api/wallet',
    method: 'post',
    data: {
      id
    }
  })
}