/*
 * @Date: 2020-03-17 00:29:21
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 00:32:15
 * @FilePath: /decentralized-voting/vapp/src/api/register.js
 * @Description: 注册API
 */
import request from '@/utils/request'

export function register (account, password, name, organization ) {
  return request ({
    url: '/api/register',
    method: 'post',
    data: {
      account,
      password,
      name,
      organization
    }
  })
}