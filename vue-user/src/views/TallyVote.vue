<!--
 * @Date: 2020-04-24 00:57:17
 * @Author: zhen
 * @LastEditTime: 2020-05-15 16:19:04
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
            <Spin
                size="large"
                fix
                v-if="spinShow"
            ></Spin>
            <div v-if="showChart">
                <h2>
                    <Icon type="md-albums" />投票项目结果为：<strong>{{voteName}}</strong> </h2>
                <div class="chart">
                    <Button
                        shape="circle"
                        @click="changeType"
                    >切换图表类型</Button>
                    <ve-chart
                        width="800px"
                        height="400px"
                        :data="chartData"
                        :settings="chartSettings"
                    ></ve-chart>
                </div>
                <div>
                    <Card
                        v-for="(item,index) in chartData.rows"
                        :key="index"
                        class="card"
                    >
                        <p slot="title">{{ item.姓名 }}</p>
                        <p>{{ item.选票数量 }}</p>
                    </Card>
                </div>
            </div>
        </div>

    </div>
    </div>
</template>

<script>
import { tallyVoting } from '../api/api.js'

const privateKeyToAddress = require('ethereum-private-key-to-address')

export default {
    name: 'tallyote',
    data() {
        this.typeArr = ['bar', 'pie']
        this.index = 0
        return {
            sendData: {
                contractAddress: '',
                callerPRIVATEKEY: '',
            },
            showChart: false,
            spinShow: false,
            voteName: '',
            chartData: {
                columns: ['姓名', '选票数量'],
                rows: [],
            },
            chartSettings: {
                dataOrder: {
                    label: '选票数量',
                    order: 'desc',
                },
                type: this.typeArr[this.index],
            },
        }
    },
    created() {
        this.fetchData()
    },
    methods: {
        fetchData() {
            console.log(this.$route.query.contractAddress)
            if (this.$route.query.contractAddress != null) {
                this.sendData.contractAddress = this.$route.query.contractAddress
            } else {
                this.sendData.contractAddress = ''
            }
        },
        search() {
            if (this.sendData.contractAddress == '' || this.sendData.callerPRIVATEKEY == '') {
                this.$Notice.error({
                    title: '请填写密钥及投票地址',
                })
                console.log(privateKeyToAddress(this.sendData.callerPRIVATEKEY))
                console.log(localStorage.getItem('userAddress'))
            } else if (privateKeyToAddress(this.sendData.callerPRIVATEKEY) === localStorage.getItem('userAddress')) {
                this.spinShow = true
                // FIXME get name request
                tallyVoting(this.sendData)
                .then(res => {
                    // {"张祯":1,"陈浩南":0,"张震":0,"山
                    console.log(typeof res.data)
                    Object.keys(res.data).forEach((key) => {
                        console.log('姓名' + key + '选票数量' + res.data[key])
                        this.chartData.rows.push({
                            姓名: key,
                            选票数量: res.data[key],
                        })
                    })
                    this.showChart = true
                    this.spinShow = false
                })
                .catch(() => {
                    this.spinShow = false
                    this.$Notice.error({
                        title: '区块链网络交易繁忙',
                    })
                })
            } else {
                this.$Notice.error({
                    title: '密钥与账户不对应,请重试',
                })
            }
        },
        changeType() {
            this.index++
            if (this.index >= this.typeArr.length) { this.index = 0 }
            this.chartSettings = { type: this.typeArr[this.index] }
        },
    },
}
</script>

<style scoped>
.chart {
    margin: 0 auto;
}
.card {
    text-align: center;
    display: inline-block;
    margin: 3px 10px auto;
}
</style>