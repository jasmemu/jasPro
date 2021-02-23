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
                    <el-button @click="downloadJob(scope.row)" type="text" size="small">下载</el-button>
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
                comId: sessionStorage.getItem("cmtComId"),
                tableData: null
            }
        },
        mounted() {
            this.tableData = this.$route.params.jobs
        },
        methods: {
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
            deleteById(row){
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

<style scoped>

</style>