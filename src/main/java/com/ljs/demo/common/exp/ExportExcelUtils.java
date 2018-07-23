package com.ljs.demo.common.exp;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ExportExcelUtils {

    /**
     * 获取HSSFWorkbook
     *
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook() {
        return new HSSFWorkbook();
    }

    /**
     * 获取sheet
     *
     * @param workbook
     * @param sheetName
     * @return
     */
    public static HSSFSheet getHSSFSheet(HSSFWorkbook workbook, String sheetName) {
        return workbook.createSheet(sheetName);
    }

    /**
     * 获取HSSFRow
     *
     * @param sheet
     * @param index
     *            行号从0开始
     * @return
     */
    public static HSSFRow getHSSFRow(HSSFSheet sheet, int index) {
        return sheet.createRow(index);
    }

    /**
     * 写入一行数据
     *
     * @param row
     * @param rowData
     */
    public static void writeRow(HSSFRow row, String[] rowData) {

        for (int i = 0; i < rowData.length; i++) {

            HSSFCell cell = row.createCell(i);

            cell.setCellValue(wrapperCellValue(rowData[i]));
        }

    }

    /**
     * 写入多行数据
     *
     * @param row
     * @rowNum 行号 从0开始
     * @param rowData
     */
    public static void writeRow(HSSFSheet sheet, int rowNum, List<String[]> rowData) {

        for (int i = 0; i < rowData.size(); i++) {

            HSSFRow row = getHSSFRow(sheet, rowNum);

            writeRow(row, rowData.get(i));

            rowNum++;

        }

    }

    /**
     * 包装单元格内容
     *
     * @param cellValue
     * @return
     */
    public static HSSFRichTextString wrapperCellValue(String cellValue) {
        return new HSSFRichTextString(cellValue);
    }

    /**
     * 导出excel并下载文件(适用于数据量少简单导出功能)
     *
     * @param response
     * @param fileName
     *            导出文件名
     * @param sheetName
     *            sheet名称
     * @param headerData
     *            excel标题行
     * @param rowData
     *            excel正文行数据
     */
    public static void download(HttpServletResponse response, String fileName, String sheetName, String[] headerData,
                                List<String[]> rowData) {

        HSSFWorkbook workbook = getHSSFWorkbook();

        HSSFSheet sheet = getHSSFSheet(workbook, sheetName);

        int rowNum = 1;

        HSSFRow row = getHSSFRow(sheet, 0);
        // 在excel表中添加表头

        writeRow(row, headerData);

        // 在表中存放查询到的数据放入对应的列
        for (String[] rowElement : rowData) {

            HSSFRow row1 = ExportExcelUtils.getHSSFRow(sheet, rowNum);

            writeRow(row1, rowElement);

            rowNum++;
        }

        download(response, workbook, fileName);
    }

    /**
     * 下载文件
     *
     * @param response
     * @param workbook
     * @param fileName
     */
    public static void download(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {

        String encodeFileName = null;

        try {
            encodeFileName = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            encodeFileName = fileName;
        }

        response.setContentType("application/octet-stream");

        response.setHeader("Content-disposition", "attachment;filename=" + encodeFileName);

        try {

            response.flushBuffer();

            workbook.write(response.getOutputStream());

        } catch (Exception ex) {
            try {
                response.getWriter().println(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
