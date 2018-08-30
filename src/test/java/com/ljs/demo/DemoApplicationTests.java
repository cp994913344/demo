package com.ljs.demo;

import com.ljs.demo.common.utils.StringRealLengthUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	/**
	 * 测试字符串真实长度工具类
	 */
	@Test
	public void StringRealLength(){
		String value = "刘俊生ljs";
		int a = StringRealLengthUtils.string_length(value);
		log.info("字符串真实长度|{}|",a);
		System.out.println(a);
	}

}
