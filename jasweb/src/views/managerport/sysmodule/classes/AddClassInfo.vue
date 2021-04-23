<template>
  <div style="width: 100%">
    <div style="float: left">
      <h5 v-show="showH5a">添加班级信息</h5>
      <h5 v-show="showH5u">修改班级信息</h5>
    </div>
    <div class="formBlock" style="margin-top: 40px">
      <el-form ref="elForm"  :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="专业"  prop="speId">
          <el-select v-model="formData.speId"  :disabled="updateClass" placeholder="请选择专业" clearable :style="{width: '100%'}" size="mini">
            <el-option v-for="(item, index) in speIdOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年级" disabled="true" prop="grade">
          <el-select v-model="formData.grade" :disabled="updateClass" placeholder="请选择班级" clearable :style="{width: '100%'}" size="mini">
            <el-option v-for="(item, index) in cGradeOptions" :key="index" :label="item.label"
                       :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级" prop="numClass" >
          <el-input v-model="formData.numClass" :readonly="updateClass"  placeholder="请输入班级号（如1、2、3）"  clearable :style="{width: '100%'}" size="mini">
          </el-input>
        </el-form-item>
        <el-form-item label="班主任教师编号"  prop="charge">
          <el-input v-model="formData.charge" placeholder="请输入班主任教师编号" clearable :style="{width: '100%'}" size="mini"></el-input>
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
        updateClass:false,
        showH5a: true,
        showH5u: true,
        formData: {
          speId:null,
          grade: null,
          numClass:null,
          charge: null
        },
        rules: {
          speId: [{
            required: true,
            message: '请选择专业',
            trigger: 'change'
          }, {
            pattern: /\d/,
            message: '请选择专业',
            trigger: 'change'
          }],
          grade: [{
            required: true,
            message: '请选择年级',
            trigger: 'change'
          }, {
            pattern: /\S/,
            message: '请选择年级',
            trigger: 'change'
          }],
          numClass: [{
            required: true,
            message: '请输入班级编号(如1、2、3)',
            trigger: 'blur'
          }, {
            pattern:  /[1-9]\d*$/,
            message: '班级编号不能为空，请输入班级编号(如1、2、3)',
            trigger: 'blur'
          }],
          charge: [{
            required: true,
            message: '请输入班主任教师编号',
            trigger: 'blur'
          }, {
            pattern: /\S/,
            message: '请输入班主任教师编号',
            trigger: 'blur'
          }],
        },
        speIdOptions: [],
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
      }
    },
    computed: {},
    watch: {},
    created() {
      var _this =this
      var s=this.$route.params.classesFromMaimDivForUpdate  //从**MainDiv的修改跳来的
      var s2 = this.$route.params.classesFromMaimDivForAdd //从**MainDiv的修改跳来的
      var chooseOpt = s;
      if(s!=undefined){
        chooseOpt =s
      }else if(s2!=undefined){
        chooseOpt =s2
      }
      if (chooseOpt == 'addClasses') {
        this.showH5a = true
        this.showH5u = false
        this.updateClass=false
      }else {
        this.showH5u = true
        this.showH5a = false
        this.updateClass = true
        this.formData =s
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
        var that = this
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
          // TODO 提交表单
          axios.post('http://localhost:8080/jas/mport/classes//save/class', this.formData).then(function (resp) {
            console.log(resp.data)
            if (resp.data.result == 'SUCCESS') {
              that.$router.push('/SysMainPage/ClassMainDiv')
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
