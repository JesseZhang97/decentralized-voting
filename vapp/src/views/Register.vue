<!--
 * @Date: 2020-03-13 18:48:53
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-17 01:20:55
 * @FilePath: /decentralized-voting/vapp/src/views/Register.vue
 * @Description: 注册帐号页面
 -->
<!--
 * @Date: 2020-03-13 18:15:22
 * @LastEditors: zhen
 * @LastEditTime: 2020-03-13 18:46:43
 * @FilePath: /decentralized-voting/vapp/src/views/Login.vue
 * @Description: 用户登录页面
 -->
<template>
<div class="login" id="login">
    <!-- <a href="javascript:;" class="log-close"><i class="icons close"></i></a> -->
    <div class="log-bg">
        <div class="log-cloud cloud1"></div>
        <div class="log-cloud cloud2"></div>
        <div class="log-cloud cloud3"></div>
        <div class="log-cloud cloud4"></div>

        <div class="log-logo">注册新用户</div>
        <div class="log-text">realVoting</div>
    </div>
    <div class="log-email">
        <input type="text" placeholder="帐号" :class="'log-input' + (userInfo.account==''?' log-input-empty':'')" v-model="userInfo.account">
        <input type="password" placeholder="密码" :class="'log-input' + (userInfo.password==''?' log-input-empty':'')"  v-model="userInfo.password">
        <input type="text" placeholder="姓名" :class="'log-input' + (userInfo.name==''?' log-input-empty':'')"  v-model="userInfo.name">
        <input type="text" placeholder="所属组织" :class="'log-input' + (userInfo.organization==''?' log-input-empty':'')"  v-model="userInfo.organization">
        <a href="javascript:;" class="log-btn" @click="register">注册</a>
    </div>
    <div class="log-text">基于ethereum区块链技术</div>
    <Loading v-if="isLoging" marginTop="-30%"></Loading>
</div>
</template>

<script>
import Loading from './Loading.vue'
//import sha1 from '../plugins/sha1.min.js'

export default {
  name: 'Login',
  data(){
    return {
      isLoging: false,
      userInfo:{
        account: '',
        password: '',
        name:'',
        organization:''
      }
    }
  },
  components:{
    Loading
  },
  methods:{

    //登录逻辑
    register(){
      var reg =/^[\u4e00-\u9fa5]/

      if( !this.userInfo.account || !this.userInfo.password || !this.userInfo.name || !this.userInfo.organization ) {
        this.$notify.error({
        title: '错误',
        message: '请填写全部注册内容'
        });
      } else if (!reg.test(this.userInfo.name)) {
        this.$notify.error({
          title: '错误',
          message: '请输入中文姓名'
          });
      } else {
        this.toLogin()
      }
  },

    //登录请求
    toLogin(){
    this.isLoging = true
    this.$store
    .dispatch("Register", this.userInfo)
    .then(response => {
      let code = response.data.code;
      if (code == 200) {
        this.$notify({
          title: '注册',
          message: '帐号已成功注册,即将转入登录页面',
          type: 'success'
        });
        this.$router.push({ 
          path: '/login',
          query: { data: "response.data.data" }
          //query: { data: response.data.data }
        });
        this.isLoging = false;
      } else {
        this.isLoging = false;
        this.userInfo.account = '';
        this.userInfo.password = '';
        this.userInfo.name = '';
        this.userInfo.organization = ''
        this.$notify.error({
          title: '错误',
          message: '注册失败,请重新尝试'
        });
      }
    })

  }
}
}
</script>

<style scoped>
.login{position: fixed; overflow: hidden;left: 50%; margin-left: -250px; top:50%; margin-top: -350px; width: 500px; min-height: 555px; z-index: 10; right: 140px; background: #fff;-webkit-border-radius: 5px;
-moz-border-radius: 5px;
-ms-border-radius: 5px;
-o-border-radius: 5px;
border-radius: 5px; -webkit-box-shadow:  0px 3px 16px -5px #070707; box-shadow:  0px 3px 16px -5px #070707}
.log-close{display: block; position: absolute; top:12px; right: 12px; opacity: 1;}
.log-close:hover .icons{transform: rotate(180deg);}
.log-close .icons{opacity: 1; transition: all .3s}
.log-cloud{background-image: url(../assets/login-cloud.png); width: 63px ;height: 40px; position: absolute; z-index: 1}
.login .cloud1{top:21px; left: -30px; transform: scale(.6); animation: cloud1 20s linear infinite;}
.login .cloud2{top:87px; right: 20px; animation: cloud2 19s linear infinite;}
.login .cloud3{top:160px; left: 5px;transform: scale(.8);animation: cloud3 21s linear infinite;}
.login .cloud4{top:150px; left: -40px;transform: scale(.4);animation: cloud4 19s linear infinite;}
.log-bg{background: url(../assets/login-bg.jpg); width: 100%; height: 312px; overflow: hidden;}
.log-logo{height: 80px; margin: 120px auto 25px; text-align: center; color: #1fcab3; font-weight: bold; font-size: 40px;}
.log-text{color: #57d4c3; font-size: 13px; text-align: center; margin: 0 auto;}
.log-logo,.log-text{z-index: 2}
.icons{background:url(../assets/icons.png) no-repeat; display: inline-block;}
.close{height:16px;width:16px;background-position:-13px 0;}
.login-email{height:17px;width:29px;background-position:-117px 0;}

.log-btns{padding: 15px 0; margin: 0 auto;}
.log-btn{width:402px; display: block; text-align: left; line-height: 50px;margin:0 auto 15px; height:50px; color:#fff; font-size:13px;-webkit-border-radius: 5px; background-color: #3B5999;
-moz-border-radius: 5px;
-ms-border-radius: 5px;
-o-border-radius: 5px;
border-radius: 5px;
position: relative;}
.log-btn.tw{background-color: #13B4E9}
.log-btn.email{background-color: #50E3CE}
.log-btn:hover,.log-btn:focus{color: #fff; opacity: .8;}

.log-email{text-align: center; margin-top: 20px;}
.log-email .log-btn{background-color: #50E3CE;text-align: center;}
.log-input-empty{border: 1px solid #f37474 !important;}
.isloading{background: #d6d6d6}
.log-btn .icons{margin-left: 30px; vertical-align: middle;}
.log-btn .text{left: 95px; line-height: 50px; text-align: left; position: absolute;}
.log-input{width: 370px;overflow: hidden; padding: 0 15px;font-size: 13px; border: 1px solid #EBEBEB; margin:0 auto 15px; height: 48px; line-height: 48px; -webkit-border-radius: 5px;
-moz-border-radius: 5px;
-ms-border-radius: 5px;
-o-border-radius: 5px;
border-radius: 5px;}
.log-input.warn{border: 1px solid #f88787}

 @-webkit-keyframes cloud1 {  
    0%{left: 200px}  
    100%{left:-130px;} 
}
@keyframes cloud1{
    0%{left: 200px}  
    100%{left:-130px;} 
}

 @-webkit-keyframes cloud2 {  
    0%{left:500px;}  
    100%{left:-90px;} 
}
@keyframes cloud2{
    0%{left:500px;}  
    100%{left:-90px;} 
}

@-webkit-keyframes cloud3 {  
    0%{left:620px;}  
    100%{left:-70px;} 
}
@keyframes cloud3{
    0%{left:620px;}  
    100%{left:-70px;} 
}@-webkit-keyframes cloud4 {  
    0%{left:100px;}  
    100%{left:-70px;} 
}
@keyframes cloud4{
    0%{left:100px;}  
    100%{left:-70px;} 
}

</style>
