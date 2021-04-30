<template>
  <div style="text-align: center">
    <el-container>
      <el-header style="height: 90px">
        <h1>欢迎登录学生端系统</h1>
      </el-header>
      <el-main :style="windowHeight">
        <div  style="margin-left: 500px">
          <div style=" width: 55%">
            <div style="margin-top: 110px">
              <p style="color: #a50912" v-show="isShow">用户名或密码错误</p>
              <span style="font-weight: bold">用户名 </span>
              <el-input v-model="loginAccount" placeholder="请输入账号">
              </el-input>
            </div>

            <div style="margin-top: 15px">
              <span style="font-weight: bold">密 码 </span>
              <el-input v-model="loginPassword" show-password placeholder="请输入密码"></el-input>
              <br/>
            </div>

            <div style="margin-top: 15px">
              <el-button type="primary" @click="login()">登录</el-button>
            </div>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "",
  data() {
    return{
      isShow :false,
      loginAccount: '',
      loginPassword: '',
      windowHeight:{height:document.documentElement.clientHeight -120 +'px'}  //获取屏幕高度
    }
  },
  created(){
  },
  methods:{
    login(){
      var _this = this
      var formDate = new FormData();
      formDate.append("loginAccount",this.loginAccount)
      formDate.append("password",this.loginPassword)
      axios.post('http://localhost:8080/jas/sport/student/login',formDate).then(function (resp) {
        console.log(resp)
        console.log(resp.data)
        if (resp.data !=null &&resp.data!=''){
          sessionStorage.setItem("isLogin", '100')
          sessionStorage.setItem("stuName",resp.data.name)
          sessionStorage.setItem("stuAccount",resp.data.sNo)
          _this.$router.push("/StudentMainPage")
        }else {
          _this.isShow = true
        }
      })

    }
  }
}
</script>

<style lang="scss" scoped>
.el-input {
  position: relative;
  font-size: 14px;
  display: inline-block;
  width: 60%;
}
</style>









































<!--<template>-->
<!--  <div>-->
<!--    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">-->
<!--      <h3 class="login-title">欢迎 登录</h3>-->
<!--      <el-form-item label=" 账号" prop="username">-->
<!--        <el-input type="text" placeholder="请输入账号" v-model="form.username"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label=" 密码" prop="password">-->
<!--        <el-input type="password" placeholder=" 请输入密码" v-model="form.password"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" v-on:click="onSubmit( 'loginForm' )">登录</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
<!--    <el-dialog-->
<!--        title="温馨提示"-->
<!--        :visible.sync="dialogVisible"-->
<!--        width="30%"-->
<!--        :before-close="handleClose">-->
<!--      <span>请输入账号和密码</span>-->
<!--      <span slot="footer" class="dialog- footer">-->
<!--        <el-button type="primary" @click="dialogVisible = false">确定</el-button>-->
<!--      </span>-->
<!--    </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "StudentLogin",-->
<!--  data() {-->
<!--    return {-->
<!--      form: {-->
<!--        username: '',-->
<!--        password: ''-->
<!--      },-->
<!--      //表单验证，需要在el-form-item 元素中增加prop 属性-->
<!--      rules: {-->
<!--        username: [-->
<!--          {required: true, message: " 账号不可为空", trigger: 'blur'}-->
<!--        ],-->
<!--        password: [-->
<!--          {required: true, message: " 密码不可为空 ", trigger: 'blur'}-->
<!--        ]-->
<!--      },-->
<!--//对话框显示和隐藏-->
<!--      dialogVisible: false-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    onSubmit(formName) {-->
<!--//为表单绑定验证功能-->
<!--      this.$refs [formName].validate((valid) => {-->
<!--        if (valid) {-->
<!--//使用vue-router路由到指定页面，该方式称之为编程式导航-->
<!--          this.$router.push("/StudentMainPage");-->
<!--        } else {-->
<!--          this.dialogVisible = true;-->
<!--          return false;-->
<!--        }-->
<!--      });-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style lang="scss" scoped>-->
<!--.login-box {-->
<!--  border: 1px solid #DCDFE6;-->
<!--  width: 350px;-->
<!--  margin: 180px auto;-->
<!--  padding: 35px 35px 15px 35px;-->
<!--  border-radius: 5px;-->
<!--  -webkit-border-radius: 5px;-->
<!--  -moz-border-radius: 5px;-->
<!--  box-shadow: 0 0 25px #909399;-->
<!--}-->

<!--.login-title {-->
<!--  text-align: center;-->
<!--  margin: 0 auto 40px auto;-->
<!--  color: #303133;-->
<!--}-->
<!--</style>-->







<!--&lt;!&ndash;<script>&ndash;&gt;-->
<!--&lt;!&ndash;export default {&ndash;&gt;-->
<!--&lt;!&ndash;name: "StudentLogin"&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->
<!--&lt;!&ndash;</script>&ndash;&gt;-->

<!--&lt;!&ndash;<style scoped>&ndash;&gt;-->

<!--&lt;!&ndash;</style>&ndash;&gt;-->