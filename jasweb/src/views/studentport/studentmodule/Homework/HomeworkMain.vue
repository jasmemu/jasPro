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
      <el-table
          :data="tableData"
          @selection-change="handleSelectionChange"
          style="width: 100%">
        <!--        <el-table-column-->
        <!--            fixed-->
        <!--            type="selection"-->
        <!--            width="50">-->
        <!--        </el-table-column>-->
        <el-table-column
            fixed
            label="课程号"
            prop ="courseId"
            type="courseId"
            width="100">
        </el-table-column>
        <el-table-column
            prop="name"
            label="课程名"
            width="200">
        </el-table-column>

        <el-table-column
            fixed="right"
            label="查看详情"
            width="200">
          <template slot-scope="scope">
            <el-button @click="viewById(scope.row)" type="text" size="small">查看</el-button>
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
  name: "HomeworkMain",
  data(){
    return{
      account: sessionStorage.getItem("stuAccount"),
      sNo: '',
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
    this.sNo =sessionStorage.getItem('stuAccount')
    var that = this
    axios.get('http://localhost:8080/jas/sport/student/getCourseBySno/'+this.sNo).then(function (resp) {
      that.total = resp.data.total;
    })
  },
  mounted(){
    var _this = this
    axios.get('http://localhost:8080/jas/sport/student/getCourseBySno/'+this.sNo).then(function (resp) {
      console.log(resp.data)
      sessionStorage.setItem("courseId",resp.data.courseId)  //没有取到值。。。
      // courseId = resp.data.courseId;
      _this.tableData = resp.data

      console.log(resp.data.courseId);
    })
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val
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
      this.$router.push({name:'ViewHomeworkDetail',params:{CourseId:row.id}})
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

</style>




















<!--<template>-->
<!--  <div>-->
<!--&lt;!&ndash;    <hr>&ndash;&gt;-->
<!--    <div>-->
<!--      <el-table-->
<!--          :data="tableData"-->
<!--          @selection-change="handleSelectionChange"-->
<!--          style="width: 100%">-->
<!--        <el-table-column-->
<!--            fixed-->
<!--            label="课程号"-->
<!--            prpo ="courseId"-->
<!--            type="courseId"-->
<!--            width="80">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="name"-->
<!--            label="课程名"-->
<!--            width="200">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="period"-->
<!--            label="课时"-->
<!--            show-overflow-tooltip-->
<!--            width="200">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="credit"-->
<!--            label="学分"-->
<!--            width="200">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="beginDate"-->
<!--            label="课程开始日期"-->
<!--            width="200">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            prop="endDate"-->
<!--            label="课程结束日期"-->
<!--            width="200">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            fixed="right"-->
<!--            label="查看详情"-->
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
<!--</template>-->

<!--<script>-->
<!--import axios from 'axios'-->
<!--export default {-->
<!--  name: "StudentCoursesMain",-->
<!--  data(){-->
<!--    return{-->
<!--      account: sessionStorage.getItem("stuAccount"),-->
<!--      comId: '',-->
<!--      pageSize: 5,-->
<!--      total: 10,-->
<!--      formForSearch: {-->
<!--        publishDate: '',-->
<!--        noticeTitle: ''-->
<!--      },-->
<!--      // props:{-->
<!--      //   courseId:String,-->
<!--      //   name:String,-->
<!--      //   period:Number,-->
<!--      //   callback: Function,-->
<!--      //-->
<!--      //-->
<!--      // }-->
<!--      // ,-->
<!--      tableData: [{-->
<!--        courseId:this.tableData.courseId,-->
<!--        name:this.tableData.name,-->
<!--        period:this.tableData.period,-->
<!--        beginDate:this.tableData.beginDate,-->
<!--        endDate:this.tableData.endDate,-->
<!--        credit:this.tableData.credit,-->
<!--      }]-->
<!--    }-->
<!--  },-->
<!--  created(){-->
<!--    this.sNo =sessionStorage.getItem('stuAccount')-->
<!--    var that = this-->
<!--    axios.get('http://localhost:8080/jas/sport/student/getCourseBySno/'+this.sNo).then(function (resp) {-->
<!--      // that.tableData = resp.data.courses.name-->
<!--      that.tableData.courseId = resp.data.courses.courseId;-->
<!--      that.tableData.name = resp.data.courses.name;-->
<!--      that.tableData.period = resp.data.courses.period;-->
<!--      that.tableData.beginDate = resp.data.courses.beginDate;-->
<!--      that.tableData.endDate = resp.data.courses.endDate;-->
<!--      that.tableData.credit = resp.data.courses.credit;-->
<!--    })-->
<!--  },-->
<!--  mounted(){-->
<!--    var _this = this-->
<!--    axios.get('http://localhost:8080/jas/sport/student/getCourseBySno/'+this.sNo).then(function (resp) {-->
<!--      console.log(resp.data)-->
<!--      _this.tableData = resp.data.courses-->
<!--    })-->
<!--  },-->
<!--  methods: {-->
<!--    handleSelectionChange(val) {-->
<!--      this.multipleSelection = val;-->
<!--      // console.log(val)-->
<!--    },-->
<!--    //table中的-->
<!--    mypage (currentpage) {-->
<!--      const _this = this-->
<!--      axios.get('http://localhost:8080/jas/mport/notice/getNoticeWithoutId/'+_this.currentPage +'/' +_this.pageSize).then(function (resp) {-->
<!--        _this.tableData = resp.data-->
<!--      })-->
<!--    },-->
<!--    viewById(row){-->
<!--      this.$router.push({name:'ViewNoticeDetail',params:{noticeId:row.id}})-->
<!--    },-->
<!--    search() {-->
<!--      if (this.formForSearch.publishDate!='' || this.formForSearch.noticeTitle!=''){-->
<!--        var _this = this-->
<!--        var form = new FormData()-->

<!--        form.append('account',this.account)-->
<!--        // form.append('publishDate',this.formForSearch.publishDate)-->
<!--        form.append('noticeTitle',this.formForSearch.noticeTitle)-->
<!--        axios.post('http://localhost:8080/jas/mport/notice/getNoticesForSearch',form).then(function (resp) {-->
<!--          const noticeList = resp.data-->
<!--          if (noticeList.length > 0){-->
<!--            _this.$router.push({name:'ViewNotice',params:{notices:noticeList}})-->
<!--          } else {-->
<!--            alert("没有符合条件的查询")-->
<!--          }-->

<!--        })-->
<!--      } else {-->
<!--        alert('请输入查询条件')-->
<!--      }-->

<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style lang="scss" scoped>-->

<!--</style>-->
















