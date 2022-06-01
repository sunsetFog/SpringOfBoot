package com.core;

import com.core.pojo.Cat;
import com.core.pojo.Dog;
import com.core.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class CoreApplicationTests {
	@Autowired // 写入
	private Dog dog;
	@Autowired
	private Person person;
	@Autowired
	private Cat cat;
	@Autowired
	DataSource dataSource;
	@Autowired
	JavaMailSenderImpl mailSender;
	@Autowired // 写入Redis
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() throws SQLException {
		System.out.println("测试Dog类：-------" + dog);
		System.out.println("测试Person类：-------" + person);
		System.out.println("测试Cat类：-------" + cat);

		System.out.println("查看默认数据源：-------" + dataSource.getClass());

		Connection connection = dataSource.getConnection();
		System.out.println("获得数据库连接：-------" + connection);
		// 关闭数据库连接
		connection.close();

//		 简单邮件  自动配置类：MailSenderAutoConfiguration
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		simpleMailMessage.setSubject("夕阳小月，你好呀！");
//		simpleMailMessage.setText("远方有你真好");
//		simpleMailMessage.setTo("1456300078@qq.com");
//		simpleMailMessage.setFrom("1456300078@qq.com");
//		mailSender.send(simpleMailMessage);
//		System.out.println("---邮箱发送成功---");

	}

	@Test
	void contextLoads2() throws MessagingException {
		// 一个复杂的邮件
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		// 组装 true支持多邮件发送
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		// 正文 true支持标签
//		helper.setSubject("夕阳彤彤，你好呀！");
//		helper.setText("<p style='color:red'>今晚有红烧排骨！</p>", true);
//		// 附件
//		helper.addAttachment("bill@2x.png", new File("C:\\Users\\USER\\Desktop\\sunsetFog\\66666\\core\\src\\main\\resources\\static\\img\\bill@2x.png"));
//		// 发件人和收件人
//		helper.setTo("1456300078@qq.com");
//		helper.setFrom("1456300078@qq.com");
//		// 发送
//		mailSender.send(mimeMessage);
//		System.out.println("---邮箱发送成功---");


	}

	@Test
	void contextLoads3() {
		/*
			自动配置类 RedisAutoConfiguration
		 	redisTemplate操作不同的数据类型, 事务
		 	https://blog.csdn.net/qq_40580037/article/details/107508694
		 	Redis下载安装：https://github.com/microsoftarchive/redis/releases
		*/
		//  操作字符串
		redisTemplate.opsForValue().set("honey", "自来水");
		System.out.println("redis字符串= " + redisTemplate.opsForValue().get("honey"));
		// 操作List
//		redisTemplate.opsForList();
		// 获取redis的连接对象
//		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//		connection.flushDb();
//		connection.flushAll();
	}

}
