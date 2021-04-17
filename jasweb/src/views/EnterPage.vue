<template>
    <div>
        <!-- PRELOADER
        ============================================= -->
        <div id="loader-wrapper">
            <div id="loader">
                <div class="cssload-spin-box"></div>
            </div>
        </div>

        <!-- PAGE CONTENT
        ============================================= -->
        <div id="page-content" class="page-content demo-01">

            <!-- HEADER
            ============================================= -->
            <header id="header" class="header fixed-top">
                <nav class="navbar navbar-expand-lg navbar-dark roiton-menu">
                    <div class=container>

                        <!-- LOGO NAME -->
                        <!--   <a href="index.html" class="navbar-brand">Roiton</a> -->


                        <!-- Responsive Menu Button -->
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-bar-icon"><i class="lni-menu"></i></span>
                        </button>

                        <!-- Navigation Menu -->
                        <div id="navbarSupportedContent" class="collapse navbar-collapse">

                            <ul class="navbar-nav ml-auto">
                                <!-- Dropdown Link -->
                                <li class="nav-item nl-simple"><a class=nav-link @click="goLoginPage('student')"  target="blank">学生登录</a></li>
                                <li class="nav-item nl-simple"><a class=nav-link @click="goLoginPage('cmt')" target="blank">学委登录</a></li>
                                <li class="nav-item nl-simple"><a class=nav-link @click="goLoginPage('manager')" target="blank">管理员登录</a></li>
                            </ul>

                        </div>  <!-- End Navigation Menu -->

                    </div>  <!-- End container -->

                </nav>  <!-- End navbar -->
            </header>  <!-- END HEADER -->

            <!-- HERO
            ============================================= -->
            <section id="hero-01" class="hero full-screen py-6">
                <div class="hero-bg-graphics">
                    <img src="~@/assets/img/hero-bg-01.png" alt="/">
                </div>
                <div class="hero-center">
                    <div class="container">
                        <div class="row align-items-center">
                            <!-- HERO TEXT -->
                            <div class="col-lg-6 ">
                                <div class="hero-txt">
                                    <div>
                                        <!-- Title -->
                                        <h1 class="font-weight-bold">
                                            欢迎使用JAS系统
                                        </h1>
                                        <!-- Text -->
                                        <h5 class="text-capitalize" ><span> <br> </span> <span class="element second-color" data-elements="will look perfect in every way.,will be more powerfull.,Will Have Incredible Business Feature."></span></h5>
                                        <h5 class="py-3">
                                            天行健，君子以自强不息。 --《周易乾象》
                                        </h5>
                                    </div>
                                </div>
                            </div>	<!-- END HERO TEXT -->

                            <!-- HERO IMAGE -->
                            <div class="col-lg-6 ">
                                <div class="hero-img">
                                    <img class="img-fluid" src="~@/assets/img/hero-01.png" alt="hero-image" />
                                </div>
                            </div>
                        </div>  <!-- End row -->

                    </div>  <!-- End container -->
                </div>  <!-- END HERO-CENTER -->
            </section>   <!-- END HERO -->


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
                loginAccountForCmt: '',
                loginPassword: '',
                windowHeight:{height:document.documentElement.clientHeight -120 +'px'}  //获取屏幕高度
            }
        },
        mounted(){
            $("#loader-wrapper").delay(100).fadeOut("slow");
        },
        methods:{
            goLoginPage(val){
                if (val == 'student') {
                    this.$router.push('/StudentMainPage')
                }else if (val== 'cmt'){
                    this.$router.push('/CmtLogin')
                }else if (val=='manager') {
                    this.$router.push('/SysLogin')
                }

            }
        }
    }
</script>

<style lang="scss" scoped>
    @import url('~@/assets/css/bootstrap.css');
    @import url('~@/assets/css/style.css');
    .icon{
        font-family: FontAwesome;
    }
    .el-input {
        position: relative;
        font-size: 14px;
        display: inline-block;
        width: 60%;
    }
</style>
