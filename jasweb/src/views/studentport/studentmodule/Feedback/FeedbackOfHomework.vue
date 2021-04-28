<template>



<div>
  <el-badge :value="3" class="item"  style="float:right">
    <el-button size="small" @click="seeReply()">回复</el-button>
  </el-badge>

  <el-input
      label="sss"
      type="textarea"
      :rows="8"
      placeholder="请输入反馈内容"
      v-model="textarea">
  </el-input>
<!--  <el-button type="primary"  style="float:right"  @click="back()"  plain>返回</el-button>-->
  <el-button type="primary"  style="float:right"  @click="commit()"  plain>提交</el-button>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "FeedbackOfHomework",
  data() {

    return {
      updateTime:"",
      comid:'',
      stuid:'',
      textarea: '',
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: '',

      }
    };
  },

  created() {
    var sno =sessionStorage.getItem('stuAccount')
    var that = this

    axios.get('http://localhost:8080/jas/sport/student/getComidByStudent/'+sno).then(function (resp) {
      that.comid = resp.data
      that.stuid = sno
    })
  },


  // created(){
    // var that = this
    // axios.get('http://localhost:8080/jas/sport/student/findMyHomework/'+this.formForSearch.courseId).then(function (resp) {
    //   that.total = resp.data
    // })
  // },
  // mounted(){
  //   var _this = this
  //   axios.get('http://localhost:8080/jas/sport/student/findMyHomework/'+this.formForSearch.courseId).then(function (resp) {
  //     console.log(resp.data)
  //     _this.tableData = resp.data
  //   })
  // },

  methods:{

    // back(){
    //   this.$router.push('/StudentMainPage/CourseHomework')
    // },

    // addDate(){
    //   const nowDate = new Date();
    //   const date = {
    //     year: nowDate.getFullYear(),
    //     month: nowDate.getMonth() + 1,
    //     date: nowDate.getDate(),
    //   }
    //   const newmonth = date.month>10?date.month:'0'+date.month
    //   const day = date.date>10?date.date:'0'+date.date
    //   this.updateTime = date.year + '-' + newmonth + '-' + day
    //
    // },
    seeReply(){
      this.$router.push({name:'ReplyFromManager'})
    },


    commit(){
      const nowDate = new Date();
      const date = {
        year: nowDate.getFullYear(),
        month: nowDate.getMonth() + 1,
        date: nowDate.getDate(),
      }
      const newmonth = date.month>10?date.month:'0'+date.month
      const day = date.date>10?date.date:'0'+date.date
      this.updateTime = date.year + '-' + newmonth + '-' + day



      this.$notify({
        title: '提交成功',
        message: '提交成功，等待管理员回复',
        type: 'success'
      });

      var _this = this
      var formDate = new FormData();
      formDate.append("comid",this.comid)
      formDate.append("stuid",this.stuid)
      formDate.append("content",this.textarea)
      formDate.append("date",this.updateTime)

      axios.post('http://localhost:8080/jas/sport/student/insertMessage',formDate).then(function (resp) {
        console.log(resp)
        console.log(resp.data)
        // if (resp.data !=null &&resp.data!=''){
        //   sessionStorage.setItem("isLogin", '100')
        //   sessionStorage.setItem("stuName",resp.data.name)
        //   sessionStorage.setItem("stuAccount",resp.data.sNo)
        //   _this.$router.push("/StudentMainPage")
        // }else {
        //   _this.isShow = true
        // }
      })

    }

  }
}
</script>

<style scoped>

</style>
