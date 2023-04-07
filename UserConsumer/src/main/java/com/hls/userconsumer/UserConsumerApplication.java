package com.hls.userconsumer;

import com.hls.Config.LogConfiguration;
import com.hls.Feign.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableFeignClients(clients = {UserFeignClient.class},defaultConfiguration = {LogConfiguration.class} )
@SpringBootApplication
@EnableHystrix
@ComponentScan({"com.hls.Feign" , "com.hls.userconsumer"})
public class UserConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class, args);
    }

}
