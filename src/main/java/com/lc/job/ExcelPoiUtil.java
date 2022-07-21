package com.lc.job;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lc 2021/9/6 20:34
 */

public class ExcelPoiUtil {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    /**
     * 功能描述：将sheet末位为空的行去掉，得到实际有数据的行数 输入参数：<按照参数定义顺序>
     *
     *            :Excel表单封装对象 返回值: 类型 <说明>
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
     * @param row :Excel表单行封装对象 返回值: 类型 <说明>
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
     * @param cell :Excel表单单元格封装对象 返回值: 类型 <说明>
     */
    public static String transferToString(Cell cell) {
        String transferedStr;
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
     */
    public static String getCellValue(Cell cell) {
        String result;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf;
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
            default:
                result = "";
                break;
        }
        return result;
    }


}
