<template>
    <div>
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
            var _this =this
            var searchStu = this.$route.params.searchParams  //搜索按钮传的参数
            axios.post('http://localhost:8080/jas/mport/stu/getStuForSearch',searchStu).then(function (resp) {
                if (resp.data.length>0){
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
