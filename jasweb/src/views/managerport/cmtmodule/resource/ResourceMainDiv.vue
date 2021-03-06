<template>
    <div>
        <div  style="height: 50px">
            <form>
                <div style="float: left;margin-left: 20px">
                    资料名称:
                    <el-input style="width: auto"   v-model="formForSearch.resourceName"  size="small" placeholder="请输入内容"></el-input>
                </div>
                <div>
                    <el-button type="primary" size="small" style="margin-left: 30px" @click="search()">搜索</el-button>
                </div>
            </form>
        </div>
        <hr>

        <div>
            <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddResource()">添加</el-button>
            <el-button type="primary" plain size="small"  style="float:right " @click="deleteBatch()">删除</el-button>
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
                </el-table-column>
                <el-table-column
                        prop="resourceName"
                        label="资料名称"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="committee.name"
                        label="发布人"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="uploadTime"
                        label="发布日期"
                        width="200">
                </el-table-column>
                <el-table-column
                        prop="resourceType"
                        label="类型"
                        width="200">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button @click="downloadByUrl(scope.row)" type="text" size="small">下载</el-button>
                        <el-button @click="deleteById(scope.row)" type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

<!--            <a  :href="" download="" class="download_btn">下载模板</a>-->

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
                ur: 'http://localhost:8081/image/ff.html',
                down: null,
                account: sessionStorage.getItem("cmtComId"),
                pageSize: 5,
                total: 10,
                formForSearch: {
                    resourceName: ''
                },
                tableData: null
            }
        },
        created(){
            let _this = this
            axios.get('http://localhost:8080/jas/mport/resource/getResource/'+this.account+'/1/'+this.pageSize).then(function (resp) {
              _this.tableData =resp.data
            })
            axios.get('http://localhost:8080/jas/mport/resource/getCount/'+this.account).then(function (resp) {
                _this.total = resp.data
            })

        },
        methods: {
            //批量删除
            deleteBatch(){
                if (this.multipleSelection.length < 1){
                    alert("请至少选择一条")
                    return
                }
                axios.post('http://localhost:8080/jas/mport/resource/delete/byBatch',this.multipleSelection).then(function (resp) {
                    alert(resp.data)
                    location.reload()
                })

            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                // console.log(val)
            },
            goAddResource(){
                this.$router.push('/CmtMainPage/AddResourceInfo')
            },
            //table中的
            mypage (currentpage) {
                const _this = this
                axios.get('http://localhost:8080/jas/mport/resource/getResource/'+this.account +'/'+ +currentpage +'/' +_this.pageSize).then(function (resp) {
                    _this.tableData = resp.data
                })
            },
            // viewById(row){
            //     this.$router.push({name:'ViewNoticeDetail',params:{noticeId:row.id}})
            // },
            deleteById(row){
                var de;
                var _this = this;
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var  that = this
                    axios.get('http://localhost:8080/jas/mport/resource/deleteById/'+ row.id).then(function (resp) {
                        if (resp.data == 1){
                            alert('删除成功')
                            location.reload();
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            downloadByUrl(row){
                console.log(row)
                const a = document.createElement('a'); // 创建a标签
                let url = row.resourceUrl
                var x=url.indexOf('/');
                for(var i=0;i<2;i++){
                    x=url.indexOf('/',x+1);
                }
                var  endUrl =url.slice(x)
                console.log(endUrl)
                a.setAttribute('download',row.resourceName);// download属性
                a.setAttribute('href',endUrl);// href链接
                a.click();// 自执行点击事件
            },
            search() {
                if (this.formForSearch.resourceName!=''){
                    var _this = this
                    axios.get('http://localhost:8080/jas/mport/resource/getForSearch/'+this.account+'/'+this.formForSearch.resourceName).then(function (resp) {
                        const resources = resp.data
                        if (resources.length > 0){
                            _this.$router.push({name:'ViewResource',params:{resources:resources}})
                        } else {
                            alert("没有符合条件的查询")
                        }

                    })
                } else {
                    alert('请输入查询条件')
                }

            }
        }
    }
</script>

<style scoped>

</style>