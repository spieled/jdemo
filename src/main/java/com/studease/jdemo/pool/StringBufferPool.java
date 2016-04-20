package com.studease.jdemo.pool;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Author: liushaoping
 * Date: 2016/3/30.
 */
public class StringBufferPool {

    private final int maxCount = 100;
    ObjectPool<StringBuffer> _pool = new GenericObjectPool<StringBuffer>(new StringBufferFactory());
    private AtomicInteger indexCount = new AtomicInteger(0);
    private List<StringBuffer> _list = new LinkedList<>();
    private Iterator<StringBuffer> iterator = _list.iterator();

}
