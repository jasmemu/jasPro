<template>
  <div style="width: 100%">
   <div style="float: left">
       <h5 v-show="showH5a">添加学生信息</h5>
       <h5 v-show="showH5u">修改学生信息</h5>
   </div>
    <div class="formBlock">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="学号" prop="sNo">
          <el-input v-model="formData.sNo" placeholder="请输入学号" clearable :style="{width: '100%'}" size="mini"></el-input>
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
          <el-input v-model="formData.email" placeholder="请输入邮箱" clearable :style="{width: '100%'}" size="mini"></el-input>
        </el-form-item>
        <el-form-item label="身份证号" prop="identify">
          <el-input v-model="formData.identify" placeholder="请输入身份证号" clearable :style="{width: '100%'}" size="mini">
          </el-input>
        </el-form-item>
        <el-form-item label="专业" prop="speId">
          <el-select v-model="formData.speId" placeholder="请选择专业" clearable :style="{width: '100%'}" size="mini">
            <el-option v-for="(item, index) in speIdOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入学时间" prop="enrollment">
          <el-date-picker v-model="formData.enrollment" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                          :style="{width: '100%'}" placeholder="请选择入学时间"  size="mini"></el-date-picker>
        </el-form-item>
        <el-form-item label="年级" prop="sGrade">
          <el-select v-model="formData.sGrade" placeholder="请选择年级" clearable :style="{width: '100%'}" size="mini">
            <el-option v-for="(item, index) in sGradeOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled" ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级" prop="sClass">
          <el-select v-model="formData.sClass" placeholder="请选择班级" clearable :style="{width: '100%'}" size="mini">
            <el-option v-for="(item, index) in sClassOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
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
          sNo: undefined,
          name: undefined,
          password: '123456',
          phone: undefined,
          email: undefined,
          identify: undefined,
          speId: undefined,
          enrollment: null,
          sGrade: undefined,
          sClass: undefined,
        },
        rules: {
          sNo: [{
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
          speId: [{
            required: true,
            message: '请选择专业',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择专业',
            trigger: 'change'
          }],
          enrollment: [{
            required: true,
            message: '请选择入学时间',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择入学时间',
            trigger: 'change'
          }],
          sGrade: [{
            required: true,
            message: '请选择年级',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择年级',
            trigger: 'change'
          }],
          sClass: [{
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
        sGradeOptions: [{
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
        sClassOptions: [{
          "label": "1班",
          "value": 1
        }, {
          "label": "2班",
          "value": 2
        }],
      }
    },
    computed: {},
    watch: {},
    created() {
        var _this =this
        var s=this.$route.params.sNo
        var s2 = this.$route.params.stuNo
      console.log("s=="+s)
        console.log('s2=='+s2)

        var sNo ='';
        if(s!=undefined){
          sNo =s
        }else if(s2!=undefined){
          sNo =s2
        }
      console.log('sNo=='+sNo)

        if(sNo == ''){  //添加学生信息
            this.showH5a = true
            this.showH5u = false
        }else {  //修改学生信息
            this.showH5u = true
            this.showH5a = false
          console.log('sNo2=='+sNo)
            axios.get('http://localhost:8080/jas/mport/stu/getStuBySno/'+ sNo).then(function (resp) {
                console.log(resp)
                _this.formData = resp.data
            })
        }

    },
    mounted() {
        var _this = this;
        axios.get('http://localhost:8080/jas/mport/spe/getspecialties').then(function (resp) {
            // console.log(resp)
            if(resp.data!== null){
                for (var i =0;i<resp.data.length;i++) {
                    var  spe= {"label": "", "value": ""};
                    spe.value = resp.data[i].speId;
                    spe.label = resp.data[i].speName;
                    _this.speIdOptions.push(spe)

                }
            }
        })
        // console.log("获取的系信息")
        // console.log(this.speIdOptions)
    },

    methods: {
      submitForm() {
          console.log(this.formData)
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          // TODO 提交表单
          axios.post('http://localhost:8080/jas/mport/stu/savestu', this.formData).then(function (resp) {
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
