package org.springframework.cloud.alibaba.cloud.examples.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/07/04 8:44
 * Version 1.0
 **/
@RestController
@RefreshScope
public class HelloController {
    @Value("${user.name}")
    String userName;


    @RequestMapping(value = "/hello/{string}", method = RequestMethod.GET)
    public String hello(@PathVariable String string) {
        return "Hello " + string + "!I am "+userName+".";
    }

    @RequestMapping(value = "/sleep/{time}")
    public String sleep(@PathVariable Long  time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
