<template>
    <div>
        <el-row :gutter="15">
            <el-form ref="elForm" :model="formData"  size="medium" label-width="100px">
                <el-col :span="12">
                    <el-form-item label="学号" >
                        <el-input v-model="formData.comId" placeholder="学号" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="姓名" >
                        <el-input v-model="formData.name" placeholder="请输入姓名" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="手机号" >
                        <el-input v-model="formData.phone" placeholder="请输入手机号" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="邮箱" >
                        <el-input v-model="formData.email" placeholder="请输入邮箱" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="专业" >
                        <el-input v-model="formData.specialty.speName" placeholder="请输入专业" readonly clearable
                                  :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="年级" >
                        <el-input v-model="formData.cGrade" placeholder="年级" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="班级" >
                        <el-input v-model="formData.cClass" placeholder="班级" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="" >
                        <el-input v-model="kong" placeholder="" readonly clearable :style="{width: '100%'}">
                        </el-input>
                    </el-form-item>
                </el-col>

                <div style="margin-left: 100px">
                    管理课程
                    <el-checkbox-group v-model="chooseCourse">
                        <el-checkbox :label="item" v-for="(item,index) in chooseCourse" :key="index" onclick="return false;" ></el-checkbox>
                    </el-checkbox-group>
                </div>
                <el-col :span="50">
                    <el-form-item size="large">
                        <el-button type="primary" @click="alterCmt()">修改</el-button>
                        <el-button @click="goBack()">返回</el-button>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-row>
    </div>
</template>
<script>
import axios from 'axios'
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
            this.formData = this.$route.params.cmt
            console.log(this.formData)
            for (let i=0;i<this.formData.courses.length;i++){
                this.chooseCourse.push(this.formData.courses[i].name)
            }
        },
        mounted() {
        },
        methods: {
            alterCmt() {
                var speId  =this.formData.speId
                this.$router.push({name:'AddCmtInfo',params:{speIdNoFromV: speId}})
            },
            goBack() {
                this.$router.push("/SysMainPage/CmtMainDiv")
            },
        }
    }

</script>
<style lang="scss" scoped>
</style>
