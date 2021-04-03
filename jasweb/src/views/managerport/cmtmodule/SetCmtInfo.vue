<template>
    <div style="width: 100%">
        <div style="float: left">
            <h1>课程管理员个人信息设置</h1>
        </div>
        <div class="formBlock" >
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-form-item label="账号" prop="comId">
                    <el-input v-model="formData.comId" placeholder="请输入账号" readonly="true" clearable :style="{width: '100%'}" size="mini"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入姓名" readonly="true" clearable :style="{width: '100%'}" size="mini"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password" >
                    <el-input v-model="formData.password" placeholder="请输入密码"  clearable :style="{width: '100%'}" size="mini">
                    </el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="formData.phone" placeholder="请输入电话" clearable :style="{width: '100%'}" size="mini"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="formData.email" placeholder="请输入邮箱" clearable :style="{width: '100%'}" size="mini"></el-input>
                </el-form-item>
                <el-form-item size="small" style="margin-left: 250px">
                    <el-button type="primary" @click="submitForm">提交</el-button>
                    <el-button @click="resetForm">重置</el-button>
                    <el-button @click="goBack">返回</el-button>
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
                courseOption: [],
                chooseCourse: [],
                oldAccount: '',
                oldPassword: '',
                formData: {
                    comId: undefined,
                    name: undefined,
                    password: '',
                    phone :undefined,
                    email: undefined,
                    identify: undefined
                },
                rules: {
                    comId: [{
                        required: true,
                        message: '请输入账号',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '账号不能为空',
                        trigger: 'blur'
                    }],
                    name: [{
                        required: true,
                        message: '请输入姓名',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '姓名不能为空',
                        trigger: 'blur'
                    }],
                    password: [{
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '密码不能为空',
                        trigger: 'blur'
                    }],
                    phone: [{
                        required: true,
                        message: '请输入电话',
                        trigger: 'blur'
                    }, {
                        pattern: /0?(13|14|15|18|17)[0-9]{9}/,
                        message: '请输入正确的手机号',
                        trigger: 'blur'
                    }],
                    email: [{
                        required: true,
                        message: '请输入邮箱',
                        trigger: 'blur'
                    }, {
                        pattern: /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/,
                        message: '请输入正确的邮箱',
                        trigger: 'blur'
                    }],
                    identify: [{
                        required: true,
                        message: '请输入身份证号',
                        trigger: 'blur'
                    }, {
                        pattern: /\d{17}[\d|x]|\d{15}/,
                        message: '请输入正确的身份证号',
                        trigger: 'blur'
                    }],
                },
            }
        },
        computed: {},
        watch: {},
        created() {
            var cmtComId =sessionStorage.getItem('cmtComId')
            alert(cmtComId)
            var _this =this
            axios.get('http://localhost:8080/jas/mport/cmt/getCmtByComIdNoWith/'+cmtComId).then(function (resp) {
                console.log(resp.data)
                _this.formData = resp.data
                _this.oldPassword = resp.data.password
            })

        },
        mounted() {

        },

        methods: {
            submitForm() {
                const _this = this
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    // TODO 提交表单
                    axios.post('http://localhost:8080/jas/mport/cmt/updateCmt', this.formData).then(function (resp) {
                        if (resp.data === 'success') {
                            alert("提交成功")
                        }else{
                            alert("提交失败")
                        }

                        // 账号或者密码被修改后，跳转到登录页面
                        if (_this.oldPassword != _this.formData.password) {
                            sessionStorage.clear()
                            _this.$router.push('/CmtLogin')
                        }
                    })




                })
            },
            resetForm() {
                this.$refs['elForm'].resetFields()
            },
            goBack(){
                this.$router.push('/CmtMainPage')
            }
        }
    }

</script>
<style lang="scss"  scoped>
    .formBlock{
        width: 50%;
        margin-left: 150px;
        float: left;
        margin-top: 110px;
    }
</style>
