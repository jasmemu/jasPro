<template>
    <div>
        <div>
            <el-table
                    :data="tableData"
                    border
                    style="width: 100%">
                <el-table-column
                        fixed
                        label="序号"
                        type="index"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="student.name"
                        label="学生姓名"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="homework.hName"
                        label="作业名称"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="homework.course.name"
                        label="学科"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="score"
                        label="分数"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="correctDate"
                        label="提交时间"
                        width="200">
                </el-table-column>

                <el-table-column
                        fixed="right"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button @click="downloadJob(scope.row)" type="text" size="small">下载</el-button>
                        <el-button @click="setScore(scope.row)" type="text" size="small">打分</el-button>
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
                pageSize: 5,
                total: 10,
                commit: this.$route.params.commit,
                formForSearch: {
                    hName: this.$route.params.hName,
                    comId: sessionStorage.getItem('cmtComId'),
                    courseId: this.$route.params.courseId
                },
                tableData: null
            }
        },
        created(){
            var that = this
            var f = new FormData()
            f.append("cmtId",this.formForSearch.comId)
            f.append("hName",this.formForSearch.hName);
            f.append("commit",this.commit)
            f.append("courseId",this.formForSearch.courseId)
            axios.post('http://localhost:8080/jas/mport/score/searchCount',f).then(function (resp) {
                alert(resp.data)
                that.total = resp.data

            })
        },
        mounted(){
            var _this = this
            var f = new FormData()
            f.append("cmtId",this.formForSearch.comId)
            f.append("hName",this.formForSearch.hName);
            f.append("commit",this.commit)
            f.append("courseId",this.formForSearch.courseId)
            f.append("pageNo",1)
            f.append("pageSize",this.pageSize)
            axios.post('http://localhost:8080/jas/mport/score/search',f).then(function (resp) {
                console.log("dfadf")
                console.log(resp.data)
                _this.tableData = resp.data
            })
        },
        methods: {
            goAddJob(){
                this.$router.push('/CmtMainPage/AddJobInfo')
            },
            //table中的
            mypage (currentpage) {
                var _this = this
                var f = new FormData()
                f.append("cmtId",this.formForSearch.comId)
                f.append("hName",this.formForSearch.hName);
                f.append("commit",this.commit)
                f.append("courseId",this.formForSearch.courseId)
                f.append("pageNo",currentpage)
                f.append("pageSize",this.pageSize)
                axios.post('http://localhost:8080/jas/mport/score/search',f).then(function (resp) {
                    console.log("dfadf")
                    console.log(resp.data)
                    _this.tableData = resp.data
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
            setScore(row){
                console.log("row is")
                console.log(row)
                this.$router.push({name:'ViewCheckJobDetail',params:{scoreObj:row}})
            },

            deleteById(row){
                var de;
                var _this = this;
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var  that = this
                    axios.get('http://localhost:8080/jas/mport/homework/deleteByhId/'+ row.hId).then(function (resp) {
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
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>