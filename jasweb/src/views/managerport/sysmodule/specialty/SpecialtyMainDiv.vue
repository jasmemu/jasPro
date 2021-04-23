<template>
    <div>
        <div  style="width: 70%;margin-left: 5%">
            <div>
                <el-button type="primary" plain style="float: right;margin-left: 30px" size="small" v-on:click="goAddSpecialty()">添加</el-button>
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
                        prop="speNum"
                        label="专业编号"
                        width="180"
                        text-align="center">
                </el-table-column>
                <el-table-column
                        prop="speName"
                        label="专业名称"
                        width="180"
                        text-align="center">
                </el-table-column>
                <el-table-column
                        prop="setDate"
                        label="开设日期"
                        width="180">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="150">
                    <template slot-scope="scope">
                        <el-button @click="alertSpecialtyById(scope.row)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteSpecialtyById(scope.row)" type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data(){
            return{
                api: this.$store.state.api,
                tableData: null,
                // 批量删除
                multipleSelection:[]
            }
        },
        methods:{
            deleteSpecialtyById(row){
                var specialty = new Array();
                specialty.push(row);
                axios.post(this.api+'/mport/spe/delete/by/specialty',specialty).then(function (resp) {
                    if (resp.data.result=='FAILED'){
                       alert(resp.data.message)
                    }
                    setTimeout(location.reload,5000)

                })
            },
            //批量选择
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            goAddSpecialty(){
                this.$router.push({name:'AddSpecialtyInfo',params:{'forWhat':null}})
            },
            alertSpecialtyById(row){
                this.$router.push({name:'AddSpecialtyInfo',params:{'forWhat':row}})
            },
            deleteBatch(){
                var specialty = new Array();
                var specialtyListDelete = this.multipleSelection
                for (var i=0;i<specialtyListDelete.length;i++){
                    specialty.push(specialtyListDelete[i])
                }
                axios.post(this.api+'/mport/spe/delete/by/specialty',specialty).then(function (resp) {
                    location.reload()
                })
            },
        },
        mounted() {
            var that = this
            axios.get(this.api+'/mport/spe/getspecialties').then(function (resp) {
                console.log(resp.data)
                that.tableData = resp.data
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
    hr {
        margin-top: 0rem;
        margin-bottom: 0.1rem;
        border: 0;
        border-top-color: currentcolor;
        border-top-style: none;
        border-top-width: 0px;
        border-top: 1px solid rgba(0,0,0,.1);
    }
    .el-form-item {
        margin-bottom: 1px;
    }

</style>
