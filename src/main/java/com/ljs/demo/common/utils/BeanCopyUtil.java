package com.ljs.demo.common.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentHashMap;


public class BeanCopyUtil {

    static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<String, BeanCopier>();

    /**
     * 测试bean拷贝工具类,第一个参数为原始bean，第二个为待copy的bean
     */
    public static void copy(Object srcObj, Object destObj) {
        String key = genKey(srcObj.getClass(), destObj.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj, destObj, null);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + "#" + destClazz.getName();
    }

}
