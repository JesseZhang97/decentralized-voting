<!--
 * @Date: 2020-03-19 02:36:25
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-19 23:16:16
 * @FilePath: /decentralized-voting/vapp/src/views/InfoPaper.vue
 * @Description: 
-->
<template>
<el-container direction="vertical">
  <el-main>
    <h1>注册信息</h1>
    <h4>*请务必认真阅读以下注意事项</h4>
    <el-card class="box-card">
      <div class="text item">
        <li>点击下方按钮将展示出系统发放的公钥、私钥、助记词。</li>
        <li>私钥与助记词为唯一有效凭证,请私人保管。</li>
        <li>TODO......</li>
        <!-- TODO按钮居中或者靠右  按钮点击倒计时-->
        <el-button style="bt-class" type="success" round @click="confirm">我已知晓</el-button>
      </div>
    </el-card>
  </el-main>
  <el-main v-if="secretVisible">
    <el-card class="box-card">
      <el-row>
        <el-col :span="6"><el-image :src="img" scale-down class="image"></el-image></el-col>
        <el-col :span="11">
            <el-form ref="form" :model="form" label-width="60px" size="medium ">
            <el-form-item label="助记词">
              <el-input v-model="form.mnemonic "></el-input>
            </el-form-item>
            <el-form-item label="私钥">
              <el-input v-model="form.prikey"></el-input>
            </el-form-item>
            <el-form-item label="公钥">
              <el-input v-model="form.pubkey"></el-input>
            </el-form-item>
          </el-form>
          <!-- TODO 二维码位置 -->
        </el-col>
      </el-row>
      <el-row> 
        <el-button type="primary" icon="el-icon-download" round></el-button>
            <el-button type="primary" round>进入主页</el-button>
      </el-row>
      
      
    </el-card>
  </el-main>
</el-container>
</template>

<script>
import imgURL from '../assets/ethereum_logo.png'
export default {
  name: 'InfoPaper',
  data() {
    return {
      img: imgURL,
      secretVisible : false,
      form : {
        mnemonic: '',
        prikey: '',
        pubkey: ''
      }
    }
  },
  methods:{
    confirm () {
      this.secretVisible = true
    }
  }
}
</script>

<style>
.el-footer {
    /* background-color: #B3C0D1; */
    /* color: #333; */
    text-align: center;
    line-height: 1500px;
  }
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 80px;
}
.text {
  font-size: 14px;
}
.item {
    text-align: left;
  }
.box-card {
  width: 85%;
  margin:0 auto
}
  .image {
    width: 50%;
    display: block;
  }

/*
	找到html标签、body标签，和挂载的标签
	都给他们统一设置样式
*/
  html,body,#app,.el-container{
        /*设置内部填充为0，几个布局元素之间没有间距*/
        padding: 0px;
        /*外部间距也是如此设置*/
        margin: 0px;
        /*统一设置高度为100%*/
        height: 100%;
    }
</style>