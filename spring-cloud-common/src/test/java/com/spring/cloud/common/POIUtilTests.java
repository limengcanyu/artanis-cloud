package com.spring.cloud.common;

import com.spring.cloud.common.constant.POIConst;
import com.spring.cloud.common.util.POIUtil;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

public class POIUtilTests {
    @Test
    public void test() {
        Workbook workbook = POIUtil.createWorkbook();
        Sheet sheet = POIUtil.createSheet(workbook, "示例");
        Row row = sheet.createRow(0);

        CellStyle cellStyle = POIUtil.createCellStyle(workbook);
        cellStyle.setAlignment(HorizontalAlignment.GENERAL);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        POIUtil.createStringCell(workbook, row, 0, "sample1", cellStyle);

        cellStyle = POIUtil.createCellStyle(workbook);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
        POIUtil.createStringCell(workbook, row, 0, "sample2", cellStyle);

        POIUtil.writeFile(workbook, "示例" + POIConst.XSSF_file_suffix);
    }
}
