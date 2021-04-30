<template>
    <div style="text-align: center;">
        <h3>留言详情</h3>
        <table style="width: 80%;margin-left: 1%">
            <tr>
                <td class="td1">
                    留言人姓名：
                </td>
                <td class="td2">
                    <el-input v-model="formData.student.name" :readonly=true placeholder="请输入内容"></el-input>
                </td>
            </tr>
            <tr>
                <td class="td1">
                    留言日期：
                </td>
                <td class="td2">
                    <el-input v-model="formData.lmDate" :readonly=true placeholder="请输入内容"></el-input>
                </td>
            </tr>
            <tr>
                <td  class="td1">
                    留言：
                </td>
                <td class="td2">
                    <el-input
                            type="textarea"
                            :rows="5"
                            :readonly=true
                            placeholder="请输入内容"
                            v-model="formData.leaveMessage">
                    </el-input>
                </td>
            </tr>
            <tr>
                <td  class="td1">
                    回复：
                </td>
                <td class="td2">
                    <el-input
                            type="textarea"
                            :rows="5"
                            placeholder="请输入内容"
                            v-model="formData.replyMessage">
                    </el-input>
                </td>
            </tr>
        </table>

        <el-button type="primary" @click="reply()">提交</el-button>
        <el-button type="primary" @click="goBack()">返回</el-button>

    </div>
</template>

<script>
import axios from 'axios'
    export default {
        name: "",
        data() {
            return {
                formData: {
                    id: 0,
                    lmDate: null,
                    rmDate: null,
                    leaveMessage: '',
                    replyMessage: '',
                    student: {
                        name: ''
                    }
                },
            }
        },
        created() {
            const _this = this
            const id = this.$route.params.messageId
            axios.get('http://localhost:8080/jas/mport/message/getById/'+ id).then(function (resp) {
                console.log(resp.data)
                _this.formData = resp.data
            })
        },
        methods:{
            reply(){
                axios.post('http://localhost:8080/jas/mport/message/reply',this.formData).then(function (resp) {
                    if(resp.data == 1){
                        alert('回复成功')
                    }else{
                        alert('请重试！')
                    }
                })
            },
            goBack(){
                this.$router.push('/CmtMainPage/MessageMainDiv')
            }
        }
    }
</script>

<style lang="scss" scoped>
.td1{
    width: 15%;
    text-align: right;
}
    .td2{
        width: 50%;
    }
</style>