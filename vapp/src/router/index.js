/*
 * @Date: 2020-03-15 03:27:40
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-04-10 22:10:43
 * @FilePath: /decentralized-voting/vapp/src/router/index.js
 * @Description:
 */
import Vue from "vue"; //引入 Vue
import VueRouter from "vue-router"; //引入 Vue 路由

Vue.use(VueRouter); //安装插件

export const constantRouterMap = [
  //配置默认的路径，默认显示登录页
  { path: "/", redirect: "/login" },

  {
    path: "/login",
    component: () => import("@/views/Login")
  },

  { path: "/register", component: () => import("@/views/Register") },

  {
    path: "/home",
    component: () => import("@/views/Home"),
    meta: {
      requireAuth: true
    }
  },

  {
    path: "/infopage",
    component: () => import("@/views/InfoPaper")
  },

  {
    path: "/home/createvote",
    component: () => import("@/views/CreateVote")
  },

  {
    path: "/registervote",
    component: () => import("@/views/RegisterVote")
  }
];

export default new VueRouter({
  mode: "history", //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap //指定路由列表
});
