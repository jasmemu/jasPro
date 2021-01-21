import Vue from 'vue'
import VueRouter from 'vue-router'

import copy from "@/views/managerport/copy"
//下列属于管理员端url
import SysMainPage from "@/views/managerport/SysMainPage"
import StuMainDiv from "@/views/managerport/student/StuMainDiv"
import AddStuInfo from "@/views/managerport/student/AddStuInfo"
import ViewStuInfo from "@/views/managerport/student/ViewStuInfo"

import CourseMainDiv from "@/views/managerport/course/CourseMainDiv"
import AddCourseInfo from "@/views/managerport/course/AddCourseInfo"
import ViewCourseInfo from "@/views/managerport/course/ViewCourseInfo"

import TeaMainDiv from "@/views/managerport/teacher/TeaMainDiv"
import AddTeaInfo from "@/views/managerport/teacher/AddTeaInfo"
import ViewTeaInfo from "@/views/managerport/teacher/ViewTeaInfo"



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
