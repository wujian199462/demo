package com.example.demo.util;

import org.apache.poi.hssf.usermodel.*;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class Excle {

    Excle() {
    }

    ;

    /***
     * 创建表头
     * @param sheet
     */
    private static void createTitle(HSSFSheet sheet, String titleName[]) {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 10 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 80 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 80 * 256);

        for (int i = 0; i < titleName.length; i++) {
            row.createCell(i).setCellValue(titleName[i]);
        }


      /*  HSSFCell cell;

        cell = row.createCell(0);
        cell.setCellValue("姓名");

        cell = row.createCell(1);
        cell.setCellValue("性别");

        cell = row.createCell(2);
        cell.setCellValue("电话");

        cell = row.createCell(3);
        cell.setCellValue("家庭地址");*/

    }

    /**
     * @param list      数据列
     * @param titleName 表头
     * @param sheetName sheetName
     * @param dirPath   表路径
     * @param fileName  文件名
     * @throws Exception
     */
    public static int downloadAllClassmate(List<?> list, String titleName[], String sheetName, String dirPath, String fileName) throws Exception {
        int status = 0;  //返回0下载失败，返回1下载成功
        HSSFWorkbook workbook = new HSSFWorkbook();       //新建一个工作簿
        HSSFSheet sheet = workbook.createSheet(sheetName);//sheetName

        HSSFCellStyle style = workbook.createCellStyle();
        style.setLocked(false);
        //设置表头
        createTitle(sheet, titleName);
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        Class clazz = list.get(0).getClass();

        for (int i = 0; i < list.size(); i++) {      //遍历获取的每一行数据
            HSSFRow row = sheet.createRow(rowNum);
            int j = 0;
            //获得Object对象中的所有方法
            Object obj = list.get(i);
            Field[] fields = obj.getClass().getDeclaredFields();//获得属性
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();//获得get方法
                getMethod.invoke(obj);//此处为执行该Object对象的get方法
                row.createCell(j).setCellValue((String) getMethod.invoke(obj)); //利用反射调用get方法为每一个列赋值
                j++;
                // Method setMethod = pd.getWriteMethod();//获得set方法
                //setMethod.invoke(obj,"参数");//此处为执行该Object对象的set方法
            }
            rowNum++;
        }
        File f = new File(dirPath);
        if (!f.exists()) {
            f.mkdir();
        }

        File Exfile = new File(dirPath + "/" + fileName);
        Exfile.createNewFile();

        try {
            workbook.write(Exfile);//目录存放路径
            status = 1;
        } catch (Exception e) {
            status = 0;
            throw e;
        } finally {
            workbook.close();
        }
        return status;
    }
}
