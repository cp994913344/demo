package test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateTest {

    @Test
    public void datetest(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        log.info("当前时间是[{}]",df.format(System.currentTimeMillis()));
        log.info(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
        log.info("当前时间是[{}]",new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void numtest(){
        String a = "123";
        int b = Integer.parseInt(a);
        System.out.println(b);
    }

    //三目运算符
    @Test
    public void test1(){
        int a = 10;
        System.out.println(a>20 ? 1:2);
    }
}
