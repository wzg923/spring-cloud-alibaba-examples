package org.springframework.cloud.alibaba.cloud.examples.service.fallback;

import org.springframework.cloud.alibaba.cloud.examples.service.EchoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName EchoServiceFallback
 * @Description TODO
 * @Author Administrator
 * @Date 2019/06/11 14:01
 * Version 1.0
 **/
@Service
public class EchoServiceFallback implements EchoService {
    @Override
    public String echo(@PathVariable("str") String str) {
        return "echo fallback";
    }

    @Override
    public String divide(@RequestParam Integer a, @RequestParam Integer b) {
        return "divide fallback";
    }

    @Override
    public String notFound() {
        return "notFound fallback";
    }
}
