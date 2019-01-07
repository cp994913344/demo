package com.ljs.demo.pojo.utilDomain;

import com.ljs.demo.pojo.domain.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class testBean {

    /***
     * 首先要说明的是java中是没有指针的，java中只存在值传递，只存在值传递！！！
     * 然而我们经常看到对于对象（数组，类，接口）的传递似乎有点像引用传递，可以改变对象中某个属性的值。
     * 但是不要被这个假象所蒙蔽，实际上这个传入函数的值是对象引用的拷贝，即传递的是引用的地址值，所以还是按值传递
     * @param args
     */
    public static void main(String[] args) {
        User user = new User();
        user.setId("1");
        user.setName("ljs");
        String a = testBeanMethod.changeUser(user);
        log.info("bean|{}|",user);
        System.out.println(a);
    }
}
