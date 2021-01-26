<template>
  <div style="width: 100%">
   <div style="float: left">
       <h5 v-show="showH5a">添加课程信息</h5>
       <h5 v-show="showH5u">修改课程信息</h5>
   </div>
    <div class="formBlock">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="课程编号" prop="courseId">
          <el-input v-model="formData.courseId" placeholder="请输入课程编号" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="课程名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入课程名称" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="课时" prop="period" >
          <el-input v-model="formData.period" placeholder="请输入课时"  clearable :style="{width: '100%'}" size="mini">
          </el-input>
        </el-form-item>
        <el-form-item label="学分" prop="credit">
          <el-input v-model="formData.credit" placeholder="请输入学分" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        </el-form-item>
        <el-form-item label="开课日期" prop="beginDate">
          <el-date-picker v-model="formData.beginDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                          :style="{width: '100%'}" placeholder="请选择开课日期" size="mini"></el-date-picker>
        </el-form-item>
        <el-form-item label="结课日期" prop="endDate">
          <el-date-picker v-model="formData.endDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                          :style="{width: '100%'}" placeholder="请选择结课日期" size="mini"></el-date-picker>
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
          showH5a: true,
          showH5u: true,
        formData: {
          courseId: undefined,
          name: undefined,
          period: undefined,
          credit: undefined,
          beginDate: null,
          endDate: null
        },
        rules: {
          courseId: [{
            required: true,
            message: '请输入课程编号',
            trigger: 'blur'
          }, {
            pattern: /\d{9}/,
            message: '请输入九个字符',
            trigger: 'blur'
          }],
          name: [{
            required: true,
            message: '请输入课程名',
            trigger: 'blur'
          }, {
            pattern: /\S/,
            message: '课程名不能为空',
            trigger: 'blur'
          }],
          period: [{
            required: true,
            message: '请输入课时',
            trigger: 'blur'
          }, {
            pattern: /\d/,
            message: '请输入数字',
            trigger: 'blur'
          }],
          credit: [{
            required: true,
            message: '请输入学分',
            trigger: 'blur'
          }, {
            pattern: /\d/,
            message: '请输入数字',
            trigger: 'blur'
          }],
          beginDate: [{
            required: true,
            message: '请选择开课日期',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择开课日期',
            trigger: 'change'
          }],
          endDate: [{
            required: true,
            message: '请选择结课日期',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择结课日期',
            trigger: 'change'
          }],
        }
      }
    },
    computed: {},
    watch: {},
    created() {
        var _this =this
        var s=this.$route.params.courseIdFromM  //从**MainDiv的修改跳来的
        var s2 = this.$route.params.courseIdFromV //从View**Info的修改跳来的

        var courseId ='';
        if(s!=undefined){
          courseId =s
        }else if(s2!=undefined){
          courseId =s2
        }

        if(courseId == ''){  //添加课程信息
            this.showH5a = true
            this.showH5u = false
        }else {  //修改学生信息
            this.showH5u = true
            this.showH5a = false
            axios.get('http://localhost:8080/jas/mport/course/getCourseById/'+ courseId).then(function (resp) {
                _this.formData = resp.data
            })
        }

    },
    mounted() {
    },

    methods: {
      submitForm() {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          // TODO 提交表单
          axios.post('http://localhost:8080/jas/mport/course/saveCourse', this.formData).then(function (resp) {
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
