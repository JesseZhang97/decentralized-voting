<!--
 * @Date: 2020-05-11 22:34:40
 * @Author: zhen
 * @LastEditTime: 2020-05-12 03:13:21
 * @Description:
 -->
<template>
    <div>
        <content class="content">
            <Card
                :bordered="false"
                class="card"
            >
                <strong>更新须知:</strong>
                <p>电子邮箱更新无需经过审核</p>
                <p>认证姓名更新需通过管理员进行审核</p>
                <p>并且在审核完成前系统将暂时回收投票权限</p>
            </Card>
            <Card
                :bordered="false"
                class="card"
            >
                <Form
                    ref="userForm"
                    :model="userForm"
                >
                    <FormItem label="认证姓名">
                        <Input
                            v-model="userForm.name"
                            style="width: 400px"
                        />
                        <Icon
                            type="md-checkmark"
                            v-if="verifiedFlag"
                        />
                        <Icon
                            type="md-close"
                            v-else
                        />
                        <p
                            class="error"
                            v-if="!verifiedFlag"
                        >{{nameVerified}}</p>
                    </FormItem>
                    <FormItem label="电子邮箱">
                        <Input
                            v-model="userForm.email"
                            style="width: 400px"
                        />
                    </FormItem>
                    <FormItem label="网络地址">
                        <Input
                            disabled
                            v-model="userForm.publickey"
                            style="width: 400px"
                        />
                    </FormItem>
                    <FormItem label="所属组织">
                        <Input
                            disabled
                            v-model="userForm.organization"
                            style="width: 400px"
                        />
                    </FormItem>
                    <Button
                        type="success"
                        long
                        @click="updateInfo"
                    >更新信息</Button>
                </Form>
            </Card>
        </content>
    </div>
</template>

<script>
import { getUserInfo, update } from '../api/api'

export default {
    name: 'userinfo',
    data() {
        return {
            userForm: {
                name: '',
                organization: '',
                email: '',
                publickey: '',
            },
            nameVerified: '',
            verifiedFlag: false,
            refresh: true,
        }
    },
    created() {
        this.fetchData()
    },
    methods: {
        fetchData() {
            this.userForm.publickey = localStorage.getItem('userAddress')
            getUserInfo(this.userForm.publickey)
            .then(res => {
                this.userForm.name = res.data.name
                this.userForm.organization = res.data.organization
                this.userForm.email = res.data.email
                this.verifiedFlag = res.data.verified

                if (this.verifiedFlag == false) {
                    this.nameVerified = '请前往线下网点进行实名认证'
                }
            })
        },
        updateInfo() {
            if (this.userForm.name != ''
                && this.userForm.organization != ''
                && this.userForm.email != '') {
                update(this.userForm)
                .then(res => {
                    if (res.data == 1) {
                        this.$Message.success('用户信息更新成功')
                    } else {
                        this.$Message.error('用户信息更新失败')
                    }
                })
            } else {
                this.$Notice.error({
                    title: '修改信息不能为空',
                })
            }
        },
    },
}
</script>

<style scoped>
.card {
    width: 50%;
    margin: 20px auto;
    text-align: left;
}
.error {
    color: red;
    text-align: left;
    margin: 5px auto;
    font-size: 12px;
    padding-left: 30px;
    height: 20px;
}
</style>
