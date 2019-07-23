package org.jeecg.modules.demo.nacos.service;


import org.jeecg.modules.demo.nacos.service.fallback.EchoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author wangzhenguang
 * @Description <ol>
 *     <li>FeignClient组件调用内部服务，通过指定name/value服务ID，配置fallback失败处理</li>
 *     <li>FeignClient组件调用外部服务，需要通过url参数指定具体的接口地址host</li>
 * </ol>
 * @Date 14:10 2019/06/11
 * @Param
 * @return
 **/
//@FeignClient(url = "",fallback = EchoServiceFallback.class)
@FeignClient(name = "service-provider", fallback = EchoServiceFallback.class)
public interface EchoService {
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    String echo(@PathVariable("str") String str);

    @RequestMapping(value = "/divide", method = RequestMethod.GET)
    String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

    @RequestMapping(value = "/notFound", method = RequestMethod.GET)
    String notFound();
}
