<!--
 * @Date: 2020-04-15 21:22:46
 * @Author: zhen
 * @LastEditTime: 2020-04-15 21:22:53
 * @Description: 
 -->
<!--
 * @Date: 2020-03-25 21:39:50
 * @Author: zhen
 * @LastEditTime: 2020-04-15 21:13:43
 * @Description: 
 -->
<template>
    <el-container>
        <el-aside width="250px">
            <el-menu
                default-active="1"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
            >
                <el-menu-item index="1">
                    <template slot="title">
                        <i class="el-icon-s-home"></i>
                        <span>Home</span>
                    </template>
                </el-menu-item>
                <el-menu-item index="2">
                    <i class="el-icon-cpu"></i>
                    <span slot="title">Blockchian</span>
                </el-menu-item>
                <el-submenu index="3">
                    <template slot="title">
                        <i class="el-icon-s-check"></i>
                        <span>投票发起</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="3-1" route>
                            <template>
                                <i class="el-icon-circle-plus"></i>
                            </template>
                            <span>发起投票</span>
                        </el-menu-item>
                        <el-menu-item index="3-2">
                            <template>
                                <i class="el-icon-s-order"></i>
                            </template>
                            <span>投票列表</span>
                        </el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-submenu index="4">
                    <template slot="title">
                        <i class="el-icon-s-claim"></i>
                        <span>参与投票</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="4-1">
                            <template>
                                <i class="el-icon-document-checked"></i>
                            </template>
                            <span>投票列表</span>
                        </el-menu-item>
                        <el-menu-item index="4-2">选项2</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-menu-item index="5">
                    <i class="el-icon-setting"></i>
                    <span slot="title">个人设置</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <el-container>
            <!-- header界面 -->
            <el-header>
                <div align="right">
                    <!-- 下拉菜单 -->
                    <el-dropdown split-button @click="handleClick">
                        {{ username }}用户名位置
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>修改密码</el-dropdown-item>
                            <el-dropdown-item>基本资料</el-dropdown-item>
                            <el-dropdown-item>退出登陆</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <!-- main界面 -->
            <el-main>
                <div class="home">
                    <el-row>
                        <el-col :span="24">
                            <el-card class="box-card">
                                <div class="text item">
                                    <h3>投票系统使用说明：本系统基于区块链技术,并依此实现去中心化的功能。用户无需担心自己的选票数据被篡改,整个投票系统分为发起投票、注册投票、给出选票、投票结束、查看结果几个阶段。</h3>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-card class="box-card">
                                <div class="text item">
                                    <h3>账户余额</h3>
                                </div>
                            </el-card>
                        </el-col>
                        <el-col :span="8">
                            <el-card class="box-card">
                                <div class="text item">
                                    <h3>快速投票</h3>
                                </div>
                            </el-card>
                        </el-col>
                        <el-col :span="8">
                            <el-card class="box-card">
                                <div class="text item">
                                    <h3>区块链运行状态</h3>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </div>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
export default {};
</script>

<style>
.el-row {
    margin-bottom: 50px;
    &:last-child {
        margin-bottom: 0;
    }
}
.el-col {
    border-radius: 40px;
}
.el-header {
    background-color: #b3c0d1;
    color: #333;
    text-align: right;
    line-height: 60px;
}

.el-aside {
    /* background-color: #545c64; */
    line-height: 200px;
}

.el-main {
    background-color: #e9eef3;
    color: #333;
    text-align: center;
    line-height: 160px;
}

body > .el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}
.box-card {
    width: 96%;
}
.item {
    padding: 10px 0;
}
.text {
    font-size: 14px;
    line-height: 18pt;
    text-align: left;
}
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
