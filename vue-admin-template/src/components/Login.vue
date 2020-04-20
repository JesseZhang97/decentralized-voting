/* eslint-disable no-var */
<template>
    <div
        class="login-vue"
        :style="bg"
    >
        <div class="container">
            <p class="title">EtherVOTE</p>
            <div class="input-c">
                <Input
                    prefix="ios-contact"
                    v-model="loginInfo.account"
                    placeholder="用户名"
                    clearable
                    @on-blur="verifyAccount"
                />
                <p class="error">{{accountError}}</p>
            </div>
            <div class="input-c">
                <Input
                    type="password"
                    v-model="loginInfo.password"
                    prefix="md-lock"
                    placeholder="密码"
                    clearable
                    @on-blur="verifyPwd"
                />
                <p class="error">{{pwdError}}</p>
            </div>
            <Button
                :loading="isShowLoading"
                class="submit"
                type="primary"
                @click="submit"
            >登陆</Button>
            <p class="account">
                <span @click="register">注册账号</span> |
                <span @click="forgetPwd">忘记密码</span>
            </p>
        </div>
    </div>
</template>

<script>
import { login } from '../api/api.js'

export default {
    name: 'login',
    data() {
        return {
            loginInfo: {
                account: '',
                password: '',
            },
            accountError: '',
            pwdError: '',
            isShowLoading: false,
            bg: {},
        }
    },
    created() {
        this.bg.backgroundImage = 'url('
            + require('../assets/imgs/bg0' + new Date().getDay() + '.jpg')
            + ')'
    },
    watch: {
        $route: {
            handler(route) {
                this.redirect = route.query && route.query.redirect
            },
            immediate: true,
        },
    },
    methods: {
        verifyAccount() {
            if (this.loginInfo.account === '') {
                this.accountError = '请输入帐号！'
            } else {
                this.accountError = ''
            }
        },
        verifyPwd() {
            if (this.loginInfo.password === '') {
                this.pwdError = '请输入密码'
            } else {
                this.pwdError = ''
            }
        },
        register() {
            this.$router.push({ path: '/register' })
        },
        forgetPwd() {},
        submit() {
            const sha256 = require('js-sha256').sha256
            if (this.loginInfo.account != '' && this.loginInfo.password != '') {
                this.loginInfo.password = sha256(this.loginInfo.password)
                this.isShowLoading = true
                login(this.loginInfo)
                .then(res => {
                    let code = res.data.code
                    let name = res.data.data
                    let pubkey = res.data.msg
                    if (code === 200) {
                        // 登陆成功 设置用户信息
                        localStorage.setItem('userName', name)
                        localStorage.setItem('userAddress', pubkey)
                        // 登陆成功 假设这里是后台返回的 token
                        localStorage.setItem('token', 'i_am_token')
                        this.$router.push({ path: this.redirect || '/' })
                        // this.$router.replace({ path: '/home' })
                        this.isShowLoading = false
                        console.log(code)
                    } else {
                        this.isShowLoading = false
                        this.loginInfo.account = ''
                        this.loginInfo.password = ''
                        this.$Notice.error({
                            title: '帐号或密码错误',
                        })
                        console.log(code)
                    }
                })
            } else {
                if (this.loginInfo.account === '') {
                    this.accountError = '帐号不能为空'
                    this.$Notice.error({
                        title: '帐号不能为空',
                    })
                }
                if (this.loginInfo.password === '') {
                    this.pwdError = '密码不能为空'
                    this.$Notice.error({
                        title: '密码不能为空',
                    })
                }
            }
        },
    },
}
</script>

<style>
.login-vue {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #fff;
}
.login-vue .container {
    background: rgba(255, 255, 255, 0.5);
    width: 300px;
    text-align: center;
    border-radius: 10px;
    padding: 30px;
}
.login-vue .ivu-input {
    background-color: transparent;
    color: #fff;
    outline: #fff;
    border-color: #fff;
}
.login-vue ::-webkit-input-placeholder {
    /* WebKit, Blink, Edge */
    color: rgba(255, 255, 255, 0.8);
}
.login-vue :-moz-placeholder {
    /* Mozilla Firefox 4 to 18 */
    color: rgba(255, 255, 255, 0.8);
}
.login-vue ::-moz-placeholder {
    /* Mozilla Firefox 19+ */
    color: rgba(255, 255, 255, 0.8);
}
.login-vue :-ms-input-placeholder {
    /* Internet Explorer 10-11 */
    color: rgba(255, 255, 255, 0.8);
}
.login-vue .title {
    font-size: 16px;
    margin-bottom: 20px;
}
.login-vue .input-c {
    margin: auto;
    width: 200px;
}
.login-vue .error {
    color: red;
    text-align: left;
    margin: 5px auto;
    font-size: 12px;
    padding-left: 30px;
    height: 20px;
}
.login-vue .submit {
    width: 200px;
}
.login-vue .account {
    margin-top: 30px;
}
.login-vue .account span {
    cursor: pointer;
}
.login-vue .ivu-icon {
    color: #eee;
}
.login-vue .ivu-icon-ios-close-circle {
    color: #777;
}
</style>
