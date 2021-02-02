<template>
    <div>
        <div  style="height: 50px">
            <form>
                <div style="float: left;margin-left: 20px" >
                    日期:
                    <el-date-picker
                        v-model="formForSearch.publishDate"
                        type="date"
                        size="small"
                        placeholder="选择日期">
                    </el-date-picker>
                </div>
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
            <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddNotice()">添加</el-button>
        </div>

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
                        prop="noticeTitle"
                        label="标题"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="content"
                        label="内容"
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
                        <el-button @click="viewByTno(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="alertByTno(scope.row)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteByTno(scope.row)" type="text" size="small">删除</el-button>
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
        }
    }
</script>

<style scoped>

</style>