package com.core.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    // 声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_order_exchange", true, false);
    }
    // 声明队列
    @Bean
    public Queue smsFanoutQueue() {
        return new Queue("sms.fanout.queue", true);
    }
    @Bean
    public Queue duanxinFanoutQueue() {
        return new Queue("duanxin.fanout.queue", true);
    }
    @Bean
    public Queue emailFanoutQueue() {
        return new Queue("email.fanout.queue", true);
    }
    // 队列和交换机完成绑定关系
    @Bean
    public Binding smsFanoutBangding() {
        return BindingBuilder.bind(smsFanoutQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding duanxinFanoutBangding() {
        return BindingBuilder.bind(duanxinFanoutQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding emailFanoutBangding() {
        return BindingBuilder.bind(emailFanoutQueue()).to(fanoutExchange());
    }


    // 声明注册direct模式的交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct_order_exchange", true, false);
    }
    // 声明队列
    @Bean
    public Queue smsDirectQueue() {
        return new Queue("sms.direct.queue", true);
    }
    // 队列和交换机完成绑定关系
    @Bean
    public Binding smsDirectBangding() {
        // direct路由模式，需要绑定路由key
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }
}
