<template>
    <div>
        <div style="width: 40%">
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-form-item label="新密码" prop="newPWD">
                    <el-input v-model="formData.newPWD" placeholder="" clearable :style="{width: '30%'}" size="mini"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPWD">
                    <el-input v-model="formData.confirmPWD" placeholder="" clearable :style="{width: '30%'}" size="mini"></el-input>
                </el-form-item>
            </el-form>

<!--            新密码： <el-input placeholder="请输入密码" v-model="newPWD" show-password></el-input>-->
<!--            确认密码：<el-input placeholder="请输入密码" v-model="confirmPWD" show-password></el-input>-->
            <el-button type="primary" @click="submitPWD">提交</el-button>

        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "",
        data(){
            return{
                api: this.$store.state.api,
                formData:{
                    newPWD: '',
                    confirmPWD: ''
                },
                rules: {
                    newPWD: [{
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '请输入密码',
                        trigger: 'blur'
                    }],
                    confirmPWD: [{
                        required: true,
                        message: '请确认密码',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '请确认密码',
                        trigger: 'blur'
                    }]
                }
            }
        },
        methods:{
            submitPWD(){
                var that = this
                if (this.formData.newPWD == this.formData.confirmPWD){
                    var account = this.$route.params.acc
                    alert(account)
                    var formData = new FormData()
                    formData.append('account',account)
                    formData.append('newPWD',this.formData.newPWD)
                    axios.post(this.api+'/mport/cmt/set/pwd/by/authcode',formData).then(function (resp) {
                        if ("SUCCESS" == resp.data.result) {
                            console.log(resp.data)
                            that.$message({
                                showClose: true,
                                message: '密码修改成功',
                                type: 'success'
                            });
                            setInterval(function(){
                               that.$router.push('/CmtLogin');
                            },3000)
                        }
                    })

                } else{
                    this.$message({
                        type: 'warn',
                        message: '密码输入不一致！请重新输入'
                    });
                }
            }
        }
    }
</script>

<style scoped>

</style>