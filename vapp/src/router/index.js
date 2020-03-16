/*
 * @Date: 2020-03-15 03:27:40
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-16 21:28:44
 * @FilePath: /decentralized-voting/vapp/src/router/index.js
 * @Description: 
 */
import Vue from 'vue' //引入 Vue
import VueRouter from 'vue-router' //引入 Vue 路由

Vue.use(VueRouter); //安装插件

export const constantRouterMap = [
    //配置默认的路径，默认显示登录页
    {   path: '/',
        redirect: '/login'
    },

    {
        path: '/login',
        component: () => import('@/views/Login')
    },

    //配置登录成功页面，使用时需要使用 path 路径来实现跳转
    { path: '/register', component: () => import('@/views/Register')},

    //配置登录失败页面，使用时需要使用 path 路径来实现跳转
    { path: '/home', component: () => import('@/views/Home')}
]

export default new VueRouter({
    mode: 'history', //后端支持可开
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRouterMap //指定路由列表
})