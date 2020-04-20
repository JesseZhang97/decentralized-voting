/*
 * @Date: 2020-04-16 02:35:50
 * @Author: zhen
 * @LastEditTime: 2020-04-18 23:11:43
 * @Description:
 */

import { get, post } from './request'
// 登陆
export const login = (login) => post('/login', login)
// 注册
export const register = (register) => post('/register', register)
// FIXME wallet info
// 获取org list
export const getOrglist = () => get('/orglist')
// 获取voter list
export const getVoterlist = (selectorg) => get('/voterlist', { orgname: selectorg })
// send setup vote data
export const setup = (setup) => post('/setup', setup)