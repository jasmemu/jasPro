<template>
    <div>
        <div style="text-align: center">
            <h3>系统管理员个人信息设置</h3>
        </div>
        <hr>
        <div style="width: 80%;margin-left: 200px;">
            <el-table
                    :data="tableData"
                    border
                    style="width: 100%;text-align: center">
                <el-table-column
                        fixed
                        label="序号"
                        type="index"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="excelName"
                        label="文件名"
                        width="180"
                        text-align="center">
                </el-table-column>
                <el-table-column
                        prop="explain"
                        label="说明"
                        width="">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="150">
                    <template slot-scope="scope">
                        <el-button @click="downloadExcel(scope.row)" type="text" size="small">下载</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div >
            <el-button style="margin:30px auto auto 800px" @click="goBack()">返回</el-button>
        </div>

    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                tableData:[]
            }
        },
        methods:{
            goBack(){
                this.$router.push('/SysMainPage/StuMainDiv')
            },
            downloadExcel(row) {
                console.log(row.excelUrl)
                const a = document.createElement('a'); // 创建a标签
                let url = row.excelUrl
                var x=url.indexOf('/');
                for(var i=0;i<2;i++){
                    x=url.indexOf('/',x+1);
                }
                var  endUrl =url.slice(x)
                a.setAttribute('download',row.excelName);// download属性
                a.setAttribute('href',endUrl);// href链接
                a.click();// 自执行点击事件
            }
        },
        created() {
            var _this = this
            axios.get('http://localhost:8080/jas/mport/sys/downloadExcel').then(function (resp) {
                var list =resp.data
                if(list!= null&&list.length>0){
                    var obj1 = {excelName:'学委.xls',explain:'学委信息模板,按照里面的格式填写学委信息',excelUrl:list[0].cmtExcelUrl}
                    _this.tableData .push(obj1)
                    var obj2 = {excelName:'课程.xls',explain:'课程信息模板，按照里面的格式填写课程信息',excelUrl: list[0].courseExcelUrl}
                    _this.tableData .push(obj2)
                    var obj3 = {excelName:'学生.xls',explain:'学生信息模板，按照里面的格式填写学生信息',excelUrl:list[0].stuExcelUrl}
                    _this.tableData .push(obj3)
                    var obj4 = {excelName:'教师.xls',explain:'教师信息模板，按照里面的格式填写教师信息',excelUrl: list[0].teaExcelUrl}
                    _this.tableData .push(obj4)
                }
                // list[0].cmtExcelUrl
                // list[0].courseExcelUrl
                // list[0].stuExcelUrl
                // list[0].teaExcelUrl

            })
        }
    }
</script>

<style scoped>

</style>