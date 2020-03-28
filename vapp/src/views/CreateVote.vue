<!--
 * @Date: 2020-03-26 00:47:08
 * @Author: zhen
 * @LastEditTime: 2020-03-26 01:22:02
 * @Description: 
 -->
<template>
  <el-container>
    <el-header>Header</el-header>
    <el-container>
      <el-aside width="250px">
        <el-menu
          default-active="3-1"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
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
      <!-- main界面 -->
      <el-main>
        <el-row>
          <el-col :span="18">
            <el-card>
              <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="投票名称">
                  <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="活动区域">
                  <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="活动时间">
                  <el-col :span="8">
                    <el-date-picker
                      type="date"
                      placeholder="选择日期"
                      v-model="form.date1"
                      style="width: 100%;"
                    ></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="1">——</el-col>
                  <el-col :span="11">
                    <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
                  </el-col>
                </el-form-item>
                <el-form-item label="活动形式">
                  <el-input type="textarea" v-model="form.desc"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">立即创建</el-button>
                </el-form-item>
              </el-form>
            </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
export default {
  data() {
    return {
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: ""
      }
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    }
  }
};
</script>




<style>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #545c64;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
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
.el-card {
  margin-bottom: 200px;
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