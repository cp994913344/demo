package com.ljs.demo.pojo.utilDomain;

import com.ljs.demo.pojo.domain.User;

public class testBeanMethod {

    public static String changeUser(User user) {

        if (true) {
            user.setName("zll");
        }
        return "123";

    }
}
