<template xmlns:text-overflow="http://www.w3.org/1999/xhtml">
    <div>
        <div >
            <form>
                <div style="float: left;margin-left: 20px">
                    专业:
                    <select v-model=formForSearch.speId  style="width: 100px;" >
                        <option value=""  style="display: none;" disabled selected>搜索内容</option>
                        <option v-for="(item,i) in speOPtions" :key="i"  v-text="item"></option>
                    </select>
                </div>
                <div style="float: left;margin-left: 20px">
                    年级:
                    <select v-model=formForSearch.cGrade style="width: 100px;" >
                        <option value=""  style="display: none;" disabled selected>搜索内容</option>
                        <option v-for="(item,i) in gradeOPtions" :key="i" v-text="item"></option>
                    </select>
                </div>
                <div style="float: left;margin-left: 20px">
                    班级:
                    <select v-model=formForSearch.cClass style="width: 100px;" >
                        <option value=""  style="display: none;" disabled selected>搜索内容</option>
                        <option v-for="(item,i) in classOPtions" :key="i" v-text="item"></option>
                    </select>
                </div>
                <div>
                    <input type="button"  value="查找" style="margin-left: 30px" @click="search()">
                </div>
            </form>
        </div>
        <hr>

        <div>
            <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddCmt()">添加</el-button>
            <!--            <router-link tag="button" to="/SysMainPage/AddStuInfo"  style="float: right;margin-left: 30px">添加</router-link>-->
            <el-button type="primary" plain size="small"  style="float:right " @click="dialogFormVisible = true">导入Excel</el-button>
            <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
            <el-dialog title="文件上传" :visible.sync="dialogFormVisible">
                <el-form :model="form">
                    <input id="fUpload" multiple type="file" />
<!--                    <el-upload-->
<!--                            class="upload-demo"-->
<!--                            action="https://jsonplaceholder.typicode.com/posts/"-->
<!--                            :on-preview="handlePreview"-->
<!--                            :on-remove="handleRemove"-->
<!--                            :before-remove="beforeRemove"-->
<!--                            multiple-->
<!--                            :limit="3"-->
<!--                            :on-exceed="handleExceed"-->
<!--                            :file-list="fileList">-->
<!--                        <el-button size="small" type="primary">点击上传</el-button>-->
<!--                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
<!--                    </el-upload>-->
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="commitFile()">确 定</el-button>
                </div>
            </el-dialog>
        </div>

        <div >
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
                        prop="specialty.speName"
                        label="专业"
                        width="180"
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
                        width="180">
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
                        prop="email"
                        label="邮箱"
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
                //search使用
                speOPtions:[],
                gradeOPtions: ['大一','大二','大三','大四'],
                classOPtions: ['1','2'],
                selected:true,//默认的字体样式
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
                    speId: '',
                    cGrade: '',
                    cClass: ''
                },
                windowHeight:{height:document.documentElement.clientHeight -80 -100 +'px'},
                specialtyName: '',
                tableData: null
            }
        },
        methods: {
            // 文件上传
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
                    axios.post('http://localhost:8080/jas/mport/cmt/dealExcel',formDate).then(function (resp) {
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
            //批量删除
            deleteBatch(){
                if (this.multipleSelection.length < 1){
                    alert("请至少选择一条")
                    return
                }
                axios.post('http://localhost:8080/jas/mport/cmt/delete/byBatch',this.multipleSelection).then(function (resp) {
                    alert(resp.data)
                    location.reload()
                })

            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                // console.log(val)
            },
            //上传excel中的
            beforeAvatarUpload(file) {
                var testmsg=file.name.substring(file.name.lastIndexOf('.')+1)
              //  const extension = testmsg === 'xls'
                const isLt2M = file.size / 1024 / 1024 < 10
                // if(!extension ) {
                //     this.$message({
                //         message: '上传文件只能是 xls格式!',
                //         type: 'warning'
                //     });
                // }
                if(!isLt2M) {
                    this.$message({
                        message: '上传文件大小不能超过 10MB!',
                        type: 'warning'
                    });
                }
                return  isLt2M
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                const formData = new FormData()
                formData.append('file', file.raw)
                axios.post('http://localhost:8080/jas/mport/cmt/dealExcel',formData).then(function (resp) {
                    alert(resp.data)
                    location.reload()
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
                axios.get('http://localhost:8080/jas/mport/cmt/getAllCmt/' + currentpage +'/' +_this.pageSize).then(function (resp) {
                    console.log("获取学生信息")
                    console.log(resp.data);
                    _this.tableData = resp.data
                })
            },
            alertByComId(row) {
                this.$router.push({ name: 'AddCmtInfo', params: { comIdFromM: row.comId } })
            },
            viewByComId(row){
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

            goAddCmt(){
                this.$router.push({name: 'AddCmtInfo'})
            },
            //sear中的
            search() {
                console.log(this.formForSearch)
                if (this.formForSearch.sNo!='' || this.formForSearch.name!='' || this.formForSearch.identify!=''){
                    var f = new FormData();
                    f.append("speName",this.formForSearch.speId)
                    f.append("cGrade",this.formForSearch.cGrade)
                    f.append("cClass",this.formForSearch.cClass)
                    var _this = this
                    var cmtPojoList;
                    axios.post('http://localhost:8080/jas/mport/cmt/getCmtForSearch',f).then(function (resp) {
                        cmtPojoList = resp.data
                        console.log(" cmtPojoList")
                        console.log(cmtPojoList)
                        if (cmtPojoList!=null){
                            _this.$router.push({name: 'ViewCmtInfo',params: {cmtList: cmtPojoList}})
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
            axios.get('http://localhost:8080/jas/mport/cmt/getCmtTotal').then(function (resp) {
                _this.total = resp.data
            });
            axios.get('http://localhost:8080/jas/mport/cmt/getAllCmt/1/'+ _this.pageSize).then(function (resp) {
                console.log("学位信息")
                console.log(resp.data)
                _this.tableData = resp.data

            });
            axios.get('http://localhost:8080/jas/mport/spe/getspecialties').then(function (resp) {  //获取所用专业
                if(resp.data!== null){
                    for (var i =0;i<resp.data.length;i++) {
                        _this.speOPtions.push(resp.data[i].speName)
                    }
                }
            })
        },
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
