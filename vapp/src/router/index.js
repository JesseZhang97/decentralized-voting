/*
 * @Date: 2020-03-13 17:50:04
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-13 22:00:28
 * @FilePath: /decentralized-voting/vapp/src/router/index.js
 * @Description: 
 */
import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
Vue.use(VueRouter)


const constantRouterMap = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path:'/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register')
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('@/views/Home')
  }
]

const router = new VueRouter({
  routes: constantRouterMap
})

export default router