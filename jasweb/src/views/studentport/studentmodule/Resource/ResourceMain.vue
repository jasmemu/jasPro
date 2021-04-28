<template>
  <div>
    <div  style="height: 50px">
      <form>
        <div style="float: left;margin-left: 20px">
          标题:
          <el-input style="width: auto"   v-model="formForSearch.noticeTitle"  size="small" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <el-button type="primary" size="small" style="margin-left: 30px" @click="search()">搜索</el-button>
        </div>
      </form>
    </div>
    <hr>
    <div>
      <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddJob()">添加</el-button>

    </div>

    <div>
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            fixed
            label="序号"
            type="index"
            width="50">
        </el-table-column>
        <el-table-column
            prop="resourceName"
            label="资源名称"
            width="200">
        </el-table-column>
        <el-table-column
            prop="resourceType"
            label="资源类型"
            show-overflow-tooltip
            width="400">
        </el-table-column>
        <el-table-column
            prop="uploadTime"
            label="上传日期"
            width="200">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="下载"
            width="200">
          <template slot-scope="scope">
            <el-button @click="viewById(scope.row)" type="text" size="small">下载</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="float: right;margin-top: 10px">
        <el-pagination
            background
            layout="prev, pager, next"
            :page-size="pageSize"
            :total="total"
            >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "AnnounceMainPage",
  data(){
    return{
      account: sessionStorage.getItem("cmtComId"),
      comId: '',
      pageSize: 5,
      total: 10,
      formForSearch: {
        publishDate: '',
        noticeTitle: ''
      },
      tableData: null
    }
  },
  created(){
    // this.comId =sessionStorage.getItem('cmtComId')
    var that = this
    axios.get('http://localhost:8080/jas/mport/resource/getResourceWithoutId').then(function (resp) {
      // that.total = resp.data
      that.tableData = resp.data
    })
  },
  // mounted(){
  //   var _this = this
  //   axios.get('http://localhost:8080/jas/mport/notice/getNoticeWithoutId').then(function (resp) {
  //     console.log(resp.data)
  //     _this.tableData = resp.data
  //   })
  // },
  // methods: {
  //   handleSelectionChange(val) {
  //     this.multipleSelection = val;
  //     // console.log(val)
  //   },
  //   //table中的
  //   mypage (currentpage) {
  //     const _this = this
  //     axios.get('http://localhost:8080/jas/mport/resource/getResourceWithoutId'+_this.currentPage +'/' +_this.pageSize).then(function (resp) {
  //       _this.tableData = resp.data
  //     })
  //   },
    viewById(row){
      this.$router.push({name:'ViewNoticeDetail',params:{noticeId:row.id}})
    },
    // search() {
    //   if (this.formForSearch.publishDate!='' || this.formForSearch.noticeTitle!=''){
    //     var _this = this
    //     var form = new FormData()
    //
    //     form.append('account',this.account)
    //     // form.append('publishDate',this.formForSearch.publishDate)
    //     form.append('noticeTitle',this.formForSearch.noticeTitle)
    //     axios.post('http://localhost:8080/jas/mport/notice/getNoticesForSearch',form).then(function (resp) {
    //       const noticeList = resp.data
    //       if (noticeList.length > 0){
    //         _this.$router.push({name:'ViewNotice',params:{notices:noticeList}})
    //       } else {
    //         alert("没有符合条件的查询")
    //       }
    //
    //     })
    //   } else {
    //     alert('请输入查询条件')
    //   }
    //
    // }
}
</script>

<style lang="scss" scoped>

</style>