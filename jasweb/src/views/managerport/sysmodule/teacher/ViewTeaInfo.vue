<template>
    <div>
        <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
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
                        prop="tNo"
                        label="教师编号"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="手机号"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="email"
                        label="邮箱"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="identify"
                        label="身份证号"
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
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "",
        data() {
            return {
                multipleSelection: [],
                //文件上传===================
                fileList: [],
                //弹出框====================
                dialogTableVisible: false,
                dialogFormVisible: false,
                form: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                formLabelWidth: '120px',
                //table内容
                pageSize: 5,//每页几个记录
                total: 10, //共有多少记录 （通过total/pageSize字段计算页数）
                formForSearch: {
                    tNo: '',
                    name: ''
                },
                windowHeight:{height:document.documentElement.clientHeight -80 -100 +'px'},
                tableData: null
            }
        },
        methods: {
            //批量删除
            deleteBatch(){
                if (this.multipleSelection.length < 1){
                    alert("请至少选择一条")
                    return
                }
                axios.post('http://localhost:8080/jas/mport/tea/delete/byBatch',this.multipleSelection).then(function (resp) {
                    alert(resp.data)
                   location.reload()
                })
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                // console.log(val)
            },
            //==
            alertByTno(row) {
                this.$router.push({ name: 'AddTeaInfo', params: { tNoFromM: row.tNo } })
            },
            viewByTno(row){
                var _this = this
                axios.get('http://localhost:8080/jas/mport/tea/getTeaByTno/'+ row.tNo).then(function (resp) {
                    var teaPojo = resp.data
                    _this.$router.push({name: 'ViewTeaDetail',params: {tea: teaPojo}})
                })
                // this.$router.push({ name: 'AddStuInfo', params: { sNoFromM: row.sNo } })
            },
            deleteByTno(row){
                // var de;
                // var _this = this;
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.get('http://localhost:8080/jas/mport/tea//deleteByTno/'+row.tNo).then(function (resp) {
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

            goAddTea(){
                this.$router.push({name: 'AddTeaInfo'})
            },
            //sear中的
            search() {
                if (this.formForSearch.tNo!='' || this.formForSearch.name!=''){
                    var _this = this
                    var teaPojo = null;
                    axios.post('http://localhost:8080/jas/mport/tea/getTeaForSearch',this.formForSearch).then(function (resp) {
                        teaPojo = resp.data
                        if (teaPojo !==''){
                            _this.$router.push({name: 'ViewTeaInfo',params: {tea: teaPojo}})
                        } else {
                            alert("无")
                        }
                    })
                }else {
                    alert("输入查询条件")
                }

            }
        },
        created(){
            const _this =this
            var searchTea = this.$route.params.searchTeaP  //搜索按钮传的参数
            // console.log(searchTea)
            axios.post('http://localhost:8080/jas/mport/tea/getTeaForSearch',searchTea).then(function (resp) {
                if (resp.data.length>0){
                    _this.tableData = resp.data
                } else {
                    alert("无")
                }
            })
        }
    }
</script>

<style lang="scss" scoped>
    .el-main[data-v-9d9380ac] {
        /* background-color: #E9EEF3; */
        color: #333;
        /*text-align: left;*/
        /* line-height: 160px; */
    }

</style>
