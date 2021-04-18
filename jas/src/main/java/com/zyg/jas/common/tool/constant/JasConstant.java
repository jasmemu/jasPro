package com.zyg.jas.common.tool.constant;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasConstant {
    public static final String MESSAGE_LOGIN_FAILED = "账号密码错误，请重新输入！";
    public static final String MESSAGE_ADD_CLASSES_FAILED="没有该教师编号，添加班级失败！";
    public static final String MESSAGE_ADD_CLASSES_SUCCESS="添加班级成功";

    public static  final String []  GRADE_LIST = {"大一","大二","大三","大四"};
    public static final String JAS_EMAIL_TITLE= "JAS系统验证码";

    public static final int AUTH_CODE_OUT_TIME = 180;

    public static final String DO_NOT_SUBMIT_lIST_TITLE="课程的本学期未提交作业的学生名单";
    public static final String SUBMITED_lIST_TITLE ="课程的本学期学生作业成绩单";

    public static final String DO_NOT_EXCEL_NAME ="list1.xlsx";
    public static final String SUBMITED_EXCEL_NAME ="list2.xlsx";

    public static final String NEW_YEAR_PWD_ERROR="密码错误，清空失败！";

}
