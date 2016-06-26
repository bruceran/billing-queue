/*
 * Copyright (c) 2011 Shanda Corporation. All rights reserved.
 *
 * Created on 2011-12-19.
 */
package com.sdo.billing.queue;

import java.io.IOException;
import java.util.List;

/**
 * 队列管理类.
 *
 * @author ranpengfei
 */
public interface PersistQueueManager {

    /**
     * 初始化.
     */
    void init() throws IOException;

    /**
     * 关闭.
     */
    void close();

    /**
     * 关闭指定队列.
     */
    void close(String name);
    
    /**
     * 获取所有队列名.
     */
    List<String> getQueueNames();

    /**
     * 获取指定队列，没有则自动创建.
     */
    PersistQueue getQueue(String name) throws IOException;

}
