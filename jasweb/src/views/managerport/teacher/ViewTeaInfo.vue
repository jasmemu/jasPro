<template>
    <div>
        <el-row :gutter="15">
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">

                <el-col :span="12">
                    <el-form-item label="教师编号" prop="tNo">
                        <el-input v-model="formData.tNo" placeholder="教师编号" readonly clearable :style="{width: '100%'}">
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
                    <el-form-item label="手机号" prop="phone">
                        <el-input v-model="formData.phone" placeholder="请输入手机号" readonly clearable
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
                    <el-form-item label="身份证号" prop="identify">
                        <el-input v-model="formData.identify" placeholder="请输入身份证号" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="" prop="identify">
                        <el-input v-model="kong" placeholder="" readonly clearable disabled
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <div style="margin-left: 100px">
                    已选课程
                    <el-checkbox-group v-model="chooseCourse">
                        <el-checkbox :label="item" v-for="(item,index) in chooseCourse" :key="index" onclick="return false;" ></el-checkbox>
                    </el-checkbox-group>
                </div>
                <el-col :span="50">
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
                kong: null,
                chooseCourse: [],
                formData: null,
                sNo: ''
            }
        },
        computed: {},
        watch: {},
        created() {
            this.formData = this.$route.params.tea
            for (let i=0;i<this.formData.courses.length;i++){
                this.chooseCourse.push(this.formData.courses[i].name)
            }
        },
        mounted() {},
        methods: {
            alterStu() {
                var tNo  =this.formData.tNo
                this.$router.push({name:'AddTeaInfo',params:{tNoFromV: tNo}})
            },
            goBack() {
                this.$router.push("/SysMainPage/TeaMainDiv")
            },
        }
    }

</script>
<style>
</style>
