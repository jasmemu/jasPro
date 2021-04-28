package com.zyg.jas.studentport.dao;

import com.zyg.jas.common.pojo.Student;

public interface StuDao {
    Student selectStudentById(String sNo);//学生端登录查询使用
    Student selectStudentByIdWith(String id);//根据id查询一条记录，联合查询
    Integer updateStudentPersonal(Student student);//在学生端改变自己可以改变的信息
    public int updateStudentById(Student student);//根据id更新一条记录
    //Course getCourseByComid(String comid);//根据学委id查询公告
    //通过comid查询公告
//    List<Notice> selectAllNotice(String cmtId);
//    List<Notice> selectForSearch(Map map);
//    Integer selectTotal(String cmtId);
//    Notice selectByIdWith(Integer id);
//    //获取学生的课程list
//    List<Course> getMyCourseById(String sNo);


//通过学生的id查询grade,class,spe_id，再查询对应committee的对应的学委的comid用来插入一条message，返回结果为comid


}
