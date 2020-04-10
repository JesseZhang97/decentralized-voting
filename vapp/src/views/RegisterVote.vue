<!--
 * @Date: 2020-04-10 21:33:54
 * @Author: zhen
 * @LastEditTime: 2020-04-11 02:07:13
 * @Description: 
 -->
<template>
  <div class="secret">
    <el-row>
      <el-steps :active="1">
        <el-step title="连接Ethereum" description="输入私钥连接至区块链" icon="el-icon-link"></el-step>
        <el-step title="注册确认" description="确认投票信息,并登记注册投票" icon="el-icon-check"></el-step>
      </el-steps>
    </el-row>
    <div>
      <el-row>
        <el-input placeholder="请输入私钥" v-model="input" show-password></el-input>
      </el-row>
      <el-button
        class="button-center"
        type="primary"
        @click="openFullScreen"
        v-loading.fullscreen.lock="fullscreenLoading"
      >确认连接</el-button>
    </div>

    <div>
      <el-image :src="src"></el-image>
      <el-row>
        <el-card class="box-card">
          <span>voteInfo</span>
          <el-divider></el-divider>
          <span>notice</span>
        </el-card>
      </el-row>
      <el-button type="success" class="button-center">成功按钮</el-button>
    </div>
  </div>
</template>


<script>
import src from "../assets/rgIMG.jpg";
export default {
  name: "RegisterVote",
  data() {
    return {
      src: src,
      fullscreenLoading: false
    };
  },
  methods: {
    openFullScreen() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 2000);
    }
  }
};
</script>

<style>
.secret {
  position: fixed;
  overflow: hidden;
  left: 50%;
  margin-left: -410px;
  top: 50%;
  margin-top: -350px;
  width: 800px;
  min-height: 555px;
  z-index: 10;
  right: 140px;
  background: #fff;
}
.el-row {
  margin-bottom: 20px;
  /* &:last-child {
    margin-bottom: 0;
  } */
}
.button-center {
  display: block;
  margin: 0 auto;
}
</style>