<template>
    <div>
        <div  style="width: 90%;margin-left: 3%">
            <div>
                <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
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
                        width="180">
                    <template slot-scope="scope">
                        <el-button @click="viewByClassesId(scope.row)" type="text" size="small">查看</el-button>
                        <el-button @click="alertClassesById(scope.row)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteClassesById(scope.row)" type="text" size="small">删除</el-button>
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
                // 批量删除
                multipleSelection: [],
                //table内容
                pageSize: 5,//每页几个记录
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
                var that = this
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
                        that.$router.push({value:'ClassMainDiv',params:{}})
                    }
                })
            },
            //table中的
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
            alertClassesById(row){
                this.$router.push({ name: 'AddClassInfo', params: {classesFromMaimDivForUpdate: row } })
            },
            viewByClassesId(row){
                this.$router.push({name: 'ViewStuInfoByClasses',params:{classIdForClassMainDiv:row}})
            }
        },
        created(){
            var that = this
           var speId = this.$route.params.speId
            var cGrade = this.$route.params.cGrade
            var cClass = this.$route.params.cClass
            var formData = new  FormData()
            formData.append("speId",speId)
            formData.append("cGrade",cGrade)
            formData.append("cClass",cClass)
            axios.post(this.api+'/mport/classes/get/for/search',formData).then(function (resp) {
                console.log(resp.data.data)
                that.tableData = resp.data.data
            })
        },
        mounted(){
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
