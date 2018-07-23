package com.ljs.demo.common.exp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * excel导出接口处理器(针对xls类型的excel)
 * @param <T>
 */
public interface ExcelExportHandler<T> {

    /**
     * 导出excel处理接口
     * @param request
     * @param response
     * @param params
     */
    public void export(HttpServletRequest request, HttpServletResponse response, T params);
}
