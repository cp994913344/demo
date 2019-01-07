package com.ljs.demo.common.exp;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

/**
 * excel导出抽象类(针对xls类型的excel)
 * @param <T>
 */
public abstract  class  AbstractExcelExportHandler<T> implements ExcelExportHandler<T> {
    /**
     * 导出excel处理
     *
     * @param request
     * @param response
     * @param params
     *            前端传入的bean对象参数
     *
     */
    public void export(HttpServletRequest request, HttpServletResponse response, T params) {

        /**
         * 文件名称
         */
        String fileName = getFileName();

        /**
         * sheet名称
         */
        String sheetName = getSheetName();

        /**
         * 行号从0开始
         */
        int rowNum = 0;

        /**
         * 页码从1开始
         */
        int page = 1;

        /**
         * sheet存储的最大行数
         */
        int maxRows = getMaxRows();

        HSSFWorkbook workbook = ExportExcelUtils.getHSSFWorkbook();

        HSSFRow row = null;

        // 获取列标题
        List<String[]> headers = getHeaderData(params);

        // 获取sheet
        HSSFSheet sheet = ExportExcelUtils.getHSSFSheet(workbook, sheetName + new Random().nextInt(1000));

        // excel写入列标题
        if (headers != null && headers.size() > 0) {

            for (String[] header : headers) {

                row = ExportExcelUtils.getHSSFRow(sheet, rowNum);

                ExportExcelUtils.writeRow(row, header);

                rowNum++;

            }

        }

        // 获取第一页正文数据列表
        List<String[]> list = getRowData(request, response, params, page);

        // 若没有数据则退出
        if (list == null || list.size() <= 0) {
            return;
        }

        // 遍历
        while (list != null && list.size() > 0) {

            // 页码先+1,上面已经查过第一页的数据了所以+1
            page++;

            // 若sheet写入超过设置的最大行数比如(65000)条记录则重新创建一个sheet
            if (rowNum > maxRows) {

                // excel行号重置为0
                rowNum = 0;

                // 获取新的sheet
                sheet = ExportExcelUtils.getHSSFSheet(workbook, sheetName + new Random().nextInt(1000));

                // 新sheet写入列标题
                if (headers != null && headers.size() > 0) {

                    for (String[] header : headers) {

                        row = ExportExcelUtils.getHSSFRow(sheet, rowNum);

                        ExportExcelUtils.writeRow(row, header);

                        rowNum++;

                    }

                }

            }

            // 取出的行数
            int size = list.size();

            // 写入数据
            ExportExcelUtils.writeRow(sheet, rowNum, list);

            // 叠加
            rowNum += size;

            // 查询下一页数据
            list = getRowData(request, response, params, page);

        }

        ExportExcelUtils.download(response, workbook, fileName);

    }

    /**
     * excel文件名称 比如用户数据.xls
     *
     * @return
     */
    public abstract String getFileName();

    /**
     * excel sheet名称
     *
     * @return
     */
    public abstract String getSheetName();

    /**
     * sheet保存的总行数 比如65536
     *
     * @return
     */
    public abstract int getMaxRows();

    /**
     * excel列标题数据可以为一行或多行
     *
     * @param params
     *            前端传入的bean对象参数
     * @return
     */
    public abstract List<String[]> getHeaderData(T params);

    /**
     * excel内容行数据
     *
     * @param request
     * @param response
     * @param params
     *            前端传入的bean对象参数
     * @param page
     *            页码从1开始
     * @return
     */
    public abstract List<String[]> getRowData(HttpServletRequest request, HttpServletResponse response, T params,
                                              int page);

}
