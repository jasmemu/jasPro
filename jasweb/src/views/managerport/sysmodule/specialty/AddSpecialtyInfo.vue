<template>
    <div style="width: 100%">
        <div style="float: left">
            <h5 v-show="showH5a">添加专业</h5>
            <h5 v-show="showH5u">修改专业信息</h5>
        </div>
        <div class="formBlock" style="margin-top: 40px">
            <el-form ref="elForm"  :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-form-item label="专业编码" prop="speNum" >
                    <el-input v-model="formData.speNum" :readonly="updateSpecialty"  placeholder="请输入专业编码"  clearable :style="{width: '100%'}" size="mini">
                    </el-input>
                </el-form-item>
                <el-form-item label="专业名称"  prop="speName">
                    <el-input v-model="formData.speName" placeholder="请输入专业名称" clearable :style="{width: '100%'}" size="mini"></el-input>
                </el-form-item>
                <el-form-item label="开课日期" prop="setDate">
                    <el-date-picker v-model="formData.setDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                    :style="{width: '100%'}" placeholder="请选择开办日期" size="mini"></el-date-picker>
                </el-form-item>
                <el-form-item size="small" style="margin-left: 250px">
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
                api: this.$store.state.api,
                updateSpecialty:false,
                showH5a: true,
                showH5u: true,
                formData: {
                    speNum:null,
                    speName: null,
                    setDate:null
                },
                rules: {
                    speNum: [{
                        required: true,
                        message: '请输入专业编号',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '专业编号不能为空',
                        trigger: 'blur'
                    }],
                    speName: [{
                        required: true,
                        message: '请输入专业名称',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '专业名称不能为空',
                        trigger: 'blur'
                    }],
                    setDate: [{
                        required: true,
                        message: '请选择开办日期',
                        trigger: 'change'
                    }, {
                        pattern: /\S/,
                        message: '开办日期不能为空',
                        trigger: 'change'
                    }],
                },
            }
        },
        computed: {},
        watch: {},
        created() {
            var _this =this
            var specialty=this.$route.params.forWhat  //从**MainDiv的修改跳来的
            if (specialty == null) {
                this.showH5a = true
                this.showH5u = false
            }else {
                this.showH5u = true
                this.showH5a = false
                this.updateClass = true
                this.updateSpecialty = true
                this.formData = specialty
            }
        },
        methods: {
            submitForm() {
                var that = this
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    // TODO 提交表单
                    axios.post(this.api+'/mport/spe/update/or/add', this.formData).then(function (resp) {
                        console.log(resp.data)
                        if (resp.data.result == 'SUCCESS') {
                            that.$router.push('/SysMainPage/SpecialtyMainDiv')
                        }else{
                            alert("提交失败")
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
<style lang="scss"  scoped>
    .formBlock{
        width: 50%;
        margin-left: 150px;
        float: left;
    }
</style>
