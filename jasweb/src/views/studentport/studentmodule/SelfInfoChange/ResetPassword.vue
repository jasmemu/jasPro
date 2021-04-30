<template>
  <div style="width: 100%">
    <div style="float: left">
      <h1>修改密码</h1>
    </div>
    <div class="formBlock" >
      <el-form ref="elForm" :model="formData" size="medium" label-width="100px">

        <el-form-item label="旧的密码" prop="password" >
          <el-input v-model="oldPassword" placeholder="请输入旧密码"  show-password  clearable :style="{width: '100%'}" size="mini">
          </el-input>
        </el-form-item>

        <el-form-item label="新的密码" prop="password" >
          <el-input v-model="formData.password" placeholder="请输入新密码"  show-password  clearable :style="{width: '100%'}" size="mini">
          </el-input>
        </el-form-item>

        <el-form-item size="small" style="margin-left: 250px">
          <el-button type="primary" @click="submitForm">确定修改</el-button>
          <el-button @click="resetForm">重置</el-button>
          <el-button @click="goBack">返回</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ResetPassword",
  data(){
    return{
      oldPassword: '',
      formData:null
    }
},




  created() {
    var sno =sessionStorage.getItem('stuAccount')
    // alert(sno)  //弹框提示学号信息
    var _this =this
    axios.get('http://localhost:8080/jas/sport/student/getStuBySnoWith/'+sno).then(function (resp) {
      console.log(resp.data)
      _this.formData = resp.data
      _this.oldPassword = resp.data.password
    })
  },


  methods: {
    submitForm() {
      const _this = this
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
        axios.post('http://localhost:8080/jas/sport/student/updateStu', this.formData).then(function (resp) {
          if (resp.data === 'success') {
            alert("提交成功")
          }else{
            alert("提交失败")
          }
          if(_this.name != _this.formData.name)
          {
            _this.name = sessionStorage.setItem('stuName',_this.formData.name);
          }
          // 账号或者密码被修改后，跳转到登录页面
          if (_this.oldPassword != _this.formData.password) {
            sessionStorage.clear()
            _this.$router.push('/StudentLogin')
          }
        })
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    goBack(){
      this.$router.push('/StudentMainPage')
    }
  }



}
</script>

<style scoped>

</style>