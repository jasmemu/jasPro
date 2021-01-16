<template>
    <div>
      <el-container>
        <el-header>系统管理员系统</el-header>
        <el-container >
          <el-aside :style="windowHeight" width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu :default-openeds="['1', '3']">
              <el-submenu index="1">
                <template slot="title"><i class="el-icon-message"></i>学生管理</template>
                <el-menu-item-group>
                  <el-menu-item index="1-1"> <router-link tag="li"  to="/SysMainPage/StuMainDiv" >学生信息</router-link></el-menu-item>
                  <el-menu-item index="1-2"><router-link tag="li" to="">null</router-link></el-menu-item>
                </el-menu-item-group>

              </el-submenu>
              <el-submenu index="2">
                <template slot="title"><i class="el-icon-menu"></i>教师管理</template>
                <el-menu-item-group>
                  <el-menu-item index="2-1"><router-link tag="li" to="/SysMainPage/ImportTeaInfo">教师信息</router-link></el-menu-item>
                  <el-menu-item index="2-2"><router-link tag="li" to=""></router-link>null</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="3">
                <template slot="title"><i class="el-icon-setting"></i>课程管理</template>
                <el-menu-item-group>
                  <el-menu-item index="3-1"><router-link tag="li" to="/SysMainPage/CourseMainDiv">课程信息</router-link></el-menu-item>
                  <el-menu-item index="3-2"><router-link tag="li" to="">null</router-link></el-menu-item>
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
          windowHeight:{height:document.documentElement.clientHeight -80 +'px'}  //获取屏幕高度
        }
      },
      methods: {
        goStuAdd() {
          this.$router.push({ name: 'ImporStuInfo'})
        }
      },
      mounted() {
        var myDate = new Date();
        var str = '';
        var str = myDate.getMinutes().toString()+myDate.getSeconds().toString();
        this.chan = str

      }
    }
</script>

<style lang="scss" >
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
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
