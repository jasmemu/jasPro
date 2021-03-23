<template>
    <div>
        <div  style="height: 50px">
            <form>
                <div style="float: left;margin-left: 20px" >
                    作业名称:
                    <el-input style="width: auto"      v-model="formForSearch.hName"  size="small" placeholder="请输入内容"></el-input>
                </div>
                <div>
                    <el-button type="primary" size="small" style="margin-left: 30px" @click="search()">搜索</el-button>
                </div>
            </form>
        </div>
        <hr>

        <div>
            <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddJob()">添加</el-button>
            <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
        </div>

        <div>
            <el-table
                    :data="tableData"
                    @selection-change="handleSelectionChange"
                    style="width: 100%">
                <el-table-column
                        fixed
                        type="selection"
                        width="50">
                </el-table-column>
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
                        prop="course.name"
                        label="学科"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="publishDate"
                        label="发布日期"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="endDate"
                        label="截至日期"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="mark"
                        label="说明"
                        show-overflow-tooltip
                        width="400">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button v-if="scope.row.hUrl.endsWith('jpg')||scope.row.hUrl.endsWith('pdf')||scope.row.hUrl.endsWith('jpeg')||scope.row.hUrl.endsWith('png')" @click="viewPhoto(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="downloadJob(scope.row)" type="text" size="small">下载</el-button>
                        <el-button @click="deleteById(scope.row)" type="text" size="small">删除</el-button>
                        <el-button @click="jobSubmit(scope.row)" type="text" size="small">提交状况</el-button>
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
                formForSearch: {
                    hName: '',
                    comId: sessionStorage.getItem('cmtComId')
                },
                tableData: null
            }
        },
        created(){
            var that = this
            axios.get('http://localhost:8080/jas/mport/homework/getCount/'+this.formForSearch.comId).then(function (resp) {
                that.total = resp.data
            })
        },
        mounted(){
            var _this = this
            axios.get('http://localhost:8080/jas/mport/homework/getJobs/'+ this.formForSearch.comId + '/1/5').then(function (resp) {
                console.log(resp.data)
                _this.tableData = resp.data
            })
        },
        methods: {
            jobSubmit(row){
              this.$router.push({name:'JobSubmitDetail',params:{jobFromJobMainDiv:row}})
            },
            //批量删除
            deleteBatch(){
                if (this.multipleSelection.length < 1){
                    alert("请至少选择一条")
                    return
                }
                axios.post('http://localhost:8080/jas/mport/homework/delete/byBatch',this.multipleSelection).then(function (resp) {
                    alert(resp.data)
                    location.reload()
                })

            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                // console.log(val)
            },
            goAddJob(){
                this.$router.push('/CmtMainPage/AddJobInfo')
            },
            //table中的
            mypage (currentpage) {
                const _this = this
                axios.get('http://localhost:8080/jas/mport/homework/getJobs/'+this.formForSearch.comId+'/'+ +currentpage +'/' +_this.pageSize).then(function (resp) {
                    _this.tableData = resp.data
                    console.log()
                })
            },
            viewPhoto(row){
                const a = document.createElement('a'); // 创建a标签
                let url = row.hUrl
                a.setAttribute('target','_blank')
                a.setAttribute('href',url);// href链接
                a.click();// 自执行点击事件
            },
            downloadJob(row){
                const a = document.createElement('a'); // 创建a标签
                let url = row.hUrl
                var x=url.indexOf('/');
                for(var i=0;i<2;i++){
                    x=url.indexOf('/',x+1);
                }
                var  endUrl =url.slice(x)
                a.setAttribute('download',row.hName);// download属性
                a.setAttribute('href',endUrl);// href链接
                a.click();// 自执行点击事件
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
            },
            search() {
                if (this.formForSearch.hName != ''){
                    var _this = this
                    axios.get('http://localhost:8080/jas/mport/homework/getJobForSearch/'+this.formForSearch.comId+'/'+this.formForSearch.hName).then(function (resp) {
                        const jobList = resp.data
                        if (jobList.length > 0){
                            _this.$router.push({name:'ViewJob',params:{jobs:jobList}})
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