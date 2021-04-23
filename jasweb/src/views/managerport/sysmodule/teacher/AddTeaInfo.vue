<template>
  <div style="width: 100%">
   <div style="float: left">
       <h5 v-show="showH5a">添加教师信息</h5>
       <h5 v-show="showH5u">修改教师信息</h5>
   </div>
    <div class="formBlock" style="margin-top: 40px">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="教师编号" prop="tNo">
          <el-input v-model="formData.tNo" placeholder="请输入教师编号" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name" placeholder="请输入姓名" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" >
          <el-input v-model="formData.password" placeholder="请输入密码" readonly clearable :style="{width: '100%'}" size="mini">
          </el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="formData.phone" placeholder="请输入电话" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email" placeholder="请输入邮箱" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="identify">
          <el-input v-model="formData.identify" placeholder="请输入身份证号" clearable :style="{width: '100%'}" size="mini">
          </el-input>
        </el-form-item>
        <div  style="margin-left: 70px">
          课程：
          <el-checkbox-group v-model="chooseCourse">
            <el-checkbox :label="item" v-for="(item,index) in courseOption" :key="index" ></el-checkbox>
          </el-checkbox-group>
        </div>
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
        courseOption: [],
        chooseCourse: [],
          showH5a: true,
          showH5u: true,
        formData: {
          tNo: undefined,
          name: undefined,
          password: '123456',
          phone :undefined,
          email: undefined,
          identify: undefined,
          courses: []
        },
        rules: {
          tNo: [{
            required: true,
            message: '请输入学号',
            trigger: 'blur'
          }, {
            pattern: /\d{12}/,
            message: '请输入十二位数字',
            trigger: 'blur'
          }],
          name: [{
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          }, {
            pattern: /\S/,
            message: '姓名不能为空',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }, {
            pattern: /\S/,
            message: '密码不能为空',
            trigger: 'blur'
          }],
          phone: [{
            required: true,
            message: '请输入电话',
            trigger: 'blur'
          }, {
            pattern: /0?(13|14|15|18|17)[0-9]{9}/,
            message: '请输入正确的手机号',
            trigger: 'blur'
          }],
          email: [{
            required: true,
            message: '请输入邮箱',
            trigger: 'blur'
          }, {
            pattern: /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/,
            message: '请输入正确的邮箱',
            trigger: 'blur'
          }],
          identify: [{
            required: true,
            message: '请输入身份证号',
            trigger: 'blur'
          }, {
            pattern: /\d{17}[\d|x]|\d{15}/,
            message: '请输入正确的身份证号',
            trigger: 'blur'
          }],
        },
      }
    },
    computed: {},
    watch: {},
    created() {
        var _this =this
        axios.get('http://localhost:8080/jas/mport/course/getAllCourse/1/30').then(function (resp) { //获取所有课程
          if (resp.data!==null&&resp.data!=''){
            for(var i=0;i<resp.data.length;i++){
              _this.courseOption.push(resp.data[i].name)
            }
          }
        })

      var s=this.$route.params.tNoFromM   //从StuMainDiv的修改跳来的
      var s2 = this.$route.params.tNoFromV //从ViewStuInfo的修改跳来的
        var tNo ='';
        if(s!=undefined){
          tNo =s
        }else if(s2!=undefined){
          tNo =s2
        }

        if(tNo == ''){  //添加学生信息
            this.showH5a = true
            this.showH5u = false
        }else {  //修改学生信息
            this.showH5u = true
            this.showH5a = false
            axios.get('http://localhost:8080/jas/mport/tea/getTeaByTno/'+ tNo).then(function (resp) {
                _this.formData = resp.data
                _this.chooseCourse= [];
                for (let i=0;i<resp.data.courses.length;i++){
                   _this.chooseCourse.push(resp.data.courses[i].name)
                }
            })
        }

    },
    mounted() {
      // console.log("this.formDate")
      // console.log(this.formData)
    },

    methods: {
      submitForm() {
        alert(this.chooseCourse.length)
        const len =this.chooseCourse.length
        this.formData.courses = [];
        for(let i=0;i<len;i++){
            let course ={courseId: null, name: null, period: null, credit: null, beginDate: null, endDate: null}
            course.name = this.chooseCourse[i]
            this.formData.courses.push(course)
        }
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          // TODO 提交表单
          axios.post('http://localhost:8080/jas/mport/tea/saveTea', this.formData).then(function (resp) {
            if (resp.data === 'success') {

                alert("提交成功")
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
