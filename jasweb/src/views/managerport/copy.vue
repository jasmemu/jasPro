<template>
    <div>
            <div class="manage_tip">
                <span class="title">表单在提交的时候进行验证</span>
            </div>
            <el-form :model="registerUser" :rules="rules" class="registerForm" ref="registerForm" label-width="80px">
                <el-form-item label="用户名" prop="name">
                    <el-input v-model="registerUser.name" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="registerUser.email" placeholder="请输入邮箱"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="registerUser.password" placeholder="请输入密码" type="password"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="password2">
                    <el-input v-model="registerUser.password2" placeholder="请确认密码" type="password"></el-input>
                </el-form-item>
                <el-form-item label="选择身份">
                    <el-select v-model="registerUser.identity" placeholder="请选择身份">
                        <el-option label="管理员" value="manager"></el-option>
                        <el-option label="员工" value="employee"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary"  class="submit_btn" @click="submitForm('registerForm')">注 册</el-button>
                </el-form-item>
            </el-form>
    </div>
</template>

<script>
    export default {
        name: "register",
        //    判断密码是否一致；
        data() {
            var validatePass2 = (rule, value, callback) => {
                if (value !== this.registerUser.password) {
                    callback(new Error("两次输入密码不一致!"));
                } else {
                    callback();
                }
            };
            return {
                registerUser: {
                    name: "",
                    email: "",
                    password: "",
                    password2: "",
                    identity: ""
                },
               //    在return 后面；
                rules: {
                    // 要以数组形式展示
                    name: [
                        { required: true, message: "用户名不能为空", trigger: "change" },
                        { min: 2, max: 30, message: "长度在 2 到 30 个字符", trigger: "blur" }
                    ],
                    email: [
                        {
                            type: "email",
                            required: true,
                            message: "邮箱格式不正确",
                            trigger: "blur"
                        }
                    ],
                    password: [
                        { required: true, message: "密码不能为空", trigger: "blur" },
                        { min: 6, max: 30, message: "长度在 6 到 30 个字符", trigger: "blur" }
                    ],
                    password2: [
                        { required: true, message: "确认密码不能为空", trigger: "blur" },
                        {
                            min: 6,
                            max: 30,
                            message: "长度在 6 到 30 个字符",
                            trigger: "blur"
                        },
                        { validator: validatePass2, trigger: "blur" }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.$axios
                            .post("/api/users/register", this.registerUser)
                            .then(res => {
                                // 注册成功
                                this.$message({
                                    message: "注册成功！",
                                    type: "success"
                                });
                                // this.$router.push("/login");
                            });
                    } else {
                        console.log("error submit!!");
                        return false;
                    }
                });
            }
        }
    };
</script>