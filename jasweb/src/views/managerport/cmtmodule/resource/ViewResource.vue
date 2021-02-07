<template>
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
                    prop="resourceName"
                    label="资料名称"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="committee.name"
                    label="发布人"
                    width="400">
            </el-table-column>
            <el-table-column
                    prop="uploadTime"
                    label="发布日期"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="resourceType"
                    label="类型"
                    width="200">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                <template slot-scope="scope">
                    <!--                        <el-button @click="viewById(scope.row)" type="text" size="small">查看</el-button>-->
                    <el-button @click="deleteById(scope.row)" type="text" size="small">删除</el-button>
                    <el-button @click="downloadByUrl(scope.row)" type="text" size="small">下载</el-button>
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
</template>

<script>
import axios from 'axios'
    export default {
        name: "",
        data() {
            return {
                account: sessionStorage.getItem("cmtComId"),
                tableData: null
            }
        },
        mounted() {
            this.tableData = this.$route.params.resources
        },
        methods: {
            downloadByUrl(row){
                console.log(row)
                const a = document.createElement('a'); // 创建a标签
                let url = row.resourceUrl
                var x=url.indexOf('/');
                for(var i=0;i<2;i++){
                    x=url.indexOf('/',x+1);
                }
                var  endUrl =url.slice(x)
                console.log(endUrl)
                a.setAttribute('download',row.resourceName);// download属性
                a.setAttribute('href',endUrl);// href链接
                a.click();// 自执行点击事件
            },
            deleteById(row){
                var _this = this;
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var  that = this
                    axios.get('http://localhost:8080/jas/mport/resource/deleteById/'+ row.id).then(function (resp) {
                        if (resp.data == 1){
                            alert('删除成功')
                            _this.$router.push('/CmtMainPage/ResourceMainDiv')
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

<style scoped>

</style>