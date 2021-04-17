<template>
    <div>
        <div class="shadow mb-5 bg-light rounded login-top">
            <div class="head">
                <img src="~@/assets/images/logo.png"><span>
                系统管理员登录
            </span>
            </div>
        </div>
        <div class="login-box">
            <div class="tree">
                <img src="~@/assets/images/2.png" class="img-fluid">
            </div>
            <div class="slogan">
                克勤之致<br><span class="yellow">结青藤</span>之缘
            </div>
            <div id="earth" class="earth">
                <img src="~@/assets/images/1.png" class="img-fluid">
            </div>
            <!-- 登录 -->
            <div class="login">
                <div class="login-logo">
                    <img src="~@/assets/images/logo.png" class="img-fluid">
                </div>

                <div class="login-form">
                    <p style="color: #a50912" v-show="isShow">账号或密码错误</p>
                    <form method="POST">
                        <code class="check_log_phone"></code>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-user-circle-o text-success"></span>
                            </span>
                            </div>
                            <input type="text" class="form-control" v-model="loginAccount"  id="logphone" placeholder="账号">
                        </div>
                        <code class="check_log_pwd"></code>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-unlock text-success"></span>
                            </span>
                            </div>
                            <input type="password" class="form-control"  v-model="loginPassword" id="logpwd" placeholder="密码">
                        </div>
                        <button type="button" id="login"  @click="login()"  class="btn btn-success btn-block">登录</button>
                    </form>
                </div>
                <!--                <div class="login-regist">-->
                <!--                    忘记密码？<span id="ToRegist">找回密码</span>-->
                <!--                </div>-->
            </div>
        </div>
        <div class="login-footer">
            <div class="container">
                <div class="row">
                    <div class="col text-center login-nav">
                        <div class="foot">
                            咨询热线：143-****-****&emsp;&emsp;邮箱：<a href="mailto:****@126.com"
                                                                target="_blank">*****@126.com</a>&emsp;&emsp;备案号：***备*******号
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
    @import url('~@/assets/css/login.css');
    @import url("https://www.jq22.com/jquery/bootstrap-4.2.1.css");
    @import url("https://www.jq22.com/jquery/font-awesome.4.7.0.css");
    .icon{
        font-family: FontAwesome;
    }

</style>