<template>
    <div>
        <h3>你所管理的学科：</h3>
        <span v-for="item in courseList">
            <el-button style="margin-left: 20px" type="primary" @click="toCourseDiv(item.courseId)" >{{item.course.name}}</el-button>
        </span>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "",
        data() {
            return {
                cmtId: sessionStorage.getItem('cmtComId'),
                courseList: null

            }
        },
        created() {
            var that =this
            axios.get('http://localhost:8080/jas/mport/cmt/getCourseForCmtByCmtIdWith/'+this.cmtId).then(function (resp) {
                console.log(resp.data)
                that.courseList = resp.data

            })
        },
        methods:{
            toCourseDiv(couseId){
               this.$router.push({name:'CheckJobMainDiv',params:{courseIdFormJobTypeDiv:couseId}})
            }
        }
    }
</script>

<style lang="scss" scoped>
</style>