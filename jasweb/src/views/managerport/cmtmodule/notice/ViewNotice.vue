<template>
    <div>
        <div>
            <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
        </div>
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
    </div>
</template>

<script>
import axios from 'axios'
    export default {
        name: "",
        data() {
            return {
                // 批量删除
                multipleSelection: [],
                tableData: null
            }
        },
        created() {
            this.tableData = this.$route.params.notices
        },
        methods:{
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
            viewById(row){
                this.$router.push({name:'ViewNoticeDetail',params:{noticeId:row.id}})
            },
            deleteById(row){
                var _this = this
                axios.get('http://localhost:8080/jas/mport/notice/deleteNoticeById/'+ row.id).then(function (resp) {
                    if (resp.data=='success'){
                        alert("删除成功")
                        _this.$router.push('/CmtMainPage/NoticeMainDiv')
                    } else{
                        alert("删除失败")
                    }

                })
            },
        }
    }
</script>

<style scoped>

</style>