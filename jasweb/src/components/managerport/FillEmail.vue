<template>
    <div style="width: 40%;margin-left: 30%;margin-top: 100px;">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-form-item label="账号" prop="resetPwdAccount">
                <el-input v-model="formData.resetPwdAccount" placeholder="" clearable :style="{width: '60%'}" size="mini"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="filledEmail">
                <el-input v-model="formData.filledEmail" placeholder="" clearable :style="{width: '60%'}" size="mini"></el-input>
            </el-form-item>
        </el-form>

        <el-button type="primary" @click="getAuthCode" style="margin-left: 45%">下一步</el-button>

    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data(){
            return{
                api: this.$store.state.api,
                formData:{
                    filledEmail: '',
                    authCode: '',
                    resetPwdAccount: ''
                },
                rules: {
                    filledEmail: [{
                        required: true,
                        message: '请输入邮箱',
                        trigger: 'blur'
                    }, {
                        pattern: /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/,
                        message: '请输入正确的邮箱',
                        trigger: 'blur'
                    }],
                    resetPwdAccount: [{
                        required: true,
                        message: '请输入学号',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '请输入学号',
                        trigger: 'blur'
                    }]
                }
            }
        },
        methods: {
            getAuthCode(){
                var that = this
                var formDate = new FormData()
                formDate.append("account",this.formData.resetPwdAccount)
                formDate.append("email",this.formData.filledEmail)
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    // TODO 提交表单
                        axios.post(this.api+'/mport/cmt/get/auth/code',formDate).then(function (resp) {
                            if (resp.data.result== 'SUCCESS'){
                                console.log(resp.data)
                                that.authCode = resp.data.data
                                that.open()
                            }else{
                                alert('获取验证码失败！')
                            }
                        })
                })
            },
            open() {
                this.$prompt('请输入验证码', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputPattern: /\S/,
                    inputErrorMessage: '验证码格式不正确'
                }).then(({ value }) => {
                    if (value == this.authCode){
                        //this.$router.push('/FindBackPWD/ResetPWD')
                        // console.log(this.formData.resetPwdAccount)
                        //使用this.$router.push({path：}这种事没法携带params
                        // this.$router.push({path:'/FindBackPWD/ResetPWD',params:{'acc':this.formData.resetPwdAccount}})
                        this.$router.push({name:'ResetPWD',params:{'acc':this.formData.resetPwdAccount}})
                    } else {
                        this.$message({
                            type: 'error',
                            message: '验证码输入错误！'
                        });
                    }

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
            }
        }
    }
</script>