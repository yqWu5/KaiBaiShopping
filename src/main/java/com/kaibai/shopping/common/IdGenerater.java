/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.kaibai.shopping.common;

import java.security.SecureRandom;
import java.util.UUID;


public class IdGenerater {

    private static SecureRandom random = new SecureRandom();

    private static SnowflakeIdWorker singleIdWorker = new SnowflakeIdWorker(0, 0);


    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().toUpperCase();//.replaceAll("-", "");
    }

    public static String uuidStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * SnowflakeIdWorker的UUID.
     */
    public static long uuidLong()
    {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        return id;
    }

    /**
     * SnowflakeIdWorker的UUID.
     */
    public static String uuidLongStr()
    {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        return String.valueOf(id);
    }

    public static String getId()
    {
        return String.valueOf(singleIdWorker.nextId());
    }

}
