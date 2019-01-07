package com.ljs.demo.common.exp.expImpl;

import com.ljs.demo.Service.VisitorServcie;
import com.ljs.demo.common.exp.AbstractExcelExportHandler;
import com.ljs.demo.pojo.domain.Visitor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *模型操作导出excel处理器
 */
@Component("userExportImpl")
public class UserExportImpl extends AbstractExcelExportHandler<Visitor> {

    @Autowired
    private VisitorServcie visitorServcie;

    // 每次从数据库读取500条记录
    public static final int C_QUERY_LIMIT = 500;

    @Override
    public String getFileName() {
        // 时间戳当做导出文件的名称
        return System.currentTimeMillis() + ".xls";
    }

    @Override
    public String getSheetName() {
        return "模型操作数据";
    }

    @Override
    public int getMaxRows() {
        return 60000;
    }

    @Override
    public List<String[]> getHeaderData(Visitor params) {
        List<String[]> list = new ArrayList<String[]>();
        // 列标题
        String[] headerArray = {"姓名", "邮箱", "电话", "性别", "所在城市"};
        list.add(headerArray);
        return list;
    }

    @Override
    public List<String[]> getRowData(HttpServletRequest request, HttpServletResponse response, Visitor vo,
                                     int page) {


        List<Visitor> list = this.visitorServcie.queryVisitor();

        return convert(list);
    }

    /**
     * 列表转换
     *
     * @param list
     * @return
     */
    private List<String[]> convert(List<Visitor> list) {

        if (list == null || list.isEmpty()) {
            return null;
        }

        List<String[]> result = new ArrayList<String[]>();
        // {"姓名", "邮箱", "电话", "性别", "所在城市"}
        for (Visitor entity : list) {
            String[] array = new String[]{convert(entity.getName()),convert(entity.getEmail()),convert(entity.getPhone()),convert(entity.getSex()),convert(entity.getCity())};
            result.add(array);
        }

        return result;

    }

    /**
     * 内容转换
     *
     * @param field
     * @return
     */
    private String convert(String field) {
        return StringUtils.defaultIfBlank(field, "");
    }
}
