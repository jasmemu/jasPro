<template>
    <div>
        <el-form :model="jobForm" :rules="rules" class="registerForm" ref="registerForm" label-width="80px">
            <el-form-item label="作业" prop="jobFile">
                <el-button type="primary" plain size="small" @click="dialogFormVisible = true">上传作业</el-button><br>
                <el-dialog title="文件上传" :visible.sync="dialogFormVisible">
                    <el-form :model="form">
                        <el-upload
                                class="upload-demo"
                                action="https://jsonplaceholder.typicode.com/posts/"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :before-remove="beforeRemove"
                                multiple
                                :limit="3"
                                :on-exceed="handleExceed"
                                :file-list="fileList">
                            <el-button size="small" type="primary">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                        </el-upload>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
                    </div>
                </el-dialog>
            </el-form-item>
            <el-form-item label="作业类型:" prop="jobType"  >
                <select v-model=jobForm.jobType  style="width: 30%;" placeholder="请选择类型">
                    <option value=""  style="display: none;" disabled selected>请选择</option>
                    <option v-for="(item,i) in jobTypeOptions" :key="i" v-text="item.label"></option>
                </select>
            </el-form-item>
            <el-form-item label="截至时间" prop="endDate">
                <el-date-picker
                        v-model="jobForm.endDate"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="说明" prop="mark">
                <el-input v-model="jobForm.mark" placeholder="请输入说明" ></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary"  class="submit_btn" @click="submitForm('registerForm')">发布</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "register",
        //    判断密码是否一致；
        data() {
            return {
                jobForm: {
                    comId: sessionStorage.getItem('cmtComId'),
                    jobFile: "",
                    jobType: "",
                    endDate: "",
                    mark: ""
                },
                jobTypeOptions: [],
                // 文件上传
                fileList: [],
                //弹出框
                dialogTableVisible: false,
                dialogFormVisible: false,
                form: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                formLabelWidth: '120px',
                //    在return 后面；
                rules: {
                    // 要以数组形式展示
                    jobFile: [
                        { required: true, message: "用户名不能为空", trigger: "change" },
                    ],
                    jobType: [
                        {
                            required: true,
                            message: "请选择作业类型",
                            trigger: "blur"
                        }
                    ],
                    endDate: [
                        { required: true, message: "密码不能为空", trigger: "blur" }
                    ],
                    mark: [
                        { required: true, message: "确认密码不能为空", trigger: "blur" }
                    ]
                }
            };
        },
        mounted() {
            var _this = this
            axios.get('http://localhost:8080/jas/mport/course/getNames').then(function (resp) {
                for (let i=0;i<resp.data.length;i++){
                    const option ={label: '',value: ''}
                    option.label = resp.data[i].name
                    option.value = resp.data[i].courseId
                    _this.jobTypeOptions.push(option)
                }
            })
            console.log(this.jobTypeOptions)
        },
        methods: {
            //文件上传
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                this.jobForm.jobFile = file.raw
                this.$message('ok');
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            //其他
            submitForm(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        var courseId = ''
                        for(let i =0 ;i<this.jobTypeOptions.length;i++){
                            if (this.jobForm.jobType == this.jobTypeOptions[i].label){
                                courseId = this.jobTypeOptions[i].value
                            }
                        }
                        var f = new FormData();
                        f.append('jobFile',this.jobForm.jobFile)
                        f.append('courseId',courseId)
                        f.append('endDate',this.jobForm.endDate)
                        f.append('mark',this.jobForm.mark)
                        f.append('comId',this.jobForm.comId)
                        console.log(f)
                        axios.post('http://localhost:8080/jas/mport/homework/saveJob',f).then(function (resp) {
                            if (resp.data == 1){
                                alert("保存成功")
                            } else{
                                alert('保存失败')
                            }

                        })
                    } else {
                        console.log("error submit!!");
                        return false;
                    }
                });
            }
        }
    };
</script>

<style lang="scss" scoped>
    .el-form-item__label{
        width: 1000px;

    }
</style>