package com.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/*
lombok之日志

*/
@Slf4j// 定义日志变量---方便
@RestController
@CrossOrigin
public class LombokOfSlf4j {
    // 定义日志变量---不方便
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/journal")
    public String toJournal() {
        logger.info("四甲基");
        log.info("单精度季度奖金");
        log.info("占位日志--[{}]--", "可考虑");
        log.debug("开始校验[操作权限]");
        return "Are you OK?";
    }

}
