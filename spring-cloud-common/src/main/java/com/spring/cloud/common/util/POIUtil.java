package com.spring.cloud.common.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class POIUtil {
    public static Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

    public static void writeFile(Workbook wb, String fileName) {
        try (OutputStream fileOut = new FileOutputStream(fileName)) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Sheet createSheet(Workbook wb, String sheetName) {
        // You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
        // for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
        String safeName = WorkbookUtil.createSafeSheetName(sheetName); // returns " O'Brien's sales   "
        return wb.createSheet(safeName);
    }

    /**
     * sample:
     * alignment options
     * ================================================================================================
     *     Row row = sheet.createRow(2);
     *     row.setHeightInPoints(30);
     *     createCell(wb, row, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
     *     createCell(wb, row, 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
     *     createCell(wb, row, 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
     *     createCell(wb, row, 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
     *     createCell(wb, row, 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
     *     createCell(wb, row, 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
     *     createCell(wb, row, 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
     *
     * borders
     * ================================================================================================
     *     // Style the cell with borders all around.
     *     CellStyle style = wb.createCellStyle();
     *     style.setBorderBottom(BorderStyle.THIN);
     *     style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
     *     style.setBorderLeft(BorderStyle.THIN);
     *     style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
     *     style.setBorderRight(BorderStyle.THIN);
     *     style.setRightBorderColor(IndexedColors.BLUE.getIndex());
     *     style.setBorderTop(BorderStyle.MEDIUM_DASHED);
     *     style.setTopBorderColor(IndexedColors.BLACK.getIndex());
     *
     * @param wb
     * @return
     */
    public static CellStyle createCellStyle(Workbook wb) {
        return wb.createCellStyle();
    }

    public static void createStringCell(Workbook wb, Row row, int column, String cellValue, CellStyle cellStyle) {
        Cell cell = row.createCell(column);
        cell.setCellValue(cellValue);
        cell.setCellStyle(cellStyle);
    }

    public static void createDateCell(Workbook wb, Row row, int column, Date date, String format) {
        CreationHelper createHelper = wb.getCreationHelper();

        // we style the second cell as a date (and time).  It is important to
        // create a new cell style from the workbook otherwise you can end up
        // modifying the built in style and effecting not only this cell but other cells.
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(format));

        Cell cell = row.createCell(column);
        cell.setCellValue(date);
        cell.setCellStyle(cellStyle);
    }

}
