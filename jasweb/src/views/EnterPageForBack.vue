<template>
    <el-container >
        <el-header style="height: 90px">
            <div>
                <h2>计算机学院作业辅助系统</h2>
            </div>
            <div>
                <router-link tag="a"  to="/SysLogin" style="float: right;margin-bottom: 0px" >系统管理员登录</router-link>
                <router-link tag="a"  to="/CmtLogin" style="float: right;margin-right: 20px;margin-bottom: 0px">课程管理员登录</router-link>
                <router-link tag="a"  to="/StudentMainPage" style="float: right; margin-right: 20px;margin-bottom: 0px">学生登录</router-link>
            </div>
        </el-header>
        <el-main >
            <div :style="windowHeight" id="middle">
                <img width=900px src="../assets/zyg.jpg" />
            </div>
        </el-main>
    </el-container>
</template>

<script>
    import enterImage from "@/assets/enter.jpeg"
    import schooleImage from "@/assets/school.jpg"
    export default {
        name: "",
        data() {
            return{
                enterImage : enterImage,
                schoolImage: schooleImage,
                windowHeight:{height:document.documentElement.clientHeight -150 +'px'}  //获取屏幕高度
            }
        },
        create(){

        },
        mounted() {
        }
    }
</script>

<style lang="scss" scoped>
    .el-header{
        background-color: #B3C0D1;
        text-align: center;
        color: #333;
    }
    .el-main{
        padding: 2px;
    }
    #middle{
        /*border: 1px solid blue;*/
        width: 100%;
        text-align: center;
    }
    .imagePo{
        width: 100%;
        object-fit:cover;
    }
    image{
        display:none
    }
</style>