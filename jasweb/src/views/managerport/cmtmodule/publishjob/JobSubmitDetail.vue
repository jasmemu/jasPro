<template>
   <div style="width: 100%;">
       <div>
           <h2>【{{this.jobObj.hName}}】作业的提交情况：</h2>
           <table style="width:50%;margin: 5% auto auto 20%">
               <tr>
                   <td>已交人数</td>
                   <td>{{studentCount.committedCount}}</td>
                   <td><el-button type="primary" size="medium" @click="getStudentForCommittedJob">名单</el-button></td>
               </tr>
               <tr>
                   <td>未交人数</td>
                   <td>{{studentCount.noCommittedCount}}</td>
                   <td><el-button type="primary" size="medium" @click="getStudentForNoCommittedJob">名单</el-button></td>
               </tr>
           </table>
       </div>
       <div style="width: 100%">
           <div class="photo" ref="box1" >

           </div>
           <div class="photo" ref="box2" >

           </div>
       </div>
   </div>
</template>

<script>
    import axios from "axios"
    const echarts = require('echarts/lib/echarts') // 引入echarts
    require('echarts/lib/chart/bar') // 引入柱状图
    // 引入提示框和标题组件
    require('echarts/lib/component/tooltip')
    require('echarts/lib/component/title')
    export default {
        data() {
            return {
                jobObj: null,
                studentCount:{
                    committedCount: 0,
                    noCommittedCount: 0
                },
                box1Data: [
                    {value: 0, name: '已提交'},
                    {value: 0, name: '未提交'}
                ],
                box2Data:[
                    {value: 0, name: '优秀[90,100]:'},
                    {value: 0, name: '良好[80,90):'},
                    {value: 0, name: '一般[60,80):'},
                    {value: 0, name: '较差<60:'}
                    ]
            }
        },
        created(){
            this.jobObj = this.$route.params.jobFromJobMainDiv
            // console.log(this.jobObj)
        },
        mounted(){
            var _this =this
            axios.get('http://localhost:8080/jas/mport/score/jobSubmitStatus/'+this.jobObj.hId).then(function (resp) {
                _this.studentCount = resp.data
                _this.box1Data[0].value=_this.studentCount.committedCount
                _this.box1Data[1].value=_this.studentCount.noCommittedCount
                _this.polygonal_chart1()
                // console.log(resp.data)
            })
            axios.get('http://localhost:8080/jas/mport/score/getLevelByHid/'+this.jobObj.hId).then(function (resp) {
                console.log('等级')
                console.log(resp)
                _this.box2Data[0].value=resp.data.level1
                _this.box2Data[1].value=resp.data.level2
                _this.box2Data[2].value=resp.data.level3
                _this.box2Data[3].value=resp.data.level4
                if (resp.data.level1!=0||resp.data.level2!=0||resp.data.level3!=0||resp.data.level4!=0) {
                    _this.polygonal_chart2()
                }
            })
        },
        methods: {
            getStudentForCommittedJob(){
                this.$router.push({name:'StudentList',params:{committed:'yes',job:this.jobObj}})
            },
            getStudentForNoCommittedJob(){
                this.$router.push({name:'StudentList',params:{committed:'no',job:this.jobObj}})
            },
            // 作业提交比例图
            polygonal_chart1(){
                var myChart = echarts.init(this.$refs.box1);
                var option = {
                    title: {
                        text: '作业提交比例图',
                      //  subtext: '纯属虚构',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                    },
                    series: [
                        {
                            name: '人数',
                            type: 'pie',
                            radius: '70%',
                            color:[ 'rgba(84,203,90,0.68)','rgba(219,16,26,0.71)'],
                            data: this.box1Data,
                            label: {
                                normal: {
                                    position: 'inner',
                                    show : false
                                }
                            },
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 2,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                myChart.setOption(option);
            },
            // 学生成绩比例图
            polygonal_chart2(){
                var myChart = echarts.init(this.$refs.box2);
                var option = {
                    title: {
                        text: '学生成绩比例图',
                        //  subtext: '纯属虚构',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                    },
                    series: [
                        {
                            name: '人数',
                            type: 'pie',
                            radius: ['40%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '20',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: this.box2Data,
                        }
                    ]
                };
                myChart.setOption(option);
            }
        }
    }
</script>
<style lang="scss" scoped="true">
    .photo{
        width: 30%;
        height: 330px;
        margin: 3% auto auto 10%;
        display: inline-block;

    }
    td{
        border: 1px solid black;
        width: 50px;
        text-align: center;
    }
    table {
        border-collapse: collapse;
    }
</style>