package com.lc.util.excel;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.util.List;

/**
 * easyExcel 注释Handler
 *
 * @author lc 2022/9/13 3:21 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentCellWriteHandler extends CustCellWriteHandler {

    private String comment;//注释

    public CommentCellWriteHandler(String comment) {
        this.comment = comment;
    }

    //自定义注释方法
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList,
                                 Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if(isHead){
            Drawing drawing = writeSheetHolder.getSheet().createDrawingPatriarch();
            Comment comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, cell.getColumnIndex(), cell.getRowIndex(),
                    cell.getColumnIndex() + 5, cell.getRowIndex() + 6));
            comment.setString(new XSSFRichTextString(this.comment));
            cell.setCellComment(comment);
        }
    }


}
