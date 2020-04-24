<!--
 * @Date: 2020-04-17 00:54:06
 * @Author: zhen
 * @LastEditTime: 2020-04-22 16:33:31
 * @Description: 设定投票参数
 -->
<template>
    <content class="content">
        <Card
            :bordered="false"
            class="card"
        >
            <Form
                ref="setupForm"
                :model="setupForm"
                :lebel-width="80"
                :rules="ruleInLine"
            >
                <FormItem
                    label="投票名称"
                    prop="votename"
                >
                    <Input
                        v-model="setupForm.voteName"
                        placeholder="请输入投票名称"
                    ></Input>
                </FormItem>
                <FormItem
                    label="可注册时间段"
                    prop="regTimeDuration"
                >
                    <DatePicker
                        type="datetimerange"
                        format="yyyy-MM-dd HH:mm"
                        style="width: 300px"
                        v-model="setupForm.regTimeDuration"
                    ></DatePicker>
                </FormItem>
                <FormItem
                    label="可投票时间段"
                    prop="voteTimeDuration"
                >
                    <DatePicker
                        type="datetimerange"
                        format="yyyy-MM-dd HH:mm"
                        style="width: 300px"
                        v-model="setupForm.voteTimeDuration"
                    ></DatePicker>
                </FormItem>
                <!-- <FormItem label="注册结束时间">
                    <DatePicker
                        type="datetimerange"
                        format="yyyy-MM-dd HH:mm"
                        style="width: 300px"
                        v-model="setupForm.regEndTime"
                    ></DatePicker>
                </FormItem> -->
                <!-- <FormItem label="投票结束时间">
                    <DatePicker
                        type="datetimerange"
                        format="yyyy-MM-dd HH:mm"
                        style="width: 300px"
                        v-model="setupForm.voteEndTime"
                    ></DatePicker>
                </FormItem> -->
                <FormItem
                    label="添加投票人"
                    prop="targetKeys"
                >
                    <row>
                        <Select
                            v-model="voterOrg"
                            style="width:300px"
                            @on-change="selectChange"
                        >
                            <Option
                                v-for="item in orgList"
                                :value="item.value"
                                :key="item.value"
                            >{{ item.label }}</Option>
                        </Select>
                    </row>
                    <br>
                    <row>
                        <Transfer
                            filterable
                            :filter-method="filterMethod"
                            :data="voterList"
                            :target-keys="targetKeys"
                            @on-change="handleTransferChange"
                        ></Transfer>
                    </row>
                </FormItem>
                <FormItem
                    label="添加候选人"
                    prop="canditates"
                >
                    <Input
                        v-model="setupForm.canditates"
                        placeholder="输入候选人名称,候选人之间用半角符号','分隔"
                        style="width: 300px"
                    />
                </FormItem>
                <FormItem
                    label="区块链密钥"
                    prop="prikey"
                >
                    <Input
                        type="password"
                        placeholder="请输入密钥"
                        v-model="setupForm.prikey"
                    >
                    <Icon
                        type="md-lock"
                        slot="prefix"
                    />
                    </Input>
                </FormItem>
                <Button
                    type="primary"
                    style="display:block;margin:0 auto"
                    @click="submit"
                >创建投票</Button>
            </Form>
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
                <div style="text-align:center">
                    <img
                        v-if="!spinShow"
                        src="../assets/imgs/check.gif"
                        height="150"
                        width="150"
                    >
                </div>
                <p v-if="!spinShow">生成投票地址为：{{ contractAddress }} </p>
            </Modal>

        </Card>
    </content>
</template>

<script>
import { getOrglist, getVoterlist, setup } from '../api/api.js'

