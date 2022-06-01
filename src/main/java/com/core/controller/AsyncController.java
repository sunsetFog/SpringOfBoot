package com.core.controller;

import com.core.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// 异步任务
@RestController
public class AsyncController {
    @Autowired
    AsyncService asyncService;

    @GetMapping("/yibu")
    public String yibu() {
        asyncService.yibu();// 停止三秒，转圈···
        return "OK";
    }
}
