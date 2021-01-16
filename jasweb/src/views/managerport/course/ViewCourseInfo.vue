<template>
    <div>
        <el-row :gutter="15">
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-col :span="12">
                    <el-form-item label="课程号" prop="courseId">
                        <el-input v-model="formData.courseId" placeholder="课程号" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="课程名称" prop="name">
                        <el-input v-model="formData.name" placeholder="请输入课程名称" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="课时" prop="password">
                        <el-input v-model="formData.period" placeholder="请输入课时" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="学分" prop="credit">
                        <el-input v-model="formData.credit" placeholder="请输入学分" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="12">
                    <el-form-item label="开课日期" prop="beginDate">
                        <el-input v-model="formData.beginDate" placeholder="请输入开课日期" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="结课日期" prop="endDate">
                        <el-input v-model="formData.endDate" placeholder="请输入结课日期" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="24">
                    <el-form-item size="large">
                        <el-button type="primary" @click="alterCourse()">修改</el-button>
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
                sNo: ''
                // rules: {
                //     sNo: [],
                //     name: [],
                //     period: [],
                //     credit: [],
                //     speId: [],
                //     email: [],
                //     sClass: [],
                //     beginDate: [],
                //     sGrade: [],
                //     identify: [],
                // },
            }
        },
        computed: {},
        watch: {},
        created() {
            this.formData = this.$route.params.course
            console.log(this.formData)
        },
        mounted() {},
        methods: {
            alterCourse() {
                var courseId  =this.formData.courseId
                this.$router.push({name:'AddCourseInfo',params:{courseIdFromV: courseId}})
            },
            goBack() {
               this.$router.push("/SysMainPage/CourseMainDiv")
            },
        }
    }

</script>
<style>
</style>
