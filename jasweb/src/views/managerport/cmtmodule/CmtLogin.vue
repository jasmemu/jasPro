<template>
    <div style="text-align: center">
        <el-container>
            <el-header style="height: 90px">
                <h1>欢迎登录课程管理员系统</h1>
            </el-header>
            <el-main :style="windowHeight">
                <div  style="margin-left: 500px">
                    <div style=" width: 55%">
                        <div style="margin-top: 110px">
                            <p style="color: #a50912" v-show="isShow">用户名或密码错误</p>
                            <span style="font-weight: bold">用户名 </span>
                            <el-input v-model="loginAccountForCmt" placeholder="请输入账号">
                            </el-input>
                        </div>

                        <div style="margin-top: 15px">
                            <span style="font-weight: bold">密 码 </span>
                            <el-input v-model="loginPassword" placeholder="请输入密码" show-password></el-input>
                            <br/>
                            <p @click="forgetPWD" style="color: #3b529c">忘记密码？</p>
<!--                            <el-checkbox v-model="rememberMe">记住密码</el-checkbox>-->
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
                rememberMe: true,
                isShow :false,
                loginAccountForCmt: '',
                loginPassword: '',
                windowHeight:{height:document.documentElement.clientHeight -120 +'px'}  //获取屏幕高度
            }
        },

        // 账号密码无需再次填写
        created(){
            if (this.getCookie('rememberCmtAccount')!=null){
                var account=this.getCookie('rememberCmtAccount')
            }
            if (this.getCookie('rememberCmtPwd')!=null){
                var pwd =this.getCookie('rememberCmtPwd')
            }
            if (account!=null&&pwd!=null){
                this.loginAccountForCmt = account
                this.loginPassword = pwd
            }
        },
        methods:{
            setCookie(name,value)
            {
                var strsec = this.getsec('d30');
                var exp = new Date();
                exp.setTime(exp.getTime() + strsec*1);
                document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
            },
            getCookie(name)
            {
                var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
                if(arr=document.cookie.match(reg)) return unescape(arr[2]);
                else return null;
            },
            getsec(str){
                // alert(str);
                var str1=str.substring(1,str.length)*1;
                var str2=str.substring(0,1);
                if (str2=="s"){
                    return str1*1000;
                }else if (str2=="h"){
                    return str1*60*60*1000;
                }else if (str2=="d"){
                    return str1*24*60*60*1000;
                }
            },
            delCookie(name)
            {
                var exp = new Date();
                exp.setTime(exp.getTime() - 1);
                var cval=getCookie(name);
                if(cval!=null) document.cookie= name + "=" +cval+";expires="+exp.toGMTString();
            },
            // 账号密码无需再次填写
            forgetPWD(){
              // this.$router.push('/FindBackPWD/FillEmail')
                this.$router.push({path:'/FindBackPWD/FillEmail',params:{'fromSysOrCmt':'fromCmt'}})
            },
            login(){
                //是否记住账号密码
                if (this.rememberMe == true) {
                    this.setCookie("rememberCmtAccount",this.loginAccountForCmt)
                    this.setCookie("rememberCmtPwd",this.loginPassword)
                }
                // 登录
                var _this = this
                var formDate = new FormData();
                formDate.append("loginAccount",this.loginAccountForCmt)
                formDate.append("loginPassword",this.loginPassword)
                axios.post('http://localhost:8080/jas/mport/cmt/login',formDate).then(function (resp) {
                    console.log(resp)
                    console.log(resp.data)
                    if (resp.data !=null &&resp.data!=''){
                        sessionStorage.setItem("cmtLogin", '100')
                        sessionStorage.setItem("cmtName",resp.data.name)
                        sessionStorage.setItem("cmtComId",resp.data.comId)
                        _this.$router.push("/CmtMainPage/NoticeMainDiv")
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