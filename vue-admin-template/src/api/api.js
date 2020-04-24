/*
 * @Date: 2020-04-16 02:35:50
 * @Author: zhen
 * @LastEditTime: 2020-04-25 00:34:52
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
// get contract list
export const getContractlist = (address, page) => get('/contractlist', { ownerAddress: address, pageIndex: page })
// send register post
export const registerVote = (registervote) => post('/registervote', registervote)
// search voteData
export const getVoteInfo = (getVoteInfo) => post('/voteinfo', getVoteInfo)
// cast vote
export const castVote = (castvote) => post('/castvote', castvote)
// end registration
export const endRegistration = (endcmd) => post('/endregistration', endcmd)
// end voting
export const endVoting = (endcmd) => post('/endvoting', endcmd)
// tally vote
export const tallyVoting = (tally) => post('/tallyvoting', tally)