package com.ljs.demo;

import com.ljs.demo.common.utils.BeanCopyUtil;
import com.ljs.demo.common.utils.JsonUtils;
import com.ljs.demo.common.utils.StringRealLengthUtils;
import com.ljs.demo.pojo.domain.Good;
import com.ljs.demo.pojo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

	@Autowired

	@Test
	public void contextLoads() {
	}

	/**
	 * 测试字符串真实长度工具类
	 */
	@Test
	public void stringRealLength(){
		String value = "刘俊生ljs";
		int a = StringRealLengthUtils.string_length(value);
		log.info("字符串真实长度|{}|",a);
		System.out.println(a);
	}

	/**
	 * 测试bean拷贝工具类,第一个参数为原始bean，第二个为待copy的bean
	 */
	@Test
	public void testBeanCopyUtil(){
		Good good1 = new Good();
		good1.setGdId(1);
		good1.setGdPrice(20);
		good1.setGdTitle("123");
		good1.setTypeId(2);
		Good good2 = new Good();
		BeanCopyUtil.copy(good1,good2);
		log.info("拷贝后的bean2|{}|",good2);
	}

	@Test
	public void testJsonUtils(){
		Good good = new Good();
		good.setGdTitle("123");
		good.setTypeId(1);
		good.setGdPrice(20);
		//String s = JsonUtils.objToString(good);
		//String s = JsonUtils.objToStringPretty(good);//格式化
		String s = "{\"gdId\":1,\"typeId\":1,\"gdPrice\":20}";
		Good user = JsonUtils.string2Obj(s,Good.class);
		log.info("json|{}|",user);
	}

	@Test
	public void beanTest(){
		User user = new User();
		user.setId("1");
		user.setName("ljs");
		String a = null;
		for (int i = 0;i<=6;i++){
			if (i>1&&i<3){
				user.setName("zll");
				a="2";
			}
			a = "2";
		}
		System.out.println(user.toString());
	}

	public void calendarTest(){
		Calendar calendar = Calendar.getInstance();
	}

	@Test
	public void aVoid(){

		String maccArray[] = {"5811","5812","5813"};
		Boolean b = ArrayUtils.contains(maccArray,"5814");
		if(!ArrayUtils.contains(maccArray,"5811")){
			System.out.println("=================");
		}
		else{
			System.out.println("-----------------");
		}

	}

	@Test
	public void bigDecimalTest(){
		BigDecimal bigDecimal = new BigDecimal(0.6);
		BigDecimal bigDecimal1 = BigDecimal.valueOf(0.6);
		System.out.println(bigDecimal + "============" + bigDecimal1);
	}

}
