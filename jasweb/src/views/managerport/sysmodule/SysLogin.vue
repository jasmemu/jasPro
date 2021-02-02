<template>
    <div style="text-align: center">
        <el-container>
            <el-header style="height: 90px">
                <h1>欢迎登录系统管理员系统</h1>
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
                            <el-input v-model="loginPassword" placeholder="请输入密码"></el-input>
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
                formDate.append("loginPassword",this.loginPassword)
                axios.post('http://localhost:8080/jas/mport/sys/login',formDate).then(function (resp) {
                    console.log(resp)
                    console.log(resp.data)
                    if (resp.data !=null &&resp.data!=''){
                        sessionStorage.setItem("isLogin", '100')
                        sessionStorage.setItem("sysAccount",resp.data.account)
                        _this.$router.push("/SysMainPage")
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