<template>
    <div>
        <h4>班级信息</h4>
        <div>
            <el-table
                    :data="tableDataForClasses"
                    style="width: 100%">
                <el-table-column
                        fixed
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
                        prop="stuNum"
                        label="学生人数"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="charge"
                        label="班主任编号"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="teacher.name"
                        label="班主任姓名"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="teacher.phone"
                        label="联系电话"
                        width="150">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="180">
                    <template slot-scope="scope">
                        <el-button @click="viewByClassesId(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="alertClassesById(scope.row)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteClassesById(scope.row)" type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <h4>学生信息</h4>
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
                        prop="specialty.speName"
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
                tableData: null,
                tableDataForClasses: []
            }
        },
        methods: {
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
            //table中的
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
            }

        },
        created(){
            console.log(this.$route.params.classIdForClassMainDiv)
            this.tableDataForClasses.push(this.$route.params.classIdForClassMainDiv)
            var that = this
            var classesPo = this.$route.params.classIdForClassMainDiv
            axios.post(this.api+'/mport/classes/get/students/by/classes',classesPo).then(function (resp) {
                console.log(resp.data)
                that.tableData = resp.data.data
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
