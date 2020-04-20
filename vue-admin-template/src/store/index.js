/*
 * @Date: 2020-04-16 01:11:47
 * @Author: zhen
 * @LastEditTime: 2020-04-17 19:15:44
 * @Description:
 */
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        // 左侧菜单栏数据
        menuItems: [
            {
                name: 'home', // 要跳转的路由名称 不是路径
                size: 18, // icon大小
                type: 'md-home', // icon类型
                text: '主页', // 文本内容
            },

            // {
            //     name: 'createvote',
            //     size: 18,
            //     type: 'ios-paper',
            //     text: '发起投票',
            // },
            // {
            //     name: 'msg',
            //     size: 18,
            //     type: 'md-list-box',
            //     text: '投票列表',
            // },
            // {
            //     name: 'userinfo',
            //     size: 18,
            //     type: 'md-settings',
            //     text: '个人设置',
            // },
            //
            //
            //
            //
            //
            //
            {
                text: '区块链投票',
                type: 'ios-construct',
                children: [
                    {
                        name: 't1',
                        size: 18,
                        type: 'md-information-circle',
                        text: '区块链信息',
                    },
                    {
                        text: '投票',
                        type: 'ios-paper',
                        children: [
                            {
                                name: 'createvote',
                                type: 'ios-paper',
                                text: '发起投票',
                            },
                            {
                                name: 'msg',
                                type: 'md-list-box',
                                text: '投票列表',
                            },
                            {
                                type: 'md-person',
                                name: 'userinfo',
                                text: '基本资料',
                            },
                        ],
                    },
                ],
            },
            {
                name: 'userinfo',
                size: 18,
                type: 'md-settings',
                text: '个人设置',
            },
        ],
    },
    mutations: {
        setMenus(state, items) {
            state.menuItems = [...items]
        },
    },
})

export default store