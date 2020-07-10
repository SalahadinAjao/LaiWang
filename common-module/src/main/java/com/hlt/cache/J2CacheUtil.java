package com.hlt.cache;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

/**
 * @Author: houlintao
 * @Date:2020/7/10 上午8:28
 * @email 437547058@qq.com
 * @Version 1.0
 * Java 两级缓存框架，可以让应用支持两级缓存框架 ehcache(Caffeine) + redis
 * 避免完全使用独立缓存系统所带来的网络IO开销问题
 */
public class J2CacheUtil {

    /**
     * 业务缓存标签
     */
    public static String LAIWANG_CACHE_NAME = "LaiWangCache";
    //静态获取缓存channel的对象
    private static CacheChannel cacheChannel = J2Cache.getChannel();
    //写入缓存
    public static void put(String cacheName, String key, Object value){
        cacheChannel.set(cacheName, key, value);
    }
    //从缓存中获取
    public static Object get(String cacheName, String key){
        return cacheChannel.get(cacheName, key).getValue();
    }
    //将对象从缓存中删除
    public static void remove(String cacheName, String key){
        cacheChannel.evict(cacheName,key);
    }


}
