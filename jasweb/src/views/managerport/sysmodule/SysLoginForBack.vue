<template>
    <div style="text-align: center">
        <el-container>
            <el-header style="height: 90px">
                <h1>欢迎登录系统管理员系统</h1>
            </el-header>
            <el-main :style="windowHeight">
                <div >
                    <div style=" width: 90%;text-align: center">
                        <div style="margin-top: 110px;width: 30%;margin-left: 35%">
                            <p style="color: #a50912" v-show="isShow">用户名或密码错误</p>
                            <span style="font-weight: bold">用户名 </span>
                            <el-input v-model="loginAccount" placeholder="请输入账号">
                            </el-input>
                        </div>

                        <div style="margin-top: 15px;width: 30%;margin-left: 35%">
                            <span style="font-weight: bold">密 码 </span>
                            <el-input v-model="loginPassword" placeholder="请输入密码" show-password></el-input>
                            <br/>
                            <!--                            <el-checkbox v-model="rememberMe">记住密码</el-checkbox>-->
                            <!--                            <p @click="forgetPWD" style="color: #3b529c">忘记密码？</p>-->
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
                loginAccount: '',
                loginPassword: '',
                windowHeight:{height:document.documentElement.clientHeight -120 +'px;'}  //获取屏幕高度
            }
        },
        // 账号密码无需再次填写
        created(){
            var account=this.getCookie('rememberSysAccount')
            var pwd =this.getCookie('rememberSysPwd')
            if (account!=null&&account!=undefined&&pwd!=null&&pwd!=undefined){
                this.loginAccount = account
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
                this.$router.push({path:'/FindBackPWD/FillEmail',params:{'fromSysOrCmt':'fromSys'}})
            },
            login(){
                //是否记住账号密码
                if (this.rememberMe == true) {
                    this.setCookie("rememberSysAccount",this.loginAccount)
                    this.setCookie("rememberSysPwd",this.loginPassword)
                }
                // 登录
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
                        _this.$router.push("/SysMainPage/StuMainDiv")
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