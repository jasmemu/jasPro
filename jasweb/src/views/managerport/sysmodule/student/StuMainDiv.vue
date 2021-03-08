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
<!--                <div style="float: left;margin-left: 20px">-->
<!--                    身份证号:<input type="text"  v-model="formForSearch.identify">-->
<!--                </div>-->
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
            <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
            <el-dialog title="文件上传" :visible.sync="dialogFormVisible">
                <input id="fUpload" multiple type="file" />
<!--                <el-form :model="form">-->
<!--                    <el-upload-->
<!--                            class="upload-demo"-->
<!--                            action="https://jsonplaceholder.typicode.com/posts/"-->
<!--                            :beforeUpload="beforeAvatarUpload"-->
<!--                            :on-preview="handlePreview"-->
<!--                            multiple-->
<!--                            :limit="3"-->
<!--                            :on-exceed="handleExceed"-->
<!--                            :file-list="fileList">-->
<!--                        <el-button size="small" type="primary">点击上传</el-button>-->
<!--                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
<!--                    </el-upload>-->
<!--                </el-form>-->
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="commitFile()">确 定</el-button>
                </div>
            </el-dialog>
        </div>

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
                        width="135">
                    <template slot-scope="scope">
                        <el-button @click="viewBySno(scope.row)" type="text" size="small">查看</el-button>
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
                // 批量删除
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
                    sNo: '',
                    name: '',
                    identify: ''
                },
                windowHeight:{height:document.documentElement.clientHeight -80 -100 +'px'},
                tableData: null
            }
        },
        methods: {
            commitFile(){
                this.dialogFormVisible = false
                var fp = $("#fUpload");
                var items = fp[0].files;
                if ((items[0]) == undefined || (items[0]) == null || (items[0]) == ''){
                    alert("请选择文件")
                    return
                }
                var f1 = this.judgeType(items[0].name)
                var f2 = this.judgeSize(items[0].size)
                if (f1&&f2){
                    var formDate = new FormData()
                    formDate.append("file",items[0])
                    axios.post('http://localhost:8080/jas/mport/stu/dealExcel',formDate).then(function (resp) {
                        location.reload()
                    })
                }else {
                    alert("请上传xls类型文件")
                }
            },
            judgeType(fileName){
                var name = fileName
                var index = name.lastIndexOf(".")
                var endName = name.substr(index)
                console.log("文件类型"+ endName)
                if (endName == '.xls'){
                   return true
                } else {
                   return false
                }
            },
            judgeSize(fileSize){
                var size = parseInt(fileSize)/1024/1024
                console.log("大小"+size)
                if (size<50){
                    return true
                } else {
                    return false
                }
            },
            // ====
            beforeAvatarUpload(file) {
                var testmsg=file.name.substring(file.name.lastIndexOf('.')+1)
                const extension = testmsg === 'xls'
                const isLt2M = file.size / 1024 / 1024 < 10
                if(!extension ) {
                    this.$message({
                        message: '上传文件只能是 xls格式!',
                        type: 'warning'
                    });
                }
                if(!isLt2M) {
                    this.$message({
                        message: '上传文件大小不能超过 10MB!',
                        type: 'warning'
                    });
                }
                return extension  && isLt2M
            },
            //批量删除
            deleteBatch(){
                if (this.multipleSelection.length < 1){
                    alert("请至少选择一条")
                    return
                }
              axios.post('http://localhost:8080/jas/mport/stu/delete/byBatch',this.multipleSelection).then(function (resp) {
                  alert(resp.data)
                  location.reload()
              })

            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
               // console.log(val)
            },
            //上传excel中的
            handlePreview(file) {
                const formData = new FormData()
                formData.append('file', file.raw)
                axios.post('http://localhost:8080/jas/mport/stu/dealExcel',formData).then(function (resp) {
                    alert(resp.data)
                    location.reload()
                })
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            // beforeRemove(file, fileList) {
            //     return this.$confirm(`确定移除 ${ file.name }？`);
            // },
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
                })
            },
            alertBySno(row) {
                this.$router.push({ name: 'AddStuInfo', params: { sNoFromM: row.sNo } })
            },
            viewBySno(row){
                let _this = this
                axios.get('http://localhost:8080/jas/mport/stu/getStuBySno/'+ row.sNo).then(function (resp) {
                    let stuPojo = resp.data
                    console.log(stuPojo)
                    _this.$router.push({name: 'ViewStuDetail',params: {stu: stuPojo}})
                })
                // this.$router.push({ name: 'AddStuInfo', params: { sNoFromM: row.sNo } })
            },
            deleteBySno(row){
                var de;
                var _this = this;
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete('http://localhost:8080/jas/mport/stu//deleteBySno/'+row.sNo).then(function (resp) {
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
                if (this.formForSearch.sNo!='' || this.formForSearch.name!=''){
                    var searchPojo = {};
                    searchPojo.sNo = this.formForSearch.sNo
                    searchPojo.name = this.formForSearch.name
                    this.$router.push({name: 'ViewStuInfo',params: {searchParams: searchPojo}})
                }else {
                    alert("输入查询条件")
                }

            }
        },
        created(){
            var _this = this
            axios.get('http://localhost:8080/jas/mport/stu/getStuTotal').then(function (resp) {
                _this.total = resp.data
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
