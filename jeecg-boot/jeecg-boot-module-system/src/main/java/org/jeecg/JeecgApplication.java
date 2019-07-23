package org.jeecg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.alibaba.dubbo.annotation.DubboTransported;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
public class JeecgApplication {

  /**Http协议接口调用*/
  @Autowired
  @LoadBalanced
  private RestTemplate restTemplate;
  /**Dubbo协议接口调用*/
  @Bean
  @LoadBalanced
  @DubboTransported //Dubbo协议转换
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) throws UnknownHostException {
    //System.setProperty("spring.devtools.restart.enabled", "true");

    ConfigurableApplicationContext application = SpringApplication.run(JeecgApplication.class, args);
    Environment env = application.getEnvironment();
    String ip = InetAddress.getLocalHost().getHostAddress();
    String port = env.getProperty("server.port");
    String path = env.getProperty("server.servlet.context-path");
    log.info("\n----------------------------------------------------------\n\t" +
        "Application Jeecg-Boot is running! Access URLs:\n\t" +
        "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
        "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
        "swagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
        "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
        "----------------------------------------------------------");

  }
  
}