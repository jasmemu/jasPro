<template>
  <el-table
      :data="tableData"
      style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="发布人">
            <span>{{ props.row.comId }}</span>
          </el-form-item>
          <el-form-item label="公告内容">
            <span>{{ props.row.content }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
        label="发布id"
        prop="id">
    </el-table-column>
    <el-table-column
        label="公告标题"
        prop="noticeTitle">
    </el-table-column>
    <el-table-column
        label="发布时间"
        prop="publishDate">
    </el-table-column>
  </el-table>


















<!--  <div>-->
<!--    <div  style="height: 50px">-->
<!--      <form>-->
<!--        <div style="float: left;margin-left: 20px">-->
<!--          标题:-->
<!--          <el-input style="width: auto"   v-model="formForSearch.noticeTitle"  size="small" placeholder="请输入内容"></el-input>-->
<!--        </div>-->
<!--        <div>-->
<!--          <el-button type="primary" size="small" style="margin-left: 30px" @click="search()">搜索</el-button>-->
<!--        </div>-->
<!--      </form>-->
<!--    </div>-->
<!--    <hr>-->
<!--    <div>-->
<!--      <el-table-->
<!--          :data="tableData"-->
<!--          @selection-change="handleSelectionChange"-->
<!--          style="width: 100%">-->
<!--&lt;!&ndash;        <el-table-column&ndash;&gt;-->
<!--&lt;!&ndash;            fixed&ndash;&gt;-->
<!--&lt;!&ndash;            type="selection"&ndash;&gt;-->
<!--&lt;!&ndash;            width="50">&ndash;&gt;-->
<!--&lt;!&ndash;        </el-table-column>&ndash;&gt;-->
<!--        <el-table-column-->
<!--            fixed-->
<!--            label="序号"-->
<!--            type="index"-->
<!--            width="50">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="noticeTitle"-->
<!--            label="标题"-->
<!--            width="200">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="content"-->
<!--            label="内容"-->
<!--            show-overflow-tooltip-->
<!--            width="400">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="publishDate"-->
<!--            label="日期"-->
<!--            width="200">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            fixed="right"-->
<!--            label="操作"-->
<!--            width="200">-->
<!--          <template slot-scope="scope">-->
<!--            <el-button @click="viewById(scope.row)" type="text" size="small">查看</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--      </el-table>-->

<!--      <div style="float: right;margin-top: 10px">-->
<!--        <el-pagination-->
<!--            background-->
<!--            layout="prev, pager, next"-->
<!--            :page-size="pageSize"-->
<!--            :total="total"-->
<!--            @current-change="mypage">-->
<!--        </el-pagination>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
</template>

<script>
import axios from 'axios'
export default {
  name: "AnnounceMainPage",
  data(){
    return{

      // gridData: [{
      //   date: '2016-05-02',
      //   name: '王小虎',
      //   address: '上海市普陀区金沙江路 1518 弄'
      // }, {
      //   date: '2016-05-04',
      //   name: '王小虎',
      //   address: '上海市普陀区金沙江路 1518 弄'
      // }, {
      //   date: '2016-05-01',
      //   name: '王小虎',
      //   address: '上海市普陀区金沙江路 1518 弄'
      // }, {
      //   date: '2016-05-03',
      //   name: '王小虎',
      //   address: '上海市普陀区金沙江路 1518 弄'
      // }],







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
    this.comId =sessionStorage.getItem('cmtComId')
    var that = this
    axios.get('http://localhost:8080/jas/mport/notice/getTotal/'+this.comId).then(function (resp) {
      that.total = resp.data
    })
  },
  mounted(){
    var _this = this
    axios.get('http://localhost:8080/jas/mport/notice/getNoticeWithoutId').then(function (resp) {
      console.log(resp.data)
      _this.tableData = resp.data
    })
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
      // console.log(val)
    },
    //table中的
    mypage (currentpage) {
      const _this = this
      axios.get('http://localhost:8080/jas/mport/notice/getNoticeWithoutId/'+_this.currentPage +'/' +_this.pageSize).then(function (resp) {
        _this.tableData = resp.data
      })
    },
    viewById(row){
      this.$router.push({name:'ViewNoticeDetail',params:{noticeId:row.id}})
    },
    search() {
      if (this.formForSearch.publishDate!='' || this.formForSearch.noticeTitle!=''){
        var _this = this
        var form = new FormData()

        form.append('account',this.account)
        // form.append('publishDate',this.formForSearch.publishDate)
        form.append('noticeTitle',this.formForSearch.noticeTitle)
        axios.post('http://localhost:8080/jas/mport/notice/getNoticesForSearch',form).then(function (resp) {
          const noticeList = resp.data
          if (noticeList.length > 0){
            _this.$router.push({name:'ViewNotice',params:{notices:noticeList}})
          } else {
            alert("没有符合条件的查询")
          }

        })
      } else {
        alert('请输入查询条件')
      }

    }
  }
}
</script>



<style lang="scss" scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>