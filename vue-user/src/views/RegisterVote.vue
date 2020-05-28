<!--
 * @Date: 2020-04-21 23:24:47
 * @Author: zhen
 * @LastEditTime: 2020-04-30 23:58:42
 * @Description:
 -->
<template>
    <Layout>
        <Content class="content">
            <Card class="calss">
                <div class="text item">
                    <li>*注册投票说明*</li>
                    <li>确认注册投票后将认为注册用户确认能给出自己的选票</li>
                    <li>如果用户注册后没有在投票期给出选票不仅视为弃票同时会有相应惩罚</li>
                    <Input
                        v-model="sendData.contractAddress"
                        placeholder="输入您要注册的投票地址"
                        style="width: 400px"
                    />
                    <br>
                    <Input
                        type="password"
                        v-model="sendData.callerPRIVATEKEY"
                        placeholder="输入连接区块链网络所需密钥"
                        style="width: 400px"
                    />
                    <Button
                        type="warning"
                        style="display:block;margin:0 auto"
                        @click="submit"
                    >确认注册投票</Button>
                </div>
            </Card>
            <Modal v-model="modalLoading">
                <Spin
                    size="large"
                    fix
                    v-if="spinShow"
                >
                    <Icon
                        type="ios-loading"
                        size=18
                        class="demo-spin-icon-load"
                    ></Icon>等待区块链网络提交并同步数据中
                </Spin>
                <p v-if="spinShow"></p>
                <!-- <div style="text-align:center">
                    <img
                        v-if="!spinShow"
                        src="../assets/imgs/check.gif"
                        height="150"
                        width="150"
                    >
                </div> -->
                <p v-if="!spinShow">区块链记录地址为：{{ txAddress }} </p>
                <p v-if="!spinShow">网络链接</p>
            </Modal>
        </Content>
    </Layout>
</template>

<script>
import { registerVote } from '../api/api.js'

const privateKeyToAddress = require('ethereum-private-key-to-address')

export default {
    name: 'registervote',
    data() {
        return {
            sendData: {
                contractAddress: '',
                callerPRIVATEKEY: '',
            },
            txAddress: '',
            modalLoading: false,
            spinShow: false,
        }
    },
    methods: {
        submit() {
            if (this.sendData.contractAddress == '' || this.sendData.privatekey == '') {
                this.$Notice.error({
                    title: '请填写所需信息',
                })
            } else if (privateKeyToAddress(this.sendData.callerPRIVATEKEY) === localStorage.getItem('userAddress')) {
                this.modalLoading = true
                this.spinShow = true
                // post backend
                registerVote(this.sendData)
                .then(res => {
                    this.txAddress = res.data
                    this.spinShow = false
                })
                .catch(() => {
                    this.txAddress = false
                    this.$Modal.error({
                        title: '区块链网络繁忙,请稍后再试',
                    })
                })
            } else {
                this.$Notice.error({
                    title: '密钥与账户不对应,请重试',
                })
            }
        },
    },
}
</script>


<style scoped>
.content {
    background-color: #e9eef3;
    color: #333;
    text-align: center;
    line-height: 50px;
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
.demo-spin-icon-load {
    animation: ani-demo-spin 1s linear infinite;
}
</style>