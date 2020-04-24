<!--
 * @Date: 2020-04-24 00:57:17
 * @Author: zhen
 * @LastEditTime: 2020-04-25 02:36:27
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
                    <Icon type="md-albums" />投票项目为：<strong>{{voteName}}</strong> </h2>
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
            </div>
        </div>

    </div>
    </div>
</template>

<script>
import { tallyVoting } from '../api/api.js'

export default {
    name: 'tallyote',
    data() {
        this.typeArr = ['bar', 'pie']
        this.index = 0
        return {
            sendData: {
                contractAddress: '0x5e5506e407d42d7aeb82d3b4bdcd1a8514b974c2',
                callerPRIVATEKEY: '0x1d6c2a391786e3f9f6f96d55eac91a1b70875f848e982393ed6ad2fd093c4b3e',
            },
            showChart: false,
            spinShow: false,
            voteName: '',
            chartData: {
                columns: ['姓名', '选票数量'],
                rows: [],
            },
            chartSettings: { type: this.typeArr[this.index] },

        }
    },
    methods: {
        search() {
            if (this.sendData.contractAddress == '' || this.sendData.callerPRIVATEKEY == '') {
                this.$Notice.error({
                    title: '请填写密钥及投票地址',
                })
            } else {
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
</style>