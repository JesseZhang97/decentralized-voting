<!--
 * @Date: 2020-04-30 19:55:16
 * @Author: zhen
 * @LastEditTime: 2020-05-15 16:25:15
 * @Description:
 -->
<template>
    <div class="home-container">
        <div class="home-content">
            <Row>
                <Col span="10">
                <Card>
                    <p slot="title">用户区块链信息：</p>
                    <p>区块链地址：{{ address }}</p>
                    <p>区块链余额：{{ balance }} Ether</p>
                    <a @click="link">历史交易详情查询</a>
                </Card>
                </Col>
            </Row>
        </div>
    </div>
</template>
<script>
export default {
    name: 'home',
    data() {
        return {
            address: localStorage.getItem('userAddress'),
            balance: '',
        }
    },
    created() {
        this.initInfo()
    },
    methods: {
        initInfo() {
            this.$axios({
                method: 'get',
                url: 'https://api-kovan.etherscan.io/api?module=account&action=balance&address='
                + this.address + '&tag=latest&apikey=8VH92VE24SJ6S9KVGYGQJ6196FFIUS8RXS',
            })
            .then(res => {
                this.balance = res.data.result * 0.000000000000000001
            })
        },
        link() {
            let url = 'https://kovan.etherscan.io/address/'
                    + this.address
            window.open(url, '_blank')
        },
    },
}
</script>

<style scoped>
.home-container {
    padding: 10px;
    padding-top: 5px;
}
.home-content {
    padding: 10px;
    border-radius: 5px;
    background: #fff;
}
</style>