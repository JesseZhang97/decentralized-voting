<!--
 * @Date: 2020-04-22 18:42:33
 * @Author: zhen
 * @LastEditTime: 2020-05-26 18:26:10
 * @Description:
 -->
<template>
    <div style="padding: 10px">
        <div style="background: #fff; border-radius: 8px; padding: 20px;">
            <div>
                <Input
                    type="password"
                    placeholder="请输入区块链密钥"
                    style="width: 450px"
                    v-model="sendData.callerPRIVATEKEY"
                />
                <br>
                <br>
                <Input
                    search
                    enter-button
                    placeholder="请输入投票地址"
                    style="width: 450px"
                    @on-search="search"
                    v-model="sendData.contractAddress"
                />
            </div>
            <Divider />
            <Row>
                <Col span="11">
                <Card
                    class="radio-class"
                    v-if="showList"
                >
                    <p slot="title">投票项目为：<strong>{{voteName}}</strong> </p>
                    <p>候选人列表：</p>
                    <RadioGroup
                        class="radio-class"
                        v-model="slected"
                        size="large"
                    >
                        <Radio
                            v-for="item in voterList"
                            :key="item.name"
                            :label=item.name
                            class="radio-class"
                            border
                        >
                        </Radio>
                    </RadioGroup>
                    <Divider />
                    <Button
                        style="display:block;margin:0 auto"
                        type="warning"
                        @click="submit"
                    >确认投票</Button>
                </Card>
                </Col>
            </Row>
            <Spin
                size="large"
                fix
                v-if="spinShow"
            ></Spin>
            <Modal
                v-model="modal"
                width="360"
            >
                <p
                    slot="header"
                    style="color:#f60;text-align:center"
                >
                    <Icon type="ios-information-circle"></Icon>
                    <span>确认投票</span>
                </p>
                <div style="text-align:center">
                    <p>您的选择为：
                        <strong>
                            {{ slected }}
                        </strong></p>
                    <p>确认投票吗？投票一经发出不可更改</p>
                </div>
                <div slot="footer">
                    <Button
                        type="warning"
                        size="large"
                        long
                        :loading="modal_loading"
                        @click="confirm"
                    >上传投票</Button>
                </div>
            </Modal>
        </div>
    </div>
</template>
<script>
import { getVoteInfo, castVote } from '../api/api.js'

const privateKeyToAddress = require('ethereum-private-key-to-address')

export default {
    name: 'castvote',
    data() {
        return {
            modal: false,
            modal_loading: false,
            sendData: {
                contractAddress: '',
                callerPRIVATEKEY: '',
                vote: '',
            },
            slected: '',
            showList: false,
            spinShow: false,
            voteName: '',
            txAddress: '',
            voterList: [],
            config: {
                width: 600,
            },
        }
    },
    methods: {
        search() {
            if (this.sendData.contractAddress == '' || this.sendData.callerPRIVATEKEY == '') {
                this.$Notice.error({
                    title: '请填写密钥及投票地址',
                })
            } else if (privateKeyToAddress(this.sendData.callerPRIVATEKEY) === localStorage.getItem('userAddress')) {
                this.spinShow = true
                getVoteInfo(this.sendData)
                .then(res => {
                    this.spinShow = false
                    this.showList = true
                    this.voteName = res.data.voteName
                    for (let i = 0; i < res.data.candidates.length; i++) {
                        this.voterList.push({
                            name: res.data.candidates[i],
                        })
                    }
                })
                .catch(() => {
                    this.spinShow = false
                    this.$Modal.error({
                        title: '错误,请检查输入内容无误并重试',
                    })
                })
            } else {
                this.$Notice.error({
                    title: '密钥与账户不对应,请重试',
                })
            }
        },
        submit() {
            if (this.slected == '') {
                this.$Notice.error({
                    title: '请选择候选人',
                })
            } else {
                this.modal = true
            }
        },
        confirm() {
            this.sendData.vote = this.slected
            this.modal_loading = true
            // FIXME 如果用户删除了投票地址和密钥的提示
            castVote(this.sendData)
            .then(res => {
                this.modal = false
                this.modal_loading = false
                this.txAddress = res.data
                this.$Modal.info({
                    title: '投票结果',
                    content: '查询地址为：' + this.txAddress,
                    width: 630,
                })
            })
            .catch(() => {
                this.$Modal.error({
                    title: '错误,请检查密钥与投票地址无误并重试',
                })
            })
        },
    },
}
</script>
<style scoped>
.radio-class {
    margin: 2px 20px 20px 2px;
}
</style>