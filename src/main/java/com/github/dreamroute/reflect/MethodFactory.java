package com.github.dreamroute.reflect;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MethodFactory {

    private MethodFactory() {}

    private static final ConcurrentMap<Class<?>, Map<String, Method>> methodMap = new ConcurrentHashMap<>();

    public static Map<String, Method> findForClass(Class<?> cls) {
        return methodMap.computeIfAbsent(cls, ClassUtil::getClassMethods);
    }

}
