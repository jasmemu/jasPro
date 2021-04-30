<template>
  <div>
    <div  style="height: 50px">
      <form>
        <div style="float: left;margin-left: 20px" >
          作业名称:
          <el-input style="width: auto"      v-model="formForSearch.hName"  size="small" placeholder="请输入内容"></el-input>
          是否提交：
          <el-radio v-model="commit" label="yes">已交</el-radio>
          <el-radio v-model="commit" label="no">未交</el-radio>
        </div>
        <div>
          <el-button type="primary" size="small" style="margin-left: 30px" @click="search()">搜索</el-button>
          <el-button type="primary"  style="float:right"  @click="back()"  plain>返回</el-button>
        </div>
      </form>
    </div>
    <hr>

    <div>
      <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
    </div>

    <div><!--      @selection-change="handleSelectionChange"放在下面空的地方-->
      <el-table
          :data="tableData"

          style="width: 100%">
<!--        <el-table-column-->
<!--            fixed-->
<!--            type="selection"-->
<!--            width="50">-->
<!--        </el-table-column>-->
        <el-table-column
            fixed
            label="序号"
            type="index"
            width="50">
        </el-table-column>

        <el-table-column
            prop="hName"
            label="作业名称"
            width="200">
        </el-table-column>
        <el-table-column
            prop="publishDate"
            label="发布日期"
            width="200">
        </el-table-column>
        <el-table-column
            prop="endDate"
            label="截止时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="mark"
            label="作业说明"
            width="200">
        </el-table-column>

        <el-table-column
            fixed="right"
            label="操作"
            width="200">
          <template slot-scope="scope" v-for="item in tableData">
            <el-button @click="downloadJob(item.hid)" type="text" size="small">下载</el-button>
            <el-button  @click="lookDetail(item.hid)" type="text" size="small">查看详情</el-button>
            <el-button  @click="setFeedback(item.hid)" type="text" size="small">反馈</el-button>
            <!-- <el-button @click="deleteById(scope.row)" type="text" size="small">删除</el-button>-->
          </template>
        </el-table-column>
      </el-table>

      <div style="float: right;margin-top: 10px">
        <el-pagination
            background
            layout="prev, pager, next"
            :page-size="pageSize"
            :total="total"
            @current-change="mypage">
        </el-pagination>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "",
  data(){
    return{
      // 批量删除
      multipleSelection: [],
      pageSize: 5,
      total: 10,
      commit: '',
      hid:'',
      hName: '',
      formForSearch: {
        hName: '',
        courseId: this.$route.params.courseIdFromCourseType,
        // comId: sessionStorage.getItem('cmtComId')
      },
      tableData: null
    }
  },
  created(){
    // var that = this
    // axios.get('http://localhost:8080/jas/sport/student/findMyHomework/'+this.formForSearch.courseId).then(function (resp) {
    //   that.total = resp.data
    // })
  },
  mounted(){
    var _this = this
    axios.get('http://localhost:8080/jas/sport/student/findMyHomework/'+this.formForSearch.courseId).then(function (resp) {
      console.log(resp.data)
      _this.tableData = resp.data
      _this.hid = resp.data.hId
      _this.hName = resp.data.hName
      console.log(resp.data.hId)
      console.log(resp.data.hName)
      sessionStorage.setItem("hid", resp.data.hId)
      sessionStorage.setItem("hid", resp.data.hName)
      // sessionStorage.setItem("stuName",resp.data.name)
      // sessionStorage.setItem("stuAccount",resp.data.sNo)
    })
  },
  methods: {
    //批量删除
    deleteBatch(){
      if (this.multipleSelection.length < 1){
        alert("请至少选择一条")
        return
      }
      axios.post('http://localhost:8080/jas/mport/message/delete/byBatch',this.multipleSelection).then(function (resp) {
        alert(resp.data)
        location.reload()
      })

    },
    goAddJob(){
      this.$router.push('/CmtMainPage/AddJobInfo')
    },
    //table中的
    mypage (currentpage) {
      const _this = this
      axios.get('http://localhost:8080/jas/mport/score/get/score/'+ this.formForSearch.comId+'/'+this.formForSearch.courseId  +'/'+ +currentpage +'/' +_this.pageSize).then(function (resp) {
        _this.tableData = resp.data
        console.log(resp.data)
      })
    },
    downloadJob(row){
      console.log(row)
      const a = document.createElement('a'); // 创建a标签
      let url = row.hUrl
      var x=url.indexOf('/');
      for(var i=0;i<2;i++){
        x=url.indexOf('/',x+1);
      }
      var  endUrl =url.slice(x)
      console.log(endUrl)
      a.setAttribute('download',row.hName);// download属性
      a.setAttribute('href',endUrl);// href链接
      a.click();// 自执行点击事件
    },
    lookDetail(row){
      console.log("row is")
      console.log(row)
      //this.$router.push({name:'HomeworkDetail',params:{scoreObj:row}})
      this.$router.push({name:'HomeworkDetail',params:{hId:this.hid}})

    },
    setFeedback(row){
      console.log("row is")
      console.log(row)
      this.$router.push({name:'FeedbackOfHomework',params:{scoreObj:row}})
    },
    back(){
      this.$router.push('/StudentMainPage/CourseType')

    },

    // deleteById(row){
    //   var de;
    //   var _this = this;
    //   this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
    //     confirmButtonText: '确定',
    //     cancelButtonText: '取消',
    //     type: 'warning'
    //   }).then(() => {
    //     var  that = this
    //     axios.get('http://localhost:8080/jas/mport/homework/deleteByhId/'+ row.hId).then(function (resp) {
    //       if (resp.data == 1){
    //         alert('删除成功')
    //         location.reload();
    //       }
    //     });
    //   }).catch(() => {
    //     this.$message({
    //       type: 'info',
    //       message: '已取消删除'
    //     });
    //   });
    // },
    // search() {
    //   this.total=0;
    //   if (this.formForSearch.hName != '' && this.formForSearch.hName !=null){
    //     this.$router.push({name:'ViewCheckJob',params:{hName:this.formForSearch.hName,commit:this.commit,courseId:this.formForSearch.courseId }})
    //   } else {
    //     this.$message({
    //       type: 'warning',
    //       message: '请输入查询条件'
    //     });
    //   }
    //
    // }
  }
}
</script>

<style lang="scss" scoped>

</style>