package com.zyg.jas.common.tool.util;

import com.zyg.jas.common.pojo.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.Region;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by dell on 2018/5/3.
 */
@Component
public class CreateExcel {
    public static String AUTO_CREATE_EXCEL;
    private static String AUTO_CREATE_EXCEL_URL;
    @Value("${autoCreateExcel}")
    public void setLoginUrl(String autoCreateExcel) {
        AUTO_CREATE_EXCEL = autoCreateExcel;
    }
    @Value("${autoCreateExcelUrl}")
    public void  setAutoCreateExcel(String autoCreateExcelUrl){
        AUTO_CREATE_EXCEL_URL = autoCreateExcelUrl;
    }
    public static String getExcelFile(String fileName,String excelTitle, List<Student> studentList){
        CreateExcel createExcel = new CreateExcel();
        try {
            createExcel.createExcelTop(fileName,excelTitle,studentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("文件路径："+AUTO_CREATE_EXCEL);
        System.out.println("访问路径："+AUTO_CREATE_EXCEL_URL);
        return AUTO_CREATE_EXCEL_URL+fileName;

    }

    //创建excel的表头,设置字体及字体颜色，设置单元格填充色
    public void createExcelTop (String fileName,String excelTitle,List<Student> studentList) throws Exception{
        String path =AUTO_CREATE_EXCEL+ fileName;
        System.out.println("path 是"+ path);
        //创建Excel对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //Excel样式
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        //居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //Excel字体设置
        HSSFFont hssfFont = hssfWorkbook.createFont();
        hssfFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        hssfFont.setColor(HSSFColor.RED.index);
        style.setFont(hssfFont);
        //创建sheet对象
        Sheet sheet = hssfWorkbook.createSheet();
        //创建row
        Row row = sheet.createRow(1);
        //创建cell
        Cell cell = row.createCell(0);
        //设置cell的值
        cell.setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("作业名称");
        row.createCell(3).setCellValue("发布日期");
        row.createCell(4).setCellValue("分数");

        int row2Num=2;
        for (int i = 0;i<studentList.size();i++){
            for (int j=0;j<studentList.get(i).getScores().size();j++){
                Row row2 = sheet.createRow(row2Num);
                row2.createCell(0).setCellValue(studentList.get(i).getsNo());
                Cell cell2 = row2.createCell(1);
                cell2.setCellValue(studentList.get(i).getName());
                row2.createCell(2).setCellValue(studentList.get(i).getScores().get(j).getHomework().gethName());
                row2.createCell(3).setCellValue(studentList.get(i).getScores().get(j).getHomework().getPublishDate().toString());
                if (studentList.get(i).getScores().get(j).getScore()!=null){
                    row2.createCell(4).setCellValue(studentList.get(i).getScores().get(j).getScore());
                }else {
                    row2.createCell(4).setCellValue("未交");
                }
                row2Num++;
            }
        }
        //设置标题，以及设置单元格的样式，有些样式只对单元格有效
        Row row5 = sheet.createRow(0);
        Cell cell1 = row5.createCell(0);
        cell1.setCellValue(excelTitle);
        cell1.setCellStyle(style);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        //row5.createCell(3).setCellValue();

        //输出Excel对象
        FileOutputStream output = new FileOutputStream(path);
        hssfWorkbook.write(output);
        output.flush();
        output.close();
    }

    public static  void main(String[] args){
        String str ="201708044201-54";
        String [] sNoAndHid=str.split("-");
        System.out.println(sNoAndHid[0]);
        System.out.println(sNoAndHid[1]);
    }


}
