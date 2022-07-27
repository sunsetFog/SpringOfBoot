package com.core.controller;

import com.core.common.util.ExcelUtil;
import com.core.pojo.ExcelStudent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    导入和导出
*/
@RestController
public class ExcelController {
    @GetMapping("/excel/file")
    @ResponseBody
    public void excelFile(HttpServletResponse response) throws IOException {
        // 模拟数据
        List<ExcelStudent> list = new ArrayList<>();
        list.add(new ExcelStudent("小明", "001"));
        list.add(new ExcelStudent("小红", "002"));
        // 设置返回输出流excel格式
        response.setContentType("application/vnd.ms-excel");
        // 设置返回输出流的附件名字
        response.setHeader("Content-Disposition", "attachment; filename=" + "test.xlsx");
        // 调用工具类
        ExcelUtil.writerExcel(response, list);
    }
}
