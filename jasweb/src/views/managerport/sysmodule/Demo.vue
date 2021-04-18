<template>
    <div>
        <!-- Form -->
        <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>
        <el-dialog title="新的学期" :visible.sync="dialogFormVisible">
            <p ><span style="color: #a50912">这会删除本学期所有学生信息、教师信息、课程信息！</span>如确定请输入登录密码</p>
            <el-form :model="form">
                <el-form-item label="密码" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="sbmitChoose('cancel')">取 消</el-button>
                <el-button type="primary" @click="sbmitChoose('yes')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>


<script>
    export default {
        data() {
            return {
                dialogFormVisible: false,
                form: {
                    name: '',
                },
                formLabelWidth: '120px'
            };
        },
        methods:{
            sbmitChoose(val){
                this.dialogFormVisible = false
                if (val == 'cancel'){
                    alert('已取消')
                } else if (val == 'yes'){
                    alert('已提交')
                }
            }
        }
    };
</script>