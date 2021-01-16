<template>
    <div>
        <div >
            <form>
                <div style="float: left;margin-left: 20px" >
                    姓名:<input type="text" v-model="formForSearch.name">
                </div>
                <div style="float: left;margin-left: 20px">
                    学号:<input type="text"  v-model="formForSearch.sNo">
                </div>
                <div style="float: left;margin-left: 20px">
                    身份证号:<input type="text"  v-model="formForSearch.identify">
                </div>
                <div>
                    <input type="button"  value="查找" style="margin-left: 30px" @click="search()">
                </div>
            </form>
        </div>
        <hr>

        <div>
            <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddStu()">添加</el-button>
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
                        prop="sNo"
                        label="学号"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="手机号"
                        width="120">
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
                        prop="specialty"
                        label="专业"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="sGrade"
                        label="年级"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="sClass"
                        label="班级"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="enrollment"
                        label="入学时间"
                        width="120">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button @click="alertBySno(scope.row)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteBySno(scope.row)" type="text" size="small">删除</el-button>
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
                    sNo: '',
                    name: '',
                    identify: ''
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
                axios.post('http://localhost:8080/jas/mport/stu/dealExcel',formData).then(function (resp) {
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
                axios.get('http://localhost:8080/jas/mport/stu/getAllStu/' + currentpage +'/' +_this.pageSize).then(function (resp) {
                    // console.log("获取学生信息")
                    // console.log(resp);
                    _this.tableData = resp.data
                    for(var i = 0 ;i<resp.data.length;i++){
                        if(resp.data[i].speId === 1){
                            _this.tableData[i].specialty = "计算机科学与技术系"
                        }else if(resp.data[i].speId ===2){
                            _this.tableData[i].specialty = "网络工程系"
                        }else if(resp.data[i].speId ===3){
                            _this.tableData[i].specialty = "软件工程系"
                        }else if(resp.data[i].speId === 4){
                            _this.tableData[i].specialty = "计算机基础教学部"
                        }
                    }
                    console.log("tabledate is")
                    console.log(_this.tableData)
                })
            },
            alertBySno(row) {
               alert(row.sNo)
                this.$router.push({ name: 'AddStuInfo', params: { sNoFromM: row.sNo } })
            },
            deleteBySno(row){
                var de;
                var _this = this;
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.get('http://localhost:8080/jas/mport/stu//deleteBySno/'+row.sNo).then(function (resp) {
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

            goAddStu(){
              this.$router.push({name: 'AddStuInfo'})
            },
            //sear中的
            search() {
                console.log(this.formForSearch)
                if (this.formForSearch.sNo!='' || this.formForSearch.name!='' || this.formForSearch.identify!=''){
                    var _this = this
                    var stuPojo = null;
                    axios.post('http://localhost:8080/jas/mport/stu/getStuForSearch',this.formForSearch).then(function (resp) {
                        stuPojo = resp.data
                        if (stuPojo !==''){
                            _this.$router.push({name: 'ViewStuInfo',params: {stu: stuPojo}})
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
            axios.get('http://localhost:8080/jas/mport/stu/getStuTotal').then(function (resp) {
                // console.log("学生总数")
                // console.log(resp)
                _this.total = resp.data
                 console.log("学生总数")
                 console.log(resp)
            });
            axios.get('http://localhost:8080/jas/mport/stu/getAllStu/1/'+ _this.pageSize).then(function (resp) {
                    _this.tableData = resp.data
                for(var i = 0 ;i<resp.data.length;i++){
                    if(resp.data[i].speId === 1){
                        _this.tableData[i].specialty = "计算机科学与技术系"
                    }else if(resp.data[i].speId ===2){
                        _this.tableData[i].specialty = "网络工程系"
                    }else if(resp.data[i].speId === 3){
                        _this.tableData[i].specialty = "软件工程系"
                    }else if(resp.data[i].speId === 4){
                        _this.tableData[i].specialty = "计算机基础教学部"
                    }
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
