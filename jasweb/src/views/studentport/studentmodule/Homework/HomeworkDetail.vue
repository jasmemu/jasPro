<template>
  <div>
    <div  style="height: 50px">
      <form>
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
<!--            label="序号"-->
<!--            type="index"-->
<!--            width="50">-->
<!--        </el-table-column>-->

        <el-table-column
            prop="hName"
            label="作业名称"
            width="200">
        </el-table-column>
        <el-table-column
            prop="score"
            label="分数"
            width="200">
        </el-table-column>
        <el-table-column
            prop="comId"
            label="批阅人"
            width="200">
        </el-table-column>
        <el-table-column
            prop="comment"
            label="评语"
            width="200">
        </el-table-column>
        <el-table-column
            prop="correctDate"
            label="批改时间"
            width="200">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="200">
          <template slot-scope="scope">
            <el-button @click="downloadJob(scope.row)" type="text" size="small">下载</el-button>
            <el-button  @click="lookDetail(scope.row)" type="text" size="small">查看详情</el-button>
            <el-button  @click="setFeedback(scope.row)" type="text" size="small">反馈</el-button>
          </template>
        </el-table-column>
      </el-table>

<!--      <div style="float: right;margin-top: 10px">-->
<!--        <el-pagination-->
<!--            background-->
<!--            layout="prev, pager, next"-->
<!--            :page-size="pageSize"-->
<!--            :total="total"-->
<!--            @current-change="mypage">-->
<!--        </el-pagination>-->
<!--      </div>-->

    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
name: "HomeworkDetail",
  data(){
    return{
      // hid:null,
      hName:null,
      tableData: null,
      hid: this.$route.params.hId,
    }
  },


created(){
  var _this = this
  var hId = _this.hid
  //_this.hid = sessionStorage.getItem("hid")
  console.log(hId)
  _this.hName = sessionStorage.getItem("hName")
  axios.get('http://localhost:8080/jas/mport/score/getMyScore/'+hId).then(function (resp) {
        console.log(resp.data)
        _this.tableData = resp.data
  })
},



  // mounted(){
  //   var _this = this
  //   var hid = sessionStorage.getItem("hid")
  //   axios.get('http://localhost:8080/jas/mport/score/getMyScore/'+hid).then(function (resp) {
  //     console.log(resp.data)
  //     _this.tableData = resp.data
  //   })
  // },



}
</script>

<style scoped>

</style>