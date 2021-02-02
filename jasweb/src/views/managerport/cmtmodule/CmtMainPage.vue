<template>
    <div>
        <el-container>
            <el-header >
                <div style="text-align: center">
                    <span style="font-size: 20px;font-weight: bold">课程管理员系统</span>
                </div>
                <div>
                    <div style="float: right">
                        <el-dropdown @command="handleCommand">
              <span class="el-dropdown-link">
                <span style="color: #060d9c;font-size: 20px">操作</span><i class="el-icon-arrow-down el-icon--right"></i>
              </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="a">个人设置</el-dropdown-item>
                                <el-dropdown-item command="b">退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                    <div style="float: right;font-size: 20px;margin-right: 30px">
                        <span>{{cmtName}},你好</span>
                    </div>
                </div>
            </el-header>
            <el-container >
                <el-aside :style="windowHeight"  width="200px" style="background-color: rgb(238, 241, 246)">
                    <el-menu :default-openeds="['1','2','3','4']">
                        <el-submenu index="1">
                            <template slot="title"><i class="el-icon-menu"></i>公告管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="1-1"> <router-link tag="li"  to="/CmtMainPage/NoticeMainDiv" activeClass="myClass" >公告信息</router-link></el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title"><i class="el-icon-menu"></i>作业管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="2-1"><router-link tag="li" to="/SysMainPage/TeaMainDiv"  activeClass="myClass">作业发布</router-link></el-menu-item>
                                <el-menu-item index="2-2"><router-link tag="li" to="/SysMainPage/eaMainDiv"  activeClass="myClass">作业评阅</router-link></el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="3">
                            <template slot="title"><i class="el-icon-menu"></i>资料管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="3-1"><router-link tag="li" to="/SysMainPage/CourseMainDiv"  activeClass="myClass">资料信息</router-link></el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="4">
                            <template slot="title"><i class="el-icon-menu"></i>留言管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="4-1"><router-link tag="li" to="/SysMainPage/CmtMainDiv"  activeClass="myClass">留言信息</router-link></el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                    </el-menu>
                </el-aside>

                <el-main :style="windowHeight">
                    <router-view :key="chan"></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "",
        data() {
            return {
                chan: '',
                cmtName: '',
                windowHeight:{height:document.documentElement.clientHeight -80 +'px'}  //获取屏幕高度
            }
        },
        created(){
          this.cmtName = sessionStorage.getItem('cmtName')
        },
        mounted() {
            var myDate = new Date();
            var str = '';
            var str = myDate.getMinutes().toString()+myDate.getSeconds().toString();
            this.chan = str
        },
        methods:{
            handleCommand(command) {
                // this.$message('click on item ' + command);
                if (command == 'a'){
                    this.$router.push('/SetCmtInfo')
                }else {
                    sessionStorage.clear();
                    this.$router.push('/enter')
                }
            }
        }
    }
</script>

<style  lang="scss" scoped>
.myClass{
    color: #060d9c;
}
.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: left;
    line-height: 50px;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
}

body > .el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}
</style>