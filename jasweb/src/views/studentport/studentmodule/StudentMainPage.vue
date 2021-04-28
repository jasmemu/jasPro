<template>

  <div>
    <el-container>
      <el-header >
        <div style="text-align: center">
          <span style="font-size: 30px;font-weight: bold">作业管理系统</span>
        </div>
        <div>
          <div style="float: right">
            <el-dropdown @command="handleCommand" >
              <span class="el-dropdown-link">
                <span style="color: #060d9c;font-size: 20px">操作</span><i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="a">个人设置</el-dropdown-item>
                <el-dropdown-item command="b">退出登录</el-dropdown-item>
                <el-dropdown-item command="c">修改密码</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <div style="float: right;font-size: 20px;margin-right: 30px">
            <span>{{stuName}},你好</span>

          </div>

        </div>
      </el-header>

      <el-container >
        <el-aside :style="windowHeight"  width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu :default-openeds="['1','2','3','4','5']">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-folder-opened"></i>我的课程</template>
              <el-menu-item-group>
                <el-menu-item index="1-1"> <router-link tag="li"  to="/StudentMainPage/StudentCoursesMain" activeClass="myClass" >选课信息</router-link></el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-notebook-2"></i>我的作业</template>
              <el-menu-item-group>
                <el-menu-item index="2-1"><router-link tag="li" to="/StudentMainPage/CourseType"  activeClass="myClass">作业信息</router-link></el-menu-item>
                <el-menu-item index="2-2"><router-link tag="li" to="/StudentMainPage/FeedbackOfHomework"  activeClass="myClass">问题反馈</router-link></el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-files"></i>我的资料</template>
              <el-menu-item-group>
                <el-menu-item index="3-1"><router-link tag="li" to="/StudentMainPage/ResourceMain"  activeClass="myClass">学习资料</router-link></el-menu-item>
                <el-menu-item index="3-2"><router-link tag="li" to="/StudentMainPage/FeedbackOfHomework"  activeClass="myClass">我的上传</router-link></el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title"><i class="el-icon-date"></i>公告信息</template>
              <el-menu-item-group>
                <el-menu-item index="4-1"> <router-link tag="li"  to="/StudentMainPage/AnnounceMainPage" activeClass="myClass" >浏览公告</router-link></el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title"><i class="el-icon-user"></i>问题讨论</template>
              <el-menu-item-group>
                <el-menu-item index="5-1"><router-link tag="li" to="/CmtMainPage/MessageMainDiv"  activeClass="myClass">留言区</router-link></el-menu-item>
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
      stuName: '',
      windowHeight:{height:document.documentElement.clientHeight -80 +'px'} //获取屏幕高度1
    }
  },
  created(){
    this.stuName = sessionStorage.getItem('stuName')
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
        this.$router.push('/SetStudentInfo')
      }
      else if(command == 'c'){
        this.$router.push('/ResetPassword')
      }
      else if(command == 'b'){
        sessionStorage.clear();
        this.$router.push('/enter')
      }
    },
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
  background-color:#409EFF;
  //background-color: #D3DCE6;
  color: #333;
  text-align: left;
  line-height: 50px;
}

.el-main {
  //background-color:#409EFF;
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