package org.jeecg;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Demo;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.modules.demo.mock.MockController;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.demo.test.mapper.JeecgDemoMapper;
import org.jeecg.modules.demo.test.service.IJeecgDemoService;
import org.jeecg.modules.system.service.ISysDataLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

	@Resource
	private JeecgDemoMapper jeecgDemoMapper;
	@Resource
	private IJeecgDemoService jeecgDemoService;
	@Resource
	private ISysDataLogService sysDataLogService;
	@Resource
	private MockController mock;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<JeecgDemo> userList = jeecgDemoMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}

	@Test
	public void testXmlSql() {
		System.out.println(("----- selectAll method test ------"));
		List<JeecgDemo> userList = jeecgDemoMapper.getDemoByName("Sandy12");
		userList.forEach(System.out::println);
	}

	/**
	 * 测试事务
	 */
	@Test
	public void testTran() {
		jeecgDemoService.testTran();
	}
	
	//author:lvdandan-----date：20190315---for:添加数据日志测试----
	/**
	 * 测试数据日志添加
	 */
	@Test
	public void testDataLogSave() {
		System.out.println(("----- datalog test ------"));
		String tableName = "jeecg_demo";
		String dataId = "4028ef81550c1a7901550c1cd6e70001";
		String dataContent = mock.sysDataLogJson();
		sysDataLogService.addDataLog(tableName, dataId, dataContent);
	}
	//author:lvdandan-----date：20190315---for:添加数据日志测试----

	@Test
	public void testUUID(){
		log.info(UUIDGenerator.generate());
	}

	@Test
	public void test2(){
		/*String callback_url="http://192.168.29.89:8080?phone=18563956775&id_card_num=370683199009236019&user_id=1";

		String callback_url_safe= Base64Utils.encodeToUrlSafeString(callback_url.getBytes());

		log.info(callback_url_safe);



		String str2=Base64Utils.encodeToUrlSafeString("+/OykiJD5Tx6vBbbvDwhR7cznrtQ=".getBytes());
		System.out.println(str2);

		str2 =new String(Base64Utils.decodeFromUrlSafeString(str2));
		System.out.println(str2);*/

		log.info(""+Math.random());
		for(int i=0;i<10;i++){
			log.info(""+new Random().nextInt());
		}

		log.info(""+new Random().nextLong());
	}

	@Test
	public void test3(){
		Demo demo = new Demo();
		demo.setId("1");
		demo.setName("AAA");
		demo.setAddress("BBB");
		log.info(com.alibaba.fastjson.JSON.toJSONString(demo));
		try {
			log.info(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(demo));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}




}
