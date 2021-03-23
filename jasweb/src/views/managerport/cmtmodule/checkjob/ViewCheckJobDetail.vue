<template>
    <div>
        <h2>作业打分</h2>
        <div style="width: 50%;margin-left: 10%">
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-form-item label="作业名称" prop="hName">
                    <el-input v-model="formData.hName" placeholder="请输入作业名称" readonly clearable :style="{width: '100%'}">
                    </el-input>
                </el-form-item>
                <el-form-item label="学生姓名" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入学生姓名" readonly clearable :style="{width: '100%'}">
                    </el-input>
                </el-form-item>
                <el-form-item label="学号" prop="sNo">
                    <el-input v-model="formData.sNo" placeholder="请输入学号" readonly clearable :style="{width: '100%'}">
                    </el-input>
                </el-form-item>
                <el-form-item label="提交时间" prop="correctDate">
                    <el-input v-model="formData.correctDate" placeholder="请输入提交时间" readonly clearable
                              :style="{width: '100%'}"></el-input>
                </el-form-item>
                <el-form-item label="分数" prop="score">
                    <el-input v-model="formData.score" placeholder="请输入分数" clearable :style="{width: '100%'}"></el-input>
                </el-form-item>
                <el-form-item label="评语" prop="comment">
                    <el-input v-model="formData.comment" type="textarea" placeholder="请输入多行文本"
                              :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
                   <!-- <el-input v-model="formData.comment" placeholder="请输入评语" clearable :style="{width: '100%'}">-->
                    </el-input>
                </el-form-item>
                <el-form-item size="large" style="margin-left: 40%">
                    <el-button type="primary" @click="submitForm">确定</el-button>
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
                formData: {
                    hId: '',  // 提交的作业id，不是发布的作业id
                    hName: '',
                    name: '',
                    sNo: '',
                    correctDate: '',
                    score: undefined,
                    comment: undefined,
                },
                rules: {
                    hName: [{
                        required: true,
                        message: '请输入作业名称',
                        trigger: 'blur'
                    }],
                    name: [{
                        required: true,
                        message: '请输入学生姓名',
                        trigger: 'blur'
                    }],
                    sNo: [{
                        required: true,
                        message: '请输入学号',
                        trigger: 'blur'
                    }],
                    correctDate: [{
                        required: true,
                        message: '请输入提交时间',
                        trigger: 'blur'
                    }],
                    score: [{
                        required: true,
                        message: '请输入分数',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '',
                        trigger: 'blur'
                    }],
                    comment: [{
                        required: true,
                        message: '请输入评语',
                        trigger: 'blur'
                    }, {
                        pattern: /\S/,
                        message: '',
                        trigger: 'blur'
                    }],
                },
            }
        },
        computed: {},
        watch: {},
        created() {},
        mounted() {
            var score = this.$route.params.scoreObj
            this.formData.hId = score.id
            this.formData.hName = score.homework.hName
            this.formData.name = score.student.name
            this.formData.sNo = score.student.sNo
            this.formData.correctDate = score.correctDate
            this.formData.score = score.score
            this.formData.comment = score.comment
        },
        methods: {
            submitForm() {
                var f = new FormData();
                f.append("hId",this.formData.hId)
                f.append("score",this.formData.score)
                f.append("comment",this.formData.comment)
                this.$refs['elForm'].validate(valid => {
                    if (!valid) return
                    // TODO 提交表单
                  axios.post('http://localhost:8080/jas/mport/score//updateScore',f).then(function (resp) {

                      if (resp.data == 1){
                          this.$message({
                              type: 'info',
                              message: '打分成功'
                          });
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
<style>
</style>
