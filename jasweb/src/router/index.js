import Vue from 'vue'
import VueRouter from 'vue-router'

import Demo from "@/views/managerport/sysmodule/Demo"
import EnterPage from "@/views/EnterPage"
// 下列属于管理员端url
   // 系统管理员
import SysLogin from  "@/views/managerport/sysmodule/SysLogin"
import SysMainPage from "@/views/managerport/sysmodule/SysMainPage"
import SetSysInfo from "@/views/managerport/sysmodule/SetSysInfo"
import DownloadExcel from "@/views/managerport/sysmodule/DownloadExcel"

import StuMainDiv from "@/views/managerport/sysmodule/student/StuMainDiv"
import AddStuInfo from "@/views/managerport/sysmodule/student/AddStuInfo"
import ViewStuInfo from "@/views/managerport/sysmodule/student/ViewStuInfo"
import ViewStuDetail from "@/views/managerport/sysmodule/student/ViewStuDetail"

import CourseMainDiv from "@/views/managerport/sysmodule/course/CourseMainDiv"
import AddCourseInfo from "@/views/managerport/sysmodule/course/AddCourseInfo"
import ViewCourseInfo from "@/views/managerport/sysmodule/course/ViewCourseInfo"

import TeaMainDiv from "@/views/managerport/sysmodule/teacher/TeaMainDiv"
import AddTeaInfo from "@/views/managerport/sysmodule/teacher/AddTeaInfo"
import ViewTeaInfo from "@/views/managerport/sysmodule/teacher/ViewTeaInfo"
import ViewTeaDetail from "@/views/managerport/sysmodule/teacher/ViewTeaDetail"

import CmtMainDiv from "@/views/managerport/sysmodule/committee/CmtMainDiv"
import AddCmtInfo from "@/views/managerport/sysmodule/committee/AddCmtInfo"
import ViewCmtInfo from "@/views/managerport/sysmodule/committee/ViewCmtInfo"
import ViewCmtDetail from "@/views/managerport/sysmodule/committee/ViewCmtDetail"

import ClassMainDiv from "@/views/managerport/sysmodule/classes/ClassMainDiv"
import AddClassInfo from  "@/views/managerport/sysmodule/classes/AddClassInfo"
import ViewClassesInfo from  "@/views/managerport/sysmodule/classes/ViewClassesInfo"
import ViewStuInfoByClasses from "@/views/managerport/sysmodule/classes/ViewStuInfoByClasses"

import SpecialtyMainDiv from "@/views/managerport/sysmodule/specialty/SpecialtyMainDiv"
import AddSpecialtyInfo from "@/views/managerport/sysmodule/specialty/AddSpecialtyInfo"

  //下列属于课程管理员端url
import CmtLogin from "@/views/managerport/cmtmodule/CmtLogin"
import CmtMainPage from "@/views/managerport/cmtmodule/CmtMainPage"
import SetCmtInfo from "@/views/managerport/cmtmodule/SetCmtInfo"

import NoticeMainDiv from "@/views/managerport/cmtmodule/notice/NoticeMainDiv"
import AddNoticeInfo from "@/views/managerport/cmtmodule/notice/AddNoticeInfo"
import ViewNotice from "@/views/managerport/cmtmodule/notice/ViewNotice"
import ViewNoticeDetail from "@/views/managerport/cmtmodule/notice/ViewNoticeDetail"

import ResourceMainDiv from "@/views/managerport/cmtmodule/resource/ResourceMainDiv"
import AddResourceInfo from "@/views/managerport/cmtmodule/resource/AddResourceInfo"
import ViewResource from "@/views/managerport/cmtmodule/resource/ViewResource"

import MessageMainDiv from "@/views/managerport/cmtmodule/leavemessage/MessageMainDiv"
import ViewMessageDetail from "@/views/managerport/cmtmodule/leavemessage/ViewMessageDetail"
import ViewMessage from "@/views/managerport/cmtmodule/leavemessage/ViewMessage"

import JobMainDiv from "@/views/managerport/cmtmodule/publishjob/JobMainDiv"
import AddJobInfo from "@/views/managerport/cmtmodule/publishjob/AddJobInfo"
import ViewJob from "@/views/managerport/cmtmodule/publishjob/ViewJob"
import JobSubmitDetail from "@/views/managerport/cmtmodule/publishjob/JobSubmitDetail"
import StudentList from "@/views/managerport/cmtmodule/publishjob/StudentList"

