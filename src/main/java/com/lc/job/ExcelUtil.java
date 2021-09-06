package com.lc.job;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lc 7/12/21 9:14 AM
 */
public class ExcelUtil {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    /**
     * 功能描述：将sheet末位为空的行去掉，得到实际有数据的行数 输入参数：<按照参数定义顺序>
     *
     * @param HSSFSheet
     *            :Excel表单封装对象 返回值: 类型 <说明>
     * @return
     * @throws Exception
     */
    public static int filterNullRow(Sheet childSheet) {
        int rowNum = childSheet.getLastRowNum();
        int j = 1;
        // 判断末行，如果不为空，直接返回行数
        Row lastRow = childSheet.getRow(rowNum);
        if (!isNullRow(lastRow))
            return rowNum;
        // 如果末行为空，则进入循环，直到遇到不为空的为止
        for (int i = rowNum - 1; i > 0; i--) {
            Row row = childSheet.getRow(i);
            if (row == null || isNullRow(row)) {
                j++;
            } else {
                break;
            }
        }
        return rowNum - j;
    }

    /**
     * 功能描述：判断一行是否是空行，true 是 false 不是 输入参数：<按照参数定义顺序>
     *
     * @param HSSFRow
     *            :Excel表单行封装对象 返回值: 类型 <说明>
     * @return boolean
     * @throws Exception
     */
    public static boolean isNullRow(Row row) {
        if (row == null)
            return true;
        boolean nullFlag = true;
        for (int k = 0; k < row.getLastCellNum(); k++) {
            Cell cell = row.getCell((short) k);
            if (!"".equals(transferToString(cell))) {
                nullFlag = false;
                break;
            }
        }
        return nullFlag;
    }

    /**
     * 功能描述：处理cell中的值 输入参数：<按照参数定义顺序>
     *
     * @param HSSFCell
     *            :Excel表单单元格封装对象 返回值: 类型 <说明>
     * @return String
     * @throws Exception
     */
    public static String transferToString(Cell cell) {
        String transferedStr = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是date类型则 ，获取该cell的date值
                    transferedStr = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
                } else { // 纯数字
                    double cellValue = cell.getNumericCellValue();
                    transferedStr = decimalFormat.format(cellValue);
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                transferedStr = cell.getStringCellValue() + "";
                break;
            default:
                transferedStr = "";
                break;
        }
        return transferedStr;
    }

    /**
     * 获取表格单元格Cell内容
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String result = new String();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                break;
            case Cell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_BLANK:
                result = "";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

}
