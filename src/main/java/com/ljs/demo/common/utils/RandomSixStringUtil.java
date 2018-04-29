package com.ljs.demo.common.utils;

public class RandomSixStringUtil {

    public static String allString = "1234567890qwertyuiopasdfghjklzxcvbnbm";

    public static String getRandomSix(){
        String randomsix = "";
        for(int i=0;i<6;i++){
            int num = (int)(Math.random()*allString.length());
            randomsix += allString.charAt(num);
        }
        return randomsix;
    }






}
