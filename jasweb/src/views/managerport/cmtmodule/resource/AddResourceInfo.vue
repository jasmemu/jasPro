<template>
    <div>
        <div style="text-align: center">
                <el-button type="primary" plain size="small" @click="dialogFormVisible = true">上传资料</el-button><br>
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

                资料类型：
                <select v-model=formData.resourceType  style="width: 30%;" >
                    <option value=""  style="display: none;" disabled selected>请选择</option>
                    <option v-for="(item,i) in resourceTypeOptions" :key="i"  v-text="item.label"></option>
                </select>

            <el-button type="primary" @click="tijiao()">主要按钮</el-button>





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
                // 文件上传
                fileList: [],
                resourceTypeOptions: [],
                formData: {
                  resourceFile: '',
                  resourceType: ''  
                },
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
            }
        },
        computed: {},
        watch: {},
        created() {
        },
        mounted() {
            var _this = this
            axios.get('http://localhost:8080/jas/mport/course/getNames').then(function (resp) {
                for (let i=0;i<resp.data.length;i++){
                    const option ={label: '',value: ''}
                    option.label = resp.data[i].name
                    option.value = resp.data[i].courseId
                    _this.resourceTypeOptions.push(option)
                }
            })
        },
        methods: {
            //文件上传
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
               this.formData.resourceFile = file.raw
                this.$message('ok');
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            //其他
            tijiao(){
                var _this = this
                var f = new FormData();
                f.append("resourceFile",this.formData.resourceFile)
                f.append("resourceType",this.formData.resourceType)
                f.append("comId",sessionStorage.getItem('cmtComId'))
               axios.post('http://localhost:8080/jas/mport/resource/saveResource',f).then(function (resp) {
                   alert(resp.data)
                   _this.$router.push('/CmtMainPage/ResourceMainDiv')
               })
            }
        }
    }

</script>
<style>
    .el-upload__tip {
        line-height: 1.2;
    }

</style>
