<!--
 * @Date: 2020-03-19 02:36:25
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-04-24 20:27:05
 * @FilePath: /decentralized-voting/vapp/src/views/InfoPaper.vue
 * @Description:
-->
<template>
    <Layout>
        <Content class="content">
            <h1>注册信息</h1>
            <h4>*请务必认真阅读以下注意事项</h4>
            <Card class="card">
                <div class="text item">
                    <li>点击下方按钮将展示出系统发放的公钥、私钥、助记词。</li>
                    <li>私钥与助记词为唯一有效凭证,隐私性要高于账户密码。</li>
                    <li>请在安全可信任的环境下提取并记录私钥与助记词。</li>
                    <li>用户可通过下方下载按钮保存凭证或自行记录凭证内容。</li>
                    <li>生成二维码用于用户注册后到相关认证处进行认证绑定。</li>
                    <li>如已确认领取环境安全请点击"我已知晓"按钮。</li>
                    <Button
                        style="display:block;margin:0 auto"
                        type="warning"
                        @click="countDown"
                    >{{ bt_content }}</Button>
                </div>
            </Card>
        </Content>
        <Content v-if="secretVisible">
            <Card class="card2">
                <Row :gutter="16">
                    <Col span="6">
                    <img
                        :src="img"
                        class="image"
                    />
                    </Col>
                    <Col :span="16">
                    <Form
                        :model="form"
                        :label-width="80"
                    >
                        <FormItem label="助记词">
                            <Input v-model="form.mnemonic"></Input>
                        </FormItem>
                        <FormItem label="私钥">
                            <Input v-model="form.prikey"></Input>
                        </FormItem>
                        <FormItem label="公钥">
                            <Input v-model="form.pubkey"></Input>
                        </FormItem>
                        <Col
                            :span="12"
                            style="text-align: center"
                        >
                        <Button
                            icon="ios-download-outline"
                            type="info"
                            shape="circle"
                            @click="download"
                        ></Button>
                        </Col>
                        <Col :span="6"><Button
                            type="primary"
                            @click="redirectToLogin"
                        >返回登录</Button></Col>
                    </Form>
                    </Col>

                </Row>

            </Card>
        </Content>
    </Layout>
</template>

<script>
export default {
    name: 'InfoPaper',
    data() {
        return {
            img: '',
            secretVisible: false,
            timer: 5,
            canClick: true,
            bt_content: '我已知晓',
            form: {
                mnemonic: '',
                prikey: '',
                pubkey: '',
            },
        }
    },
    created() {
        this.fetchData()
    },
    methods: {
        countDown() {
            if (!this.canClick) return
            this.canClick = false
            this.bt_content = this.timer + 's后显示'
            let clock = window.setInterval(() => {
                this.timer--
                this.bt_content = this.timer + 's后显示'
                if (this.timer < 0) {
                    window.clearInterval(clock)
                    this.bt_content = ':)'
                    this.confirm()
                }
            }, 1000)
        },
        fetchData() {
            this.$axios({
                method: 'get',
                url: 'http://localhost:8085/api/wallet',
                params: {
                    id: this.$route.query.data,
                },
            })
            // let id = this.$route.query.data
            // wallet(id)
            .then(response => {
                this.form.mnemonic = response.data.mnemonic
                this.form.prikey = response.data.privatekey
                this.form.pubkey = response.data.publickey
                this.img = response.data.qrcode
                // console.log(response.data.mnemonic)
                // console.log(response.data.privatekey)
                // console.log(response.data.publickey)
            })
            .catch(error => {
                console.log(error)
            })
        },
        confirm() {
            this.secretVisible = true
        },
        download() {
            let FileSaver = require('file-saver')
            let blob = new Blob(
                [
                    '私钥助记词：'
            + this.form.mnemonic
            + '\n'
            + '私钥：'
            + this.form.prikey
            + '\n'
            + '地址：'
            + this.form.pubkey,
                ],
                { type: 'text/plain;charset=utf-8' }
            )
            FileSaver.saveAs(blob, 'EthereumKeyPair.txt')
        },
        redirectToLogin() {
            this.$router.replace({ path: '/login' })
        },
    },
}
</script>

<style>
.content {
    background-color: #e9eef3;
    color: #333;
    text-align: center;
    line-height: 80px;
}
.text {
    font-size: 14px;
}
.item {
    text-align: center;
}
.card {
    width: 45%;
    margin: 20px auto;
    line-height: 40px;
    text-align: left;
}
.card2 {
    width: 80%;
    margin: 20px auto;
    line-height: 40px;
    text-align: left;
}
.image {
    width: 50%;
    display: block;
}
/*
找到html标签、body标签，和挂载的标签
都给他们统一设置样式
*/
html,
body,
#app,
.el-container {
    /*设置内部填充为0，几个布局元素之间没有间距*/
    padding: 0px;
    /*外部间距也是如此设置*/
    margin: 0px;
    /*统一设置高度为100%*/
    height: 100%;
}
</style>