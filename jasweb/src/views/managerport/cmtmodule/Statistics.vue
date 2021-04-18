<template>
    <div>
      <div style="width: 90%">
          <el-table
                  :data="tableData"
                  style="width: 100%">
              <el-table-column
                      fixed
                      label="序号"
                      type="index"
                      width="60">
              </el-table-column>
              <el-table-column
                      prop="name"
                      label="课程名"
                      width="200">
              </el-table-column>
              <el-table-column
                      prop="credit"
                      label="学分"
                      width="200">
              </el-table-column>
              <el-table-column
                      prop="beginDate"
                      label="开设日期"
                      width="200">
              </el-table-column>
              <el-table-column
                      prop="endDate"
                      label="结课日期"
                      width="210">
              </el-table-column>
              <el-table-column
                      fixed="right"
                      label="操作"
                      width="200">
                  <template slot-scope="scope">
                      <el-button @click="statisticsForNoSubmit(scope.row)" type="text" size="small">生成未交学生名单</el-button>
                      <el-button @click="statisticsForSubmit(scope.row)" type="text" size="small">生成成绩单</el-button>
                  </template>
              </el-table-column>
          </el-table>
      </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "",
        data(){
            return{
                api: this.$store.state.api,
                cmtAccount: sessionStorage.getItem('cmtComId'),
                tableData: null
            }
        },
        methods:{
            statisticsForNoSubmit(row){
                console.log(row)
                var that = this
                var formData = new FormData()
                formData.append("courseId",row.courseId)
                formData.append("cmtId",this.cmtAccount)
                formData.append("name",row.name)
                axios.post('http://localhost:8080/jas/mport/cmt/download/no/submitted',formData).then(function (resp) {
                    console.log(resp.data)
                    if (resp.data.result =='SUCCESS'){
                        that.downloadJob(resp.data.data)
                    }
                })
            },
            statisticsForSubmit(row){
                console.log(row)
                var that = this
                var formData = new FormData()
                formData.append("courseId",row.courseId)
                formData.append("cmtId",this.cmtAccount)
                formData.append("name",row.name)
                axios.post('http://localhost:8080/jas/mport/cmt/download/submitted',formData).then(function (resp) {
                    console.log(resp.data)
                    if (resp.data.result =='SUCCESS'){
                        that.downloadJob(resp.data.data)
                    }
                })
            },
            downloadJob(excelUrl){
                const a = document.createElement('a'); // 创建a标签
                var x=excelUrl.lastIndexOf("/")
                var  endUrl =excelUrl.slice(x+1)
              //  a.setAttribute('download',endUrl);// download属性
                a.setAttribute('href',excelUrl);// href链接
                a.click();// 自执行点击事件
            },
        },
        created() {
            var that = this
            console.log(this.cmtAccount)
            axios.get(this.api+ '/mport/cmt/getCmtByComId/'+ this.cmtAccount).then(function (resp) {
                console.log(resp.data)
                that.tableData = resp.data.courses

            })
        }
    }
</script>

<style scoped>

</style>