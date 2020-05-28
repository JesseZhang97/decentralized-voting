/*
 * @Date: 2020-04-16 19:23:06
 * @Author: zhen
 * @LastEditTime: 2020-04-17 19:33:31
 * @Description:
 */
import { resetRouter } from '@/router'

export function resetTokenAndClearUser() {
    // 退出登陆 清除用户资料
    localStorage.setItem('token', '')
    localStorage.setItem('userName', '')
    localStorage.setItem('userAddress', '')
    // localStorage.removeItem('token')
    // localStorage.removeItem('userName')
    // localStorage.removeItem('userAddress')
    // 重设路由
    resetRouter()
}

export const defaultDocumentTitle = 'EtherVOTE'
export function getDocumentTitle(pageTitle) {
    if (pageTitle) return `${defaultDocumentTitle} - ${pageTitle}`
    return `${defaultDocumentTitle}`
}