export default {
    name: 'createvote',
    data() {
        return {
            ruleInLine: {
                votename: [{ required: true, message: '请填写投票名称', trigger: 'blur' }],
                regTimeDuration: [{ required: true, message: '请填写可注册时间', trigger: 'blur' }],
                voteTimeDuration: [{ required: true, message: '请填写可投票时间', trigger: 'blur' }],
                targetKeys: [{ required: true, message: '请选择投票人', trigger: 'blur' }],
                canditates: [{ required: true, message: '请填写候选人', trigger: 'blur' }],
                prikey: [{ required: true, message: '请输入区块链密钥', trigger: 'blur' }],
            },
            setupForm: {
                voteName: '',
                regTimeDuration: '',
                voteTimeDuration: '',
                canditates: '',
                prikey: '',
            },
            sendData: {
                voteName: '',
                registrationStartTime: '',
                registrationEndTime: '',
                votingStartTime: '',
                votingEndTime: '',
                voterAddr: [],
                candidates: [],
                callerPRIVATEKEY: '',
                callerPUBLICKEY: '',
            },
            voterOrg: '',
            orgList: [],
            voterList: [],
            targetKeys: [],
            contractAddress: '',
            modalLoading: false,
            spinShow: false,
        }
    },
    created() {
        this.fetchData()
    },
    methods: {
        fetchData() {
            getOrglist()
            .then(res => {
                for (let i = 0; i < res.data.length; i++) {
                    this.orgList.push({
                        value: res.data[i],
                        label: res.data[i],
                    })
                }
            })
        },
        filterMethod(data, query) {
            return data.label.indexOf(query) > -1
        },
        handleTransferChange(newTargetKeys) {
            this.targetKeys = newTargetKeys
        },
        selectChange() {
            let selectorg = this.voterOrg
            let backData = []
            getVoterlist(selectorg)
            .then(res => {
                // let test = res.data[0].name
                for (let i = 0; i < res.data.length; i++) {
                    backData.push({
                        key: i.toString(),
                        label: res.data[i].name,
                        description: res.data[i].publickey,
                    })
                }
            })
            this.voterList = backData
        },
        submit() {
            if (this.setupForm.voteName != ''
                && this.setupForm.regTimeDuration != ''
                && this.setupForm.voteTimeDuration != ''
                && this.targetKeys != ''
                && this.setupForm.canditates != ''
                && this.setupForm.prikey != ''
            ) {
            // get voters & format
                let voterAddress = []
                for (let i = 0; i < this.targetKeys.length; i++) {
                    voterAddress.push(this.voterList[this.targetKeys[i]].description)
                }
                // console.log(voterAddress)
                // console.log(this.sendData.canditates)

                // unix timestamp
                let RegStart = new Date(this.setupForm.regTimeDuration[0]).getTime()
                let RegStartStamp = Math.floor(RegStart / 1000)

                let RegEnd = new Date(this.setupForm.regTimeDuration[1]).getTime()
                let RegEndStamp = Math.floor(RegEnd / 1000)

                let VoteStart = new Date(this.setupForm.voteTimeDuration[0]).getTime()
                let VoteStartStamp = Math.floor(VoteStart / 1000)

                let VoteEnd = new Date(this.setupForm.voteTimeDuration[1]).getTime()
                let VoteEndStamp = Math.floor(VoteEnd / 1000)
                // console.log(timestamp)

                // finish sendData()
                this.sendData.voteName = this.setupForm.voteName
                this.sendData.registrationStartTime = RegStartStamp.toString()
                this.sendData.registrationEndTime = RegEndStamp.toString()
                this.sendData.votingStartTime = VoteStartStamp.toString()
                this.sendData.votingEndTime = VoteEndStamp.toString()
                this.sendData.voterAddr = voterAddress
                // get canditates & format
                this.sendData.candidates = this.setupForm.canditates.split(',')
                this.sendData.callerPRIVATEKEY = this.setupForm.prikey
                this.sendData.callerPUBLICKEY = localStorage.getItem('userAddress')
                // console.log(typeof this.sendData.regStartTime)
                // valid voter.length & canditate.length
                if (this.sendData.voterAddr.length < 2) {
                    this.$Notice.error({
                        title: '投票人数不能小于两人',
                    })
                } else if (this.sendData.candidates.length < 2) {
                    this.$Notice.error({
                        title: '候选人人数不能小于两人',
                    })
                } else {
                    // loging notifaction
                    this.modalLoading = true
                    this.spinShow = true
                    // post backend
                    setup(this.sendData)
                    .then(res => {
                        this.contractAddress = res.data
                        this.spinShow = false
                        // console.log(res)
                        // console.log(this.contractAddress.toString())
                        // console.log(this.contractAddress)
                    })
                    .catch(() => {
                        this.modalLoading = false
                        this.$Modal.error({
                            title: '区块链网络繁忙,请稍后再试',
                        })
                    })
                    // console.log(this.contractAddress.toString())
                    // console.log(this.contractAddress)
                }
            } else {
                this.$Notice.error({
                    title: '请填写全部投票信息！',
                    desc: '表格内所有内容均为必填项目,请全部填写。',
                })
            }
        },
    },
}
</script>

<style scoped>
.card {
    width: 80%;
    margin: 20px auto;
    text-align: center;
}
.card2 {
    width: 40%;
    line-height: 20px;
    margin: 20px auto;
    text-align: center;
}
.demo-spin-icon-load {
    animation: ani-demo-spin 1s linear infinite;
}
</style>>