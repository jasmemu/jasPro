<template>
  <div id="app">
    <el-button type="primary" v-on:click="devSys()" v-show="butShow">开发管理员端页面</el-button>
    <el-button type="primary" v-on:click="devStu()"  v-show="butShow" >开发学生端页面</el-button>
    <router-view :key="$route.fullPath"></router-view>
  </div>
</template>

<script>
 export default {
   data(){
    return{
      butShow : true
    }
   },
   methods:{
     devSys(){
      this.butShow = !this.butShow,
       this.$router.push("/SysMainPage")
     }
   },
   mounted() {
   }
 }
</script>
<style lang="scss" scoped>

</style>