import CheckJobMainDiv from "@/views/managerport/cmtmodule/checkjob/CheckJobMainDiv"
import ViewCheckJob from "@/views/managerport/cmtmodule/checkjob/ViewCheckJob"
import JobTypeDiv from "@/views/managerport/cmtmodule/checkjob/JobTypeDiv"
import ViewCheckJobDetail from "@/views/managerport/cmtmodule/checkjob/ViewCheckJobDetail"

import FindBackPWD  from "@/views/managerport/cmtmodule/FindBackPWD"
import FillEmail from    "@/components/managerport/FillEmail"
import ResetPWD from     "@/components/managerport/ResetPWD"

import Statistics from "@/views/managerport/cmtmodule/Statistics"

//下列属于学生端url
import StudentMainPage from  "@/views/studentport/studentmodule/StudentMainPage"

Vue.use(VueRouter)
const routes = [
  {
    path: '/FindBackPWD',
    name: 'FindBackPWD ',
    component: FindBackPWD,
    children:[
      {
        path: '/FindBackPWD/FillEmail',
        name: 'FillEmail',
        component: FillEmail
      },
      {
        path: '/FindBackPWD/ResetPWD',
        name: 'ResetPWD',
        component: ResetPWD
      }
    ]
  },

  {
    path: '/demo',
    name: 'Demo',
    component: Demo
  },
  {
    path: '*',
    redirect: '/enter'
  },
  {
    path: '/enter',
    name: 'EnterPage',
    component: EnterPage
  },
  {
    path: '/SysLogin',
    name: 'SysLogin',
    component: SysLogin
  },
  {
    path: '/CmtLogin',
    name: 'CmtLogin',
    component: CmtLogin
  },
  {
    path: '/SetSysInfo',
    name: 'SetSysInfo',
    component: SetSysInfo
  },
  {
    path: '/DownloadExcel',
    name: 'DownloadExcel',
    component: DownloadExcel
  },
  {
    path: '/SetCmtInfo',
    name: 'SetCmtInfo',
    component: SetCmtInfo
  },
  {
    path: '/StudentMainPage',
    name: 'StudentMainPage',
    component: StudentMainPage
  },
  // 课程管理员下的路由
  {
    path: '/CmtMainPage',
    name: 'CmtMainPage',
    component: CmtMainPage,
    meta:{auth:true},  // 设置当前路由需要校验   不需要校验的路由就不用写了；
    children: [
      {
        path: '/CmtMainPage/NoticeMainDiv',
        name: 'NoticeMainDiv',
        component: NoticeMainDiv
      },
      {
        path: '/CmtMainPage/AddNoticeInfo',
        name: 'AddNoticeInfo',
        component: AddNoticeInfo
      },
      {
        path: '/CmtMainPage/ViewNotice',
        name: 'ViewNotice',
        component: ViewNotice
      },
      {
        path: '/CmtMainPage/ViewNoticeDetail',
        name: 'ViewNoticeDetail',
        component: ViewNoticeDetail
      },
      {
        path: '/CmtMainPage/ResourceMainDiv',
        name: 'ResourceMainDiv',
        component: ResourceMainDiv
      },
      {
        path: '/CmtMainPage/AddResourceInfo',
        name: 'AddResourceInfo',
        component: AddResourceInfo
      },
      {
        path: '/CmtMainPage/ViewResource',
        name: 'ViewResource',
        component: ViewResource
      },
      {
        path: '/CmtMainPage/MessageMainDiv',
        name: 'MessageMainDiv',
        component: MessageMainDiv
      },
      {
        path: '/CmtMainPage/ViewMessageDetail',
        name: 'ViewMessageDetail',
        component: ViewMessageDetail
      },
      {
        path: '/CmtMainPage/ViewMessage',
        name: 'ViewMessage',
        component: ViewMessage
      },
      {
        path: '/CmtMainPage/JobMainDiv',
        name: 'JobMainDiv',
        component: JobMainDiv
      },
      {
        path: '/CmtMainPage/AddJobInfo',
        name: 'AddJobInfo',
        component: AddJobInfo
      },
      {
        path: '/CmtMainPage/ViewJob',
        name: 'ViewJob',
        component: ViewJob
      },
      {
        path: '/CmtMainPage/JobSubmitDetail',
        name: 'JobSubmitDetail',
        component: JobSubmitDetail
      },
      {
        path: '/CmtMainPage/StudentList',
        name: 'StudentList',
        component: StudentList
      },
      {
        path: '/CmtMainPage/CheckJobMainDiv',
        name: 'CheckJobMainDiv',
        component: CheckJobMainDiv
      },
      {
        path: '/CmtMainPage/ViewCheckJob',
        name: 'ViewCheckJob',
        component: ViewCheckJob
      },
      {
        path: '/CmtMainPage/JobTypeDiv',
        name: 'JobTypeDiv',
        component: JobTypeDiv
      },
      {
        path: '/CmtMainPage/ViewCheckJobDetail',
        name: 'ViewCheckJobDetail',
        component: ViewCheckJobDetail
      },
      {
        path: '/CmtMainPage/Statistics',
        name: 'Statistics',
        component: Statistics
      }
    ]
  },
  // 系统管理员下的路由
  {
    path: '/SysMainPage',
    name: 'SysMainPage',
    component: SysMainPage,
    meta:{auth:true},  // 设置当前路由需要校验   不需要校验的路由就不用写了；
    children: [
      {
        path: '/SysMainPage/StuMainDiv',
        name: 'StuMainDiv',
        component: StuMainDiv
      },
      {
        path: '/SysMainPage/AddStuInfo',
        name: 'AddStuInfo',
        component: AddStuInfo
      },
      {
        path: '/SysMainPage/ViewStuInfo',
        name: 'ViewStuInfo',
        component: ViewStuInfo
      },
      {
        path: '/SysMainPage/ViewStuDetail',
        name: 'ViewStuDetail',
        component: ViewStuDetail
      },
      {
        path: '/SysMainPage/CourseMainDiv',
        name: 'CourseMainDiv',
        component: CourseMainDiv
      },
      {
        path: '/SysMainPage/AddCourseInfo',
        name: 'AddCourseInfo',
        component: AddCourseInfo
      },
      {
        path: '/SysMainPage/ViewCourseInfo',
        name: 'ViewCourseInfo',
        component: ViewCourseInfo
      },
      {
        path: '/SysMainPage/TeaMainDiv',
        name: 'TeaMainDiv',
        component: TeaMainDiv
      },
      {
        path: '/SysMainPage/AddTeaInfo',
        name: 'AddTeaInfo',
        component: AddTeaInfo
      },
      {
        path: '/SysMainPage/ViewTeaDetail',
        name: 'ViewTeaDetail',
        component: ViewTeaDetail
      },
      {
        path: '/SysMainPage/ViewTeaInfo',
        name: 'ViewTeaInfo',
        component: ViewTeaInfo
      },
      {
        path: '/SysMainPage/CmtMainDiv',
        name: 'CmtMainDiv',
        component: CmtMainDiv
      },
      {
        path: '/SysMainPage/AddCmtInfo',
        name: 'AddCmtInfo',
        component: AddCmtInfo
      },
      {
        path: '/SysMainPage/ViewCmtInfo',
        name: 'ViewCmtInfo',
        component: ViewCmtInfo
      },
      {
        path: '/SysMainPage/ViewCmtDetail',
        name: 'ViewCmtDetail',
        component: ViewCmtDetail
      },
      {
        path: '/SysMainPage/ClassMainDiv',
        name: 'ClassMainDiv',
        component: ClassMainDiv
      },
      {
        path: '/SysMainPage/AddClassInfo',
        name: 'AddClassInfo',
        component: AddClassInfo
      },
      {
        path: '/SysMainPage/ViewClassesInfo',
        name: 'ViewClassesInfo',
        component: ViewClassesInfo
      },
      {
        path: '/SysMainPage/ViewStuInfoByClasses',
        name: 'ViewStuInfoByClasses',
        component: ViewStuInfoByClasses
      },
      {
        path: '/SysMainPage/SpecialtyMainDiv',
        name: 'SpecialtyMainDiv',
        component: SpecialtyMainDiv
      },
      {
        path: '/SysMainPage/AddSpecialtyInfo',
        name: 'AddSpecialtyInfo',
        component: AddSpecialtyInfo
      }
    ]
  },
]

// 解决 NavigationDuplicated: Avoided redundant navigation to current location（21/4/11）
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
//end（21/4/11）

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next) => {
  if(to.matched.some( m => m.meta.auth)){
    // 对路由进行验证
    if(sessionStorage.getItem('isLogin')=='100'|| sessionStorage.getItem("cmtLogin")=='100') { // 已经登陆
      next()     // 正常跳转到你设置好的页面
    }else{
      // 未登录则跳转到登陆界面，query:{ Rurl: to.fullPath}表示把当前路由信息传递过去方便登录后跳转回来；
      next('/entry')
    }
    }else{
      next()
    }
  })




export default router
