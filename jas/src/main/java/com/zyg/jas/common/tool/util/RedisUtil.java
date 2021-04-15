package com.zyg.jas.common.tool.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @Value和static一起使用时必须有@Component注解
@Component
public class RedisUtil {
    private static String HOST="192.168.229.128";
    private static Integer PWD = 123;

    private static Jedis jedis=null;

    private  RedisUtil (){}

    public static Jedis getJedis(){
       if (jedis == null){
           synchronized (RedisUtil.class){
               if (jedis == null){
                   jedis = new Jedis(HOST,PWD);
               }
           }
       }
       return jedis;
    }



}

