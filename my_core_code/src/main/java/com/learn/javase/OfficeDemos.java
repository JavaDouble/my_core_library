package com.learn.javase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

//Java对Office办公软件的读写操作的演示
/**
 * Java中操作Office的有两种比较主流的工具包： JXL 和 POI。
 * jxl只能操作Office95, 97, 2000也即以.xls为后缀的excel,.doc为后缀的word。
 * 而poi可以操作Office 95及以后的版本，即可操作后缀为 .xls 和 .xlsx两种格式的excel，.doc和.docx的word。
 *
 * POI全称 Poor Obfuscation Implementation,直译为“可怜的模糊实现”，
 * 利用POI接口可以通过JAVA操作Microsoft office 套件工具的读写功能。
 * 官网：http://poi.apache.org ，POI支持office的所有版本。
 *
 * 实际开发中，主要使用POI对Excle的读写操作
 *
 * @author Double
 *
 */
public class OfficeDemos {

	/**
	 * POI对Excel的操作 	四个基本元素：工作簿，工作表，行，单元格
	 *
	 * 一个excel文件就是一个工作簿workbook，一个工作簿中可以创建多张工作表sheet，
	 * 而一个工作表中包含多个单元格Cell，这些单元格都是由列（Column）行(Row)组成，
	 * 列用大写英文字母表示，从A开始到Z共26列，然后再从AA到AZ又26列，再从BA到BZ再26列以此类推。
	 * 行则使用数字表示，例如；A3 表示第三行第一列，E5表示第五行第五列。
	 *
	 * 四个基本元素的从属关系：
	 * 1.工作表属于工作簿，一个工作簿可以有多个工作表
	 * 2.行属于工作表，一张表可以有多行
	 * 3.单元格属于行，一行有多个单元格，单元格是由行和列确定的。
	 *
	 * 操作Excel流程：
	 * 1.创建工作簿 2.创建工作表 3.创建行 4.创建单元格
	 *
	 * 写出一个.xls格式的Excle 03级的Office的写出
	 * @throws IOException
	 *
	 */
	@Test
	public void test1() throws IOException{
		//1.创建工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//2.创建工作表 有两个重载方法：无参和有一个参数，无参就创建默认的名称的，有参就创建参数名称的.
		//指定工作表明Helloworld
		HSSFSheet sheet=workbook.createSheet("HelloWorld");
		//3、创建行；创建第3行 下标从0开始
		HSSFRow row = sheet.createRow(2);
		//4、创建单元格；创建第3行第3列 下标从0开始
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("Hello World");

		//输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("测试.xls");
		//把excel输出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	/**
	 * POI对Excel的操作 读取test3创建的Excle文档  03级的Office的读取
	 *
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		FileInputStream inputStream = new FileInputStream("测试.xls");
		//1、读取工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		//2、读取第一个工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
		//3、读取行；读取第3行
		HSSFRow row = sheet.getRow(2);
		//4、读取单元格；读取第3行第3列
		HSSFCell cell = row.getCell(2);
		System.out.println("第3行第3列单元格的内容为：" + cell.getStringCellValue());

		workbook.close();
		inputStream.close();
	}

