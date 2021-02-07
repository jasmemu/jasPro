<template>
    <div style="text-align: center">
        <h1>公告详情</h1>
        <table>
            <tr>
                <td class="td1">
                    发布人姓名：
                </td>
                <td class="td2">
                    <el-input v-model="formData.committee.name" :readonly=true placeholder="请输入内容"></el-input>
                </td>
            </tr>
            <tr>
                <td class="td1">
                    发布人学号：
                </td>
                <td class="td2">
                    <el-input v-model="formData.committee.comId" :readonly=true placeholder="请输入内容"></el-input>

                </td>
            </tr>
            <tr>
                <td class="td1">
                    发布到：
                </td>
                <td class="td2">
                    <el-input v-model="noticeFrom" :readonly=true placeholder="请输入内容"></el-input>

                </td>
            </tr>
            <tr>
                <td  class="td1">
                    发布日期：
                </td>
                <td class="td2">
                    <el-input v-model="formData.publishDate" :readonly=true placeholder="请输入内容"></el-input>
                </td>
            </tr>
            <tr>
                <td  class="td1">
                    标题：
                </td>
                <td class="td2">
                    <el-input v-model="formData.noticeTitle" :readonly=true placeholder="请输入内容"></el-input>
                </td>
            </tr>
            <tr>
                <td  class="td1">
                    公告内容：
                </td>
                <td class="td2">
                    <el-input
                            type="textarea"
                            :rows="5"
                            :readonly=true
                            placeholder="请输入内容"
                            v-model="formData.content">
                    </el-input>
                </td>
            </tr>
        </table>




    </div>
</template>
<script>
import axios from 'axios'
    export default {
        components: {},
        props: [],
        data() {
            return {
                noticeFrom: '',
                formData: {
                    noticeTitle: '',
                    content: '',
                    publishDate: null,
                    committee: {
                        comId: '',
                        name: ''
                    }
                },
            }
        },
        computed: {},
        watch: {},
        created() {
            const _this = this
            const id = this.$route.params.noticeId
            axios.get('http://localhost:8080/jas/mport/notice/getNoticeById/'+ id).then(function (resp) {
                console.log(resp.data)
                _this.formData = resp.data
                _this.noticeFrom = resp.data.committee.specialty.speName+ '、'+resp.data.committee.cGrade + '、'+resp.data.committee.cClass+'班'
            })
        },
        mounted() {},
        methods: {
        }
    }

</script>
<style lang="scss" scoped>
    .el-input,.el-textarea {
        width: 100%;
    }
    table{
        width: 900px;
    }
    .td1{
        width: 300px;
        text-align: right;
    }
    .td2{
        width: 600px;
    }
</style>
