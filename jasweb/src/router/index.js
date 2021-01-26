import Vue from 'vue'
import VueRouter from 'vue-router'

import copy from "@/views/managerport/copy"
import EnterPage from "@/views/EnterPage"
// 下列属于管理员端url
   // 系统管理员
import SysMainPage from "@/views/managerport/sysmodule/SysMainPage"
import StuMainDiv from "@/views/managerport/sysmodule/student/StuMainDiv"
import AddStuInfo from "@/views/managerport/sysmodule/student/AddStuInfo"
import ViewStuInfo from "@/views/managerport/sysmodule/student/ViewStuInfo"

import CourseMainDiv from "@/views/managerport/sysmodule/course/CourseMainDiv"
import AddCourseInfo from "@/views/managerport/sysmodule/course/AddCourseInfo"
import ViewCourseInfo from "@/views/managerport/sysmodule/course/ViewCourseInfo"

import TeaMainDiv from "@/views/managerport/sysmodule/teacher/TeaMainDiv"
import AddTeaInfo from "@/views/managerport/sysmodule/teacher/AddTeaInfo"
import ViewTeaInfo from "@/views/managerport/sysmodule/teacher/ViewTeaInfo"

import CmtMainDiv from "@/views/managerport/sysmodule/committee/CmtMainDiv"
import AddCmtInfo from "@/views/managerport/sysmodule/committee/AddCmtInfo"
import ViewCmtInfo from "@/views/managerport/sysmodule/committee/ViewCmtInfo"
import ViewCmtDetail from "@/views/managerport/sysmodule/committee/ViewCmtDetail"

  //下列属于管理员端url
import CmtMainPage from "@/views/managerport/cmtmodule/CmtMainPage"



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
    path: '/CmtMainPage',
    name: 'CmtMainPage',
    component: CmtMainPage
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

export default router
