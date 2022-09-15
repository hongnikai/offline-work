package com.lc.util.excel;

import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设置easyExcel 自适应列宽
 * @author lc 2022/9/13 3:21 PM
 */
public class CustCellWriteHandler extends AbstractColumnWidthStyleStrategy {

    private Map<Integer, Map<Integer, Integer>> CACHE = new HashMap<>();

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        boolean needSetWidth = isHead || !CollectionUtils.isEmpty(cellDataList);
        if (needSetWidth) {
            Map<Integer, Integer> maxColumWidthMap = CACHE.computeIfAbsent(writeSheetHolder.getSheetNo(), k -> new HashMap<>());
            Integer columWidth = this.dataLength(cellDataList, cell, isHead);
            if (columWidth >= 0) {
                if (columWidth > 255) {
                    columWidth = 255;
                }
                Integer maxColumWidth = maxColumWidthMap.get(cell.getColumnIndex());
                if (maxColumWidth == null || columWidth > maxColumWidth) {
                    maxColumWidthMap.put(cell.getColumnIndex(), columWidth);
                    writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columWidth * 256);
                }
            }
        }
    }

    private Integer dataLength(List<CellData> cellDataList, Cell cell, Boolean isHead) {
        if (isHead) {
            return cell.getStringCellValue().getBytes().length;
        } else {
            CellData cellData = cellDataList.get(0);
            CellDataTypeEnum type = cellData.getType();
            if (type == null) {
                return -1;
            } else {
                switch (type) {
                    case STRING:
                        return cellData.getStringValue().getBytes().length;
                    case BOOLEAN:
                        return cellData.getBooleanValue().toString().getBytes().length;
                    case NUMBER:
                        return cellData.getNumberValue().toString().getBytes().length;
                    default:
                        return -1;
                }
            }
        }
    }

}
