/*
 * @Date: 2020-04-16 01:11:47
 * @Author: zhen
 * @LastEditTime: 2020-04-24 17:49:29
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
            {
                text: '区块链投票',
                type: 'md-analytics',
                children: [
                    {
                        name: 'msg',
                        size: 18,
                        type: 'md-information-circle',
                        text: '区块链信息',
                    },
                    {
                        text: '设定投票',
                        type: 'md-checkbox',
                        children: [
                            {
                                name: 'createvote',
                                type: 'ios-paper',
                                text: '发起投票',
                            },
                            {
                                name: 'contractlist',
                                type: 'md-list-box',
                                text: '投票列表',
                            },
                            // {
                            //     type: 'md-person',
                            //     name: 'userinfo',
                            //     text: '基本资料',
                            // },
                        ],
                    },
                    {
                        text: '参与投票',
                        type: 'md-create',
                        children: [
                            {
                                name: 'registervote',
                                type: 'md-finger-print',
                                text: '注册投票',
                            },
                            {
                                name: 'castvote',
                                type: 'md-done-all',
                                text: '给出选票',
                            },
                        ],
                    },
                    {
                        type: 'md-stats',
                        name: 'tallyvote',
                        text: '投票结果与验证',
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