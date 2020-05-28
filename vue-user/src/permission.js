/*
 * @Date: 2020-04-16 01:26:14
 * @Author: zhen
 * @LastEditTime: 2020-04-17 19:22:39
 * @Description:
 */
// import { LoadingBar } from 'view-design'
import router from './router'
import store from './store'
import createRoutes from '@/utils/createRoutes'
import { getDocumentTitle, resetTokenAndClearUser } from './utils'

// 是否有菜单数据
let hasMenus = false
router.beforeEach(async (to, from, next) => {
    document.title = getDocumentTitle(to.meta.title)
    // LoadingBar.start()
    if (localStorage.getItem('token')) {
        if (to.path === '/login') {
            next({ path: '/' })
        } else if (hasMenus) {
            next()
        } else {
            try {
                const routes = createRoutes(store.state.menuItems)
                // 动态添加路由
                router.addRoutes(routes)
                hasMenus = true
                next({ path: to.path || '/' })
            } catch (error) {
                resetTokenAndClearUser()
                next(`/login?redirect=${to.path}`)
            }
        }
    } else {
        hasMenus = false
        if (to.path === '/login') {
            next()
        } else if (to.path === '/register') {
            next()
        } else if (to.path === '/infopage') {
            next()
        } else {
            next(`/login?redirect=${to.path}`)
        }
    }
})

// router.afterEach(() => {
//     LoadingBar.finish()
// })