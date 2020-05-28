<!--
 * @Date: 2020-04-16 01:21:43
 * @Author: zhen
 * @LastEditTime: 2020-04-16 23:22:30
 * @Description:
 -->
<template>
    <div
        class="login-vue"
        :style="bg"
    >
        <div class="container">
            <p class="title">EtherVOTE</p>
            <p class="title">注册新用户</p>
            <div class="input-c">
                <Input
                    prefix="ios-contact"
                    v-model="userInfo.account"
                    placeholder="用户名"
                    clearable
                    @on-blur="verifyAccount"
                />
                <p class="error">{{accountError}}</p>
            </div>
            <div class="input-c">
                <Input
                    type="password"
                    v-model="userInfo.password"
                    prefix="md-lock"
                    placeholder="密码"
                    clearable
                    @on-blur="verifyPwd"
                />
                <p class="error">{{pwdError}}</p>
            </div>
            <div class="input-c">
                <Input
                    v-model="userInfo.name"
                    prefix="md-person"
                    placeholder="姓名"
                    clearable
                    @on-blur="verifyName"
                />
                <p class="error">{{nameError}}</p>
            </div>
            <div class="input-c">
                <Input
                    v-model="userInfo.email"
                    prefix="md-mail"
                    placeholder="Email"
                    clearable
                    @on-blur="verifyEmail"
                />
                <p class="error">{{emailError}}</p>
            </div>
            <div class="input-c">
                <Input
                    v-model="userInfo.organization"
                    prefix="md-contacts"
                    placeholder="所属组织"
                    clearable
                    @on-blur="verifyOrg"
                />
                <p class="error">{{orgError}}</p>
            </div>
            <Button
                :loading="isShowLoading"
                class="submit"
                type="primary"
                @click="submit"
            >注册</Button>
        </div>
    </div>
</template>

<script>
import { register } from '../api/api.js'

export default {
    name: 'register',
    data() {
        return {
            userInfo: {
                account: '',
                password: '',
                name: '',
                email: '',
                organization: '',
            },
            accountError: '',
            pwdError: '',
            nameError: '',
            emailError: '',
            orgError: '',
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
            if (this.userInfo.account === '') {
                this.accountError = '请输入帐号！'
            } else {
                this.accountError = ''
            }
        },
        verifyPwd() {
            if (this.userInfo.password === '') {
                this.pwdError = '请输入密码'
            } else {
                this.pwdError = ''
            }
        },
        verifyName() {
            let reg = /^[\u4e00-\u9fa5]/
            if (this.userInfo.name === '') {
                this.nameError = '请输入姓名'
            } else if (!reg.test(this.userInfo.name)) {
                this.nameError = '请输入中文姓名'
            } else {
                this.nameError = ''
            }
        },
        verifyEmail() {
            // eslint-disable-next-line no-useless-escape
            let reg = /^([A-Za-z0-9_\-\.\u4e00-\u9fa5])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,8})$/
            if (this.userInfo.email === '') {
                this.emailError = '请输入邮箱地址'
            } else if (!reg.test(this.userInfo.email)) {
                this.emailError = '请输入正确的邮箱地址'
            } else {
                this.emailError = ''
            }
        },
        verifyOrg() {
            let reg = /^[\u4e00-\u9fa5]/
            if (this.userInfo.organization === '') {
                this.orgError = '请输入组织名称'
            } else if (!reg.test(this.userInfo.name)) {
                this.orgError = '请输入中文名称'
            } else {
                this.orgError = ''
            }
        },
        submit() {
            const sha256 = require('js-sha256').sha256
            if (this.userInfo.account != ''
                && this.userInfo.password != ''
                && this.userInfo.name != ''
                && this.userInfo.email != ''
                && this.userInfo.organization != '') {
                this.userInfo.password = sha256(this.userInfo.password)
                this.isShowLoading = true
                register(this.userInfo)
                .then(response => {
                    let code = response.data.code
                    if (code == 200) {
                        this.$Notice.success({
                            title: '注册成功,即将转入信息确认页面',
                        })
                        this.$router.push({
                            path: '/infopage',
                            query: { data: response.data.data },
                            // query: { data: response.data.data }
                        })
                        // this.$router.push({ path: this.redirect || '/infopage' })
                        this.isShowLoading = false
                    } else {
                        this.isShowLoading = false
                        this.userInfo.account = ''
                        this.userInfo.password = ''
                        this.userInfo.name = ''
                        this.userInfo.email = ''
                        this.userInfo.organization = ''
                        this.$Notice.error({
                            title: '帐号注册失败请重试',
                        })
                    }
                })
            } else {
                if (this.userInfo.account == '') {
                    this.accountError = '帐号不能为空'
                    this.$Notice.error({
                        title: '帐号不能为空',
                    })
                }

                if (this.userInfo.password == '') {
                    this.pwdError = '密码不能为空'
                    this.$Notice.error({
                        title: '密码不能为空',
                    })
                }

                if (this.userInfo.name == '') {
                    this.nameError = '姓名不能为空'
                    this.$Notice.error({
                        title: '姓名不能为空',
                    })
                }

                if (this.userInfo.email == '') {
                    this.emailError = 'email不能为空'
                    this.$Notice.error({
                        title: 'email不能为空',
                    })
                }

                if (this.userInfo.organization == '') {
                    this.orgError = '组织名称不能为空'
                    this.$Notice.error({
                        title: '组织名称不能为空',
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
