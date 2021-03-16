<template>
   <div style="width: 70%;">
       <h2>【{{this.jobObj.hName}}】作业的提交情况：</h2>
       <table style="width:70%;margin: 10% auto auto 20%">
           <tr>
               <td>已交人数</td>
               <td>{{studentCount.committedCount}}</td>
               <td><el-button type="primary" size="medium" @click="getStudentForCommittedJob">名单</el-button></td>
           </tr>
           <tr>
               <td>未交人数</td>
               <td>{{studentCount.noCommittedCount}}</td>
               <td><el-button type="primary" size="medium" @click="getStudentForNoCommittedJob">名单</el-button></td>
           </tr>
       </table>
   </div>
</template>

<script>
    import axios from "axios"
    export default {
        data() {
            return {
                jobObj: null,
                studentCount:{
                    committedCount: 0,
                    noCommittedCount: 0
                }
            }
        },
        created(){
            this.jobObj = this.$route.params.jobFromJobMainDiv
            // console.log(this.jobObj)
        },
        mounted(){
            var _this =this
            // alert(this.jobObj.hId)
            axios.get('http://localhost:8080/jas/mport/score/jobSubmitStatus/'+this.jobObj.hId).then(function (resp) {
                _this.studentCount = resp.data
                // console.log(resp.data)
            })
        },
        methods: {
            getStudentForCommittedJob(){
                this.$router.push({name:'StudentList',params:{committed:'yes',job:this.jobObj}})
            },
            getStudentForNoCommittedJob(){
                this.$router.push({name:'StudentList',params:{committed:'no',job:this.jobObj}})
            }
        }
    }
</script>
<style lang="scss" scoped="true">
    td{
        border: 1px solid black;
        width: 50px;
        text-align: center;
    }
    table {
        border-collapse: collapse;
    }
</style>