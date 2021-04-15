package com.zyg.jas.common.tool.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.web.multipart.MultipartFile;


public class ExcelUtil {
//    public static void main(String[] args) {
//
//        // 此处为我创建Excel路径：E:/zhanhj/studysrc/jxl下
//        File file = new File("C:/Users/MING/Desktop/0实体信息表/学生信息.xls");
//        List<List> excelList = readExcel(file);
//        for (int i = 0; i < excelList.size(); i++) {
//            List list = (List) excelList.get(i);
//            for (int j = 0; j < list.size(); j++) {
//                System.out.print(list.get(j) + ",");
//            }
//            System.out.println();
//        }
//    }

    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public static List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList = new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList = new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if (cellinfo.isEmpty()) {
                            continue;
                        }
                        innerList.add(cellinfo);
                        // System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
                    //  System.out.println();
                }
                return outerList;
//                getData(outerList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List getData(List<List> excelList) {
        for (int i = 0; i < excelList.size(); i++) {
            List list = (List) excelList.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.println("Excel中的数据打印出来");
                System.out.print(list.get(j) + ",");
            }
            System.out.println();
        }
        return excelList;
    }


    //需要使用MultipartFileToFile工具类
    public static List getExcelData(MultipartFile multiFile){
        //调用
        File file = null;
        List excelData = null;
        try {
            //将MultipartFile转为File
            file = MultipartFileToFile.multipartFileToFile(multiFile);
           excelData = readExcel(file);
            return excelData;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MultipartFileToFile.deleteTempFile(file);
        }
        return excelData;
    }


}
