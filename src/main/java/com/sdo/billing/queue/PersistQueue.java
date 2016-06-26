/*
 * Copyright (c) 2011 Shanda Corporation. All rights reserved.
 *
 * Created on 2011-12-19.
 */
package com.sdo.billing.queue;

import java.io.IOException;

/**
 * 队列.
 * <p/>
 * 没有大小限制的, 基于内存映射的，内部提供cache, 支持持久化, 可以多个线程同时写同时读.
 * <p/>
 * 正常, cache命中的情况下：只需写一次文件，不读文件，消息都在内存中通过cache传递.
 * 异常，读很慢写很快， cache不命中的情况下： 消息需要写和读各一次.
 *
 * @author ranpengfei
 */
public interface PersistQueue {

    /**
     * 初始化.
     */
    void init() throws IOException;

    /**
     * 关闭.
     */
    void close();

    /**
     * 清理队列, 删除不用的数据文件.
     */
    void purge() throws IOException;

    /**
     * 写入消息.
     */
    void put(String s) throws IOException;

    /**
     * 写入消息.
     */
    void put(byte[] bs) throws IOException;

    /**
     * 一直等待直到获取下一条消息位置.
     */
    long get() throws IOException, InterruptedException;

    /**
     * 获取下一条消息位置.
     * <p/>
     * wait=-1 一直等待
     * wait=0 不等待
     * wait>0 等待wait毫秒
     * <p/>
     * 返回-1表示超时未找到.
     */
    long get(long wait) throws IOException, InterruptedException;

    /**
     * 获取消息内容.
     */
    String getString(long idx) throws IOException;

    /**
     * 获取消息内容.
     */
    byte[] getBytes(long idx) throws IOException;

    /**
     * 提交消息.
     */
    void commit(long idx);

    /**
     * 回退消息.
     */
    void rollback(long idx);
    
    /**
     * 队列是否为空.
     * 
     */
    public boolean empty();

    /**
     * 写入消息并返回索引.
     */
    long putAndReturnIdx(String s) throws IOException;

    /**
     * 写入消息并返回索引.
     */
    long putAndReturnIdx(byte[] bs) throws IOException;
    
    /**
     * 队列大小.
     * 
     */
    public int size();

    /**
     * 缓存大小.
     * 
     */
    public int cacheSize();
}
