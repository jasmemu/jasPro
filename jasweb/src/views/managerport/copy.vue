<template>
    <div>
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
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
</template>
<script>
    export default {
        components: {},
        props: [],
        data() {
            return {
                formData: {
                    comId: undefined,
                    publishDate: null,
                },
                rules: {
                    comId: [{
                        required: true,
                        message: '请输入学委学号',
                        trigger: 'blur'
                    }],
                    publishDate: [{
                        required: true,
                        message: '请选择发布日期',
                        trigger: 'change'
                    }],
                },
            }
        },
        computed: {},
        watch: {},
        created() {},
        mounted() {},
        methods: {
            submitForm() {
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    // TODO 提交表单
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
