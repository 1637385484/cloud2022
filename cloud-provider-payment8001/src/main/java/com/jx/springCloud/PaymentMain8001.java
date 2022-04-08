package com.jx.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//master合并冲突
//hot-fix合并冲突
//修改项目推送代码到远程仓库
//通过ssh推送代码
@SpringBootApplication
@EnableEurekaClient//未来将放弃eureka
@EnableDiscoveryClient//常用 hot-fix分支修改
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
