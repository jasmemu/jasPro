import Vue from 'vue'
import VueRouter from 'vue-router'

import copy from "@/views/managerport/copy"
import EnterPage from "@/views/EnterPage"
// 下列属于管理员端url
   // 系统管理员
import SysLogin from  "@/views/managerport/sysmodule/SysLogin"
import SysMainPage from "@/views/managerport/sysmodule/SysMainPage"
import SetSysInfo from "@/views/managerport/sysmodule/SetSysInfo"

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

  //下列属于管理员端url
import CmtLogin from "@/views/managerport/cmtmodule/CmtLogin"
import CmtMainPage from "@/views/managerport/cmtmodule/CmtMainPage"

import NoticeMainDiv from "@/views/managerport/cmtmodule/notice/NoticeMainDiv"
import AddNoticeInfo from "@/views/managerport/cmtmodule/notice/AddNoticeInfo"
import SetCmtInfo from "@/views/managerport/cmtmodule/SetCmtInfo"




//下列属于学生端url
import StudentMainPage from  "@/views/studentport/studentmodule/StudentMainPage"

Vue.use(VueRouter)
const routes = [
  {
    path: '/copy',
    name: 'copy',
    component: copy
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
    path: '/SetCmtInfo',
    name: 'SetCmtInfo',
    component: SetCmtInfo
  },
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
      }
    ]
  },
  {
    path: '/StudentMainPage',
    name: 'StudentMainPage',
    component: StudentMainPage
  },
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
      }
    ]
  },
]

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
