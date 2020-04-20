<!--
 * @Date: 2020-04-20 22:49:27
 * @Author: zhen
 * @LastEditTime: 2020-04-21 00:11:32
 * @Description:
 -->
<template>
    <div style="padding: 10px">
        <div style="background: #fff; border-radius: 8px; padding: 20px;">
            <!-- <div class="query-c">
                查询：
                <Input
                    search
                    placeholder="请输入查询内容"
                    style="width: auto"
                />
            </div> -->
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
                <template slot="action">
                    <Row :gutter="10">
                        <Col span="6">
                        <div><Button>开始注册</Button></div>
                        </Col>
                        <Col span="6">
                        <div><Button>结束注册</Button></div>
                        </Col>
                        <Col span="6">
                        <div><Button>开始投票</Button></div>
                        </Col>
                        <Col span="6">
                        <div><Button>结束投票</Button></div>
                        </Col>
                    </Row>
                </template>
            </Table>
            <br>
            <Page
                :total="100"
                show-elevator
            />
        </div>
    </div>
</template>

<script>
export default {
    name: 't1',
    data() {
        return {
            currentPage: '',
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
            totaldata: [
                {
                    address: 'John Brown',
                    registrationTime: 18,
                    voteTime: 'New York No. 1 Lake Park',
                },
            ],
        }
    },
}
</script>

<style scoped>
</style>