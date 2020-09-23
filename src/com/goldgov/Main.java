package com.goldgov;

;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;

public class Main {
	public static void main(String[] args) throws Exception {
		/*String basePath=Main.class.getResource("").getPath();
		File writeFile=new File(basePath+"测试.xls");

		SQL2XLS sql2xls = new SQL2XLS(basePath+"sql.sql");
		Map<String, LinkedHashMap<String, Column>> tables = sql2xls.getTables();
		writeExcel(writeFile, tables);
		System.out.println("excel生成目录:"+basePath+"测试.xls");*/
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        objects.add(5);
        objects.add(5,10);
        objects.forEach(x->{
            System.out.println(String.valueOf(x));
        });
    }
	public static void writeExcel(File writeFile,Map<String, LinkedHashMap<String, Column>> tables) throws IOException{
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("数据库表结构");  
        
        sheet.setColumnWidth(0, 3700);
        sheet.setColumnWidth(1, 4300);
        sheet.setColumnWidth(2, 4600); 
        sheet.setColumnWidth(3, 3000); 
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 4100);
        
        HSSFCellStyle style=workBook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
        HSSFFont font = workBook.createFont();
        font.setFontName("宋体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font.setFontHeightInPoints((short) 12);//设置字体大小
        style.setFont(font);
        
        
        HSSFCellStyle style2=workBook.createCellStyle();
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        HSSFFont font2 = workBook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 11);//设置字体大小
        style2.setFont(font2);
        
        
		int rowNumber=0;
		for(String tableName:tables.keySet()){
			HSSFRow row = sheet.createRow(rowNumber++); 
			HSSFCell cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(tableName);
			HSSFRow row2 = sheet.createRow(rowNumber++); 
			HSSFCell c0 = row2.createCell(0, CellType.STRING);
			c0.setCellStyle(style);
			c0.setCellValue("字段说明");
			
			HSSFCell c1 = row2.createCell(1, CellType.STRING);
			c1.setCellStyle(style);
			c1.setCellValue("字段名称");
			
			HSSFCell c2 = row2.createCell(2, CellType.STRING);
			c2.setCellStyle(style);
			c2.setCellValue("数据类型");
			
			HSSFCell c3 = row2.createCell(3, CellType.STRING);
			c3.setCellStyle(style);
			c3.setCellValue("允许为空");
			
			HSSFCell c4 = row2.createCell(4, CellType.STRING);
			c4.setCellStyle(style);
			c4.setCellValue("唯一");
			
			LinkedHashMap<String, Column> linkedHashMap = tables.get(tableName);
			//写出行数据
			
			for(String key:linkedHashMap.keySet()){
				Column col=linkedHashMap.get(key);
				HSSFRow row1 = sheet.createRow(rowNumber++); 
				
				HSSFCell cc0 = row1.createCell(0, CellType.STRING);
				cc0.setCellStyle(style2);
				cc0.setCellValue(col.getColumnAnnotation());
				
				HSSFCell cc1 = row1.createCell(1, CellType.STRING);
				cc1.setCellStyle(style2);
				cc1.setCellValue(col.getColumnName());
				
				HSSFCell cc2 = row1.createCell(2, CellType.STRING);
				cc2.setCellStyle(style2);
				cc2.setCellValue(col.getColumnType());
				
				HSSFCell cc3 = row1.createCell(3, CellType.STRING);
				cc3.setCellStyle(style2);
				cc3.setCellValue(col.getNotNull()==1?"是":"否");
				
				HSSFCell cc4 = row1.createCell(4, CellType.STRING);
				cc4.setCellStyle(style2);
				cc4.setCellValue(col.getSole()==1?"是":"否");
				
			}
		}
		
		workBook.write(writeFile); 
	}
}
