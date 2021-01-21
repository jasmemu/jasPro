<template>
    <div>
        <div >
            <form>
                <div style="float: left;margin-left: 20px" >
                    姓名:<input type="text" v-model="formForSearch.name">
                </div>
                <div style="float: left;margin-left: 20px">
                    教师编号:<input type="text"  v-model="formForSearch.tNo">
                </div>
                <div>
                    <input type="button"  value="查找" style="margin-left: 30px" @click="search()">
                </div>
            </form>
        </div>
        <hr>

        <div>
            <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddTea()">添加</el-button>
<!--            <router-link tag="button" to="/SysMainPage/AddStuInfo"  style="float: right;margin-left: 30px">添加</router-link>-->
            <el-button type="primary" plain size="small"  style="float:right " @click="dialogFormVisible = true">导入Excel</el-button>
            <el-dialog title="文件上传" :visible.sync="dialogFormVisible">
                <el-form :model="form">
                    <el-upload
                            class="upload-demo"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :before-remove="beforeRemove"
                            multiple
                            :limit="3"
                            :on-exceed="handleExceed"
                            :file-list="fileList">
                        <el-button size="small" type="primary">点击上传</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                    </el-upload>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
                </div>
            </el-dialog>
        </div>

        <div>
            <el-table
                    :data="tableData"
                    border
                    style="width: 100%">
                <el-table-column
                        fixed
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
            //上传excel中的
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
                const formData = new FormData()
                formData.append('file', file.raw)
                axios.post('http://localhost:8080/jas/mport/tea/dealExcel',formData).then(function (resp) {
                    alert(resp.data)
                })
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            //table中的
            mypage (currentpage) {
                const _this = this
                axios.get('http://localhost:8080/jas/mport/tea/getAllTea/' + currentpage +'/' +_this.pageSize).then(function (resp) {

                    _this.tableData = resp.data

                })
            },
            alertByTno(row) {
               alert(row.tNo)
                this.$router.push({ name: 'AddTeaInfo', params: { tNoFromM: row.tNo } })
            },
            viewByTno(row){
                console.log(row)
                var _this = this
                axios.get('http://localhost:8080/jas/mport/tea/getTeaByTno/'+ row.tNo).then(function (resp) {
                    var teaPojo = resp.data
                    _this.$router.push({name: 'ViewTeaInfo',params: {tea: teaPojo}})
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
                      console.log("删除"+row.sNo)
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
                        console.log(teaPojo)
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
            var _this = this
            axios.get('http://localhost:8080/jas/mport/tea/getTeaTotal').then(function (resp) {
                _this.total = resp.data

            });
            axios.get('http://localhost:8080/jas/mport/tea/getAllTea/1/'+ _this.pageSize).then(function (resp) {
                    _this.tableData = resp.data
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
