package com.zyg.jas.common.tool.util;

import java.io.File;

/**
 * 文件工具类
 */
public class Fileutil {
    public static void main(String[] args){
        delAllFile("D:\\DevelopDir\\developbase\\tomcat8081\\apache-tomcat-9.0.43\\webapps\\ROOT\\image00");
    }


    public static boolean removeFile(String filePath){
        System.out.println("删除文件路径:"+filePath);
        File file = new File(filePath);
        if (file.exists()){
            return  file.delete();
        }else {
            return false;
        }
    }


    //删除指定文件夹下的所有文件

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }
}
