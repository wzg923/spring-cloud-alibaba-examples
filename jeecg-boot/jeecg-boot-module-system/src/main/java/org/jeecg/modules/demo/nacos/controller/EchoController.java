package org.jeecg.modules.demo.nacos.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.nacos.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EchoController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/06/12 18:48
 * Version 1.0
 **/
@Api(tags="Nacos Demo")
@RestController
@RequestMapping("/echo")
@Slf4j
@RefreshScope
public class EchoController {
    @Value("${user.name}")
    String userName;
    @Value("${user.age}")
    Integer age;

    @Autowired
    private EchoService echoService;

    @ApiOperation(value = "测试nacos配置功能", notes = "测试nacos配置功能", produces = "application/json")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        return "userName:" + userName + ",age:"+age;
    }


    @ApiOperation(value = "测试nacos服务notFound", notes = "测试nacos服务notFound", produces = "application/json")
    @RequestMapping(value = "/notFound-feign", method = RequestMethod.GET)
    public String notFound() {
        return echoService.notFound();
    }

    @ApiOperation(value = "测试nacos服务Feign调用1", notes = "测试nacos服务Feign调用1:RequestParam方式传参", produces = "application/json")
    @RequestMapping(value = "/divide-feign", method = RequestMethod.GET)
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return echoService.divide(a, b);
    }

    @ApiOperation(value = "测试nacos服务Feign调用2", notes = "测试nacos服务Feign调用2：PathVariable方式传参", produces = "application/json")
    @RequestMapping(value = "/echo-feign/{str}", method = RequestMethod.GET)
    public String feign(@PathVariable String str) {
        return echoService.echo(str);
    }

}
