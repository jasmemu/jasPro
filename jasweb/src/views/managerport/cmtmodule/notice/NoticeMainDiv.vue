<template>
    <div>
        <div  style="height: 50px">
            <form>
<!--                <div style="float: left;margin-left: 20px" >-->
<!--                    发布日期:-->
<!--                    <el-date-picker-->
<!--                        v-model="formForSearch.publishDate"-->
<!--                        type="date"-->
<!--                        size="small"-->
<!--                        placeholder="选择日期">-->
<!--                    </el-date-picker>-->
<!--                </div>-->
                <div style="float: left;margin-left: 20px">
                    标题:
                    <el-input style="width: auto"   v-model="formForSearch.noticeTitle"  size="small" placeholder="请输入内容"></el-input>
                </div>
                <div>
                    <el-button icon="el-icon-search" circle style="margin-left: 8px" @click="search()"></el-button>
<!--                    <el-button type="primary" size="small" style="margin-left: 30px" @click="search()">搜索</el-button>-->
                </div>
            </form>
        </div>
        <hr>

        <div>
            <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddNotice()">添加</el-button>
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
                        prop="noticeTitle"
                        label="标题"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="content"
                        label="内容"
                        show-overflow-tooltip
                        width="400">
                </el-table-column>
                <el-table-column
                        prop="publishDate"
                        label="日期"
                        width="200">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button @click="viewById(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="deleteById(scope.row)" type="text" size="small">删除</el-button>
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
            axios.get('http://localhost:8080/jas/mport/notice/getAllNotice/'+ this.comId + '/1/5').then(function (resp) {
                console.log(resp.data)
                _this.tableData = resp.data
            })
        },
        methods: {
            //批量删除
            deleteBatch(){
                if (this.multipleSelection.length < 1){
                    alert("请至少选择一条")
                    return
                }
                axios.post('http://localhost:8080/jas/mport/notice/delete/byBatch',this.multipleSelection).then(function (resp) {
                    alert(resp.data)
                    location.reload()
                })

            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                // console.log(val)
            },
            // ==
            goAddNotice(){
                this.$router.push('/CmtMainPage/AddNoticeInfo')
            },
            //table中的
            mypage (currentpage) {
                const _this = this
                axios.get('http://localhost:8080/jas/mport/notice/getAllNotice/'+this.comId +'/'+ +currentpage +'/' +_this.pageSize).then(function (resp) {
                    _this.tableData = resp.data
                })
            },
            viewById(row){
                this.$router.push({name:'ViewNoticeDetail',params:{noticeId:row.id}})
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
                    axios.get('http://localhost:8080/jas/mport/notice/deleteNoticeById/'+ row.id).then(function (resp) {
                        if (resp.data =='success'){
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