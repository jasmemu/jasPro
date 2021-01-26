<template>
    <div>
        <el-row :gutter="15">
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-col :span="12">
                    <el-form-item label="学号" prop="sNo">
                        <el-input v-model="formData.sNo" placeholder="学号" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="formData.name" placeholder="请输入姓名" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="formData.password" placeholder="请输入密码" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="手机号" prop="phone">
                        <el-input v-model="formData.phone" placeholder="请输入手机号" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="专业" prop="speId">
                        <el-input v-model="formData.speId" placeholder="请输入专业" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="邮箱" prop="email">
                        <el-input v-model="formData.email" placeholder="请输入邮箱" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="班级" prop="sClass">
                        <el-input v-model="formData.sClass" placeholder="请输入班级" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="入学时间" prop="enrollment">
                        <el-input v-model="formData.enrollment" placeholder="请输入入学时间" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="年级" prop="sGrade">
                        <el-input v-model="formData.sGrade" placeholder="请输入年级" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="身份证号" prop="identify">
                        <el-input v-model="formData.identify" placeholder="请输入身份证号" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <div style="margin-left: 100px">
                    已选课程
                    <el-checkbox-group v-model="chooseCourse">
                        <el-checkbox :label="item" v-for="(item,index) in chooseCourse" :key="index" onclick="return false;" ></el-checkbox>
                    </el-checkbox-group>
                </div>
                <el-col :span="24">
                    <el-form-item size="large">
                        <el-button type="primary" @click="alterStu()">修改</el-button>
                        <el-button @click="goBack()">返回</el-button>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-row>
    </div>
</template>
<script>
    export default {
        components: {},
        props: [],
        data() {
            return {
                formData: null,
                chooseCourse: [],
                sNo: '',
                rules: {
                    sNo: [],
                    name: [],
                    password: [],
                    phone: [],
                    speId: [],
                    email: [],
                    sClass: [],
                    enrollment: [],
                    sGrade: [],
                    identify: [],
                    courses: []
                },
            }
        },
        computed: {},
        watch: {},
        created() {
            this.formData = this.$route.params.stu  //搜索按钮和查看按钮函数中的路由都有参数stu
            for (let i=0;i<this.formData.courses.length;i++){
                this.chooseCourse.push(this.formData.courses[i].name)
            }
        },
        mounted() {},
        methods: {
            alterStu() {
                var sNo  =this.formData.sNo
                this.$router.push({name:'AddStuInfo',params:{sNoFromV: sNo}})
            },
            goBack() {
               this.$router.push("/SysMainPage/StuMainDiv")
            },
        }
    }

</script>
<style scoped>
</style>
