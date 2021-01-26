<template>
        <div >
            <el-table
                    :data="tableData"
                    border
                    style="width: 100%;text-align: center">

                <el-table-column
                        fixed
                        prop="specialty.speName"
                        label="专业"
                        width="270"
                        text-align="center">
                </el-table-column>
                <el-table-column
                        prop="cGrade"
                        label="年级"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="cClass"
                        label="班级"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="comId"
                        label="学号"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="手机号"
                        width="170">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="150">
                    <template slot-scope="scope">
                        <el-button @click="viewByComId(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="alertByComId(scope.row)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteByComId(scope.row)" type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
    </div>
</template>
<script>
import axios from 'axios'
    export default {
        components: {},
        props: [],
        data() {
            return {
                kong: null,
                chooseCourse: [],
                tableData: null,
                sNo: ''
            }
        },
        computed: {},
        watch: {},
        created() {
            this.tableData = this.$route.params.cmtList

        },
        mounted() {

        },
        methods: {
            alertByComId(row) {
                this.$router.push({ name: 'AddCmtInfo', params: { comIdFromM: row.comId } })
            },
            viewByComId(row){
                alert(row.comId)
                let _this = this
                axios.get('http://localhost:8080/jas/mport/cmt/getCmtByComId/'+ row.comId).then(function (resp) {
                    let cmtPojo = resp.data
                    _this.$router.push({name: 'ViewCmtDetail',params: {cmt: cmtPojo}})
                })
                // this.$router.push({ name: 'AddStuInfo', params: { sNoFromM: row.sNo } })
            },
            deleteByComId(row){
                var de;
                var _this = this;
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete('http://localhost:8080/jas/mport/cmt/deleteByComId/'+row.comId).then(function (resp) {
                    });
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    location.reload();

                }).catch(() => {
                    alert("我去")
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
