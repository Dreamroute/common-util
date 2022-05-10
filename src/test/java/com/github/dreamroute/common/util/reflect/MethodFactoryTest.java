package com.github.dreamroute.common.util.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class MethodFactoryTest {

    @Test
    public void findForClassTest() {
        Map<String, Method> map = MethodFactory.findForClass(Demo.class);
        System.err.println(map);
    }

}
