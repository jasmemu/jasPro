<template>
  <div>
    <h3>你所选择的学科：</h3>
    <span v-for="item in courseList">
            <el-button style="margin-left: 20px" type="primary" @click="toCourseDiv(item.courseId)" >{{item.name}}</el-button>
        </span>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "",
  data() {
    return {
      sNo: sessionStorage.getItem('stuAccount'),
      courseList: null

    }
  },
  created() {
    var that =this
    axios.get('http://localhost:8080/jas/sport/student/getCourseBySno/'+this.sNo).then(function (resp) {
      console.log(resp.data)
      // console.log(resp.data.courseId)
      that.courseList = resp.data

    })
  },
  methods:{
    toCourseDiv(courseId){
      this.$router.push({name:'CourseHomework',params:{courseIdFromCourseType:courseId}})
      // this.$router.push({name:'CourseHomework',params:{nameFromCourseType:name}})
    }
  }
}
</script>

<style lang="scss" scoped>
</style>