	/**
	 * POI对Excel的操作 写出一个.xlsx格式Excle文档  07级的Office的写出
	 *
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		//1、创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//2、创建工作表
		XSSFSheet sheet = workbook.createSheet("hello world");//指定工作表名
		//3、创建行；创建第3行
		XSSFRow row = sheet.createRow(2);
		//4、创建单元格；创建第3行第3列
		XSSFCell cell = row.createCell(2);
		cell.setCellValue("Hello World");

		//输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("测试.xlsx");
		//把excel输出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	/**
	 * POI对Excel的操作 读取一个.xlsx格式Excle文档  07级的Office的读取
	 *
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException {
		FileInputStream inputStream = new FileInputStream("测试.xlsx");
		//1、读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		//2、读取第一个工作表
		XSSFSheet sheet = workbook.getSheetAt(0);
		//3、读取行；读取第3行
		XSSFRow row = sheet.getRow(2);
		//4、读取单元格；读取第3行第3列
		XSSFCell cell = row.getCell(2);
		System.out.println("第3行第3列单元格的内容为：" + cell.getStringCellValue());

		workbook.close();
		inputStream.close();
	}

	/**
	 * 读取.xls,.xlsx两个格式的Excel文档
	 *
	 * @throws IOException
	 */
	@Test
	public void test5() throws IOException {
		String fileName = "测试.xlsx";
		//判断是否excel文档 不区分大小写(?i)
		if(fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){

			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");

			FileInputStream inputStream = new FileInputStream(fileName);

			//1、读取工作簿
			Workbook workbook = is03Excel ?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			//2、读取第一个工作表
			Sheet sheet = workbook.getSheetAt(0);
			//3、读取行；读取第3行
			Row row = sheet.getRow(2);
			//4、读取单元格；读取第3行第3列
			Cell cell = row.getCell(2);
			System.out.println("第3行第3列单元格的内容为：" + cell.getStringCellValue());

			workbook.close();
			inputStream.close();
		}
	}

	/**
	 * 合并单元格(CellRangeAddress)  合并单元格对象属于工作簿，应用于工作表
	 * 样式是属于工作簿的，运用于单元格
	 * 字体是属于工作簿的，家在于样式：通用样式，应用于单元格
	 * @throws IOException
	 */
	@Test
	public void test6() throws IOException {
		//1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1、创建合并单元格对象;合并第3行的第3列到第5列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 2, 4);//起始行号，结束行号，起始列号，结束列号
		//1.2、创建单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 version3.10
		style.setAlignment(HorizontalAlignment.CENTER);//水平居中 version3.17

		//style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中 version3.10
		style.setVerticalAlignment(VerticalAlignment.CENTER);//水平居中 version3.17

		//1.3、创建字体
		HSSFFont font = workbook.createFont();
		//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体 version3.10
		font.setBold(true);//加粗字体 version3.17
		font.setFontHeightInPoints((short) 16);//设置字体大小
		//加载字体
		style.setFont(font);

		//单元格背景
		//设置背景填充模式
		//style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//version3.10
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);//version3.17
		//设置填充背景色
		//style.setFillBackgroundColor(HSSFColor.YELLOW.index);//version3.10  参数过时
		style.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
		//version3.17 HSSFColor.HSSFColorPredefined取代HSSFColor
		//设置填充前景色
		//style.setFillForegroundColor(HSSFColor.RED.index);//version3.10  参数过时
		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.RED.getIndex());

		//2、创建工作表
		HSSFSheet sheet = workbook.createSheet("Hello World");//指定工作表名
		//2.1、加载合并单元格对象
		sheet.addMergedRegion(cellRangeAddress);

		//3、创建行；创建第3行
		HSSFRow row = sheet.createRow(2);
		//4、创建单元格；创建第3行第3列
		HSSFCell cell = row.createCell(2);
		//加载样式
		cell.setCellStyle(style);
		cell.setCellValue("Hello World!");

		//输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("测试.xls");
		//把excel输出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	/**
	 * 批量操作 自动导出批量数据
	 */
	@Test
	public void test7() {

	}

	/**
	 * 批量操作 自动导入批量数据
	 *
	 * @throws IOException
	 */
	@Test
	public void test8() throws IOException {
		String fileName = "测试.xls";
		//判断是否excel文档 不区分大小写(?i)
		if(fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){

			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");

			FileInputStream inputStream = new FileInputStream(fileName);

			//1、读取工作簿
			Workbook workbook = is03Excel ?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			//2、读取第一个工作表
			Sheet sheet = workbook.getSheetAt(0);
			for(Row row :sheet){
				for(Cell cell:row){
					String stringCellValue=cell.getStringCellValue();
					System.out.println(stringCellValue+"\t");
				}
				System.out.println();
			}

			workbook.close();
			inputStream.close();
		}
	}

}
