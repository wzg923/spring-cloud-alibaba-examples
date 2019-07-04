package org.springframework.cloud.alibaba.cloud.examples.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EchoController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/06/11 18:01
 * Version 1.0
 **/
@RestController
@RefreshScope
public class EchoController {
    @Value("${user.name}")
    String userName;

    @Value("${user.age}")
    int age;



    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "hello Nacos Discovery " + string;
    }

    @RequestMapping(value = "/divide", method = RequestMethod.GET)
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return String.valueOf(a / b);
    }

    @RequestMapping(value = "/echo2", method = RequestMethod.GET)
    public String echo2() {
        return "userName:" + userName + ",age:"+age;
    }





}