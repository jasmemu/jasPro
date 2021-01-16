import Vue from 'vue'
import VueRouter from 'vue-router'

import copy from "@/views/managerport/copy"
//下列属于管理员端url
import SysMainPage from "@/views/managerport/SysMainPage"
import StuManage from "@/views/managerport/student/StuManage"
import AddStuInfo from "@/views/managerport/student/AddStuInfo"
import ViewStuInfo from "@/views/managerport/student/ViewStuInfo"


import TeaInfoCheck from "@/views/managerport/teacher/TeaInfoCheck"
import ImportCourseInfo from "@/views/managerport/course/ImportCourseInfo"
import CourseInfoCheck from "@/views/managerport/course/CourseInfoCheck"
//下列属于学生端url

Vue.use(VueRouter)

const routes = [
  {
    path: '/copy',
    name: 'copy',
    component: copy
  },
  {
    path: '/SysMainPage',
    name: 'SysMainPage',
    component: SysMainPage,
    children: [
      {
        path: '/SysMainPage/StuManage',
        name: 'StuManage',
        component: StuManage
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
        path: '/SysMainPage/TeaInfoCheck',
        name: 'TeaInfoCheck',
        component: TeaInfoCheck
      },
      {
        path: '/SysMainPage/ImportCourseInfo',
        name: 'ImporCourseInfo',
        component: ImportCourseInfo
      },
      {
        path: '/SysMainPage/CourseInfoCheck',
        name: 'CourseInfoCheck',
        component: CourseInfoCheck
      },
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

export default router
