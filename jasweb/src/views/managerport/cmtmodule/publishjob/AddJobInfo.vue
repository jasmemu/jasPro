<template>
    <div>
        <el-form :model="jobForm" :rules="rules" class="registerForm" ref="registerForm" label-width="80px">
            <el-form-item label="作业" prop="jobFile">
                <el-button type="primary" plain size="small" @click="dialogFormVisible = true">上传作业</el-button><br>
                <span v-show="fileShow" style="color: #a50912">请上传文件</span>
            </el-form-item>
            <el-form-item label="作业类型" prop="jobType"  >
                <select v-model=jobForm.jobType  >
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
        <el-dialog title="文件上传" :visible.sync="dialogFormVisible">
            <input  id="fUpload" multiple type="file" />
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="commitFile()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "register",
        //    判断密码是否一致；
        data() {
            return {
                fileShow: false,
                cmtId: sessionStorage.getItem('cmtComId'),
                jobForm: {
                    comId: sessionStorage.getItem('cmtComId'),
                    jobFile: null,
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
                    // jobFile: [
                    //     { required: true, message: "请上传文件", trigger: "change" },
                    // ],
                    jobType: [
                        {
                            required: true,
                            message: "请选择作业类型",
                            trigger: "blur"
                        }
                    ],
                    endDate: [
                        { required: true, message: "请设置截至时间", trigger: "blur" }
                    ],
                    mark: [
                        { required: true, message: "作业说明不能为空", trigger: "blur" }
                    ]
                }
            };
        },
        mounted() {
            var _this = this
            axios.get('http://localhost:8080/jas/mport/course/getNamesByCmtId/'+this.cmtId).then(function (resp) {
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
            // 文件上传
            commitFile(){
                this.dialogFormVisible = false
                var fp = $("#fUpload");
                var items = fp[0].files;
                if ((items[0]) == undefined || (items[0]) == null || (items[0]) == ''){
                    this.fileShow = true
                    return
                }else {
                    this.fileShow = false
                    this.jobForm.jobFile = items[0]
                }

            },
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
                if (this.jobForm.jobFile == ''|| this.jobForm.jobFile ==null){
                    this.fileShow = true
                    return
                }
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