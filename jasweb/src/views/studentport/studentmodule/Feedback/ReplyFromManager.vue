<template>

<div>
  <el-table
      :data="tableData"
      style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="回复人">
            <span>{{ props.row.comId }}</span>
          </el-form-item>
          <el-form-item label="回复内容">
            <span>{{ props.row.replyMessage }}</span>
          </el-form-item>
          <el-form-item label="回复时间">
            <span>{{ props.row.rmDate }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>



<!--  <el-button @click="jumpBack()" style="float:right">返回</el-button>-->

<!--记得加一个返回的按钮！！！-->


<!--    <div>-->
<!--      <el-button @click="jumpBack()" size="small" style="float:right">返回</el-button>-->
<!--    </div>-->

    <el-table-column

        label="我的反馈"
        prop="leaveMessage">
    </el-table-column>
    <el-table-column
        label="发送时间"
        prop="lmDate">
    </el-table-column>

    <el-table-column
        fixed="right"
        label="删除"
        width="200">
      <template slot-scope="scope">
        <el-button @click="deleteById(scope.row)" type="text" size="small">删除</el-button>
      </template>
    </el-table-column>

  </el-table>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "ReplyFromManager",
  data(){
    return{
    tableData:null,
    sno:null,
  }
    },

  created() {
    var that = this
    that.sno =sessionStorage.getItem('stuAccount')

    axios.get('http://localhost:8080/jas/sport/student/getMyResponse/'+this.sno).then(function (resp) {
      that.tableData = resp.data
      // that.comid = resp.data
      // that.stuid = sno
    })
  },
  // mounted(){
  //   var _this = this
  //   axios.get('http://localhost:8080/jas/sport/student/getMyResponse/'+this.sno).then(function (resp) {
  //     console.log(resp.data)
  //     _this.tableData = resp.data
  //   })
  // },

  methods:{
    deleteById(row){
      var _this = this;
      this.$confirm('确定删除该条留言信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var  that = this
        axios.get('http://localhost:8080/jas/mport/message/delete/'+ row.id).then(function (resp) {
          if (resp.data == 1){
            alert('删除成功')
            location.reload();
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
  }



}






</script>

<style scoped>

</style>