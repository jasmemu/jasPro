<template>
    <div>
        <div style=" margin: 30px auto auto 150px">
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-form-item label="标题" prop="noticeTitle">
                    <el-input v-model="formData.noticeTitle" placeholder="请输入标题" clearable :style="{width: '65%'}">
                    </el-input>
                </el-form-item>
                <el-form-item label="内容" prop="content">
                    <el-input v-model="formData.content" type="textarea" placeholder="请输入内容"
                              :autosize="{minRows: 8, maxRows: 8}" :style="{width: '65%'}"></el-input>
                </el-form-item>
                <el-form-item label="学委学号" prop="comId" v-show="false">
                    <el-input v-model="formData.comId" placeholder="请输入学委学号" clearable :style="{width: '100%'}">
                    </el-input>
                </el-form-item>

                <el-form-item label="发布日期" prop="publishDate" v-show="false">
                    <el-date-picker v-model="formData.publishDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                    :style="{width: '100%'}" placeholder="请选择发布日期" clearable></el-date-picker>
                </el-form-item>
                <el-form-item size="large">
                    <el-button type="primary" @click="submitForm">提交</el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
    export default {
        components: {},
        props: [],
        data() {
            return {
                formData: {
                    noticeTitle: undefined,
                    content: undefined,
                    comId: undefined,
                    publishDate: null
                },
                rules: {
                    noticeTitle: [{
                        required: true,
                        message: '请输入标题',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '请输入标题',
                        trigger: 'blur'
                    }],
                    content: [{
                        required: true,
                        message: '请输入内容',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '请输入公告内容',
                        trigger: 'blur'
                    }],
                },
            }
        },
        created() {
            this.formData.comId = sessionStorage.getItem('cmtComId')
        },
        mounted() {},
        methods: {
            submitForm() {
                var _this = this
                var date = new Date();
                var today = date .getFullYear()+'-'+(date .getMonth()+1)+'-'+date .getDate();
                this.formData.publishDate = today
                console.log(this.formData)
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    // TODO 提交表单
                    axios.post('http://localhost:8080/jas/mport/notice/saveNotice',_this.formData).then(function (resp) {
                        if (resp.data =='success'){
                            alert("保存成功")
                        } else{
                            alert('保存失败')
                        }
                    })
                })
            },
            resetForm() {
                this.$refs['elForm'].resetFields()
            },
        }
    }

</script>
<style>
</style>
