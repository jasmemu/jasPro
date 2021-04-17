<template>
    <div>
      <el-container>
        <el-header >
          <div style="text-align: center">
            <span style="font-size: 20px;font-weight: bold">系统管理员系统</span>
          </div>
          <div>
            <div style="float: right">
              <el-dropdown @command="handleCommand">
              <span class="el-dropdown-link">
                <span style="color: rgb(36,67,236);font-size: 20px">操作</span><i class="el-icon-arrow-down el-icon--right"></i>
              </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="a">个人设置</el-dropdown-item>
                  <el-dropdown-item command="b">下载excel模板</el-dropdown-item>
                  <el-dropdown-item command="c">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
            <div style="float: right;font-size: 20px;margin-right: 30px">
              <span>{{sysName}},你好</span>
            </div>
          </div>
        </el-header>
        <el-container >
          <el-aside :style="windowHeight"  width="15%" style="background-color: #545c64">
                <el-menu
                        :default-openeds="['1','2','3','4','5']"
                        default-active="2"
                        class="el-menu-vertical-demo"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b">
                  <el-submenu index="1">
                    <template slot="title"><i class="el-icon-menu"></i>学生管理</template>
                    <el-menu-item-group>
                      <el-menu-item index="1-1" class="routeInCecter"> <router-link tag="li"  to="/SysMainPage/StuMainDiv" activeClass="myClass" >学生信息</router-link></el-menu-item>
                    </el-menu-item-group>
                  </el-submenu>
                  <el-submenu index="2">
                    <template slot="title"><i class="el-icon-menu"></i>教师管理</template>
                    <el-menu-item-group>
                      <el-menu-item index="2-1" class="routeInCecter"><router-link tag="li" to="/SysMainPage/TeaMainDiv"  activeClass="myClass">教师信息</router-link></el-menu-item>
                    </el-menu-item-group>
                  </el-submenu>
                  <el-submenu index="3">
                    <template slot="title"><i class="el-icon-menu"></i>课程管理</template>
                    <el-menu-item-group>
                      <el-menu-item index="3-1" class="routeInCecter"><router-link tag="li" to="/SysMainPage/CourseMainDiv"  activeClass="myClass">课程信息</router-link></el-menu-item>
                    </el-menu-item-group>
                  </el-submenu>
                  <el-submenu index="4">
                    <template slot="title"><i class="el-icon-menu"></i>学委管理</template>
                    <el-menu-item-group>
                      <el-menu-item index="4-1" class="routeInCecter"><router-link tag="li" to="/SysMainPage/CmtMainDiv"  activeClass="myClass">学委信息</router-link></el-menu-item>
                    </el-menu-item-group>
                  </el-submenu>
                  <el-submenu index="5">
                    <template slot="title"><i class="el-icon-menu"></i>班级管理</template>
                    <el-menu-item-group>
                      <el-menu-item index="5-1" class="routeInCecter"><router-link tag="li" to="/SysMainPage/ClassMainDiv"  activeClass="myClass">班级信息</router-link></el-menu-item>
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
          sysName: '123',
          squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
          windowHeight:{height:document.documentElement.clientHeight -80 +'px'}  //获取屏幕高度
        }
      },
      methods:{
        handleCommand(command) {
          // this.$message('click on item ' + command);
          if (command == 'a'){
            this.$router.push('/SetSysInfo')
          }else if(command == 'b'){
            this.$router.push('/DownloadExcel')
          } else if(command == 'c') {
            sessionStorage.clear();
            this.$router.push('/enter')
          }
        }
      },
      created(){
         this.sysName = sessionStorage.getItem('sysAccount')
      },
      mounted() {
        var myDate = new Date();
        var str = '';
        var str = myDate.getMinutes().toString()+myDate.getSeconds().toString();
        this.chan = str
      }
    }
</script>

<style lang="scss" scoped >
  .routeInCecter{
    text-align: center;
  }
  .myClass{
   color: #FFD04B;
  }
  .el-header, .el-footer {
    background-color: #389ea9;
    color: #333;
  }


  .el-aside {
    background-color: #545c64;
    color:  #545c64;
    text-align: left;
    line-height: 50px;
  }

  .el-aside[data-v-709e568e]{
    background-color: #545c64;
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
