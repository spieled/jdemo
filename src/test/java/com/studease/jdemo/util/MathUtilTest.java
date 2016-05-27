package com.studease.jdemo.util;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Author: liushaoping
 * Date: 2016/5/9.
 */
public class MathUtilTest {

    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals(3, MathUtil.add(1,2));
    }
}