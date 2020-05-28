/*
 * @Date: 2020-04-16 01:21:17
 * @Author: zhen
 * @LastEditTime: 2020-04-30 21:22:36
 * @Description:
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const commonRoutes = [
    {
        path: '/login',
        name: 'login',
        meta: { title: '登录' },
        component: () => import('../components/Login.vue'),
    },
    {
        path: '/register',
        name: 'register',
        meta: { title: '注册' },
        component: () => import('../components/Register.vue'),
    },
    {
        path: '/infopage',
        name: 'infopage',
        component: () => import('../components/InfoPaper.vue'),
    },
    {
        path: '/404',
        name: '404',
        meta: { title: '404' },
        component: () => import('../components/404.vue'),
    },
    { path: '/', redirect: '/home' },
]

// 本地所有的页面 需要配合后台返回的数据生成页面
export const asyncRoutes = {
    home: {
        path: 'home',
        name: 'home',
        meta: { title: 'Home' },
        component: () => import('../views/Home.vue'),
    },
    // t1: {
    //     path: 't1',
    //     name: 't1',
    //     meta: { title: 'BlockChain' },
    //     component: () => import('../views/T1.vue'),
    // },
    createvote: {
        path: 'createvote',
        name: 'createvote',
        meta: { title: '发起投票' },
        component: () => import('../views/CreateVote.vue'),
    },
    msg: {
        path: 'msg',
        name: 'msg',
        meta: { title: '通知消息' },
        component: () => import('../views/Msg.vue'),
    },
    kovan: {
        path: '/kovan',
        name: 'kovan',
        meta: { title: '区块链网络' },
        beforeEnter() { window.open('https://kovan.etherscan.io/', '_blank') },
    },
    userinfo: {
        path: 'userinfo',
        name: 'userinfo',
        meta: { title: '用户信息' },
        component: () => import('../views/UserInfo.vue'),
    },
    contractlist: {
        path: 'contractlist',
        name: 'contractlist',
        meta: { title: '投票列表' },
        component: () => import('../views/ContractList.vue'),
    },
    registervote: {
        path: 'registervote',
        name: 'registervote',
        meta: { title: '注册投票' },
        component: () => import('../views/RegisterVote.vue'),
    },
    castvote: {
        path: 'castvote',
        name: 'castvote',
        meta: { title: 'castvote' },
        component: () => import('../views/CastVote.vue'),
    },
    tallyvote: {
        path: 'tallyvote',
        name: 'tallyvote',
        meta: { title: 'tallyvote' },
        component: () => import('../views/TallyVote.vue'),
    },
}

const createRouter = () => new Router({
    routes: commonRoutes,
})

const router = createRouter()

export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher
}

export default router