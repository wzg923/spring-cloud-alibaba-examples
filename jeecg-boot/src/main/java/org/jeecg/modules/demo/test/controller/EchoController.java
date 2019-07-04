package org.jeecg.modules.demo.test.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EchoController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/06/12 18:48
 * Version 1.0
 **/
@RestController
@RequestMapping("/echo")
@Slf4j
@RefreshScope
public class EchoController {
    @Value("${user.name}")
    String userName;
    @Value("${user.age}")
    Integer age;

    @ApiOperation(value = "/echo/test1", notes = "测试nacos配置功能", produces = "application/json")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        return "userName:" + userName + ",age:"+age;
    }
}
