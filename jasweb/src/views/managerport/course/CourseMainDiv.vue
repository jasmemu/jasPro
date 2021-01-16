<template>
    <div>
        <div >
            <form>
                <div style="float: left;margin-left: 20px" >
                    课程号:<input type="text" v-model="formForSearch.courseId">
                </div>
                <div style="float: left;margin-left: 20px">
                    课程名称:<input type="text"  v-model="formForSearch.name">
                </div>
                <div>
                    <input type="button"  value="查找" style="margin-left: 30px" @click="search()">
                </div>
            </form>
        </div>
        <hr>

        <div>
            <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddCourse()">添加</el-button>
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
                        prop="courseId"
                        label="课程号"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="课程名"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="period"
                        label="课时"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="credit"
                        label="学分"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="beginDate"
                        label="开设日期"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="endDate"
                        label="结课日期"
                        width="200">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button @click="alertByCourseId(scope.row)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteByCourseId(scope.row)" type="text" size="small">删除</el-button>
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
                    courseId: '',
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
                axios.post('http://localhost:8080/jas/mport/course/dealExcelToCourse',formData).then(function (resp) {
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
                axios.get('http://localhost:8080/jas/mport/course/getAllCourse/' + currentpage +'/' +_this.pageSize).then(function (resp) {
                    // console.log("获取学生信息")
                    // console.log(resp);
                    _this.tableData = resp.data
                    console.log("tabledate is")
                    console.log(_this.tableData)
                })
            },
            alertByCourseId(row) {
               alert(row.courseId)
                this.$router.push({ name: 'AddCourseInfo', params: { courseIdFromM: row.courseId } })
            },
            deleteByCourseId(row){
                var de;
                var _this = this;
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.get('http://localhost:8080/jas/mport/course/deleteByCourseId/'+row.courseId).then(function (resp) {
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

            goAddCourse(){
              this.$router.push({name: 'AddCourseInfo'})
            },
            //sear中的
            search() {
                console.log(this.formForSearch)
                if(this.formForSearch.courseId!='' || this.formForSearch.name!=''){
                    var _this = this
                    var coursePojo = null;
                    axios.post('http://localhost:8080/jas/mport/course/getCourseForSearch',this.formForSearch).then(function (resp) {
                        coursePojo = resp.data
                        if (coursePojo !==''){
                            _this.$router.push({name: 'ViewCourseInfo',params: {course: coursePojo}})
                        } else {
                            alert("无")
                        }
                    })
                }else {
                    alert("请输入查询条件")
                }
            }
        },
        created(){
            var _this = this
            axios.get('http://localhost:8080/jas/mport/course/getCourseTotal').then(function (resp) {
                // console.log("学生总数")
                // console.log(resp)
                _this.total = resp.data
                 // console.log("学生总数")
                 // console.log(resp)
            });
            axios.get('http://localhost:8080/jas/mport/course/getAllCourse/1/'+ _this.pageSize).then(function (resp) {
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
