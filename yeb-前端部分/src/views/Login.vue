<template>
<div>
  <el-form ref="loginForm" :rules="rules" :model="loginForm" class="loginContainer">
    <h3 class="loginTitle">系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" auto-complete="false" placeholder="请输入用户名">

      </el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" auto-complete="false" placeholder="请输入密码">

      </el-input>
    </el-form-item>

    <el-form-item prop="code">
      <el-input type="text" v-model="loginForm.code" auto-complete="false" placeholder="点击图片切换验证码"
      style="width: 250px;margin-right: 5px"></el-input>
      <img :src="captchaUrl" @click="updateCaptcha">

    </el-form-item>
    <el-checkbox class="loginRemember">记住我</el-checkbox>
    <el-button type="primary" style="width: 100%" @click="submitLogin" :plain=true>登录</el-button>
  </el-form>

</div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return {
      captchaUrl:'/captcha?time'+new Date(),   //接口只有/captcha这一部分,关于后边的?time=new Date(), 是为了给服务器发一个时间,服务器返回的验证码不相同
      loginForm:{
        username:'admin',
        password:'123',
        code:''
      },
      rules:{
        username:[{required: true, message: '请输入用户名', trigger: 'blur'}],
        password:[{required: true, message: '请输入密码', trigger: 'blur'}],
        code:[{required: true, message: '请输入验证码', trigger: 'blur'}],
    }
  }}
  ,
  methods:{
    updateCaptcha(){
      this.captchaUrl='/captcha?time'+new Date()
    },
    submitLogin(){
      this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          this.$message({
            message: '恭喜你，登陆成功',
            type: 'success'
          });
        } else {
          this.$message.error('请输入有效字段',);
          return false;
        }
      });
    },
  },
}
</script>

<style scoped>
.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.loginTitle {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}

.loginRemember {
  text-align: left;
  margin: 0px 0px 15px 0px;
}

.el-form-item__content {
  display: flex;
  align-items: center;
}
</style>