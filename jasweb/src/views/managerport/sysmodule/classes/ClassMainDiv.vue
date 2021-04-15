<template>
    <div>
        <div >
            <el-form :model="formForSearch"  label-width="80px">
                <el-form-item label="专业" style="float: left">
                    <el-select size="mini" v-model="formForSearch.speId" placeholder="请选择" >
                        <el-option
                                v-for="item in speOPtions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年级" style="float: left">
                    <el-select size="mini" v-model="formForSearch.cGrade" placeholder="请选择">
                        <el-option
                                v-for="item in gradeOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="班级" style="float: left">
                    <el-select size="mini" v-model="formForSearch.cClass" placeholder="请选择">
                        <el-option
                                v-for="item in classOptions"
                                :key="item.id"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-button icon="el-icon-search" circle style="margin-left: 8px" @click="search()"></el-button>
            </el-form>
        </div>

        <hr style="clear:both"/>
        <div  style="width: 90%;margin-left: 3%">
            <div>
                <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddClass()">添加</el-button>
                <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
                <!--            <el-button type="primary" plain size="small"  style="float:right " @click="dialogFormVisible = true">导入Excel</el-button>-->
                <!--            <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>-->
                <!--            <el-dialog title="文件上传" :visible.sync="dialogFormVisible">-->
                <!--                <el-form :model="form">-->
                <!--                    <input id="fUpload" multiple type="file" />-->
                <!--                </el-form>-->
                <!--                <div slot="footer" class="dialog-footer">-->
                <!--                    <el-button @click="dialogFormVisible = false">取 消</el-button>-->
                <!--                    <el-button type="primary" @click="commitFile()">确 定</el-button>-->
                <!--                </div>-->
                <!--            </el-dialog>-->
            </div>
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
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="specialty.speName"
                        label="专业"
                        width="180"
                        text-align="center">
                </el-table-column>
                <el-table-column
                        prop="grade"
                        label="年级"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="numClass"
                        label="班级"
                        width="130">
                </el-table-column>
                <el-table-column
                        prop="teacher.name"
                        label="班主任"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="stuNum"
                        label="学生人数"
                        width="100">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="150">
                    <template slot-scope="scope">
                        <el-button @click="viewByClassesId(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="alertClassesById(scope.row)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteClassesById(scope.row)" type="text" size="small">删除</el-button>
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
                api: this.$store.state.api,
                // 批量删除
                multipleSelection: [],
                //table内容
                pageSize: 6,//每页几个记录
                total: 10, //共有多少记录 （通过total/pageSize字段计算页数）
                tableData: null,
                // 样式
                windowHeight:{height:document.documentElement.clientHeight -80 -100 +'px'},
                formLabelWidth: '120px',
                //search使用
                speOPtions:[],
                gradeOptions:[],
                classOptions:[],
                formForSearch: {
                    speId: '',
                    cGrade: '',
                    cClass: ''
                },

            }
        },
        methods: {
            //批量选择
            handleSelectionChange(val) {
                this.multipleSelection = val;
                // console.log(val)
            },
            //批量删除
            deleteBatch(){
                var idList=[]
                var that = this
                if (this.multipleSelection.length < 1){
                    alert("请至少选择一条")
                    return
                }else {
                    for (var i=0;i<this.multipleSelection.length;i++){
                        idList.push(this.multipleSelection[i].id)
                    }
                }
                axios.post('http://localhost:8080/jas/mport/classes/delete/batch',idList).then(function (resp) {
                    if (resp.data.result =='SUCCESS'){
                        location.reload()
                    }
                })
            },
            //table中的
            mypage (currentpage) {
                const _this = this
                axios.get('http://localhost:8080/jas/mport/classes/getAllClasses/' + currentpage +'/' +_this.pageSize).then(function (resp) {
                    // console.log("获取学生信息")
                    // console.log(resp.data);
                    _this.tableData = resp.data
                })
            },
            deleteClassesById(row){
                var that = this
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.get('http://localhost:8080/jas/mport/classes/remove/class/by/id/'+row.id).then(function (resp) {
                        if (resp.data.result=='SUCCESS'){
                            that.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            location.reload();
                        }
                    });
                    // axios.get('http://localhost:8080/jas/mport/classes/test/'+row.id).then(function (resp) {
                    //     console.log(resp.data)
                    // })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            goAddClass(){
                this.$router.push({name: 'AddClassInfo',params:{classesFromMaimDivForAdd: 'addClasses'}})
            },
            alertClassesById(row){
                this.$router.push({ name: 'AddClassInfo', params: {classesFromMaimDivForUpdate: row } })
            },
            viewByClassesId(row){
                this.$router.push({name: 'ViewStuInfoByClasses',params:{classIdForClassMainDiv:row}})
            },
            search(){
                if (this.formForSearch.speId!=''||this.formForSearch.cGrade!=''||this.formForSearch.cClass!=''){
                    this.$router.push({name:'ViewClassesInfo',params:{speId:this.formForSearch.speId,
                            cGrade:this.formForSearch.cGrade,cClass:this.formForSearch.cClass}})
                } else {
                    this.$message({
                        message: '请选择搜索条件！',
                        type: 'warning'
                    });
                }
                // alert(this.formForSearch.speId+"-"+this.formForSearch.cGrade+"-"+this.formForSearch.cClass)
            },
        },
        created(){
            var _this = this
            axios.get('http://localhost:8080/jas/mport/classes/getClassesTotal').then(function (resp) {
                _this.total = resp.data
            })
            // 获取所有专业
            axios.get('http://localhost:8080/jas/mport/spe/getspecialties').then(function (resp) {
                //console.log(resp.data)
                var spePoList = resp.data
                for (var i=0;i<spePoList.length;i++){
                    var spePo = {value: '', label: ''}
                    spePo.value = spePoList[i].speId+''
                    spePo.label = spePoList[i].speName
                    _this.speOPtions.push(spePo)
                }
            }),
            // 获取所有年级
            axios.get(this.api+ '/mport/classes/get/grades').then(function (resp) {
                var gradePoList = resp.data.data
                for (var i=0;i<gradePoList.length;i++){
                    var gradePo = {value: '', label: ''}
                    gradePo.value = gradePoList[i]+''
                    gradePo.label = gradePoList[i]+''
                    _this.gradeOptions.push(gradePo)
                }
            })
            // 获取所有班级
            axios.get(this.api+ '/mport/classes/get/no/repetition').then(function (resp) {
                var classPoList = resp.data.data
                for (var i=0;i<classPoList.length;i++){
                    var classPo = {value: '', label: ''}
                    classPo.value = classPoList[i]+''
                    classPo.label = classPoList[i]+''
                    _this.classOptions.push(classPo)
                }
            })
        },
        mounted(){
            var that = this
            axios.get('http://localhost:8080/jas/mport/classes/getAllClasses/1/'+ that.pageSize).then(function (resp) {
                that.tableData = resp.data
                console.log(that.tableData)
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
