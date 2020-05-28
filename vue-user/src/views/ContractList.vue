<!--
 * @Date: 2020-04-20 22:49:27
 * @Author: zhen
 * @LastEditTime: 2020-05-15 16:35:41
 * @Description:
 -->
<template>
    <div style="padding: 10px">
        <div style="background: #fff; border-radius: 8px; padding: 20px;">
            <div class="query-c">
                区块链密钥：
                <Input
                    v-model="sendData.callerPRIVATEKEY"
                    type="password"
                    placeholder="请输入密钥串"
                    style="width: 450px"
                />
            </div>
            <br>
            <Table
                max-height="670"
                border
                :columns="columns"
                :data="totaldata"
            >
                <template
                    slot-scope="{ row }"
                    slot="address"
                >
                    <span>{{ row.address }}</span>
                </template>
                <template
                    slot-scope="{ row }"
                    slot="registrationTime"
                >
                    <span>{{ row.registrationTime }}</span>
                </template>
                <template
                    slot-scope="{ row }"
                    slot="voteTime"
                >
                    <span>{{ row.voteTime }}</span>
                </template>
                <template
                    slot="action"
                    slot-scope="{ row, index }"
                >
                    <Button
                        style="margin-right: 8px"
                        type="warning"
                        @click="endRegister(index)"
                        :loading="regLoading"
                    > <span v-if="!regLoading">结束注册</span>
                        <span v-else>Loading..</span>
                    </Button>
                    <Button
                        type="warning"
                        @click="endVoting(index)"
                        :loading="voteLoading"
                    > <span v-if="!voteLoading">结束投票</span>
                        <span v-else>Loading..</span>
                    </Button>
                    <Divider type="vertical" />
                    <Button
                        style="margin-right: 4px"
                        type="primary"
                        @click="routeTo(index)"
                    >计票</Button>
                </template>
            </Table>
            <br>
            <Page
                :total="pageTotal"
                :current="currentPage"
                @on-change="handlePage"
                show-elevator
            />
        </div>
    </div>
</template>

<script>
import { getContractlist, endRegistration, endVoting } from '../api/api.js'

const privateKeyToAddress = require('ethereum-private-key-to-address')

export default {
    name: 'ContractList',
    data() {
        return {
            columns: [
                {
                    title: '合约地址',
                    slot: 'address',
                },
                {
                    title: '注册时间',
                    slot: 'registrationTime',
                },
                {
                    title: '投票时间',
                    slot: 'voteTime',
                },
                {
                    title: '可执行操作',
                    slot: 'action',
                },
            ],
            regLoading: false,
            voteLoading: false,
            totaldata: [],
            currentPage: 1,
            pageTotal: 0,
            returnBool: false,
            sendData: {
                contractAddress: '',
                callerPRIVATEKEY: '',
            },
        }
    },
    created() {
        this.initFormatter()
        this.fetchData()
    },
    methods: {
        initFormatter() {
            // eslint-disable-next-line func-names
            Date.prototype.Format = function (fmt) { //
                let o = {
                    'M+': this.getMonth() + 1, // 月份
                    'd+': this.getDate(), // 日
                    'h+': this.getHours(), // 小时
                    'm+': this.getMinutes(), // 分
                    's+': this.getSeconds(), // 秒
                    'q+': Math.floor((this.getMonth() + 3) / 3), // 季度
                    S: this.getMilliseconds(), // 毫秒
                }
                if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length))
                Object.keys(o).forEach(k => {
                    if (new RegExp('(' + k + ')').test(fmt)) {
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
                    }
                })
                return fmt
            }
        },
        fetchData() {
            let owner = localStorage.getItem('userAddress')
            getContractlist(owner, this.currentPage)
            .then(res => {
                for (let i = 0; i < res.data.records.length; i++) {
                    this.totaldata.push({
                        address: res.data.records[i].address,
                        registrationTime: new Date(res.data.records[i].registrationStartTime * 1000).Format('yyyy-MM-dd hh:mm')
                                            + '--' + new Date(res.data.records[i].registrationEndTime * 1000).Format('yyyy-MM-dd hh:mm'),
                        voteTime: new Date(res.data.records[i].votingStartTime * 1000).Format('yyyy-MM-dd hh:mm')
                                            + '--' + new Date(res.data.records[i].votingEndTime * 1000).Format('yyyy-MM-dd hh:mm'),
                    })
                }
                this.currentPage = res.data.current
                // FIXME this.pageTotal = res.data.total
                this.pageTotal = 50
            })
        },
        handlePage(value) {
            this.currentPage = value
            this.totaldata = []
            this.fetchData()
        },
        endRegister(index) {
            this.regLoading = true
            this.sendData.contractAddress = this.totaldata[index].address
            console.log(this.sendData.contractAddress)
            if (this.sendData.callerPRIVATEKEY == '') {
                this.$Notice.error({
                    title: '请填写区块链登录密钥',
                })
                this.regLoading = false
                this.voteLoading = false
            } else if (privateKeyToAddress(this.sendData.callerPRIVATEKEY) === localStorage.getItem('userAddress')) {
                endRegistration(this.sendData)
                .then(res => {
                    this.returnBool = res.data
                    if (this.returnBool) {
                        this.$Message.success('所选合约停止注册成功')
                    }
                    this.regLoading = false
                })
                .catch(() => {
                    this.$Modal.error({
                        title: '合约已经结束该状态,请勿重复操作',
                    })
                    this.regLoading = false
                })
            } else {
                this.$Notice.error({
                    title: '密钥与账户不对应,请重试',
                })
            }
        },
        endVoting(index) {
            this.voteLoading = true
            this.sendData.contractAddress = this.totaldata[index].address
            console.log(this.sendData.contractAddress)
            if (this.sendData.callerPRIVATEKEY == '') {
                this.$Notice.error({
                    title: '请填写区块链登录密钥',
                })
            } else if (privateKeyToAddress(this.sendData.callerPRIVATEKEY) === localStorage.getItem('userAddress')) {
                endVoting(this.sendData)
                .then(res => {
                    this.returnBool = res.data
                    if (this.returnBool) {
                        this.$Message.success('所选合约停止投票成功')
                    }
                    this.voteLoading = false
                })
                .catch(() => {
                    this.$Modal.error({
                        title: '合约已经结束该状态,请勿重复操作',
                    })
                    this.voteLoading = false
                })
            } else {
                this.$Notice.error({
                    title: '密钥与账户不对应,请重试',
                })
            }
        },
        routeTo(index) {
            this.$router.push({
                path: '/tallyvote',
                query: {
                    contractAddress: this.totaldata[index].address,
                },
            })
        },
    },
}
</script>

<style scoped>
</style>