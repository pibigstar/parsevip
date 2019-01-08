package com.pibigstar.parsevip.common.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.crypto.Data;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pibigstar
 * @create 2018-12-04 16:44
 * @desc Excel工具类
 **/
public class ExcelUtil {

    /**
     * @Author:pibigstar
     * @Description: 读取Excel数据到集合中
     */
    public static <T> List<T> readExcel(String filePath, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Field[] fields = clazz.getDeclaredFields();

            Workbook workbook = null;
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else {
                workbook = new HSSFWorkbook(fis);
            }
            Sheet sheet = workbook.getSheetAt(0);
            int startNum = sheet.getFirstRowNum() + 1;//去掉表头
            int endNum = sheet.getLastRowNum();
            int colNum = fields.length; // 列数

            for (int i = startNum; i <= endNum; i++) {
                Row row = sheet.getRow(i);
                T t = clazz.newInstance();
                for (int j = 0; j < colNum; j++) {
                    Field field = fields[j];
                    Cell cell = row.getCell(j);
                    field.setAccessible(true);
                    String value = getValue(cell);
                    setValue(t, field, value);
                }
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @Author:pibigstar
     * @Description: 为字段赋值
     */
    private static <T> void setValue(T t, Field field, String value) {
        Class<?> type = field.getType();
        Object fieldValue = null;
        if (type.equals(String.class)) {
            fieldValue = value;
        } else if (type.equals(Integer.class)) {
            fieldValue = Integer.parseInt(value);
        } else if (type.equals(int.class)) {
            fieldValue = Integer.parseInt(value);
        } else if (type.equals(Boolean.class)) {
            fieldValue = Boolean.parseBoolean(value);
        } else if (type.equals(boolean.class)) {
            fieldValue = Boolean.parseBoolean(value);
        } else if (type.equals(Float.class)) {
            fieldValue = Float.parseFloat(value);
        } else if (type.equals(Double.class)) {
            fieldValue = Double.parseDouble(value);
        } else if (type.equals(Data.class)) {
            fieldValue = DateUtil.parseYYYYMMDDDate(value);
        }
        try {
            field.set(t, fieldValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author:pibigstar
     * @Description: 得到此格的值
     */
    private static String getValue(Cell cell) {
        if (cell == null) return null;
        String result = "";
        CellType cellType = cell.getCellType();
        switch (cell.getCellType()) {
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                    result = format.format(cell.getNumericCellValue());
                    return result;
                }
            case BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                return result;
            case STRING:
                return cell.getStringCellValue();
        }
        return null;
    }

    /**
     * @Author:pibigstar
     * @Description: 将集合中对象导入到Excel中
     */
    public static <T> void writeExcel(List<T> list, String outPath) {
        int size = list.size();
        if (size == 0) return;
        T t = null;
        String fileName = "";
        FileOutputStream fos = null;
        try {
            if (outPath.contains("/")) {
                fileName = outPath.substring(outPath.lastIndexOf("/") + 1, outPath.lastIndexOf("."));
            } else {
                fileName = outPath.substring(0, outPath.lastIndexOf("."));
            }
            Workbook workbook = new HSSFWorkbook();
            // 创建表单
            Sheet sheet = workbook.createSheet(fileName);
            // 设置表头
            t = list.get(0);
            Class<?> clazz = t.getClass();
            setTitle(sheet, workbook, clazz);

            // 写入内容
            for (int i = 0; i < size; i++) {
                Row row = sheet.createRow(i + 1);
                t = list.get(i);
                Field[] fs = t.getClass().getDeclaredFields();
                int colNum = fs.length;
                PropertyDescriptor pd = null;
                for (int j = 0; j < colNum; j++) {
                    Cell cell = row.createCell(j);
                    Field field = fs[j];
                    String fieldName = field.getName();
                    pd = new PropertyDescriptor(fieldName, t.getClass());
                    Method readMethod = pd.getReadMethod();
                    Object result = readMethod.invoke(t);
                    cell.setCellValue(result.toString());
                }
            }
            fos = new FileOutputStream(outPath);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author:pibigstar
     * @Description: 设置表头
     */
    private static void setTitle(Sheet sheet, Workbook workbook, Class<?> clazz) {
        Row row = sheet.createRow(0);// 第一行为表头
        //设置为居中加粗
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        Field[] fields = clazz.getDeclaredFields();
        int colNum = fields.length;
        for (int i = 0; i < colNum; i++) {
            sheet.setColumnWidth(i, 20 * 256);
            Cell cell = row.createCell(i);
            Field field = fields[i];
            cell.setCellValue(field.getName());
            cell.setCellStyle(style);
        }
    }
}
