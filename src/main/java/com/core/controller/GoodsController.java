package com.core.controller;

import com.core.mapper.GoodsMapper;
import com.core.pojo.Goods;
import com.core.pojo.PageParams;
import com.core.service.PageService;
import com.core.service.UploadService;
import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
/*
    Mybatis操作Mysql
    报错：Cause: java.sql.SQLSyntaxErrorException: Table 'db_news.***' doesn't exist
    pojo文件名要与Mysql表名一致
*/
@RestController// 等于@Controller + @ResponseBody
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private PageService pageService;
    @Autowired
    private UploadService uploadService;
    // 分页查询表所有数据与模糊搜索
    @PostMapping("/shop/list")
    public ResponseData shopList(@RequestBody PageParams pageParams, HttpServletRequest request) {
        // 这是分页data里的数据
        pageParams.setName('%'+pageParams.getName()+'%');
        System.out.println("--pageParams--"+ pageParams);
        PageResult pageResult = pageService.queryPage(pageParams, request);

        return ResponseDataUtil.buildSuccess(pageResult);
    }
    // 上传图片，修改imgUrl字段
    @ResponseBody
    @PostMapping("/shop/upload")
    public <T> ResponseData updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
//        Object picrureModify = uploadService.picrureModify(avatorFile, id);
        HashMap<String, String> Sites = uploadService.picrureModify(avatorFile);
        System.out.println("--picrureModify--"+Sites);
        // 设置实体类
        Goods goods = new Goods();
        goods.setId(id);
        goods.setImgUrl(Sites.get("avator"));
        // sql语句操作
        int res = goodsMapper.uploadGoods(goods);
        System.out.println("--res--"+res);
//        return ResponseDataUtil.buildSuccess("700", "OKOK", Sites.get("avator"));
        return ResponseDataUtil.buildSuccess(Sites.get("avator"));
    }
    // 添加
//    @ResponseBody
    @PostMapping("/shop/add")
    public ResponseData addGoods(@RequestBody Goods goods) {
        System.out.println("----12------"+goods);
        // 处理日期格式
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date isDate = new Date();
//        try {
//            isDate = dateFormat.parse(date);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        // 设置实体类
        goods.setCreate_time(new Date());
        // sql语句操作
        int res = goodsMapper.insertSelective(goods);
        System.out.println("--res--"+res);
        return ResponseDataUtil.buildSuccess("200", "添加成功");
    }
    // 查询指定单条数据
    @GetMapping("/shop/strip")
    public ResponseData shopStrip(@RequestParam("id") int id) {
        System.out.println("--id--"+id);
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return ResponseDataUtil.buildSuccess(goods);
    }
    // 删除
    @GetMapping("/shop/delete")
    public ResponseData shopDelete(@RequestParam("id") int id) {
        System.out.println("--id--"+id);
        int res = goodsMapper.deleteGoods(id);
        System.out.println("--res--"+res);
        return ResponseDataUtil.buildSuccess("200", "删除成功");
    }
    // 编辑修改
    @ResponseBody
    @RequestMapping(value = "/shop/update", method = RequestMethod.POST)
    public ResponseData shopUpdate(@RequestBody Goods goods) {
        goods.setUpdate_time(new Date());
        System.out.println("--goods--"+goods);
        // sql语句操作
        int res = goodsMapper.updateGoods(goods);
        System.out.println("--res--"+res);
        return ResponseDataUtil.buildSuccess("200", "修改成功");
    }
}
