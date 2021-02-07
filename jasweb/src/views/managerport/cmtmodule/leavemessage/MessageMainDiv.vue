<template>
    <div>
        <div  style="height: 50px">
            <form>
                <div style="float: left;margin-left: 20px" >
                    日期:
                    <el-date-picker
                            v-model="formForSearch.lmDate"
                            type="date"
                            size="small"
                            placeholder="选择日期">
                    </el-date-picker>
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
                        label="留言学生"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="leaveMessage"
                        label="留言"
                        show-overflow-tooltip
                        width="400">
                </el-table-column>
                <el-table-column
                        prop="lmDate"
                        label="留言日期"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="replyMessage"
                        label="回复"
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
        data() {
            return {
                pageSize: 5,
                account: sessionStorage.getItem("cmtComId"),
                total: 10,
                formForSearch: {
                    publishDate: ''
                },
                tableData: null
            }
        },
        methods:{
            //table中的
            mypage (currentpage) {
                const _this = this
                axios.get('http://localhost:8080/jas/mport/message/getMessages/'+this.account +'/'+ +currentpage +'/' +_this.pageSize).then(function (resp) {
                    _this.tableData = resp.data
                })
            },
            viewById(row){
                this.$router.push({name:'ViewMessageDetail',params:{messageId:row.id}})
            },
            // viewById(row){
            //     this.$router.push({name:'ViewNoticeDetail',params:{noticeId:row.id}})
            // },
            deleteById(row){
                var _this = this;
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
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
            search() {
                if (this.formForSearch.lmDate!=''){
                    var _this = this
                    axios.get('http://localhost:8080/jas/mport/message/getForSearch/'+this.account+'/'+this.formForSearch.lmDate).then(function (resp) {
                        const messages = resp.data
                        if (messages.length > 0){
                            _this.$router.push({name:'ViewMessage',params:{messages:messages}})
                        } else {
                            alert("没有符合条件的查询")
                        }

                    })
                } else {
                    alert('请输入查询条件')
                }

            }
        },
        created(){
            let _this = this
            axios.get('http://localhost:8080/jas/mport/message/getMessages/'+this.account+'/1/'+this.pageSize).then(function (resp) {
                console.log(resp.data)
                _this.tableData =resp.data
            })
            axios.get('http://localhost:8080/jas/mport/message/getCount/'+this.account).then(function (resp) {
                _this.total = resp.data
            })

        },
    }
</script>

<style scoped>

</style>