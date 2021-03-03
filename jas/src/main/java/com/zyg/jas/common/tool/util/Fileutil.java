package com.zyg.jas.common.tool.util;

import java.io.File;

/**
 * 文件工具类
 */
public class Fileutil {
    public static boolean removeFile(String filePath){
        System.out.println("删除文件路径:"+filePath);
        File file = new File(filePath);
        if (file.exists()){
            return  file.delete();
        }else {
            return false;
        }
    }
}
