<template>
  <div>
      <h1>{{title}}【{{jobObj.hName}}】的学生名单</h1>
      <el-table
              :data="tableData"
              style="width: 70%;margin-left: 10%">
          <el-table-column
                  fixed
                  label="序号"
                  type="index"
                  width="100">
          </el-table-column>
          <el-table-column
                  prop="sNo"
                  label="学号"
                  width="180">
          </el-table-column>
          <el-table-column
                  prop="name"
                  label="姓名"
                  width="180">
          </el-table-column>
          <el-table-column
                  prop="phone"
                  label="电话">
          </el-table-column>
      </el-table>
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                title: '',
                jobObj :null,
                tableData: null
            }
        },
        mounted() {
            var commited = this.$route.params.committed
            this.jobObj = this.$route.params.job
            var _this = this
            if (commited == 'yes'){
                this.title = "已提交"
                axios.get('http://localhost:8080/jas/mport/score/jobSubmitDetail/'+this.jobObj.hId).then(function (resp) {
                    _this.tableData = resp.data
                    console.log(resp.data)
                })
            }else{
                this.title= "没有提交"
                axios.get('http://localhost:8080/jas/mport/score/jobNoSubmitDetail/'+this.jobObj.hId).then(function (resp) {
                    _this.tableData = resp.data
                    console.log(resp.data)
                })
            }


        }
    }
</script>