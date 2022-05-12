package com.github.dreamroute.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.github.dreamroute.common.util.CollectionUtil.nonNull;

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


    }

}
