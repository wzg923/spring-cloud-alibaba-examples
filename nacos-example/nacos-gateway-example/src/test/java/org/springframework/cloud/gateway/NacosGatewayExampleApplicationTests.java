package org.springframework.cloud.gateway;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NacosGatewayExampleApplicationTests {
	private static final AtomicInteger atomicInteger = new AtomicInteger(1);
	private static ThreadLocal<StringBuilder> threadLocal = new ThreadLocal<StringBuilder>();
	@Test
	public void contextLoads() {
	}

	@Test
	public void test1(){
		FastDateFormat pattern = FastDateFormat.getInstance("yyyyMMddHHmmss");
		StringBuilder builder = new StringBuilder(pattern.format(System.currentTimeMillis()));// 取系统当前时间作为订单号前半部分
		String lock= StringUtils.replace(UUID.randomUUID().toString(), "-", "");
		builder.append(Math.abs(lock.hashCode()));// HASH-CODE
		builder.append(ThreadLocalRandom.current().nextInt(0,999));// 随机数
		builder.append(atomicInteger.getAndIncrement());// 自增顺序
		threadLocal.set(builder);
		String str= threadLocal.get().toString();
		System.out.println(str);
	}
}
