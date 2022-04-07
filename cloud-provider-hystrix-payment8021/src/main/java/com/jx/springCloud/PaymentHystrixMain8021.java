package com.jx.springCloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServlet;

/**
 * @author LDW
 * @date 2022/4/1 21:22
 */

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class PaymentHystrixMain8021 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8021.class, args);
    }

    /**
     * 此配置是为了服务监控而配置，与服务容错本身无关，springCloud升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
     * 只要在自己的项目里配置上下面的servlet就可以了
     */
    @Bean
    public ServletRegistrationBean<HttpServlet> getServlet() {
        ServletRegistrationBean<HttpServlet> servletRegistrationBean =
                new ServletRegistrationBean<>(
                        new HystrixMetricsStreamServlet(),
                        "/hystrix.stream"
                );
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
}
