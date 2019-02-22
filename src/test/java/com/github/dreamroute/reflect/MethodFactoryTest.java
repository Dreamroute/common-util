package com.github.dreamroute.reflect;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Test;

public class MethodFactoryTest {

    @Test
    public void findForClassTest() {
        Map<String, Method> map = MethodFactory.findForClass(Demo.class);
        System.err.println(map);
    }

}
