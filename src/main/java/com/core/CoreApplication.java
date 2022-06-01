package com.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync // 开启异步注解功能
@EnableScheduling // 开启定时任务
// 程序主入口
@SpringBootApplication
//@MapperScan("com.core.mapper")// mybatis的扫描包，@MapperScan是全局设置，不然就局部设置@Mapper
public class CoreApplication {
	// springboot应用启动
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
