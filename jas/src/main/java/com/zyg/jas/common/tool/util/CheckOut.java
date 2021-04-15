package com.zyg.jas.common.tool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckOut  {
    public static void main(String[]args){
        String s = "100";
        System.out.println(checkIsNumber(s));
    }
    //检验是否为数字
    public static boolean checkIsNumber(String str){
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return  m.matches();
    }

    // 校验是否为空
    public static boolean checkIsNull(String str){
        String pattern = "^\\s*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
       // System.out.println(m.matches());
        return m.matches();
    }
    //校验是否符合学生学号
    public static boolean  checkIsStuNo(String str){
        String pattern = "\\d{12}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        //System.out.println(m.matches());
        return m.matches();
    }
    //校验是否符合手机号
    public static boolean  checkIsPhone(String str){
        String pattern = "0?(13|14|15|18|17)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        //System.out.println(m.matches());
        return m.matches();
    }
    //校验是否符合邮箱
    public static boolean  checkIsEmail(String str){
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        //System.out.println(m.matches());
        return m.matches();
    }
    //校验是否符合身份证号
    public static boolean  checkIsIdentify(String str){
        String pattern = "\\d{17}[\\d|x]|\\d{15}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
       // System.out.println(m.matches());
        return m.matches();
    }
    //校验是否符合yyyy/MM/dd
    public static boolean  checkIsDate(Date str) throws ParseException {
        //SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(str);
        System.out.println("格式： "+format);
        String pattern = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(format);
        return m.matches();
    }
}