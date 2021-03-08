<template>
  <div>
      <input id="fUpload" multiple type="file" />
      <input  @click="commitFile()" type="button" value="显示上传文件的详细详细" />
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {

            }
        },

        methods: {
            // 文件上传
            commitFile(){
                this.dialogFormVisible = false
                var fp = $("#fUpload");
                var items = fp[0].files;
                if ((items[0]) == undefined || (items[0]) == null || (items[0]) == ''){
                    alert("请选择文件")
                    return
                }
                var f1 = this.judgeType(items[0].name)
                var f2 = this.judgeSize(items[0].size)
                if (f1&&f2){
                    var formDate = new FormData()
                    formDate.append("file",items[0])
                    axios.post('http://localhost:8080/jas/mport/stu/dealExcel',formDate).then(function (resp) {
                        location.reload()
                    })
                }else {
                    alert("请上传xls类型文件")
                }
            },
            judgeType(fileName){
                var name = fileName
                var index = name.lastIndexOf(".")
                var endName = name.substr(index)
                console.log("文件类型"+ endName)
                if (endName == '.xls'){
                    return true
                } else {
                    return false
                }
            },
            judgeSize(fileSize){
                var size = parseInt(fileSize)/1024/1024
                console.log("大小"+size)
                if (size<50){
                    return true
                } else {
                    return false
                }
            },

            // commitFile(){
            //     var fp = $("#fUpload");
            //     var items = fp[0].files;
            //     this.judgeType(items[0].name)
            //     this.judgeSize(items[0].size)
            //     var formDate = new FormData()
            //     formDate.append("file",items[0])
            //     axios.post('http://localhost:8080/jas/test/t1',formDate).then(function (resp) {
            //         alert(resp.data)
            //
            //     })
            // },
            // judgeType(fileName){
            //     var name = fileName
            //     var index = name.lastIndexOf(".")
            //     var endName = name.substr(index)
            //     console.log("类别"+endName)
            // },
            // judgeSize(fileSize){
            //     var size = parseInt(fileSize)/1024/1024
            //     console.log("大小"+size)
            // },
        }
    }
</script>