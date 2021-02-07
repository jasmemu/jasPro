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
    </div>
</template>

<script>
    export default {
        name: "",
        data(){
            return {
                account: sessionStorage.getItem("cmtComId"),
                formForSearch: {
                    lmDate: ''
                },
                tableData: null

            }
        },
        created() {
            this.tableData = this.$route.params.messages
        },
        methods: {
            viewById(row){
                this.$router.push({name:'ViewMessageDetail',params:{messageId:row.id}})
            },
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

        }
    }
</script>

<style scoped>

</style>