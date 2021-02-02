<template>
  <div style="width: 100%">
    <div style="float: left">
      <h5 v-show="showH5a">添加学习委员信息</h5>
      <h5 v-show="showH5u">修改学习委员信息</h5>
    </div>
    <div class="formBlock">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="学号" prop="comId">
          <el-input v-model="formData.comId" placeholder="请输入学号" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="formData.name" placeholder="请输入姓名" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" >
          <el-input v-model="formData.password" placeholder="请输入密码"  readonly clearable :style="{width: '100%'}" size="mini">
          </el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="formData.phone" placeholder="请输入电话" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email" placeholder="请输入电话" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="">
          <el-select v-model="formData.specialty.speName" placeholder="请选择专业" clearable :style="{width: '100%'}" size="mini">
            <el-option v-for="(item, index) in speIdOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年级" prop="cGrade">
          <el-select v-model="formData.cGrade" placeholder="请选择年级" clearable :style="{width: '100%'}" size="mini">
            <el-option v-for="(item, index) in cGradeOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled" ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级" prop="cClass">
          <el-select v-model="formData.cClass" placeholder="请选择班级" clearable :style="{width: '100%'}" size="mini">
            <el-option v-for="(item, index) in cClassOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
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
          comId: undefined,
          name: undefined,
          password: '123456',
          phone: undefined,
          email: undefined,
          speId: undefined,
          cGrade: undefined,
          cClass: undefined,
          courses: [],
          specialty: {
            speId: 0,
            speName: ''
          }
        },
        rules: {
          comId: [{
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
          speName: [{
            required: true,
            message: '请选择专业',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择专业',
            trigger: 'change'
          }],
          cGrade: [{
            required: true,
            message: '请选择年级',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择年级',
            trigger: 'change'
          }],
          cClass: [{
            required: true,
            message: '请选择班级',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择班级',
            trigger: 'change'
          }],
        },
        speIdOptions: [],
        // speIdOptions: [{
        //   "label": "选项一",
        //   "value": 1
        // }],
        cGradeOptions: [{
          "label": "大一",
          "value": "大一"
        }, {
          "label": "大二",
          "value": "大二"
        },{
          "label": "大三",
          "value": "大三"
        }, {
          "label": "大四",
          "value": "大四"
        }],
        cClassOptions: [{
          "label": "1班",
          "value": 1
        }, {
          "label": "2班",
          "value": 2
        }],
        //     [{
        //    "label": "选项一",
        //    "value": 1
        // }, {
        //     "label": "选项二",
        //     "value": 2
        // }],
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

      var s=this.$route.params.comIdFromM   //从StuMainDiv的修改跳来的
      var s2 = this.$route.params.comIdFromV //从ViewStuInfo的修改跳来的
      var speId ='';
      if(s!=undefined){
        speId =s
      }else if(s2!=undefined){
        speId =s2
      }

      if(speId == ''){  //添加学生信息  从StuMainDiv的添加按钮挑来的
        this.showH5a = true
        this.showH5u = false
      }else {  //修改学生信息
        this.showH5u = true
        this.showH5a = false
        axios.get('http://localhost:8080/jas/mport/cmt/getCmtByComId/'+ speId).then(function (resp) {
          console.log(resp.data)
          _this.formData = resp.data
          _this.chooseCourse= [];
          for (let i=0;i<resp.data.courses.length;i++){
            _this.chooseCourse.push(resp.data.courses[i].name)
          }
        })
      }

    },
    mounted() {
      var _this = this;
      axios.get('http://localhost:8080/jas/mport/spe/getspecialties').then(function (resp) {  //获取所用专业
        if(resp.data!== null){
          for (var i =0;i<resp.data.length;i++) {
            var  spe= {"label": "", "value": ""};
            spe.value = resp.data[i].speId;
            spe.label = resp.data[i].speName;
            _this.speIdOptions.push(spe)
          }
        }
      })
    },

    methods: {
      submitForm() {

       this.formData.specialty.speId = this.formData.speId

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
          axios.post('http://localhost:8080/jas/mport/cmt/saveCmt', this.formData).then(function (resp) {
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
