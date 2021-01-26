<template>
    <el-container >
        <el-header style="height: 90px">
            <div>
                <h2>计算机学院作业辅助系统</h2>
            </div>
            <div>
                <router-link tag="a"  to="/SysMainPage" style="float: right;margin-bottom: 0px" >系统管理员登录</router-link>
                <router-link tag="a"  to="/CmtMainPage" style="float: right;margin-right: 20px;margin-bottom: 0px">课程管理员登录</router-link>
                <router-link tag="a"  to="/StudentMainPage" style="float: right; margin-right: 20px;margin-bottom: 0px">学生登录</router-link>
            </div>
        </el-header>
        <el-main :style="windowHeight" style="text-align: center">
            <div class="demo-image__placeholder">
                <div class="block">
                    <el-image :src="enterImage" class="imagePo"></el-image>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script>
    import enterImage from "@/assets/enter.jpeg"
    export default {
        name: "",
        data() {
            return{
                enterImage : enterImage,
                windowHeight:{height:document.documentElement.clientHeight -120 +'px'}  //获取屏幕高度
            }
        }
    }
</script>

<style lang="scss" scoped>
    .el-header{
        background-color: #B3C0D1;
        text-align: center;
        color: #333;
    }
    .imagePo{
        width: 40%;
        object-fit:cover;
    }
</style>