package com.github.dreamroute.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.github.dreamroute.common.util.CollectionUtil.newArrayList;
import static com.github.dreamroute.common.util.CollectionUtil.nonNull;
import static com.github.dreamroute.common.util.CollectionUtil.nonNullStream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

/**
 * @author w.dehi.2022-05-10
 */
class CollectionUtilTest {

    @Test
    void nonNullTest() {
        List<Integer> ls = null;
        List<Integer> listResult = nonNull(ls);
        Assertions.assertNotNull(listResult);

        Set<Integer> set = null;
        Set<Integer> setResult = nonNull(set);
        Assertions.assertNotNull(setResult);
    }

    @Test
    void collectTest() {
        List<String> names = newArrayList(5);
        nonNull(names).forEach(e -> {
            System.err.println(e);
        });
        List<String> result = nonNullStream(names).map(identity()).collect(toList());
        System.err.println(result);

    }

}
